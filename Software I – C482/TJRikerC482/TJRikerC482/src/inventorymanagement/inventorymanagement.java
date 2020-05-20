package inventorymanagement;

import inventorymanagement.model.Inventory;
import inventorymanagement.view_controller.MainController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class inventorymanagement extends Application {
  private Stage primaryStage;
  private BorderPane mainLayout;
  private final Inventory inventory = new Inventory();


  public Inventory getInventory(){
    return inventory;
  }
    
  @Override
  public void start(Stage primaryStage) throws IOException {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("Inventory Management System");
    showMain();
  }
    
  public void showMain() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(inventorymanagement.class.getResource("view_controller/Main.fxml"));
    mainLayout = (BorderPane) loader.load();

    MainController mainController = loader.getController();
    mainController.setApp(this);
    mainController.setPrimaryStage(primaryStage);

    Scene scene = new Scene(mainLayout);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
   
  public Stage getPrimaryStage() {
    return primaryStage;
  }
  
  public void close(){
    primaryStage.close();
  }

  public static void main(String[] args) {
    launch(args);
  }
    
}
