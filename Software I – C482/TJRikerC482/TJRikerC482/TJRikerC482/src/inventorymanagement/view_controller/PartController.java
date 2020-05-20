package inventorymanagement.view_controller;

import inventorymanagement.inventorymanagement;
import inventorymanagement.model.AlertDialog;
import inventorymanagement.model.Inhouse;
import inventorymanagement.model.Outsourced;
import inventorymanagement.model.Part;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PartController {
  
  private Stage partStage;
  private Part part;
    
  @FXML
  private Label partLabel;
  @FXML
  private Label partTextLabel;
  
  @FXML
  private TextField inventoryTextField;
  @FXML
  private TextField IDTextField;
  @FXML
  private TextField maxTextField;
  @FXML
  private TextField minTextField;
  @FXML
  private TextField nameTextField;
  @FXML
  private TextField partTextField;
  @FXML
  private TextField priceTextField;

  @FXML
  private RadioButton inHouseRadioButton;
  @FXML
  private RadioButton OutsourcedRadioButton;
  
  @FXML
  private ToggleGroup group;
  
  private String checkNumbers() {
    String errorMessage = "";
    int inventory = Integer.parseInt(inventoryTextField.getText());
    int max = Integer.parseInt(maxTextField.getText());
    int min = Integer.parseInt(minTextField.getText());
    if(max <= min){
      errorMessage += "No valid max (must be greater than min)!\n"; 
    }
    if(min >= max){
      errorMessage += "No valid min (must be less than max)!\n";
    }
    if(inventory < min || inventory > max){
      errorMessage += "No valid inventory (must be between min and max)!\n"; 
    }
    return errorMessage;
  }
  
  @FXML
   void handleCancel() {
    if(AlertDialog.cancelDialog()){
      partStage.close();
    }
  }

  @FXML
   void handleInHouse() {
    partTextLabel.setText("Machine ID");
    if(this.part != null){
      partTextField.setText(Integer.toString(((Inhouse)part).getMachineID()));
    }else{
      partTextField.setText("");
    }
  }

  @FXML
   void handleOutsourced() {
    partTextLabel.setText("Company Name");
    if(this.part != null){
      partTextField.setText(((Outsourced)this.part).getCompanyName());
    }else{
      partTextField.setText("");
    }
  }

  @FXML
   void handleSave(){
     if (isInputValid()) {
      if(this.part == null){
        if("Outsourced".equals(getPartType())){
          this.part = new Outsourced(nameTextField.getText(), Double.parseDouble(priceTextField.getText()), Integer.parseInt(inventoryTextField.getText()), Integer.parseInt(minTextField.getText()), Integer.parseInt(maxTextField.getText()), partTextField.getText());
        }else {
          this.part = new Inhouse(nameTextField.getText(), Double.parseDouble(priceTextField.getText()), Integer.parseInt(inventoryTextField.getText()), Integer.parseInt(minTextField.getText()), Integer.parseInt(maxTextField.getText()), Integer.parseInt(partTextField.getText()));
        }
      }else {
        this.part.setName(nameTextField.getText());
        this.part.setPrice(Double.parseDouble(priceTextField.getText()));
        this.part.setInventory(Integer.parseInt(inventoryTextField.getText()));
        this.part.setMin(Integer.parseInt(minTextField.getText()));
        this.part.setMax(Integer.parseInt(maxTextField.getText()));
        if("Outsourced".equals(getPartType())){
          ((Outsourced)this.part).setCompanyName(partTextField.getText());
        }else {
          ((Inhouse)this.part).setMachineID(Integer.parseInt(partTextField.getText()));
        }
      }
      partStage.close();
     }
  }
    
  private boolean isInputValid() {
    String errorMessage = "";

    if (nameTextField.getText() == null || nameTextField.getText().length() == 0) {
      errorMessage += "Invalid Name\n"; 
    }
    
    if (priceTextField.getText() == null || priceTextField.getText().length() == 0) {
      errorMessage += "Invalid Price\n"; 
    }
    
    if (inventoryTextField.getText() == null || inventoryTextField.getText().length() == 0) {
      errorMessage += "Invalid Inventory\n"; 
    }
    
    if (minTextField.getText() == null || minTextField.getText().length() == 0) {
      errorMessage += "Invalid Min\n"; 
    }
    
    if (maxTextField.getText() == null || maxTextField.getText().length() == 0) {
      errorMessage += "Invalid Max\n"; 
    }
    
    if("Outsourced".equals(getPartType())){
      if (partTextField.getText() == null || partTextField.getText().length() == 0) {
        errorMessage += "Invalid Company Name\n"; 
      }
    }else {
      if (partTextField.getText() == null || partTextField.getText().length() == 0) {
        errorMessage += "Invalid Machine ID\n"; 
      }
    }
    
    if(errorMessage.length() == 0){
      errorMessage += checkNumbers();
    }
    
    if (errorMessage.length() == 0) {
      return true;
    } else {
      AlertDialog.errorDialog(errorMessage);
      return false;
    }
  }
  
  @FXML
  public void disableRadio(){
    inHouseRadioButton.setDisable(true);
    OutsourcedRadioButton.setDisable(true);
  }
  
  public Part getPart(){
    return part;
  }
  
  public String getPartType() {
    RadioButton selected = (RadioButton)group.getSelectedToggle();
    return selected.getText();
  }
  
  @FXML
  public void initialize() {
    inHouseRadioButton.setSelected(true);
    inHouseRadioButton.setToggleGroup(group);
    OutsourcedRadioButton.setToggleGroup(group);
    partTextLabel.setText("Machine ID");
    IDTextField.setDisable(true);
  }
  
  public void setPart(Part part) {
    nameTextField.setText(part.getName());
    inventoryTextField.setText(Integer.toString(part.getInventory()));
    priceTextField.setText(Double.toString(part.getPrice()));
    maxTextField.setText(Integer.toString(part.getMax()));
    minTextField.setText(Integer.toString(part.getMin()));
    IDTextField.setText(Integer.toString(part.getID()));

    if("Outsourced".equals(part.getPartType())){
      partTextField.setText(((Outsourced)part).getCompanyName());
      inHouseRadioButton.setSelected(false);
      OutsourcedRadioButton.setSelected(true);
      partTextLabel.setText("Company Name");
    }else{
      partTextField.setText(Integer.toString(((Inhouse)part).getMachineID()));
      inHouseRadioButton.setSelected(true);
      OutsourcedRadioButton.setSelected(false);
      partTextLabel.setText("Machine ID");
    }
    this.part = part;
  }
  
  @FXML
  public void setPartLabel(String str){
    partLabel.setText(str);
  }

  public void setPartStage(Stage partStage) {
    this.partStage = partStage;
  }
  
  public static Part showDialog(Stage primaryStage, Part part, String title, boolean disableRadio) throws IOException{
    
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(inventorymanagement.class.getResource("view_controller/Part.fxml"));
      AnchorPane page = (AnchorPane) loader.load();

      Stage partStage = new Stage();
      partStage.setTitle(title);
      partStage.initModality(Modality.APPLICATION_MODAL);
      partStage.initOwner(primaryStage);
      Scene scene = new Scene(page);
      partStage.setScene(scene);

      PartController partController = loader.getController();
      partController.setPartLabel(title);
      if(disableRadio){
        partController.disableRadio();
      }
      partController.setPartStage(partStage);
      if(part != null){
        partController.setPart(part);
      }

      partStage.showAndWait();

      return partController.getPart();
  }

}
