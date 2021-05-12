package ru.sapteh.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Category_product;
import ru.sapteh.model.Product;
import ru.sapteh.model.Warehouse;
import ru.sapteh.service.Category_productServ;
import ru.sapteh.service.ProductServ;
import ru.sapteh.service.WarehouseServ;

public class EditController {
    private SessionFactory factory;
    private DAO<Product, Integer> productIntegerDAO;
    private DAO<Category_product, Integer> category_productIntegerDAO;
    private DAO<Warehouse, Integer> warehouseIntegerDAO;
    private ObservableList<Product> products;
    private ObservableList<Category_product> category_products;
    private ObservableList<Warehouse> warehouses;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField costTxt;

    @FXML
    private ComboBox<?> categoryBox;

    @FXML
    private ComboBox<?> warehouseBox;

    @FXML
    private Button buttonOk;

    @FXML
    void initialize(){

    }

    @FXML
    void actionOk(ActionEvent event) {
    }
    public void initData(){
        factory = new Configuration().configure().buildSessionFactory();
        productIntegerDAO = new ProductServ(factory);
        category_productIntegerDAO = new Category_productServ(factory);
        warehouseIntegerDAO = new WarehouseServ(factory);
    }
}
