package inventorymanagement.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Part {
  private static int ID = 0;
  private final IntegerProperty partID;
  private final StringProperty name;
  private final DoubleProperty price;
  private final IntegerProperty inventory;
  private final IntegerProperty min;
  private final IntegerProperty max;

  public Part(String name, double price, int inventory, int min, int max) {
      this.partID = new SimpleIntegerProperty(generateID());
      this.name = new SimpleStringProperty(name);
      this.price = new SimpleDoubleProperty(price);
      this.inventory = new SimpleIntegerProperty(inventory);
      this.min = new SimpleIntegerProperty(min);
      this.max = new SimpleIntegerProperty(max);
  }
    
    
  public int getID(){
    return this.partID.get();
  }

  public IntegerProperty IDProperty() {
    return partID;
  }

  public void setName(String name){
    this.name.set(name);
  }

  public String getName(){
    return this.name.get();
  }
    
  public StringProperty nameProperty() {
    return name;
  }

  public void setPrice(double price){
    this.price.set(price);
  }

  public double getPrice(){
    return this.price.get();
  }

  public DoubleProperty priceProperty() {
    return price;
  }

  public void setInventory(int inventory){
    this.inventory.set(inventory);
  }

  public int getInventory(){
    return this.inventory.get();
  }
    
  public IntegerProperty inventoryProperty() {
    return inventory;
  }

  public void setMin(int min){
    this.min.set(min);
  }

  public int getMin(){
    return this.min.get();
  }

  public void setMax(int max){
    this.max.set(max);
  }

  public int getMax(){
    return this.max.get();
  }

  public String getPartType(){
    return "";
  }

  private int generateID(){
    return ID++;
  }
}
