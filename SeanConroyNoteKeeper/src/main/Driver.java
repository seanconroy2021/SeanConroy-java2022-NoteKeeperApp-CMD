package main;

import models.Item;
import models.Note;
import utils.CategoryUtility;
import utils.ScannerInput;
import utils.Utilities;
import controllers.NoteAPI;

public class Driver {

    private  NoteAPI noteAPI = new NoteAPI();

 public static void main(String [] args)
 {
     new Driver();
 }

 public Driver()
 {
     // use to make the text https://patorjk.com/software/taag/#p=display&f=Slant&t=note%20keeper%20app
     String titleApp = """
                             
                                           ⡏⠥⠤⠤⠬⠤⠤⠤⠬⠤⠤⠤⠥⠤⠤⠤⠥⠤⠤⠬⠤⠤⠤⠬⠤⠤⠤⠥⠤⠤
                                           ⡇⡇⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡾⠿⢶⣄⣀⣠⣤⣶⡀⠀⠀⠀⠀
                                           ⡇⡀⠀⠀⢰⡿⠻⠷⢶⣦⣤⣀⣀⣀⣀⣤⣿⣆⣀⣼⡟⠋⠉⠁⢹⣇⠀⠀⠀⠀
                                           ⡇⡇⠀⠀⣾⠇⣀⣠⣤⣶⠿⠿⠛⠛⠉⠉⣼⠟⠛⠉⠀⠀⠀⠀⠈⣿⡄⠀⠀⠀
                                           ⡇⡀⠀⢸⡿⠀⢿⡏⠁⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⢸⣧⠀⠀⠀
                                           ⡇⠇⠀⣿⠃⠀⠸⣷⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣤⣶⠾⠟⠃⠀⠀⣿⡄⠀⠀
                                           ⡇⡄⢸⡟⠀⠀⠀⢿⡆⠀⠀⣤⣴⡶⠿⠛⠛⠉⠉⣀⣠⣤⣴⡶⠀⠀⢸⣧⠀⠀
                                           ⡇⢇⣿⠃⠀⠀⠀⠸⣷⠀⠀⠀⣀⣤⣤⣶⠶⠿⠛⠛⠉⢁⣀⣠⣄⠀⠀⢿⡆⠀
                                           ⡇⣼⡏⠀⠀⠀⠀⠀⢻⡇⠀⠈⠋⠉⣀⣀⣤⣴⣶⠾⠟⠛⠋⠉⠁⠀⠀⠸⣷⠀
                                           ⣇⣿⠁⠀⠀⠀⠀⠀⠘⣿⠀⠀⠸⠛⠛⠉⠉⣀⣠⣄⠀⠀⠀⠀⠀⠀⠀⠀⢿⡆
                                           ⣿⡏⠀⠀⠀⠀⠀⠀⠀⢻⡇⠀⠀⢶⠶⠿⠛⠛⠉⠁⠀⠀⠀⠀⠀⢀⣀⣠⣼⣷
                                           ⡟⠿⠶⣶⣤⣤⣀⣀⠀⠘⣿⡀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣤⣶⠶⠿⠛⠋⠉⠁⠀
                                           ⡇⡆⠀⠀⠀⠉⠉⠛⠻⠷⢿⣇⣠⣤⣴⡶⠾⠟⢻⣿⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                           ⡇⠁⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠉⠉⠙⠛⠿⠶⣾⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                           ⡇⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                             __          __                                               \s
                ____  ____  / /____     / /_____  ___  ____  ___  _____   ____ _____  ____\s                
               / __ \\/ __ \\/ __/ _ \\   / //_/ _ \\/ _ \\/ __ \\/ _ \\/ ___/  / __ `/ __ \\/ __ \\
              / / / / /_/ / /_/  __/  / ,< /  __/  __/ /_/ /  __/ /     / /_/ / /_/ / /_/ /
             /_/ /_/\\____/\\__/\\___/  /_/|_|\\___/\\___/ .___/\\___/_/      \\__,_/ .___/ .___/\s
                                                   /_/                      /_/   /_/     \s
                                          BY: Sean Conroy | Version 1.0
             """;
     System.out.println(titleApp);
     runMenu();

 }

 private void runMenu()
 {
     int option = mainMenu();

     while(option != 0)
     {
         switch (option)
         {
             //NOTE MENU Options
             case 1 -> addNote();
             case 2 -> viewNotes();
             case 3 -> updateNote();
             case 4 -> deleteNote();
             case 5 -> archiveeNote();


             case 6 ->  addItemToNote();
             case 7 ->  updateItemDescInNote();
             case 8 ->  deleteItemFromNote();
             case 9 ->  markCompletionOfItem();

             case 10 -> printActiveAndArchivedReport();
             case 11 -> archiveNotesWithAllItemsComplete();
             case 12 -> printNotesBySelectedCategory();
             case 13 -> printNotesByPriority();
             case 14 -> searchNotesByTitle();

             case 15 -> printAllTodoItems();
             case 16 -> printOverallItemsTodoComplete();
             case 17 -> printItemCompletionStatusByCategory();
             case 18 -> searchItemsByDescription();

             case 20 ->savePosts();
             case 21 ->loadPosts();
             default -> System.out.println("Invalid option entered: " + option);
         }

         ScannerInput.readNextLine("\n Press enter key to continue....");

         option = mainMenu();
     }

     // when the user exits
     System.out.println("Exit Goodbye");
     System.exit(0);
 }

 private int mainMenu()
 {


     return ScannerInput.readNextInt("""
                ------------------------------------------------------------------
                |                         NOTE KEEPER APP                        |
                ------------------------------------------------------------------
                | NOTE MENU                                                      |
                |   1) Add a note                                                |
                |   2) List all notes (all, active, archive                      |
                |   3) Update a note                                             | 
                |   4) Delete a note                                             | 
                |   5) Archive a note                                            | 
                ------------------------------------------------------------------
                | ITEM MENU                                                      |
                |   6) Add an item to a note                                     |
                |   7) Update item description on a note                         |
                |   8) Delete an item from a note                                |
                |   9) Mark item as complete/todo                                |
                ------------------------------------------------------------------
                | REPORT MENU FRO NOTES                                          |
                |   10) All notes and their items (active & archived)            |  
                |   11) Archive notes whose items are all complete               |  
                |   12) All notes within a selected Category                     | 
                |   13) All notes within a selected Priority                     | 
                |   14) Search for all notes (by note title)                     |
                ------------------------------------------------------------------
                | REPORT MENU FRO NOTES                                          |
                |   15) All items that are todo (with note title)                |
                |   16) Overall number of items todo/complete                    |
                |   17) Todo/complete items by specific Category                 |
                |   18) Search for all items (by item description )              |
                ------------------------------------------------------------------
                | SETTINGS MENU                                                  |  
                |   20) Save                                                     |  
                |   21) Load                                                     |  
                |   0)  Exit                                                     |  
                ------------------------------------------------------------------
                ==>""");


 }


// NOTE MENU OPTIONS

    private void addNote()
    {
        String title = ScannerInput.readNextLine("Enter the Note Name: ");

        // if the user inputs a wrong priority it makes them input again
        int priority = ScannerInput.readNextInt("Enter the priority (1-5): " );
            while(!Utilities.validRange(priority, 1, 5))
            {
                priority = ScannerInput.readNextInt("Sorry "+priority+" Is not a valid number please try again (1-5)" );
            }

        // if the user inputs a wrong Category they will be told to enter it again
        String category = ScannerInput.readNextLine("Enter the Category "+ CategoryUtility.getCategories()+ " : ");
            while(!CategoryUtility.isValidCategory(category))
            {
                category = ScannerInput.readNextLine("Sorry "+ category+" is not a  Category (please try again) : ");
            }


        // check to see if note was added successfully.
       boolean isAdded = noteAPI.add(new Note(title, priority, category));
        if (isAdded)
        {
            System.out.println("Note Was Added Successfully");
        }
        else
        {
            System.out.println("\n No Note Added");
        }



    }


    //helper method
    private boolean noteInTheSystem()
    { // this checks to see if their is any notes in the system.
        if(noteAPI.numberOfNotes()==0)
        {
            System.out.println("No Notes In the System");
            return false;

        }
        else
        {return true;}
    }



    private void viewNotes() {
        //https://texteditor.com/ascii-frames/
        if (noteInTheSystem())
        {

            int option = ScannerInput.readNextInt("""
                    / \\--------------------------,\s
                    \\_,|         SELECT          |
                       |                          |
                       | 1) View All Notes        |\s
                       | 2) View ACTIVE Note      |\s
                       | 3) View Archived Note    |\s
                       |                          |\s
                       |  ,-----------------------
                       \\_/______________________/\s
                                   
                       ==>""");

            switch (option)
            {
                case 1 -> printAllNotes();
                case 2 -> printActivateNotes();
                case 3 -> printArchivedNotes();
                default -> System.out.println("Invalid option entered: " + option);
            }





        }
    }

    private void printAllNotes()
    {
        System.out.println(noteAPI.listAllNotes());
    }

    private void printActivateNotes()
    {
        System.out.println(noteAPI.listActiveNotes());
    }

    private void  printArchivedNotes()
    {
        System.out.println(noteAPI.listArchivedNotes());
    }


    private void updateNote()
    {
        if(noteInTheSystem())
        {

            printAllNotes();
            int indexToUpdate =ScannerInput.readNextInt("Enter the index of note to update ==> ") ;
            while(!noteAPI.isValidIndex(indexToUpdate))
            {
                indexToUpdate =ScannerInput.readNextInt("Enter the index of note is valid try again ==> ") ;
            }
            String noteTitle =ScannerInput.readNextLine("Enter the new Title: ");

            int priority = ScannerInput.readNextInt("Enter the new priority (1-5): " );
            while(!Utilities.validRange(priority, 1, 5))
            {
                priority = ScannerInput.readNextInt("Sorry "+priority+" Is not a valid number please try again (1-5)" );
            }


            String category = ScannerInput.readNextLine("Enter the new Category "+ CategoryUtility.getCategories()+ " : ");
            while(!CategoryUtility.isValidCategory(category))
            {
                category = ScannerInput.readNextLine("Sorry "+ category+" is not a  Category (please try again) : ");
            }

                boolean test =noteAPI.updateNote(indexToUpdate, noteTitle, priority, category) ;
                System.out.println(Utilities.wasSuccessfulOutput(test));

        }
    }

    private void deleteNote()
    {
        if(noteInTheSystem())
        {
            printAllNotes();
            int indexToDelete =ScannerInput.readNextInt("Enter the index of note to delete ==> ") ;
            while(!noteAPI.isValidIndex(indexToDelete))
            {
                indexToDelete =ScannerInput.readNextInt("Enter the index of note is valid try again ==> ") ;
            }

          Note note = noteAPI.deleteNote(indexToDelete);
            if(note == null)
            {
                System.out.println(Utilities.wasSuccessfulOutput(false));
            }
            else
            {
                System.out.println(Utilities.wasSuccessfulOutput(true));

            }


        }
    }


    private void archiveeNote()
    {
        if(noteInTheSystem())
        {
            printActivateNotes();

            int indexToArchive = ScannerInput.readNextInt("Enter the index of note to archive  ==> ");
            while (!noteAPI.isValidIndex(indexToArchive)) {
                indexToArchive = ScannerInput.readNextInt("Enter the index of note is valid try again ==> ");
            }
            boolean test = noteAPI.archiveNote(indexToArchive);
            System.out.println(Utilities.wasSuccessfulOutput(test));
        }
    }

    //ITEM MENU Options

    private void addItemToNote()
    {
        if(noteInTheSystem())
        {
            printActivateNotes();

            int index = ScannerInput.readNextInt("Enter the index of note to add item  ==> ");
            while (!noteAPI.isValidIndex(index)) {
                index = ScannerInput.readNextInt("Enter the index of note is valid try again ==> ");
            }
            String itemDesc = ScannerInput.readNextLine("Enter the Description for the item: ");
            Note note =noteAPI.findNote(index);

           boolean test = note.addItem(new Item(itemDesc));
           System.out.println(Utilities.wasSuccessfulOutput(test));


        }
    }

    private void updateItemDescInNote() //notdone
    {
        if(noteInTheSystem()) {
            printActivateNotes();
            int index = ScannerInput.readNextInt("Enter the index of note that wish change item of  ==> ");
            while (!noteAPI.isValidIndex(index)) {
                index = ScannerInput.readNextInt("Enter the index of note is valid try again ==> ");
            }

            int itemIndex = ScannerInput.readNextInt("Enter the index of item like to change  ==> ");
            String itemDesc = ScannerInput.readNextLine("Enter the new Description for the item: ");

            char status = ScannerInput.readNextChar("Enter T for Todo & C for complete: ");

            while(!Utilities.itemStatusConvertInputChecker(status))
            {
                status = ScannerInput.readNextChar("Enter T for Todo & C for complete (Try Again) : ");
            }


            Note note = noteAPI.findNote(index);
            boolean test = note.updateItem(itemIndex, itemDesc,Utilities.ItemStatusConvert(status));
            Utilities.wasSuccessfulOutput(test);

        }
    }

    private void deleteItemFromNote() {
        if(noteInTheSystem())
        {
            printActivateNotes();
            int index = ScannerInput.readNextInt("Enter the index of note of which like delete an item from  ==> ");
            while (!noteAPI.isValidIndex(index)) {
                index = ScannerInput.readNextInt("Enter the index of note of which like delete an item from try again ==> ");
            }

            Note note = noteAPI.findNote(index);

            int itemIndex = ScannerInput.readNextInt("Enter the index of item to delete  ==> ");

//        while (itemIndex <= note.getItems().size() && index>=0) { // need to be fixed
//            index = ScannerInput.readNextInt("Enter the index of item to delete  ==> ");
//        }

            Item item = note.deleteItem(itemIndex);
            if (item == null) {
                System.out.println(Utilities.wasSuccessfulOutput(false));
            } else {
                System.out.println(Utilities.wasSuccessfulOutput(true));

            }
        }
    }

       private void markCompletionOfItem()
       {
           if(noteInTheSystem())
           {
               printActivateNotes();
               int index = ScannerInput.readNextInt("Enter the index of note of which like update completion status of   ==> ");
               while (!noteAPI.isValidIndex(index)) {
                   index = ScannerInput.readNextInt("Enter the index of note of which like update completion status of  (try again) ==> ");
               }

               int itemIndex = ScannerInput.readNextInt("Enter the index of item like to change  ==> ");

               char status = ScannerInput.readNextChar("Enter T for Todo & C for complete: ");

               while(!Utilities.itemStatusConvertInputChecker(status))
               {
                   status = ScannerInput.readNextChar("Enter T for Todo & C for complete (Try Again) : ");
               }


               Note note = noteAPI.findNote(index);
               Item item = note.getItems().get(itemIndex);
               System.out.println(item.getItemDescription());
               boolean test = note.updateItem(itemIndex, item.getItemDescription() ,Utilities.ItemStatusConvert(status));
               Utilities.wasSuccessfulOutput(test);
               System.out.println(item.getItemDescription());



           }
       }

        //REPORT MENU FOR NOTES Options
        private void printActiveAndArchivedReport()
        {
            if(noteInTheSystem())
            {
                printActivateNotes();
                printArchivedNotes();
            }
        }

        private void archiveNotesWithAllItemsComplete()
        {
            if(noteInTheSystem())
            {
                noteAPI.archiveNotesWithAllItemsComplete();
            }

        }

        private void printNotesBySelectedCategory()
        {
            if(noteInTheSystem())
            {
                String category =ScannerInput.readNextLine("Enter a Category "+ CategoryUtility.getCategories()+ " to display notes ==> ");
                while(!CategoryUtility.isValidCategory(category))
                {
                    category = ScannerInput.readNextLine("Enter a Category "+ CategoryUtility.getCategories()+ " to display notes (try again) ==> ");
                }

                System.out.println(noteAPI.listNotesBySelectedCategory(category));
            }
        }

        private void printNotesByPriority()
        {
            if(noteInTheSystem())
            {
                int priority = ScannerInput.readNextInt("Enter the a priority (1-5) to list notes ==>");
                while(!Utilities.validRange(priority, 1, 5));
                {
                    priority = ScannerInput.readNextInt("Enter the a priority (1-5) to list notes (try again) ==>");
                }

                noteAPI.listNotesBySelectedPriority(priority);
            }

        }

        private void searchNotesByTitle()
        {
            if(noteInTheSystem())
            {
                String title = ScannerInput.readNextLine("Enter the title note to search by: ");
                System.out.println(noteAPI.searchNotesByTitle(title));
            }
        }

        //REPORT MENU FOR ITEMS Options

       private void printAllTodoItems()
       {
           if(noteInTheSystem())
           {
               System.out.println(noteAPI.listTodoItems()); // with note title
           }
       }

       private void printOverallItemsTodoComplete()
       {
           if(noteInTheSystem())
           {
               System.out.println("ITEMS [Completed] : "+ noteAPI.numberOfCompleteItems()+"\n"+"ITEMS [TO D0] : "+noteAPI.numberOfTodoItems());
           }
       }

       private void printItemCompletionStatusByCategory()
       {
           if(noteInTheSystem())
           {
               String category =ScannerInput.readNextLine("Enter a Category "+ CategoryUtility.getCategories()+ " to show Completion Status ==> ");
               while(!CategoryUtility.isValidCategory(category))
               {
                   category = ScannerInput.readNextLine("Enter a Category "+ CategoryUtility.getCategories()+ " to show Completion Status (try again) ==> ");
               }

               System.out.println(noteAPI.listItemStatusByCategory(category));
           }
       }

       private void searchItemsByDescription()
       {
           if(noteInTheSystem())
           {
               String itemDesc = ScannerInput.readNextLine("Enter the item Description you are looking for : ");
               System.out.println(noteAPI.searchItemByDescription(itemDesc));
           }
       }



    //load all the posts into the newsFeed from a file on the hard disk
    private void loadPosts() {
        try {
            noteAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

    //save all the posts in the newsFeed to a file on the hard disk
    private void savePosts() {
        try {
            noteAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }









}
























