package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Khubaib ch on 7/17/2017.
 */
public class Add {

    Stage window5;
    Label title,title1,batchNo1,particulars1,expDate1,qty1,mrp1,rate1;
    TextField batchNoField1,particularsField1,qtyField1,rateField1,mrpField1;
    ChoiceBox<Integer>dayChoiceBox,MonthChoiceBox,YearChoiceBox;
    TableView<AddStockTable> tableTableView;
    TableColumn batchNo,name,expDate,qty,rate,mrp;
    ObservableList<AddStockTable> observableList,selectedItem,allItem;
    Button save,back,add,delete;
    String date1;
    Scene scene5;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    AddQueryController addQueryController;
    Integer months1,years1,days;

    public Add() {
        window5=new Stage();
        window5.setTitle("Add Drug");

        BorderPane layout=new BorderPane();

        title =new Label("Data Entry");
        title.setStyle("-fx-font-size:120%");
        title1=new Label("Add Drug");
        title1.setStyle("-fx-font-size:250%;-fx-font-weight:bold");


        VBox vBox=new VBox(7);
        vBox.setPadding(new Insets(15,10,20,10));
        vBox.getChildren().addAll(title,title1);
        vBox.setAlignment(Pos.BASELINE_CENTER);

        layout.setTop(vBox);

        GridPane gridPane=new GridPane();
        gridPane.setPadding(new Insets(10,30,10,30));
        gridPane.setHgap(20);

        //1st row

        batchNo1=new Label("Batch No");
        GridPane.setConstraints(batchNo1,0,1);

        batchNoField1=new TextField();
        batchNoField1.setMaxWidth(100);
        GridPane.setConstraints(batchNoField1,1,1);

        particulars1=new Label("Particulars");
        GridPane.setConstraints(particulars1,2,1);

        particularsField1=new TextField();
        particularsField1.setMaxWidth(230);
        GridPane.setConstraints(particularsField1,3,1);

        expDate1=new Label("ExpDate");
        GridPane.setConstraints(expDate1,4,1);

        dayChoiceBox=new ChoiceBox<>();
        dayChoiceBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31);
        dayChoiceBox.setValue(1);
        GridPane.setConstraints(dayChoiceBox,5,1);


        MonthChoiceBox=new ChoiceBox<>();
        MonthChoiceBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        MonthChoiceBox.setValue(1);
        GridPane.setConstraints(MonthChoiceBox,6,1);

        YearChoiceBox=new ChoiceBox<>();
        YearChoiceBox.getItems().addAll(2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);
        YearChoiceBox.setValue(2017);
        GridPane.setConstraints(YearChoiceBox,7,1);

        qty1=new Label("Qty");
        GridPane.setConstraints(qty1,8,1);

        qtyField1=new TextField();
        GridPane.setConstraints(qtyField1,9,1);
        qtyField1.setMaxWidth(70);

        rate1=new Label("Rate");
        GridPane.setConstraints(rate1,10,1);

        rateField1= new TextField();
        GridPane.setConstraints(rateField1,11,1);
        rateField1.setMaxWidth(80);

        mrp1=new Label("MRP");
        GridPane.setConstraints(mrp1,12,1);

        mrpField1= new TextField();
        mrpField1.setMaxWidth(80);
        GridPane.setConstraints(mrpField1,13,1);

        add=new Button("Add");
        add.setMinWidth(80);
        GridPane.setConstraints(add,14,1);

        gridPane.getChildren().addAll(batchNo1,batchNoField1,particulars1,particularsField1,expDate1,dayChoiceBox,MonthChoiceBox,YearChoiceBox,
                qty1,qtyField1,rate1,rateField1,mrp1,mrpField1,add);

        batchNo=new TableColumn<>("Batch No");
        batchNo.setMinWidth(120);


        name=new TableColumn("Particulars");
        name.setMinWidth(200);

        expDate=new TableColumn("EXP Date");
        expDate.setMinWidth(190);

        qty=new TableColumn("Qty");
        qty.setMinWidth(120);


        rate=new TableColumn("Rate");
        rate.setMinWidth(140);


        mrp=new TableColumn("MRP");
        mrp.setMinWidth(120);

        tableTableView=new TableView<>();
        observableList= FXCollections.observableArrayList();

        tableTableView.getColumns().addAll(batchNo,name,expDate,qty,rate,mrp);

        HBox hBox1=new HBox();
        hBox1.setPadding(new Insets(10,0,10,200));
        hBox1.getChildren().addAll(tableTableView);

        VBox vBox1=new VBox(10);
        vBox1.getChildren().addAll(gridPane,hBox1);

        layout.setCenter(vBox1);

        save=new Button("Save");
        save.setMinWidth(120);

        back=new Button("Back");
        back.setMinWidth(120);

        delete=new Button("Delete");
        delete.setMinWidth(120);

        HBox hBox=new HBox(70);
        hBox.setPadding(new Insets(10,10,30,10));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(back,delete,save);
        layout.setBottom(hBox);

        scene5=new Scene(layout,1300,550);
        window5.setScene(scene5);
        window5.show();

        addQueryController=new AddQueryController();

        addDrug();

    }

    private void addDrug(){

        //set Table data
        batchNo.setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        name.setCellValueFactory(new PropertyValueFactory<>("particulars"));
        expDate.setCellValueFactory(new PropertyValueFactory<>("expdate"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        mrp.setCellValueFactory(new PropertyValueFactory<>("mrp"));

        //delete button action
        delete.setOnAction(e->{
            deleteButtonClicked();
        });

        //add button action
        add.setOnAction(e->{
            addButtonClick();
        });

        //save button action
        save.setOnAction(e->{
            ObservableList<AddStockTable> table=tableTableView.getItems();
            for(AddStockTable row:table){
                addQueryController.Create(row);
                addQueryController.execute();
            }
            JOptionPane.showMessageDialog(null,"Drugs Add Successfully");

        });

        //back button action
        back.setOnAction(e->{
            Home home=new Home();
            window5.close();
        });


    }

    // add button method
    private void addButtonClick(){
        //set Date

        days=dayChoiceBox.getValue();
        months1=MonthChoiceBox.getValue();
        years1=YearChoiceBox.getValue();
        date1=days+"-"+months1+"-"+years1;

        AddStockTable addStockTable=new AddStockTable();
        addStockTable.setBatchNo(batchNoField1.getText());
        addStockTable.setParticulars(particularsField1.getText());
        addStockTable.setExpdate(date1);
        addStockTable.setQty(qtyField1.getText());
        addStockTable.setRate(rateField1.getText());
        addStockTable.setMrp(mrpField1.getText());
        addStockTable.setMonth(months1);
        addStockTable.setYear(years1);
        tableTableView.getItems().add(addStockTable);

        batchNoField1.clear();
        particularsField1.clear();
        qtyField1.clear();
        rateField1.clear();
        mrpField1.clear();
    }

    // delete button method
    private void deleteButtonClicked(){
        allItem=tableTableView.getItems();
        selectedItem=tableTableView.getSelectionModel().getSelectedItems();
        selectedItem.forEach(allItem::remove);
    }
}
