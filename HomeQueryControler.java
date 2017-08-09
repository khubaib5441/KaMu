package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Khubaib CH on 8/5/2017.
 */
public class HomeQueryControler {
//    private List<String> queryList = new ArrayList<String>();
    Connection connection;
    PreparedStatement preparedStatement,preparedStatement1;
    ResultSet resultSet;


    public void Create(HomeTable row,int totalAmount,String billDate,int invoice)  {
        connection=DataBaseConnection.ConnectDB();
//        queryList.add("");

        String sql="insert into Bill(Name,Quantity,Batch,ExpDate,MRP,Rate,Amount,Total,BillDate,Invoice)Values(?,?,?,?,?,?,?,?,?,?)";
        try {


            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,row.getParticulars());
            preparedStatement.setString(2, String.valueOf(row.getQty()));
            preparedStatement.setString(3,row.getBatchNo());
            preparedStatement.setString(4,row.getDate());
            preparedStatement.setString(5,row.getMrp());
            preparedStatement.setString(6,row.getRate());
            preparedStatement.setString(7, String.valueOf(row.getAmount()));
            preparedStatement.setString(8, String.valueOf(totalAmount));
            preparedStatement.setString(9,billDate);
            preparedStatement.setString(10, String.valueOf(invoice));

        }catch (Exception ex){
            ex.printStackTrace();
        }
  try {
      String sql1="insert into SaleReport(InvoiceNo,Amount,InvoiceDate)Values(?,?,?)";
      preparedStatement1=connection.prepareStatement(sql1);
      preparedStatement1.setString(1, String.valueOf(invoice));
      preparedStatement1.setString(2, String.valueOf(totalAmount));
      preparedStatement1.setString(3,billDate);
        }catch (Exception e1){
            e1.printStackTrace();
  }
    }

    public void execute()  {
        try {
            preparedStatement.execute();
            preparedStatement1.execute();

        }catch (Exception e){

        }finally {
            try {
               preparedStatement.close();
            }catch (Exception e){

            }
        }
    }
}
