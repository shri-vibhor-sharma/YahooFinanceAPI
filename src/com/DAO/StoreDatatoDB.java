package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import com.bean.*;
import com.utility.ConnectionManager;

public class StoreDatatoDB {
	Connection con=null;
	Connection con1=null;
	PreparedStatement pst= null;
	public int storeinDB(GetStocks gtstock){
		con = ConnectionManager.getConnection();
		try {
			pst = con.prepareStatement("insert into stocks values(?,?,?,?)");
			System.out.println(gtstock.getPrice());
			pst.setString(1, gtstock.getPrice());
			pst.setString(2, gtstock.getPeg());
			pst.setString(3, gtstock.getDividend());
			pst.setString(4, gtstock.getChange());
			pst.execute();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
		
	}
	ResultSet rs = null;
	public void getStockSymbol(GetStocks gtstock){
		con1 = ConnectionManager.getConnection();
		GetStocks gt = new GetStocks();	
		
		 
			YahooFinance fin = new YahooFinance();
			String p1=null,p2=null,p3=null,p4=null;
	
		
		try {
			pst = con1.prepareStatement("select symbol from COMPANYLIST where symbol is not null");
			rs=pst.executeQuery();
			while(rs.next() &&rs!=null){
				gtstock.setStockSymbol(rs.getString(1));
				Stock stock = YahooFinance.get(gtstock.getStockSymbol());
				 if(stock.getQuote().getDayHigh()!=null||stock.getQuote().getDayLow()!=null||stock.getName()!=null||stock.getCurrency()!=null){
					 p1=stock.getQuote().getDayHigh().toString();
					 p2=stock.getQuote().getDayLow().toString();
				 p3=stock.getName().toString();
				 p4=stock.getCurrency().toString();
				 }
				 
				 else{
					 rs.next();
				 }
				 if(stock!=null||p1!=null||p2!=null||p3!=null||p4!=null){
				 gt.setPrice(p1);
				 gt.setChange(p2);
				 gt.setPeg(p3);
				 gt.setDividend(p4);
				 storeinDB(gt);
			}
				 else{
					 rs.next();
				 }
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
