package ru.sapteh.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.models.CategoryProduct;
import ru.sapteh.models.Product;
import ru.sapteh.models.Warehouse;
import ru.sapteh.service.CategoryProductServ;
import ru.sapteh.service.ProductServ;
import ru.sapteh.controller.CreateController;
import ru.sapteh.service.WarehouseServ;

import java.io.IOException;

public class TableController {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    DAO<Product, Integer> dao = new ProductServ(factory);
    ObservableList<Product> observableList = FXCollections.observableArrayList();
    Stage stage = new Stage();
    private Product product;

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, Integer> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, String> costColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;

    @FXML
    private TableColumn<Product, String> warehouseTxt;

    @FXML
    private Button buttonCreate;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonDelete;

    @FXML
    void initialize(){
        observableList.addAll(dao.findByAll());
        tableView.setItems(observableList);
        idColumn.setCellValueFactory(a -> new SimpleObjectProperty<Integer>(a.getValue().getId()));
        nameColumn.setCellValueFactory(a -> new SimpleObjectProperty<>(a.getValue().getName()));
        costColumn.setCellValueFactory(a -> new SimpleObjectProperty<>(a.getValue().getCost()));
        categoryColumn.setCellValueFactory(a -> new SimpleObjectProperty<>(a.getValue().getCategoryProduct().getName()));
        warehouseTxt.setCellValueFactory(a -> new SimpleObjectProperty<>(a.getValue().getWarehouse().getAddress()));
        tableView.getSelectionModel().selectedItemProperty().addListener((obj, oldValue, newValue) ->{
            product = newValue;
        });
    }

    public void actionCreate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/buttons/create.fxml"));
        AnchorPane pane = loader.load();
        stage.setTitle("Добавьте данные");
        stage.setScene(new Scene(pane));
        CreateController createController = loader.getController();
        createController.setData(observableList);
        stage.show();
    }

    public void actionEdit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/buttons/edit.fxml"));
        AnchorPane pane = loader.load();
        stage.setTitle("Изменить данные");
        stage.setScene(new Scene(pane));
        EditController editController = loader.getController();
        editController.setData(product);
        editController.setDataList(observableList);
        stage.show();
    }

    public void actionDelete(ActionEvent event) {
        Product product = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(product);
        dao.delete(product);
    }
}
