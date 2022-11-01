package controllers;
import models.Item;
import models.Note;
import utils.CategoryUtility;

import java.util.ArrayList;


public class NoteAPI {

    private ArrayList<Note> notes;

    public NoteAPI()
    {
        notes = new ArrayList<Note>();
    }


    public boolean isValidIndex(int index)
    {
        return (index >= 0) && (index < notes.size());
    }

    /**
     * Add note object it is passed as a parameter to the arrayList.
     * @param note models.Note object to be added into the arrayList.
     * @return true if it was successful and false otherwise.
     */
    public boolean add (Note note) {return notes.add(note);}


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
            Note note = findNote(indexToDelete);


            for (int i = 0; i < note.getItems().size(); i++)
            {
                note.deleteItem(i);
            }


            return notes.remove(indexToDelete);

        }
        return null;
    }

    public boolean archiveNote(int indexToArchive) //not done
    {

        if (isValidIndex(indexToArchive)) {

            Note noteFound = findNote(indexToArchive);

            if (noteFound.isNoteArchived() == false && noteFound.checkNoteCompletionStatus() == true)
            {
                noteFound.setNoteArchived(true); // the note is then archived
                return true;


            } else if (noteFound.isNoteArchived() == true && noteFound.checkNoteCompletionStatus()==false) {

                return false;
                //If the note exists, but is already archived or items on
                // the note are still TODO, return false.
            }

        }


        return false; // if the note index is invalid it send false

    }

    public String archiveNotesWithAllItemsComplete()
    {
        String newlyArchived="";
        if(notes.isEmpty()|| numberOfActiveNotes()==0) // same as writing == true
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

    public int numberOfNotesByPriority(int priority)
    {
        int numNoteInPriority=0;
        for (Note note : notes) {
            if (note.getNotePriority() == priority) //false
            {
                numNoteInPriority += 1;

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
        int numOfItems = 0;
        for (Note note : notes)
        {

            numOfItems= numOfItems +note.numberOfCompleteItems();
        }
        return numOfItems;
    }

    public int numberOfTodoItems()
    {
        int numOfItems =0;
        for (Note note : notes)
        {

            numOfItems= numOfItems +note.numberOfTodoItems();
        }
        return numOfItems;
    }
// LISTING METHODS

    public String listAllNotes() {
        if (notes.isEmpty() == true) {
            return "No Notes Stored";

        } else {
            String listOfNotes = "";

            for (int i = 0; i < notes.size(); i++) {
                listOfNotes = listOfNotes + i + ": " + notes.get(i) + "\n";
            }

            return listOfNotes;
        }
    }


    public String listActiveNotes()
    {
        if(numberOfActiveNotes()==0)
        {
            return "No active notes stored";
        }

        else
        {
            String listOfActNotes="";
            for (Note note : notes)
            {
                if(!note.isNoteArchived())
                {

                    {listOfActNotes= listOfActNotes +  notes.indexOf(note) +": "+ note +"\n";}
                }

            }

            return listOfActNotes;

        }
    }

    public String listArchivedNotes()
    {
        if(notes.isEmpty())
        {
            return"no archived notes";
        }
        else
        {
            String listOfActNotes="";
            for (Note note : notes)
            {
                if(note.isNoteArchived())//true
                {
                    for (int i = 0; i < notes.size(); i++)
                    {listOfActNotes= listOfActNotes + notes.indexOf(note) +": "+ note+"\n";}
                }

            }

            return listOfActNotes;

        }
    }

    public String listNotesBySelectedCategory(String category)
    {
        if(numberOfNotes() ==0 )
        {
            return "No notes stored";
        } else if (numberOfNotesByCategory(category)==0)
        {
            return "No notes with "+category;
        }
        else
        {
            String listOfNoteByCat="";
            for (Note note : notes)
            {
                if(note.getNoteCategory().equalsIgnoreCase(category))//true
                {

                    {listOfNoteByCat= listOfNoteByCat  +notes.indexOf(note) +": "+ note+"\n";}
                }

            }
            return numberOfNotesByCategory(category)+ " notes with category "+category+"\n"+listOfNoteByCat;
        }

    }

    public String listNotesBySelectedPriority(int priority)
    {
        if(numberOfNotes() ==0 )
        {
            return "No notes stored";
        }
        else if (numberOfNotesByPriority(priority)==0)
        {
            return "No notes of that "+priority;
        }
        else
        {
            String listOfNoteByPriority="";
            for (int i = 0; i < notes.size(); i++)
            {

                if(findNote(i).getNotePriority()==priority)//true
                {

                    {
                        listOfNoteByPriority= listOfNoteByPriority + i +": "+ notes.get(i)+"\n";
                    }
                }

            }
            return numberOfNotesByPriority(priority)+ " notes with priority "+priority+"\n"+listOfNoteByPriority;
        }

    }

    public String listTodoItems()
    {
        if(numberOfNotes() ==0 )
        {
            return "No notes stored";
        }
        else {
            String toDoItemString = "";
            for (Note note : notes) {

                for (int i = 0; i < note.numberOfItems(); i++) {
                    if (note.findItem(i).isItemCompleted() == false) {
                        Item item = note.findItem(i);
                        toDoItemString = toDoItemString + note.getNoteTitle() + ": "+item;

                    }

                }
            }
            return toDoItemString;
        }
    }

     public String listItemStatusByCategory(String category)
    {
        return "notdone-test";
    }

    //Finding / Searching Methods

    public Note findNote(int index)
    {
        if(isValidIndex(index))
        {
            return notes.get(index);

        }
        return null;
    }
    public String searchNotesByTitle(String searchString)
    {
        String foundNote = "";
       if(notes.isEmpty())
       {
           return "No notes stored";
       }
       else
       {
           for (int i = 0; i < notes.size(); i++)
           {

               if( findNote(i).getNoteTitle().toLowerCase().contains(searchString.toLowerCase()) == true)
               {
                   foundNote = foundNote + i + findNote(i).getNoteTitle();
               }
           }
       }

       if (foundNote.equals("")) {
           return "No notes found for" + ": " + searchString;
       }
       return foundNote;
    }

    public String searchItemByDescription(String searchString)
    {
        String itemMatching ="";
        if(numberOfNotes() ==0 )
        {
            return "No notes stored";
        }
        else
        {

            for (Note note : notes)
            {

                for (int i = 0; i < note.numberOfItems(); i++)
                {
                        if( (note.findItem(i).getItemDescription().toLowerCase()).contains(searchString.toLowerCase()))
                        {
                            Item item = note.findItem(i);
                            itemMatching = itemMatching + note.getNoteTitle() + item ;
                        }

                }




            }
        }
        return itemMatching ;
    }



















}
