import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.Desktop.*;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.io.File; 
import java.awt.image.*; 
import javax.imageio.*; 
import java.io.IOException;

/** HATTPAD reads records from two external text files, adds/deletes records, handles much of the GUI and other core functionally.
* @author Sara Hosseini, Bilal Ahmed, Nanziba Tasneem, Hermann Tamnou */

public class HATTPAD extends JFrame implements ActionListener
{
      //Declare variables
      static String fileName = "records2.txt";   //fileName = "records.txt"; this text file stores basic information of existing books
      static String library_content = "library.txt";    //library_content = "library.txt"; this text file stores all existing books
      int MAX = 21;                       //Set MAX number of records
      String rows[] = new String[MAX];          //String max 10 records
      String info[][] = new String[MAX][8];     //Store records in 2D array
      String libraryRows[] = new String[MAX];    //Store a maximum of 10 books in the library
      String libraryInfo[][] = new String[MAX][8];   //Store information about each book in 2D array
      Sorter s = new Sorter();                  //Instantiate the class Sorter
      ReadData rd = new ReadData();             //Instantiate the class ReadData
      Records re = new Records();               //Instantiate the class Records
      JTextArea library = new JTextArea(10,20);
      DefaultListModel libraryModel = new DefaultListModel();
      JList libraryList = new JList(libraryModel);
      JScrollPane scrollpane = new JScrollPane(libraryList);
      boolean admin = true;
      public int index = 0;
      public int index2 = 0;
      int bookCount = 0;
      int a = 0;
      int x = 0;     
      int y = 0;   
      
      
      /** The draw_library() method reads an external file and outputs the data onto a TextArea in the View Menu 
        * @param z   The specific column to sort when displaying the list of books in view_menu()    */     
      public void draw_library(int z)
      {
         rows = rd.readFile(fileName, MAX); //Calls readFile method
         info = re.getRecords(rows); //Calls getRecords method
         if(z > -1)
            info = s.sort(info, z);
         libraryModel.removeAllElements();
         for(int x = 0; x < MAX; x++)
         {
            if(rows[x] != null)
            libraryModel.addElement(String.valueOf(info[x][0] + "          " + info[x][1] + "   " + info[x][2] + "   " + info[x][3]).trim()); 
            //libraryModel.addElement("\n");
         }
         libraryList.setModel(libraryModel);
                  
      }

            
      /** The HATTPAD() method sets-up the title page GUI interface 
        * @param admin  This boolean variable helps determine if the user has administrative privelages or not.    */
      public HATTPAD(boolean admin)      
      {
      final boolean admin2 = admin;

		final JFrame menu = new JFrame("Hattpad");
      menu.setResizable(false);
      //menu.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Image Resources\\Bowlerhat.png")));
      
		ImagePanel panel = new ImagePanel(new ImageIcon("Image Resources\\background title.png").getImage());
      panel.setLayout(null);

      //Make JButtons (x-axis, y-axis, width, height)
		JButton close = new JButton("Exit");
      close.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("Exit"))
            System.exit(0);
         }
      }); 
      
      
      JButton view = new JButton("View Library");
      view.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("View Library"))
            {
               draw_library(-1);
               menu.dispose();
               view_menu(admin2);
            }
            
         }
      });
      
      JButton help = new JButton("Help");
      help.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("Help"))
            { 
               try{
                     
                     Desktop.getDesktop().open(new File("Book PDFs\\help page.pdf"));
   
                  }
               catch(Exception ex)
               {
                  System.out.println("Cannot find book!");
               }
            }
      }}); 

      
      
      
      close.setBounds(650, 450, 100, 30);
      view.setBounds(380, 450, 120, 30);
      help.setBounds(130, 450, 100, 30);
      
      //Customize the look of the buttons
		close.setBackground(new Color(95,128,203)); //Hex colour code
      close.setForeground(Color.WHITE);
      close.setFocusPainted(false);
      close.setFont(new Font("Calibri", Font.BOLD, 16));
      
      view.setBackground(new Color(95,128,203));
      view.setForeground(Color.WHITE);
      view.setFocusPainted(false);
      view.setFont(new Font("Calibri", Font.BOLD, 16));
      
      help.setBackground(new Color(95,128,203));
      help.setForeground(Color.WHITE);
      help.setFocusPainted(false);
      help.setFont(new Font("Calibri", Font.BOLD, 16));
      
      
      //Add items to the panel
      //panel.add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Image Resources\\background title.png")))).setBounds(0, 0, 900, 600);
		panel.add(close);
      panel.add(view);
      panel.add(help);
      
      //Add panel to the frame and adjust frame as well
		menu.add(panel);
		menu.setSize(900,600);
		menu.setLocationRelativeTo(null);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);


      }
                     
     /**In the view_menu() method, a new frame is constructed which is used to display the books that are in the library. This window is also used to add/delete books. 
       * @param admin2  This boolean variable helps determine if the user has administrative privelages or not.     */ 
     public void view_menu(boolean admin2)
      {
      //Construct JFrame
      final JFrame view_menu = new JFrame("View Library");
      view_menu.setResizable(false);
      //view_menu.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Image Resources\\Bowlerhat.png")));
      
      //Construct Panel
		ImagePanel view_panel = new ImagePanel(new ImageIcon("Image Resources\\background (png).png").getImage());
      view_panel.setLayout(null);
      

      //Make JButtons (x-axis, y-axis, width, height)
		JButton close = new JButton("Exit");
      JButton back = new JButton("Back");
      JButton sort_number = new JButton("Original Order");
      JButton sort_name = new JButton("Sort by Name");
      JButton sort_author = new JButton("Sort by Author");
      JButton sort_rating = new JButton("Sort by Rating");
      JButton addBook = new JButton("Add a Book");
      JButton deleteBook = new JButton("Delete a Book");
      
      //Customize the look of the buttons
		close.setBackground(new Color(95,128,203)); //Hex colour code
      close.setForeground(Color.WHITE);
      close.setFocusPainted(false);
      close.setFont(new Font("Calibri", Font.BOLD, 16));
      
      back.setBackground(new Color(95,128,203));
      back.setForeground(Color.WHITE);
      back.setFocusPainted(false);
      back.setFont(new Font("Calibri", Font.BOLD, 16));
      
      sort_number.setBackground(new Color(95,128,203));
      sort_number.setForeground(Color.WHITE);
      sort_number.setFocusPainted(false);
      sort_number.setFont(new Font("Calibri", Font.BOLD, 14));
      
      sort_name.setBackground(new Color(95,128,203));
      sort_name.setForeground(Color.WHITE);
      sort_name.setFocusPainted(false);
      sort_name.setFont(new Font("Calibri", Font.BOLD, 14));
      
      sort_author.setBackground(new Color(95,128,203));
      sort_author.setForeground(Color.WHITE);
      sort_author.setFocusPainted(false);
      sort_author.setFont(new Font("Calibri", Font.BOLD, 13));
      
      sort_rating.setBackground(new Color(95,128,203));
      sort_rating.setForeground(Color.WHITE);
      sort_rating.setFocusPainted(false);
      sort_rating.setFont(new Font("Calibri", Font.BOLD, 14));
      
      addBook.setBackground(new Color(95,128,203));
      addBook.setForeground(Color.WHITE);
      addBook.setFocusPainted(false);
      addBook.setFont(new Font("Calibri", Font.BOLD, 14));
      
      deleteBook.setBackground(new Color(95,128,203));
      deleteBook.setForeground(Color.WHITE);
      deleteBook.setFocusPainted(false);
      deleteBook.setFont(new Font("Calibri", Font.BOLD, 14));
      
      
      //Set location and dimensions of buttons
      close.setBounds(250, 500, 100, 30);
      back.setBounds(500, 500, 100, 30);
      sort_number.setBounds(85, 40, 130, 30); 
      sort_name.setBounds(290, 40, 120, 30);
      sort_author.setBounds(495, 40, 120, 30);
      sort_rating.setBounds(690, 40, 120, 30);
      addBook.setBounds(245, 440, 110, 30);
      deleteBook.setBounds(490, 440, 120, 30);
		
      //Add ActionListener for each button
      
      close.addActionListener(new ActionListener() //Action Listener for Close button
      {
         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("Exit"))
            System.exit(0);
         }
      });  
      

      back.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("Back"))
            {
               view_menu.setVisible(false);
               new HATTPAD(admin);
            }
         }
      });
      
      sort_number.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("Original Order"))
            {
               draw_library(0);
               view_menu.repaint(); //Update the frame without closing and re-opening it
            }
         }
      });
      
      sort_name.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("Sort by Name"))
            {
               draw_library(1);
               view_menu.repaint(); //Update the frame without closing and re-opening it
            }
         }
      });
      
      sort_author.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("Sort by Author"))
            {
               //info = s.sort(info, 1); //Sort library by Author Name
               draw_library(2);
               view_menu.repaint();
            }
         }
      });
      
      sort_rating.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("Sort by Rating"))
            {
               //info = s.sort(info, 2); //Sort library by rating
               draw_library(3);
               view_menu.repaint();
            }
         }
      });
      
      addBook.addActionListener(new ActionListener() //Action Listener for Close button
      {
         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("Add a Book"))
            {
               addBook();
               view_menu.dispose();
            }
         }
      });
      
      deleteBook.addActionListener(new ActionListener() //Action Listener for Close button
      {
         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("Delete a Book"))
            {
               String itemToDelete = info[index2][1].trim();   //The Book Name that is being removed   
         
               //Construct JFrame
               final JFrame deleteBook = new JFrame("Delete Confirmation");
               deleteBook.setResizable(false);
   
               //Setup Panel
               JPanel deleteBookPanel = new JPanel(); //ImagePanel(new ImageIcon("Image Resources\\background_search.png").getImage());
               deleteBookPanel.setLayout(null);
         
               //Construct JLabels
               JLabel confirm = new JLabel("Are you sure you wish to delete the following book? :");
               confirm.setFont(new Font("Century Gothic", Font.BOLD, 12));  
               confirm.setBounds(15,20,500,15);  

               JLabel bookToDelete = new JLabel("'" + itemToDelete + "' written by " + info[index2][2].trim());
               bookToDelete.setFont(new Font("Century Gothic", Font.BOLD, 12));   
               bookToDelete.setBounds(40, 50, 500, 15); 
         
         
               //Construct JButtons
               JButton yes = new JButton("Yes");
               JButton no = new JButton("No");
               //Customize look and location of button
               yes.setBackground(new Color(95,128,203)); //Hex colour code
               yes.setForeground(Color.WHITE);
               yes.setFocusPainted(false);
               yes.setFont(new Font("Calibri", Font.BOLD, 12));
               yes.setBounds(170, 85, 60, 20);
         
               no.setBackground(new Color(95,128,203)); //Hex colour code
               no.setForeground(Color.WHITE);
               no.setFocusPainted(false);
               no.setFont(new Font("Calibri", Font.BOLD, 12));
               no.setBounds(100, 85, 60, 20);
         
               //Add actionListeners for each JButton
               yes.addActionListener(new ActionListener() //Action Listener for Close button
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     String str = e.getActionCommand();  //Find out which button the user pressed
                     if(str.equals("Yes"))
                     {
                        deleteBook.dispose();
                        boolean confirmDelete = true;
                        deleteBook(confirmDelete);
                     }
                  }
               });
         
               no.addActionListener(new ActionListener() //Action Listener for Close button
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     String str = e.getActionCommand();  //Find out which button the user pressed
                     if(str.equals("No"))
                     {
                        deleteBook.dispose();
                     }
                  }
               });
         
               //Add items to the panel
               deleteBookPanel.add(confirm);
               deleteBookPanel.add(bookToDelete);
               deleteBookPanel.add(yes);
               deleteBookPanel.add(no);
               
               //Add panel to the frame and adjust frame as well
               deleteBook.add(deleteBookPanel);
		         deleteBook.setSize(350,150);
		         deleteBook.setLocationRelativeTo(null);
		         deleteBook.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		         deleteBook.setVisible(true); 
            }
         }
      });

      
      libraryList.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent evt) {
             JList libraryList = (JList)evt.getSource();
             if (evt.getClickCount() == 2) {
               
                index = libraryList.locationToIndex(evt.getPoint()); //store the book that the user clicked on as string
                
                libraryRows = rd.readFile(library_content, MAX); //Calls readFile method
                libraryInfo = re.getRecords(libraryRows); //Calls getRecords method 
         
                String printBook = info[index][1];
                //System.out.println(index);
                //System.out.println(printBook);
                String imageToPrint = "";
                LibraryTemp getLibrary = new LibraryTemp(libraryInfo, printBook, imageToPrint, MAX); //Instantiate the class LibraryTemp

             }         
          }
       });
       //Second mouse listener to determine which book to delete
       libraryList.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent evt) {
             JList libraryList = (JList)evt.getSource();
             if (evt.getClickCount() == 1) {
               
                index2 = libraryList.locationToIndex(evt.getPoint()); //store the book that the user clicked on as string
                
                libraryRows = rd.readFile(library_content, MAX); //Calls readFile method
                libraryInfo = re.getRecords(libraryRows); //Calls getRecords method 
         
             }         
          }
       });
      


      //Add a non-editable textfield to display the books library
      library.setBounds(150, 85, 600, 30); //x-axis, y-axis, width, height
      library.setVisible(true);
      library.setEditable(false); //Prevents user from adding/removing text from the textbox 

      library.setText("");
      //library.append("     #");
      library.append("Book Name: \t\t");
      library.append("Author: \t");
      library.append("      Rating: ");
         
      //Add scrollpanes to display info

      scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      scrollpane.setBounds(150, 115, 600, 300);
      
      //Add items to the panel
		view_panel.add(close);
      view_panel.add(back);
      view_panel.add(sort_number);
      view_panel.add(sort_name);
      view_panel.add(sort_author);
      view_panel.add(sort_rating);
      if(admin2 == true)
      view_panel.add(addBook);
      if(admin2 == true)
      view_panel.add(deleteBook);
      view_panel.add(scrollpane);
      view_panel.add(library);
      //Add panel to the frame and adjust frame as well
      view_menu.add(view_panel);
		view_menu.setSize(900,600);
		view_menu.setLocationRelativeTo(null);
		view_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view_menu.setVisible(true);

      }
      
      

      
      
      /**The actionPerformed(ActionEvent e) method determines which button a user presses and follows a certain task 
        * @param e  The event that is occurring.    */
      public void actionPerformed(ActionEvent e)
      {

      }
      
      /**The addBook() method takes in user input to generate a new record and book cover as well. */
      public void addBook()
      {
         //Construct JFrame
         final JFrame addBook = new JFrame("Add a Book");
         addBook.setResizable(false);
         //addBook.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Image Resources\\Bowlerhat.png")));
   
         //Setup Panel
         final JPanel addBookPanel = new JPanel(); //ImagePanel(new ImageIcon("Image Resources\\background_search.png").getImage());
         addBookPanel.setLayout(null);
         
         //Construct JLabels
         JLabel bookLabel = new JLabel("Enter the Book's name (Ex. 'The Kite Runner'):");
         bookLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));   
         bookLabel.setForeground (Color.red); //Set colour text to Red
         bookLabel.setBounds(30,25,500,15);
         
         JLabel authorLabel = new JLabel("Enter an Author's name (Ex. 'John Smith'):");
         authorLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));
         authorLabel.setForeground (Color.red);    
         authorLabel.setBounds(30,75,500,15);
         
         JLabel ratingLabel = new JLabel("Enter a rating (Ex. ***, **, ****):");
         ratingLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));
         ratingLabel.setForeground (Color.red);    
         ratingLabel.setBounds(30,125,500,15);
         
         JLabel dateLabel = new JLabel("Enter the date published (Ex. '08 June, 1995'):");
         dateLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));    
         dateLabel.setBounds(30,175,500,15);
         
         JLabel genreLabel = new JLabel("Enter a Genre/Genres (Ex. 'Action, Horror, Thriller'):");
         genreLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));    
         genreLabel.setBounds(30,225,500,15);
         
         JLabel descriptionLabel = new JLabel("Enter a Description for the Book:");
         descriptionLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));    
         descriptionLabel.setBounds(30,275,500,15);
         
         JLabel reviewLabel = new JLabel("Enter a Review for the book:");
         reviewLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));    
         reviewLabel.setBounds(30,375,500,15);
         
         JLabel imageLabel = new JLabel("Enter the name of the Book Cover (Ex. 'something.jpg'):");
         imageLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));    
         imageLabel.setBounds(30,475,500,15);
         
         //Construct TextFields
         final JTextField bookName = new JTextField(200);
         final JTextField bookAuthor = new JTextField(120);
         final JTextField bookRating = new JTextField(200);
         final JTextField bookDate = new JTextField(200);
         final JTextField bookGenre = new JTextField(200);
         
         final JTextArea bookDescription = new JTextArea(200, 50);
         bookDescription.setLineWrap(true);        //Make the text go to the next line once it reaches the end
         bookDescription.setWrapStyleWord(true);
         
         final JTextArea bookReview = new JTextArea(200, 50);
         bookReview.setLineWrap(true);
         bookReview.setWrapStyleWord(true);
         
         final JTextField bookImage = new JTextField(200);
         
         //Set Default text for each TextField
         bookAuthor.setToolTipText("To add multiple authors, please separate via commas.");
         bookRating.setToolTipText("You may only give a maximum of a 5 star rating (*****).");
         bookDescription.setToolTipText("Do not worry about separating text into paragraphs, it will be done automatically!");
         bookImage.setToolTipText("Note: The book cover must be located in the 'Book Covers' folder!");
         
         //Setbounds for each Book
         bookName.setBounds(30, 40, 280, 30);
         bookAuthor.setBounds(30, 90, 280, 30);
         bookRating.setBounds(30, 140, 280, 30);
         bookDate.setBounds(30, 190, 280, 30);
         bookGenre.setBounds(30, 240, 280, 30);
         bookDescription.setBounds(30, 290, 280, 80);
         bookReview.setBounds(30, 390, 280, 80);
         bookImage.setBounds(30, 490, 280, 30);
         
         //Place bookReview and bookDescription into JScrollPane to allow adming to scroll through text
         JScrollPane bookDescriptionScrollPane = new JScrollPane(bookDescription);
         bookDescriptionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
         bookDescriptionScrollPane.setBounds(30, 290, 280, 80);
      
         JScrollPane bookReviewScrollPane = new JScrollPane(bookReview);
         bookReviewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
         bookReviewScrollPane.setBounds(30, 390, 280, 80);
         
         //Construct JButton
         JButton back = new JButton("Back");
         JButton insertBook = new JButton("Add Book");
         //Customize look and location of button
         back.setBackground(new Color(95,128,203)); //Hex colour code
         back.setForeground(Color.WHITE);
         back.setFocusPainted(false);
         back.setFont(new Font("Calibri", Font.BOLD, 14));
         back.setBounds(30, 530, 100, 30);
         
         insertBook.setBackground(new Color(95,128,203)); //Hex colour code
         insertBook.setForeground(Color.WHITE);
         insertBook.setFocusPainted(false);
         insertBook.setFont(new Font("Calibri", Font.BOLD, 14));
         insertBook.setBounds(210, 530, 100, 30);

         //Add actionlistener to button
         back.addActionListener(new ActionListener() //Action Listener for Close button
         {
            public void actionPerformed(ActionEvent e)
            {
               String str = e.getActionCommand();  //Find out which button the user pressed
               if(str.equals("Back"))
               {
                  addBook.dispose();
                  view_menu(true);
                  JOptionPane.showMessageDialog(addBookPanel, "It is STRONGLY recommended that you restart HATTPAD after using the 'Add Book' button in the 'View Library' window!", "Warning!", JOptionPane.WARNING_MESSAGE);
               }
            }
         });
         
         insertBook.addActionListener(new ActionListener() //Action Listener for Close button
         {
            public void actionPerformed(ActionEvent e)
            {
               String str = e.getActionCommand();  //Find out which button the user pressed
               if(str.equals("Add Book"))
               {
                  boolean keepGoing = true;
                  String temp;
                  bookCount = 0;
                  
                  //Count number of existing books in library, and prevent admin from adding more than 20 books
                  for(int q = 0; q < 21; q++)
                  {
                     if(rows[q] != null && rows[q] != "")
                     bookCount++;
                  }
                  if(bookCount > 19)
                  {
                     JOptionPane.showMessageDialog(addBookPanel, "You may only have a maximum of 20 books in this library! Please delete an existing book to add a new one.", "Error", JOptionPane.ERROR_MESSAGE); //Display error message
                     keepGoing = false;
                  }
                  
                  //Identify if the book an admin is entering already exists in the library. No two books can have the same name.
                  for(int r = 0; r < MAX; r++)
                  {
                     if(bookName.getText().equals(""))
                     {//Do nothing
                     }
                     else
                     {
                        if(bookName.getText().toLowerCase().trim().equals(info[r][1].toLowerCase().trim()))
                        {
                           JOptionPane.showMessageDialog(addBookPanel, "A book with that name already exists! Please choose a different name.", "Error", JOptionPane.ERROR_MESSAGE); //Display error message
                           keepGoing = false;
                        }
                     }
                  }
                        
                  //Trauncate the length of each input string to a certain ammount
                  if(bookName.getText().length() > 22)
                  {
                     temp = bookName.getText();
                     temp = temp.substring(0, Math.min(temp.length(), 22));
                     bookName.setText(temp);
                     JOptionPane.showMessageDialog(addBookPanel, "The Book Name has been reduced to 22 characters.", "Important!", JOptionPane.INFORMATION_MESSAGE); //Display error message
                  }  
                  
                  //Force admin to enter a book name, author, and rating   
                  if(bookName.getText().equals(""))
                  {   
                     JOptionPane.showMessageDialog(addBookPanel, "You must enter a Book Name!", "Error", JOptionPane.ERROR_MESSAGE); //Display error message
                     keepGoing = false;
                  }  
                  if(bookAuthor.getText().equals(""))
                  {
                     JOptionPane.showMessageDialog(addBookPanel, "You must enter at least one Author!", "Error", JOptionPane.ERROR_MESSAGE); //Display error message
                     keepGoing = false;
                  }
                  if(bookRating.getText().equals(""))
                  {
                     JOptionPane.showMessageDialog(addBookPanel, "You must enter a Rating!", "Error", JOptionPane.ERROR_MESSAGE);
                     keepGoing = false;
                  }
                  //Makes sure that a user only enter "*" for ratings
                  if(bookRating.getText().equals("*") || bookRating.getText().equals("**") || bookRating.getText().equals("***") || bookRating.getText().equals("****") || bookRating.getText().equals("*****"))
                  { //Do nothing
                  }
                  else{
                     JOptionPane.showMessageDialog(addBookPanel, "A Rating may only contain '*'.", "Error", JOptionPane.ERROR_MESSAGE);
                     keepGoing = false;
                  }
                    
                  if(bookRating.getText().length() > 5)
                  {
                     JOptionPane.showMessageDialog(addBookPanel, "You can only give a maximum of a 5 star rating!", "Error", JOptionPane.ERROR_MESSAGE);
                     keepGoing = false;
                  }
                  
                  if(bookDate.getText().equals(""))
                     bookDate.setText(" ");
                  
                  if(bookGenre.getText().equals(""))
                     bookGenre.setText(" ");
                  
                  if(bookDescription.getText().equals(""))
                     bookDescription.setText(" ");

                  if(bookReview.getText().equals(""))
                     bookReview.setText(" ");
                  
                  if(keepGoing){
                  try 
                  {
                     BufferedWriter output = new BufferedWriter(new FileWriter(fileName, true));
                     output.append(" #" + bookName.getText() + "      #" + bookAuthor.getText() + "#                    " + bookRating.getText() + " #" + "   ##");
                     output.newLine();
                     output.close();
                  } 
                  catch (Exception ex) {JOptionPane.showMessageDialog(addBookPanel, "Could not update records.txt. Please restart the program and try again.", "Error", JOptionPane.ERROR_MESSAGE);}
                  
                  try 
                  {
                     BufferedWriter output2 = new BufferedWriter(new FileWriter(library_content, true));
                     output2.append(bookName.getText() + "#" + bookAuthor.getText() + "#" + bookDate.getText() + "#" + bookGenre.getText() + "#" + bookDescription.getText() + "#" + bookReview.getText() + "#" + bookImage.getText() + "#");
                     output2.newLine();
                     output2.close();
                     addBook.dispose();
                     draw_library(-1);
                     view_menu(true);
                  } 
                  catch (Exception ex) {JOptionPane.showMessageDialog(addBookPanel, "Could not update library.txt. Please restart the program and try again.", "Error", JOptionPane.ERROR_MESSAGE);}
                  JOptionPane.showMessageDialog(addBookPanel, "It is STRONGLY recommended that you restart HATTPAD after using the 'Add Book' button in the 'View Library' window!", "Warning!", JOptionPane.WARNING_MESSAGE);
                 }
               }  
            }
         });
         
         //Add items to the panel
		   //addBookPanel.add(bookNumber);
         addBookPanel.add(back);
         addBookPanel.add(bookLabel);
         addBookPanel.add(authorLabel);
         addBookPanel.add(ratingLabel);
         addBookPanel.add(dateLabel);
         addBookPanel.add(genreLabel);
         addBookPanel.add(descriptionLabel);
         addBookPanel.add(reviewLabel);
         addBookPanel.add(imageLabel);
         
         addBookPanel.add(bookName);
         addBookPanel.add(bookAuthor);
         addBookPanel.add(bookRating);
         addBookPanel.add(bookDate);
         addBookPanel.add(bookGenre);
         addBookPanel.add(bookDescriptionScrollPane);
         addBookPanel.add(bookReviewScrollPane);
         addBookPanel.add(bookImage);
         
         addBookPanel.add(insertBook);
         
         //Add panel to the frame and adjust frame as well
         addBook.add(addBookPanel);
		   addBook.setSize(350,600);
		   addBook.setLocationRelativeTo(null);
		   addBook.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		   addBook.setVisible(true);

      }
      
      /**The deleteBook() method located the desired book and removes it from all records. */
      public void deleteBook(boolean confirmDelete)
      {
         String itemToDelete = info[index2][1].trim();   //The Book Name that is being removed
                  
         if(confirmDelete)
         {         
                  int q = 0;
                  for(q = 0; q < MAX; q++)
                  {
                     if(itemToDelete.equals(info[q][1].trim()))
                     {
                        info[q][0] = null;
                        info[q][1] = null;
                        info[q][2] = null;
                        info[q][3] = null;
                        break;
                     }
                  
                  }
                  //Locate book from library.txt, and each value in the array to null
                  for(int k = 0; k < MAX; k++)
                  {  
                     if(itemToDelete.equals(libraryInfo[k][0].trim()))
                     {
                        libraryInfo[k][0] = null;
                        libraryInfo[k][1] = null;
                        libraryInfo[k][2] = null;
                        libraryInfo[k][3] = null;
                        libraryInfo[k][4] = null;
                        libraryInfo[k][5] = null;
                        break;
                     } 
                     
                  }
                  
                  try 
                  {
                     BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                     writer.append("");
                     for(int i = 0; i < info.length; i++)
                     {
                        for(int j = 0; j < 4; j++)
                        {
                           if(info[i][0] != null && info[i][0] != "")
                           writer.append(info[i][j] + "#");
                        }
                        if(info[i][0] != null && info[i][0] != "")
                        writer.newLine();
                     }
                     writer.close();
                  } 
                  catch (Exception ex) {System.out.println("Cannot update records.txt!");}
                  
                  try
                  {
                     BufferedWriter writer2 = new BufferedWriter(new FileWriter(library_content));
                     writer2.append("");
                     for(int i = 0; i < libraryInfo.length; i++)
                     {
                        for(int j = 0; j < 8; j++)
                        {
                           if(libraryInfo[i][0] != null && libraryInfo[i][0] != "")
                           writer2.append(libraryInfo[i][j] + "#");
                        }
                        if(libraryInfo[i][0] != null && libraryInfo[i][0] != "")
                        writer2.newLine();
                     }
                     writer2.close();
                     draw_library(-1);
                  } 
                  catch (Exception ex) {System.out.println("Cannot update library.txt!");}

      }  }
      /**This method runs the program from the login screen. */
      public static void main(String str[]) {
         new Login();
         //new HATTPAD(true);
      }
      
}



