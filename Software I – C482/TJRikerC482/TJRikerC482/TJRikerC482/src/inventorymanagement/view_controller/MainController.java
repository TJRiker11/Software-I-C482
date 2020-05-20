package inventorymanagement.view_controller;

import inventorymanagement.inventorymanagement;
import inventorymanagement.model.AlertDialog;
import inventorymanagement.model.Part;
import inventorymanagement.model.Product;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
  

  private inventorymanagement app;
  private Stage primaryStage;

  @FXML
  private TextField partsTextField;
  @FXML
  private TextField productsTextField;

  @FXML
  private TableView<Part> partsTableView;
  @FXML
  private TableColumn<Part, Integer> partIDColumn;
  @FXML
  private TableColumn<Part, String> partNameColumn;
  @FXML
  private TableColumn<Part, Integer> partInventoryColumn;
  @FXML
  private TableColumn<Part, Double> partPriceColumn;

  @FXML
  private TableView<Product> productsTableView;
  @FXML
  private TableColumn<Product, Integer> productIDColumn;
  @FXML
  private TableColumn<Product, String> productNameColumn;
  @FXML
  private TableColumn<Product, Integer> productInventoryColumn;
  @FXML
  private TableColumn<Product, Double> productPriceColumn;

  @FXML
   void handleAddPart() throws IOException {
    Part part = PartController.showDialog(primaryStage, null, "Add Part", false);
    if(part != null){
      app.getInventory().getParts().add(part);
    }
  }
  
  @FXML
  void handleAddProduct() throws IOException {
    Product product = ProductController.showDialog(app, primaryStage, "Add Product", null);
    if(product != null){
      app.getInventory().getProducts().add(product);
    }
  }

  @FXML
   void handleDeletePart() {
    int index = partsTableView.getSelectionModel().getSelectedIndex();
    if (index >= 0) {
      if(AlertDialog.deleteDialog()){
        partsTableView.getItems().remove(index);
      }
    } else {
      AlertDialog.noSelectionDialog("part");
    }
  }
  
  @FXML
   void handleDeleteProduct() {
    int index = productsTableView.getSelectionModel().getSelectedIndex();
    if (index >= 0) {
      Product product = productsTableView.getItems().get(index);
      if(product.getAssociatedParts().isEmpty()){
        if(AlertDialog.deleteDialog()){
          productsTableView.getItems().remove(index);
        }
      }else{
        AlertDialog.cantDeleteDialog();
      }
    } else {
      AlertDialog.noSelectionDialog("product");
    }
  }

  @FXML
   void handleExit() {
    app.close();
  }

  @FXML
   void handleModifyPart() throws IOException {
    Part part = partsTableView.getSelectionModel().getSelectedItem(); 
    if (part != null) {
      PartController.showDialog(primaryStage, part, "Modify Part", true);
    } else {
      AlertDialog.noSelectionDialog("part");
    }
  }
  
  @FXML
   void handleModifyProduct() throws IOException {
      
    Product product = productsTableView.getSelectionModel().getSelectedItem(); 
    if (product != null) {
      ProductController.showDialog(app, primaryStage, "Modify Product", product);
    } else {
      AlertDialog.noSelectionDialog("product");
    }
  }
  
  @FXML
   void handleSearchParts() {
    String search = partsTextField.getText();
    if("".equals(search)){
      partsTableView.setItems(app.getInventory().getParts());
    }else{
      partsTableView.setItems(app.getInventory().searchParts(search));
    }
  }

  @FXML
   void handleSearchProducts() {
    String search = productsTextField.getText();
    if("".equals(search)){
      productsTableView.setItems(app.getInventory().getProducts());
    }else{
      productsTableView.setItems(app.getInventory().searchProducts(search));
    }
  }
  
  @FXML
   void initialize() {
    partIDColumn.setCellValueFactory(cellData -> cellData.getValue().IDProperty().asObject());
    partNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    partInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().inventoryProperty().asObject());
    partPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
    
    productIDColumn.setCellValueFactory(cellData -> cellData.getValue().IDProperty().asObject());
    productNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    productInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().inventoryProperty().asObject());
    productPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
  }

  public void setApp(inventorymanagement app) {
    this.app = app;

    partsTableView.setItems(app.getInventory().getParts());
    productsTableView.setItems(app.getInventory().getProducts());
  }
  
  public void setPrimaryStage(Stage primaryStage){
    this.primaryStage = primaryStage;
  }

}
