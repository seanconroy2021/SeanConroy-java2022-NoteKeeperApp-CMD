package models;

import java.awt.*;
import java.util.ArrayList;

public class Note {


private String noteTitle ="No Title";
private int notePriority =1;
private  String noteCategory ="";
private boolean isNoteArchived= false;

private ArrayList<Item> items;//Item is the class


    //constructor
    public Note(String noteTitle, int notePriority, String noteCategory)
    {
            this.noteTitle = noteTitle;
            this.notePriority= notePriority;
            this.noteCategory = noteCategory;
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
        this.noteTitle = noteTitle;
    }

    public void setNotePriority(int notePriority) {
        this.notePriority = notePriority;
    }

    public void setNoteCategory(String noteCategory) {
        this.noteCategory = noteCategory;
    }

    public void setNoteArchived(boolean noteArchived) {
        isNoteArchived = noteArchived;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
