package com.company;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Khubaib CH on 8/6/2017.
 */
public class AddQueryController {
    Connection connection;
    PreparedStatement preparedStatement;
    //method to store table data in database
    public void Create(AddStockTable row) {
        connection = DataBaseConnection.ConnectDB();
        String sql="Insert into AddDrugs(BatchNo,Particulars,ExpDate,Qty,Rate,MRP,Month,Year)Values(?,?,?,?,?,?,?,?)";
        try {

            //store first row data into database

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, row.getBatchNo());
            preparedStatement.setString(2,row.getParticulars());
            preparedStatement.setString(3,row.getExpdate());
            preparedStatement.setString(4,row.getQty());
            preparedStatement.setString(5,row.getRate());
            preparedStatement.setString(6,row.getMrp());
            preparedStatement.setString(7, String.valueOf(row.getMonth()));
            preparedStatement.setString(8, String.valueOf(row.getYear()));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


        public void execute()  {
            try {
                preparedStatement.execute();
            }catch (Exception e){

            }finally {
                try {
                    preparedStatement.close();
                }catch (Exception e){
                }
            }

        }

    }
