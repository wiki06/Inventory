package com.rafsan.inventory.controller.pos;

import com.rafsan.inventory.entity.Item;
import com.rafsan.inventory.entity.Supplier;
import com.rafsan.inventory.pdf.PrintInvoice;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class ConfirmController implements Initializable {

    @FXML
    private TextArea billingArea;
    @FXML
    private Label retailLabel;
    private double retail;
    private ObservableList<Item> items;
    private String barcode;
    private String invoiceId;
    private Supplier supplier;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        retailLabel.setText("Total Amount: Rs." + retail);

        StringBuilder details = new StringBuilder("Item Name\t\t\t" + "Cost\t\t\t" + "Quantity\t\t\t" + "Total\n");

        for (Item i : items) {
            details.append(i.getItemName())
                    .append("\t\t\t")
                    .append(i.getUnitPrice())
                    .append("\t\t\t")
                    .append(i.getQuantity())
                    .append("\t\t\t")
                    .append(i.getTotal())
                    .append("\n");
        }
        

        billingArea.setText(details.toString());
    }

    public void setData(double retail, ObservableList<Item> items, String barcode, Supplier supplier) {
        this.retail = retail;
        this.items = FXCollections.observableArrayList(items);
        this.barcode = barcode;
        this.invoiceId = barcode;
        this.supplier = supplier;
    }

    @FXML
    public void doneAction(ActionEvent event) {
        billingArea.setText("");
        System.out.println("Icalled success item --->"+items.get(0).getHsncode());
        PrintInvoice pi = new PrintInvoice(items, barcode, invoiceId, supplier);
        pi.generateReport();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
    
    @FXML
    public void closeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
