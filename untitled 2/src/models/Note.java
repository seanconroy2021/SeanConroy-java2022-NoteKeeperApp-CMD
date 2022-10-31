package models;

import utils.CategoryUtility;
import utils.Utilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;


public class Note {


private String noteTitle ="No Title";
private int notePriority = 1;
private  String noteCategory ="";
private boolean isNoteArchived= false;

private ArrayList<Item> items; //Item is the class


    //constructor
    public Note(String noteTitle, int notePriority, String noteCategory)
    {
            this.noteTitle =  Utilities.truncateString(noteTitle, 20);
            if (Utilities.validRange(notePriority,1,5)) { this.notePriority =notePriority;}
            if (CategoryUtility.isValidCategory(noteCategory) == true) {this.noteCategory = noteCategory;}
            this.isNoteArchived= isNoteArchived;
            items = new ArrayList<Item>(); // you will have a collection of items in note

    }



    // getters
    public String getNoteTitle() {
        return noteTitle;
    }

    public int getNotePriority() {
        return notePriority;
    }

    public String getNoteCategory() {
        return noteCategory;
    }

    public boolean isNoteArchived() {
        return isNoteArchived;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    //setters


    public void setNoteTitle(String noteTitle) {
        if ( Utilities.validateStringLength(noteTitle,20) == true ) {this.noteTitle=noteTitle;}

    }

    public void setNotePriority(int notePriority) {
      if (Utilities.validRange(notePriority,1,5)) {this.notePriority =notePriority;}

    }

    public void setNoteCategory(String noteCategory) {if (CategoryUtility.isValidCategory(noteCategory) == true) {this.noteCategory = noteCategory;}}

    public void setNoteArchived(boolean noteArchived) {
        isNoteArchived = noteArchived;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }


    // methods

    public boolean isValidIndex ( int index )
    {
        return (index >= 0) && (index < items.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return notePriority == note.notePriority
                && isNoteArchived
                == note.isNoteArchived
                && Objects.equals(noteTitle, note.noteTitle)
                && Objects.equals(noteCategory, note.noteCategory)
                && Objects.equals(items, note.items);
    }


    /** addItem object, passed as a parameter, to the arraylist.
     *
     * @param item model.Item object to be xadded to the arraylist.
     * @return a boolean indicating success / failure of the add.
     */
    public boolean addItem (Item item)
    {
        return items.add(item);
    }

    /**
     * This method builds & returns a string that contains all items in the arrayList.
     * For each item store in the array lists the listItems will show the index that is associated.
     * If there is no items in the arrayList it will return "No items added".
     * @return A string which is built up to includes all the products in the arrayList or "No items added" is returned.
     */
    public String listItems()
    {
        if (items.isEmpty()== true)
        {
            return"No items added";
        }
        else
        {
            String listOfItems= "";

            for (int i =0; i<items.size();i++)
            {
                listOfItems = listOfItems + i +": "+ items.get(i)+"\n";
            }

            return listOfItems;
        }

    }


    public boolean updateItem (int index, String itemDescription, boolean itemCompleted)
    {

        Item foundItem = null;
        if (isValidIndex(index))
        {
            foundItem = items.get(index); // find the item object by the associated index number.
        }

        if(foundItem != null)
        {
            foundItem.setItemDescription(itemDescription);
            foundItem.setItemCompleted(itemCompleted);
            return true;
        }
       return false;
    }

    public Item deleteItem ( int index )
    {
        if(isValidIndex(index)== true)
        {
            return items.remove(index);
        }
        return null;
    }

    public Item findItem(int index)
    {
        if(isValidIndex(index))
        {
            return items.get(index);

        }
         return null;

    }



    public int  numberOfItems()
    {
        return items.size();
    }

    /**
     * This method look at the compleation status for each in the arrayList
     *
     * @return If their is no items in the arrayList it will return true,
     * It will use for each loop and once it find one item [todo] it return false.
     * If all items are completed it will send back true.
     *
     */

    public boolean checkNoteCompletionStatus() //notDone
    {
        if (items.size() == 0)
        {
            return true; // if there is noting in the arrayList of item it will return false
        }

        for (Item item : items) {
            boolean test  = item.isItemCompleted();
            if (test == false)
            {
                return false;
            }

        }

        return true;
    }




    @Override
    public String toString() { // notDone
        return "Note{" +
                "noteTitle='" + noteTitle + '\'' +
                ", notePriority=" + notePriority +
                ", noteCategory='" + noteCategory + '\'' +
                ", isNoteArchived=" + Utilities.booleanToYN(isNoteArchived) +
                ", items=" + items +
                '}';
    }
}
