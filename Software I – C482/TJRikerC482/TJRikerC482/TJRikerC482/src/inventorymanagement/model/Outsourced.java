package inventorymanagement.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class Outsourced extends Part{
  private final StringProperty companyName;

  public Outsourced(String name, double price, int inventory, int min, int max, String companyName) {
    super(name, price, inventory, min, max);
    this.companyName = new SimpleStringProperty(companyName);
  }

  public void setCompanyName(String companyName) {
    this.companyName.set(companyName);
  }

  public String getCompanyName() {
    return this.companyName.get();
  }

  public StringProperty companyNameProperty() {
    return companyName;
  }

  @Override
  public String getPartType(){
    return "Outsourced";
  }
}
