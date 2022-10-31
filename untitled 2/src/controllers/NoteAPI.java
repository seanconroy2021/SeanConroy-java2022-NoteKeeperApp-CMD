package controllers;
import models.Item;
import models.Note;
import utils.CategoryUtility;
import utils.Utilities;

import java.util.ArrayList;
import java.util.Objects;



public class NoteAPI {

    private ArrayList<Note> notes;


    public boolean isValidIndex(int index)
    {
        return (index >= 0) && (index < notes.size());
    }

    /**
     * Add note object it is passed as a parameter to the arrayList.
     * @param note models.Note object to be added into the arrayList.
     * @return true if it was successful and false otherwise.
     */
    public boolean add (Note note)
    {
        return notes.add(note);
    }


    public boolean updateNote(int indexToUpdate, String noteTitle, int notePriority, String noteCategory)
    {
        Note foundNote = null;
        if (isValidIndex(indexToUpdate))
        {
            foundNote =notes.get(indexToUpdate);
        }

        if(foundNote != null)
        {
            foundNote.setNoteTitle(noteTitle);
            foundNote.setNotePriority(notePriority);
            foundNote.setNoteCategory(noteCategory);
            return true;
        }
        return false;
    }

    public Note deleteNote(int indexToDelete)
    {
        if(isValidIndex(indexToDelete))
        {
            return notes.remove(indexToDelete);
        }
        return null;
    }

    public boolean archiveNote(int indexToArchive) //not done
    {

        if (isValidIndex(indexToArchive)) {
            Note noteFound = findNote(indexToArchive);
            boolean isArchivedTest = noteFound.isNoteArchived();
            if (isArchivedTest == true) {
                return false; //If the note exists, but is already archived or items on
                // the note are still TODO, return false.
            } else if (isArchivedTest == false) {
                if (noteFound.checkNoteCompletionStatus() == true) {
                    return true;
                }

            }

        }

        return false;

    }

    public String archiveNotesWithAllItemsComplete()
    {
        String newlyArchived="";
        if(notes.isEmpty()) // same as writing == true
        {
            return"No active notes stored";
        }
        else
        {
         for(Note note:notes)
         {

             if(note.checkNoteCompletionStatus())// true
             {
                 note.setNoteArchived(true);
                 newlyArchived =newlyArchived + note.toString();
             }
         }
        }
        return newlyArchived;
    }


    //Counting methods
    public int numberOfNotes()
    {
        return notes.size();
    }

    public Note findNote(int index)
    {
        if(isValidIndex(index))
        {
            return notes.get(index);

        }
        return null;
    }

    public int numberOfArchivedNotes()
    {
        int numArchived = 0;
        for(Note note:notes)
        {
           if( note.isNoteArchived()) //true
           {
               numArchived = numArchived+1;
           }
        }

        return numArchived;
    }

    public int numberOfActiveNotes()
    {
        int numActive = 0;
        for(Note note:notes)
        {
            if( !note.isNoteArchived()) //false
            {
                numActive = numActive+1;
            }
        }
        return numActive;
    }



    public int numberOfNotesByCategory(String category) {
        int numNoteInCategory = 0;
        if (CategoryUtility.isValidCategory(category)) {

            for (Note note : notes) {
                if (note.getNoteCategory().equalsIgnoreCase(category) ) //true
                {
                   numNoteInCategory = numNoteInCategory+1;

                }
            }

        }
        return numNoteInCategory;
    }

    public int numberOfNotesByPriority(int priority)//notdone
    {
        int numNoteInPriority=0;
        for (Note note : notes) {
            if (note.getNotePriority() == priority) //false
            {
                numNoteInPriority = numNoteInPriority+1;

            }
        }

        return numNoteInPriority;
    }


    public int numberOfItems()
    {
        int numOfItems =0;
        for (Note note : notes) {
            numOfItems = numOfItems + note.numberOfItems();
        }
        return numOfItems;
    }
    public int numberOfCompleteItems()
    {
        int numOfItems =0;
        for (Note note : notes) {
            if(note.checkNoteCompletionStatus())//true
            {  numOfItems = numOfItems + note.numberOfItems();}

        }
        return numOfItems;
    }

    public int numberOfTodoItems()
    {
        int numOfItems =0;
        for (Note note : notes) {
            if(!note.checkNoteCompletionStatus())//false
            {  numOfItems = numOfItems + note.numberOfItems();}

        }
        return numOfItems;
    }

















}
