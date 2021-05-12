package ru.sapteh.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.models.CategoryProduct;
import ru.sapteh.models.Product;
import ru.sapteh.models.Warehouse;
import ru.sapteh.service.CategoryProductServ;
import ru.sapteh.service.ProductServ;
import ru.sapteh.service.WarehouseServ;

public class CreateController {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    DAO<Product, Integer> dao = new ProductServ(factory);
    DAO<CategoryProduct, Integer> categoryProductIntegerDAO = new CategoryProductServ(factory);
    DAO<Warehouse, Integer> warehouseIntegerDAO = new WarehouseServ(factory);
    ObservableList<Product> observableList = FXCollections.observableArrayList();

    public void setData(ObservableList observableList){
        this.observableList = observableList;
    }

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

    @FXML
    void initialize(){

    }

    public void actionOk(ActionEvent event) {
        Product product = new Product();
        product.setName(nameTxt.getText());
        product.setCost(costTxt.getText());
        CategoryProduct categoryProduct = new CategoryProduct();
        categoryProduct.setName(categoryTxt.getText());
        Warehouse warehouse = new Warehouse();
        warehouse.setAddress(warehouseTxt.getText());
        dao.create(new Product(nameTxt.getText(),costTxt.getText()));
        categoryProductIntegerDAO.create(categoryProduct);
        warehouseIntegerDAO.create(warehouse);
        observableList.addAll(dao.findByAll());
    }
}
