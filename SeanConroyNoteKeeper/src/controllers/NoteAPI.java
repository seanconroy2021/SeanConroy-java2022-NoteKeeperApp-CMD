package controllers;
import models.Item;
import models.Note;
import utils.CategoryUtility;
import utils.Utilities;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class NoteAPI {

    private ArrayList<Note> notes;

    public NoteAPI()
    {
        notes = new ArrayList<Note>();
    }


    /**
     * This method takes in a number and checks if it is a valid index in the products ArrayList.
     *
     * @param index A number representing a potential index in the ArrayList.
     * @return True of the index number passed is a valid index in the ArrayList, false otherwise.
     */

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

    /**
     * Update a models.Note in the ArrayList with the contents passed in the models.Note object parameter
     * @param indexToUpdate Index of the note in the arrayList.
     * @param noteTitle Title of note.
     * @param notePriority Priority of the note.
     * @param noteCategory Category of the note.
     * @return The status of the update if it worked true & false otherwise.
     */
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

    /**
     * Delete a note from the arrayList, if it exists, the index is passed as a parameter.
     * @param indexToDelete Index of the note object in the arrayList.
     * @return The deleted product object or null if no object is at the location.
     */
    public Note deleteNote(int indexToDelete)
    {
        if(isValidIndex(indexToDelete))
        {
            return notes.remove(indexToDelete);
        }
        return null;
    }

    /**
     * Archive a note in the arraylist of notes with index passed as a parameter.
     * @param indexToArchive  Index of the note object in the arrayList.
     * @return The status of the Archive, True if it was successful  or False if it was unsuccessful
     */
    public boolean archiveNote(int indexToArchive) //not done
    {

        if (isValidIndex(indexToArchive)) {

            Note noteFound = findNote(indexToArchive);

            if (!noteFound.isNoteArchived() && noteFound.checkNoteCompletionStatus())
            {
                noteFound.setNoteArchived(true); // the note is then archived
                return true;


            } else if (noteFound.isNoteArchived() && !noteFound.checkNoteCompletionStatus()) {

                return false;
                //If the note exists, but is already archived or items on
                // the note are still TODO, return false.
            }

        }


        return false; // if the note index is invalid it send false

    }

    /**
     * Archive all note in the arraylist if all items are completed.
     * @return A string full of all notes that are archived.
     */
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
                 newlyArchived += displayString(note);
             }
         }
        }
        return newlyArchived;
    }


    //Counting methods

    /**
     * It  will display the amount of notes in the system
     * @return It will return an int of the size of notes.
     */
    public int numberOfNotes()
    {
        return notes.size();
    }


    /**
     *It will get the number of archived notes in the system.
     * @return Return an int that has the amount of archived notes.
     */

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

    /**
     * It will get the number of activate notes in the system.
     * @return return an int has the amount of activate notes.
     */
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


    /**
     * A parameter of String is sent in of category in then check if valid category
     * if it is then calculate how many notes of that category there is and returns it.
     * @param category The category of which they are looking for.
     * @return It return a number of notes by that category if not valid category return 0.
     */
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

    /**
     * It find all notes by a priority and send back number of notes by that priority.
     * @param priority the priority parameter  the person is looking for
     * @return The number of priority that was found.
     */
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
    /**
     * This method returns the number of item objects stored in the ArrayList.
     *
     * @return An int value representing the number of items objects in the ArrayList.
     */

    public int numberOfItems()
    {
        int numOfItems =0;
        for (Note note : notes) {
            numOfItems = numOfItems + note.numberOfItems();
        }
        return numOfItems;
    }

    /**
     * This method returns the number of completed items objects in the array list.
     * @return An int value representing the number of completed items objects in the arraylist.
     */
    public int numberOfCompleteItems()
    {
        int numOfItems = 0;
        for (Note note : notes)
        {

            numOfItems= numOfItems +note.numberOfCompleteItems();
        }
        return numOfItems;
    }

    /**
     * This method  returns the number of to do  items objects in the array list.
     * @return An int value representing the number of to do items objects in the arraylist.
     */
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

    /**
     * This method build and returns a String containing all notes in the arraylist.
     * For each note stored in the arraylist , the index linked to the note is included.
     * If no notes notes in the arraylist ,  the string that returned is "no notes stored"
     *
     * @return A string containing all the notes in the arrayList ot "no notes stored"
     */
    public String listAllNotes() {
        if (notes.isEmpty() == true) {
            return "No Notes Stored";

        } else {
            String listOfNotes = "";

            for (int i = 0; i < notes.size(); i++) {
                Note note = notes.get(i);
                listOfNotes = listOfNotes + displayString(i);
            }

            return listOfNotes;
        }
    }


    // display String

    /**
     * This is method that help with user experience it takes in a index and then will display.
     * index of note ,note title, note priority , note archived , completion , and the items.
     * It will make a string and make it look pretty.
     * @param index this is index of the note
     * @return It make a string of note & items info e.g.index of note ,note title, note priority , note archived , completion , and the items.
     */
    public String displayString(int index)
    {
        Note note = notes.get(index);
        return index +": Note Title: " + note.getNoteTitle() +". Note Priority: "+ note.getNotePriority()+". Note Archived: "+ Utilities.booleanToYN(note.isNoteArchived()) + "\n"+ note.listItems()+ "\n";
    }

    /**
     * This is method that help with user experience it takes in a Note note  and then will display.
     * index of note ,note title, note priority , note archived , completion , and the items.
     * It will make a string and make it look pretty.
     *
     * @param note this is a type Note which is sent in.
     * @return It make a string of note & items info e.g.index of note ,note title, note priority , note archived , completion , and the items.
     */
    public String displayString(Note note)
    {
        int index =  notes.indexOf(note);
        return index +": Note Title: " + note.getNoteTitle() +". Note Priority: "+ note.getNotePriority()+". Note Archived: "+ Utilities.booleanToYN(note.isNoteArchived()) + "\n"+ note.listItems()+ "\n";
    }


    /**
     * This method build and return a String containing all the activate notes in the arrayList.
     * for each product note stored, the associated index is also included
     *
     * @return A string containing all the activate notes in the arrayList or "no active notes store"
     */


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

                    {listOfActNotes= listOfActNotes + displayString(note);}
                }

            }

            return listOfActNotes;

        }
    }

    /**
     * This method build and return a String containing all the archived notes in the arrayList.
     * for each product note stored, the associated index is also included
     *
     * @return A string containing all the archived notes in the arrayList or "no archived notes store"
     */


    public String listArchivedNotes()
    {
        if(numberOfArchivedNotes()==0)
        {
            return"\t \t no archived notes";
        }
        else {
            String listOfActNotes = "";

            for (int i = 0; i < notes.size(); i++)
            {   Note note = notes.get(i);
                if (note.isNoteArchived())
                {
                    //displayString() fail the test- why not used here
                return listOfActNotes = listOfActNotes + i + ": Note Title: " + note.getNoteTitle() + ". Note Priority: " + note.getNotePriority() + ". Note Archived: " + Utilities.booleanToYN(note.isNoteArchived()) + "\n"  + note.listItems() + "\n";
                }
            }



            return listOfActNotes;
        }
    }

    /**
     * This method build and return a String containing all the Notes By Selected Category in the arrayList.
     * A String category is sent in.
     * for each  note stored, the associated index is also included
     *
     * @param category this is String category looking for.
     * @return A string containing all the Notes By Selected Category in the arrayList or "no notes stored " or "No notes with " +category"
     */

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

                    { listOfNoteByCat= listOfNoteByCat +displayString(note);}
                }

            }
            return numberOfNotesByCategory(category)+ " notes with category "+category+"\n"+listOfNoteByCat;
        }

    }

    /**
     * This method build and return a String containing all the Notes By Selected priority in the arrayList.
     * A int priority is sent in.
     * for each  note stored, the associated index is also included
     *
     * @param priority this is int priority looking for.
     * @return A string containing all the Notes By Selected priority in the arrayList or "no notes stored " or "No notes with " + priority "
     */

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
                        listOfNoteByPriority= listOfNoteByPriority +displayString(i);
                    }
                }

            }
            return numberOfNotesByPriority(priority)+ " notes with priority "+priority+"\n"+listOfNoteByPriority;
        }

    }

    /**
     *This method build and return a String all items that are to do in the arraylist.
     * @return A string which contain all the todo items with note title and item.
     */

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
                    if (!note.findItem(i).isItemCompleted()) {
                        Item item = note.findItem(i);
                        toDoItemString = toDoItemString + note.getNoteTitle() +":\t" +item.getItemDescription()+ ". "+Utilities.StatusOfCompilation(item.isItemCompleted())+"\n";

                    }

                }
            }
            return toDoItemString;
        }
    }

    /**
     * This method build and return a String containing all the items by status (todo)/(complete)  and category.
     * A string category is sent in.
     *
     * @param category this is String by category looking for.
     * @return A string containing all the TODO items and COMPLETE ITEMS  along with number of completed and todo items.
     */

     public String listItemStatusByCategory(String category)
    {
        String toDoString="";
        String completeString ="";
        int numToDo =0;
        int numComp=0;
        if(numberOfNotes() ==0 )
        {
            return "No notes stored";
        }
        else
        {
            for (Note note : notes)
            {
                if(note.getNoteCategory().equalsIgnoreCase(category))
                {
                    for (int i = 0; i < note.numberOfItems(); i++) {
                        if (!note.findItem(i).isItemCompleted())
                        {
                            numToDo +=1;
                            toDoString += note.findItem(i).getItemDescription()+" (Note: "+note.getNoteTitle()+" )"+"\n";

                        } else if (note.findItem(i).isItemCompleted())
                        {
                            numComp+=1;
                            completeString += note.findItem(i).getItemDescription()+" (Note: "+note.getNoteTitle()+" )"+"\n";
                        }

                    }
                }
            }
        }
        return "Number Completed: "+numComp+"\n"+completeString+"\n"+"Number TODO: "+numToDo+"\n"+toDoString;
    }


    //Finding / Searching Methods

    /**
     * Find a note object at a specific index location
     * If the index location is not valid it will return null.
     * @param index Index of the Note object in the arrayList.
     * @return The Note object or null if no object is at the index location.
     */
    public Note findNote(int index)
    {
        if(isValidIndex(index))
        {
            return notes.get(index);

        }
        return null;
    }

    /**
     * It searches for a note by note title. A String searchString is sent in as a parameter.
     *
     * @param searchString A String search string is sent in as parameter & what looking for in
     * the note titles of the arrayList
     * @return The notes found with that note title are built up and sent back as a
     * String or if notes are empty "No notes stored" and if cant find any note tile matching "No notes found for:  searchString"
     */

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

               if(findNote(i).getNoteTitle().toLowerCase().contains(searchString.toLowerCase()))
               {
                   foundNote = foundNote + displayString(i);
               }
           }
       }

       if (foundNote.equals("")) {
           return "No notes found for" + ": " + searchString;
       }
       return foundNote;
    }

    /**
     * The method search for item based on item Description.
     * A string searchString is sent in as a parameter and is used to find the item Description.
     * @param searchString Type String it used to find an item based on item description.
     * @return A string is built and sent back with matching item description to the String sent in.
     * If cant find any items matching the description "No items found for searchString" is sent back.
     */

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
                            int indexNote =  notes.indexOf(note);
                            itemMatching = itemMatching +indexNote + ": "+ note.getNoteTitle() +" "+ i +": "+ item.getItemDescription()+". "+ Utilities.StatusOfCompilation(item.isItemCompleted())+"\n" ;
                        }

                }

            }

            if(itemMatching.equals(""))
            {
                return "No items found for: "+searchString;
            }
        }
        return itemMatching ;
    }


    /**
     * The load method uses the XStream component to read all the models.MessagePost objects from the posts.xml
     * file stored on the hard disk.  The read objects are loaded into the posts ArrayList
     *
     * @throws Exception  An exception is thrown if an error occurred during the load e.g. a missing file.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[] { Note.class , Item.class };

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("NoteKeeperSC.xml"));
        notes = (ArrayList<Note>) is.readObject();
        is.close();
    }



    /**
     * The save method uses the XStream component to write all the objects in the posts ArrayList
     * to the posts.xml file stored on the hard disk.
     *
     * @throws Exception  An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("NoteKeeperSC.xml"));
        out.writeObject(notes);
        out.close();
    }
















}
