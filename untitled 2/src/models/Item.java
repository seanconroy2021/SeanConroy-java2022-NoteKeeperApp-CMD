package models;

import utils.Utilities;

import java.util.Objects;

public class Item {

    String itemDescription = "No Description";
    boolean isItemCompleted = false;


// CONSTRUCTOR
    public Item(String itemDescription, boolean isItemCompleted )
    {
        this.itemDescription = Utilities.truncateString(itemDescription, 50);
        this.isItemCompleted = isItemCompleted;

    }


    public Item(String itemDescription)
    {
        this.itemDescription = Utilities.truncateString(itemDescription, 50);

    }



    //getters

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) { //check
         if (Utilities.validateStringLength(itemDescription, 50) == true)
         {
             this.itemDescription = Utilities.truncateString(itemDescription, 50);
         }

    }

    // setters

    public boolean isItemCompleted() {
        return isItemCompleted;
    }

    public void setItemCompleted(boolean itemCompleted) {
        isItemCompleted = itemCompleted;
    }

    // to string method

    @Override
    public String  toString() {
        return "Item{" +
                "itemDescription='" + itemDescription + '\'' +
                ", isItemCompleted=" + Utilities.StatusOfCompilation(isItemCompleted) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return isItemCompleted == item.isItemCompleted && Objects.equals(itemDescription, item.itemDescription);
    }


}
