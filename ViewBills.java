package com.company;

import com.sun.javafx.font.freetype.HBGlyphLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Khubaib ch on 7/22/2017.
 */
public class ViewBills {
    Stage window12;
    Label titleLabel,invoiceNoLabel;
    TextField invoiceNoField1,invoiceNoField2,totalAmountField,billdate;
    Button search,back;
    TableView<ViewBillsTable> billTable;
    TableColumn sn,Particulars,qtyAvailable,BatchNo,expDate,Mrp,amount,date;
    ObservableList<ViewBillsTable> tableData;
    Scene scene12;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public ViewBills() {
        window12=new Stage();
        window12.setTitle("View Bills");

        titleLabel=new Label("View Previous Bills/Invoice");
        titleLabel.setStyle("-fx-font-size:250%;-fx-font-weight:bold");
        StackPane stackPane=new StackPane();
        stackPane.getChildren().addAll(titleLabel);

        invoiceNoField1=new TextField();
        invoiceNoField1.setEditable(false);

        invoiceNoLabel=new Label("Enter Invoice No");
        invoiceNoLabel.setStyle("-fx-font-size:120%;-fx-font-weight:bold");

        invoiceNoField2=new TextField();

        search=new Button("Search");
        search.setMinWidth(100);

        HBox hBox1=new HBox(20);
        hBox1.getChildren().addAll(invoiceNoLabel,invoiceNoField2,search);

        totalAmountField=new TextField();
        totalAmountField.setEditable(false);

        billdate=new TextField();
        billdate.setEditable(false);

        VBox vBox2=new VBox(5);
        vBox2.getChildren().addAll(totalAmountField,billdate,invoiceNoField1);


        HBox hBox2=new HBox(570);
        hBox2.setPadding(new Insets(10,10,10,10));
        hBox2.getChildren().addAll(hBox1,vBox2);

        VBox vBox=new VBox(20);
        vBox.setPadding(new Insets(30,10,10,40));
        vBox.getChildren().addAll(stackPane,hBox2);

        BorderPane layout=new BorderPane();
        layout.setTop(vBox);

        Particulars=new TableColumn("Particulars");
        Particulars.setMinWidth(230);

        qtyAvailable=new TableColumn("Qty");
        qtyAvailable.setMinWidth(140);

        BatchNo=new TableColumn("Batch");
        BatchNo.setMinWidth(170);

        expDate=new TableColumn("EXP");
        expDate.setMinWidth(210);

        Mrp=new TableColumn("MRP");
        Mrp.setMinWidth(150);

        amount=new TableColumn("Amount");
        amount.setMinWidth(170);

        billTable=new TableView<>();
        tableData= FXCollections.observableArrayList();
        billTable.getColumns().addAll(Particulars,qtyAvailable,BatchNo,expDate,Mrp,amount);

        VBox vBox1=new VBox();
        vBox1.setPadding(new Insets(20,20,10,40));
        vBox1.getChildren().addAll(billTable);
        layout.setCenter(vBox1);

        back=new Button("Back");
        back.setMinWidth(100);

        HBox hBox3=new HBox();
        hBox3.setPadding(new Insets(20,10,30,70));
        hBox3.getChildren().addAll(back);

        layout.setBottom(hBox3);

        scene12=new Scene(layout,1200,650);
        window12.setScene(scene12);
        window12.show();

        viewBills();
    }

    private void viewBills(){

        connection=DataBaseConnection.ConnectDB();

        search.setOnAction(e->{
            try {
                String invoiceno=invoiceNoField2.getText();
                String sql="select * from Bill where Invoice='"+invoiceno+"'";
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    invoiceNoField1.setText("Invoice No. "+resultSet.getString("Invoice"));
                    totalAmountField.setText("Total Amount "+resultSet.getString("Total"));
                    billdate.setText("Bill Date "+resultSet.getString("BillDate"));
                    tableData.add(new ViewBillsTable(
                            resultSet.getString("Name"),
                            resultSet.getString("Quantity"),
                            resultSet.getString("Batch"),
                            resultSet.getString("ExpDate"),
                            resultSet.getString("MRP"),
                            resultSet.getString("Amount")
                    ));
                    billTable.setItems(tableData);
                }
                }catch (Exception e6){

            }finally {
                try {

                }catch (Exception e7){

                }
            }
        });

        Particulars.setCellValueFactory(new PropertyValueFactory<>("Particulars"));
        qtyAvailable.setCellValueFactory(new PropertyValueFactory<>("qty"));
        BatchNo.setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        expDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        Mrp.setCellValueFactory(new PropertyValueFactory<>("mrp"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        back.setOnAction(e->{
            Home home=new Home();
            window12.close();
        });
    }
}
