# seanconroy2021-Assingment01JAVA-2022

<h1>Assignment Overview</h1>
<p> notes from lecture </P>
You are tasked with developing a Note Keeper Menu Driven, CRUD-compliant system.

A sample meun is here:
<br>
<img src="https://pf2-it2-22.netlify.app/topic00-assignments/book-3/img/03.png" alt="">

You will see there are two main entities on the menu…Notes and Items.

Here are a few rules associated with them:

The app can have zero-many notes.

Notes can be Active or Archived.

Notes have a priority, 1-5, with 1 being low.

Notes have a category. Pre-defined categories are lised later in the spec.

A note can have zero-many items on it, so a note doesn't have to have items.

Each item on a note is either in Completed or TODO state.

Items can only be added to Active notes.

A note can be archived (through a menu option) when all the items are completed on it. You can also archive a note that has NO items on it. Any note that has TODO items on it cannot be archived until the item is complete.

<h2>Classes in the System</h2>
<br>
The Note Keeper app will have four main classes (the blue ones):
<img src="https://pf2-it2-22.netlify.app/topic00-assignments/book-3/img/04.png" alt="">



Note: The responsibility for this class is to manage a single Note in the System. A note has multiple items in it.

Item: The responsibility for this class is to manage a single Item in the system. An item belongs to a specific Note.

NoteAPI: The responsibility for this class is to store and manage all the notes in the system.

Driver: The responsibility for this class is to manage the User Interface (UI) i.e. the menu and user input/output.

<h2>Other Classes in the System</h2>

There are three other classes in the System (the orange ones):

ScannerInput: The methods of this class are used in the Driver class. These methods provide robust Scanner reads of any user input. The ScannerInput.java code is here; just include it as a class in your project.

Utilities: This class is used throughout the system and has a series of helper methods (for validation) in it. The Utilities.java code from ShopV5.0 is here; just include it as a class in your project.

CategoryUtility: This is a second Utility class that you must write yourself. It is used to validate the categories of notes.

<h2>Classes and ArrayLists</h2>
<img src="https://pf2-it2-22.netlify.app/topic00-assignments/book-3/img/05.png" alt="">


Within the system, there are two ArrayLists::



We will discuss each ArrayList in turn, in the following tabs.

