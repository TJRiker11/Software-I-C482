package inventorymanagement.model;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertDialog {
  public static void noSelectionDialog(String text){
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("No Selection");
    alert.setHeaderText("No " + text +" selected");
    alert.setContentText("Please select a " + text + " in the table.");
    alert.showAndWait();
  }
  
  public static boolean deleteDialog(){
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm Deletetion");
    alert.setHeaderText("Confirm Deletetion");
    alert.setContentText("Deletion is permanent and cannot be recovered");
    
    Optional<ButtonType> result = alert.showAndWait();
    return (result.get() == ButtonType.OK);
  }
  
  public static boolean cancelDialog(){
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm Cancellation");
    alert.setHeaderText("Confirm Cancellation");
    alert.setContentText("Any unsaved changes will be lost");

    Optional<ButtonType> result = alert.showAndWait();
    return (result.get() == ButtonType.OK);
  }
  
  public static void errorDialog(String errorMessage){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Invalid Fields");
    alert.setHeaderText("Please correct the invalid fields");
    alert.setContentText(errorMessage);
    alert.showAndWait();
  }
  
  public static void cantDeleteDialog(){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error Deleteing");
    alert.setHeaderText("Could not delete");
    alert.setContentText("Delete parts from this product before deleting the product");
    alert.showAndWait();
  }
}
