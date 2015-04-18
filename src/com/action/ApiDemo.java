package com.action;
import com.bean.GetStocks;
import com.DAO.*;

public class ApiDemo {

	
	public static void main(String[] args) {
		
      GetStocks gt = new GetStocks();
   //   FinanceAPI apiF = new FinanceAPI();
  //    GetStocks gt=apiF.getQuotes();
      StoreDatatoDB dao = new StoreDatatoDB();
      //System.out.println(gt.getChange());
      dao.getStockSymbol(gt);
      
	}

}
