package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.omg.CORBA.BAD_CONTEXT;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Khubaib ch on 7/21/2017.
 */
public class ViewStock {

    Stage window9;
    Label title, fewAlphabets, or;
    TextField name;
    Button searchName, viewAll, back, clear;
    TableView<ViewStockTable> tableView;
    TableColumn Particulars,qtyAvailable,BatchNo,Mrp,Rate,expDate;
    ObservableList<ViewStockTable> data;
    Scene scene9;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public ViewStock() {

        window9 = new Stage();
        window9.setTitle("View Stock");

        title = new Label("View Stock");
        title.setStyle("-fx-font-size:250%;-fx-font-weight:bold");

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(title);
        stackPane.setAlignment(Pos.BASELINE_CENTER);

        fewAlphabets = new Label("Type The First Few Alphabets ");
        fewAlphabets.setStyle("-fx-font-size:120%;-fx-font-weight:bold");

        name = new TextField();
        name.setMaxWidth(100);

        searchName = new Button("Search Name");
        searchName.setMinWidth(100);

        or = new Label("OR");
        or.setStyle("-fx-font-size:120%;-fx-font-weight:bold");

        viewAll = new Button("View All");
        viewAll.setMinWidth(100);

        HBox hBox = new HBox(40);
        hBox.getChildren().addAll(fewAlphabets, name, searchName, or, viewAll);

        VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(10, 10, 30, 30));
        vBox.getChildren().addAll(stackPane, hBox);

        BorderPane layout = new BorderPane();
        layout.setTop(vBox);

        Particulars = new TableColumn("Particulars");
        Particulars.setMinWidth(230);

        qtyAvailable = new TableColumn("Qty Available");
        qtyAvailable.setMinWidth(120);

        BatchNo = new TableColumn("Batch No");
        BatchNo.setMinWidth(140);

        Mrp = new TableColumn("MRP");
        Mrp.setMinWidth(120);

        Rate = new TableColumn<>("Rate");
        Rate.setMinWidth(120);

        expDate = new TableColumn("Expiry Date");
        expDate.setMinWidth(160);

        tableView = new TableView<>();
        tableView.setPadding(new Insets(0, 10, 10, 10));
        data = FXCollections.observableArrayList();
        tableView.getColumns().addAll(Particulars, qtyAvailable, BatchNo, Mrp, Rate, expDate);

        layout.setCenter(tableView);

        back = new Button("Back");
        back.setMinWidth(100);

        clear = new Button("Clear");
        clear.setMinWidth(100);

        HBox hBox1 = new HBox(300);
        hBox1.setPadding(new Insets(20, 10, 30, 20));
        hBox1.getChildren().addAll(back, clear);

        layout.setBottom(hBox1);

        scene9 = new Scene(layout, 900, 620);
        window9.setScene(scene9);
        window9.show();

        viewStock();

    }

    private void viewStock() {

        connection = DataBaseConnection.ConnectDB();

        searchName.setOnAction(e -> {
            try {
                String medicineName = name.getText();
                String sql = "select * from AddDrugs where Particulars like '" + medicineName + "%' order by Particulars";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    data.add(new ViewStockTable(
                            resultSet.getString("Particulars"),
                            resultSet.getString("Qty"),
                            resultSet.getString("BatchNo"),
                            resultSet.getString("MRP"),
                            resultSet.getString("Rate"),
                            resultSet.getString("ExpDate")


                    ));
                    tableView.setItems(data);
                }

            } catch (Exception e1) {

                JOptionPane.showMessageDialog(null, e1);
            } finally {
                try {
                    preparedStatement.close();
                    resultSet.close();
                } catch (Exception e2) {

                }
            }
        });

        viewAll.setOnAction(e -> {
            try {
                String sql = "select * from AddDrugs order by Particulars";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    data.add(new ViewStockTable(
                            resultSet.getString("Particulars"),
                            resultSet.getString("Qty"),
                            resultSet.getString("BatchNo"),
                            resultSet.getString("MRP"),
                            resultSet.getString("Rate"),
                            resultSet.getString("ExpDate")
                    ));
                    tableView.setItems(data);
                }

            } catch (Exception e1) {

                JOptionPane.showMessageDialog(null, e1);
            } finally {
                try {
                    preparedStatement.close();
                    resultSet.close();
                } catch (Exception e2) {

                }
            }
        });

        Particulars.setCellValueFactory(new PropertyValueFactory<>("Particulars"));
        qtyAvailable.setCellValueFactory(new PropertyValueFactory<>("qty"));
        BatchNo.setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        Mrp.setCellValueFactory(new PropertyValueFactory<>("mrp"));
        Rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        expDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        back.setOnAction(e -> {
            Home home=new Home();
            window9.close();

        });

        clear.setOnAction(e -> {
            data.clear();
        });
    }
}
