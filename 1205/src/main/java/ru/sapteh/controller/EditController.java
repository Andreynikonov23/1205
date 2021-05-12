package ru.sapteh.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.models.Product;
import ru.sapteh.service.ProductServ;

public class EditController {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    DAO<Product, Integer> dao = new ProductServ(factory);
    ObservableList<Product> observableList = FXCollections.observableArrayList();
    private Product product;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField costTxt;

    @FXML
    private TextField categoryTxt;

    @FXML
    private TextField warehouseTxt;

    @FXML
    private Button buttonOk;

    public void setDataList (ObservableList observableList){
        this.observableList = observableList;
    }

    public void setData(Product product){
        this.product = product;
        nameTxt.setText(product.getName());
        costTxt.setText(product.getCost());
    }


    public void actionOk(ActionEvent event) {
        product.setName(nameTxt.getText());
        product.setCost(costTxt.getText());
        dao.update(product);
    }
}
