//Authors: Sara Hosseini, Nanziba Tasneem, Bilal Ahmed, Hermann Tamnou
//Date: June 6, 2017
//Description: The LibraryTemp class designs a simple GUI for logging in a user or admin.

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.*;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.imageio.*;
import java.awt.image.*;
import java.io.File; 

public class LibraryTemp extends JFrame implements ActionListener
{
      /** The HATTPAD() method sets-up the GUI interface */
      public LibraryTemp(final String info[][], final String itemToPrint, String imageToPrint, final int max)      
      {
      setBackground(new Color(255, 1, 1));

      /*Declare book information variables
      String bookTitle = info[x][0];
      String bookAuthor = info[x][1];
      String date_pub = info[x][2];
      String bookGenre = info[x][3];
      String title_desc = info[x][4];
      String review = info[x][5];*/
      
      String bookTitle = "";
      String bookAuthor = "";
      String date_pub = "";
      String bookGenre = "";
      String title_desc = "";
      String review = "";
      //Locate the book in the library.txt file
      int x = 0;
      for(x = 0; x < max; x++)
      {
         if(itemToPrint.trim().equals(info[x][0]))
         {
            bookTitle = info[x][0];
            bookAuthor = info[x][1];
            date_pub = info[x][2];
            bookGenre = info[x][3];
            title_desc = info[x][4];
            review = info[x][5];
            imageToPrint = "Book Covers\\" + info[x][6];
            
            break;
         }
            
      }

      
      //Construct Frame
		final JFrame menu = new JFrame(itemToPrint);
      menu.setResizable(false);
      //menu.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Image Resources\\Bowlerhat.png")));
      
      //Construct Panel
		ImagePanel panel = new ImagePanel(new ImageIcon("Image Resources\\greenery.png").getImage());
      panel.setLayout(null);
      
      
      //Make JButtons (x-axis, y-axis, width, height)
		JButton goback = new JButton("Back");
      goback.addActionListener(this);   //Add an action listener, which takes in a user action and performs a task
      goback.setBackground(new Color(95,128,203));
      goback.setForeground(Color.WHITE);
      goback.setFont(new Font("Calibri", Font.BOLD, 16));
      goback.setBounds(700, 460, 100, 30);
      
      //Action listener for each button
      
      goback.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("Back"))
            {
               menu.dispose();
            }
            
         }
      });
      
      //Book Information
      
      JLabel title = new JLabel("Title: " + bookTitle);
      title.setFont(new Font("Century Gothic", Font.BOLD, 16));
      title.setBounds(220, 0, 700, 100);
    
      JLabel author = new JLabel ("Author: " + bookAuthor);
      author.setFont(new Font ("Century Gothic", Font.BOLD, 16));
      author.setBounds(220, 20, 700, 100);
      
      JLabel datePublished = new JLabel ("Date Published: " + date_pub);
      datePublished.setFont(new Font ("Century Gothic", Font.BOLD, 16));
      datePublished.setBounds(220, 40, 700, 100);

      JLabel genre = new JLabel ("Genre: " + bookGenre);
      genre.setFont(new Font ("Century Gothic", Font.BOLD, 16));
      genre.setBounds(220, 60, 500, 100);
      
      JLabel areview = new JLabel ("Review: ");
      areview.setFont(new Font ("Century Gothic", Font.BOLD, 16));
      areview.setBounds(10, 320, 900, 100);
      
      JLabel descriptionHeader = new JLabel ("Description: ");
      descriptionHeader.setFont(new Font ("Century Gothic", Font.BOLD, 16));
      descriptionHeader.setBounds(220, 80, 900, 100);
      
      JTextArea description = new JTextArea(20,40);
      description.setLineWrap(true);
      description.setWrapStyleWord(true);
      description.setEditable(false);
      description.setText(title_desc);
      JScrollPane descriptionScrollPane = new JScrollPane(description);
      descriptionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      descriptionScrollPane.setBounds(220, 150, 500, 185);
      
      
      //Cover Image
      JButton cover = new JButton ();
      cover.addActionListener(this);
      ImageIcon icon = new ImageIcon(imageToPrint);
      cover.setIcon (icon);
      
      cover.setBounds(10, 50, 200, 300);
      
      //Set ActionListener for JButton "cover"
      cover.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals(""))
            {
               try{
                     int max2 = max;
                     for(int x = 0; x < max2; x++)
                     {
                        if(itemToPrint.trim().equals(info[x][0]))
                        {
                           int confirm = JOptionPane.showConfirmDialog(menu, "Are you sure you wish to open '" + info[x][0].trim() + "' written by " + info[x][1].trim() + "?", "eBook Confirmation", JOptionPane.YES_NO_OPTION);
                           if(confirm == JOptionPane.YES_OPTION)
                           Desktop.getDesktop().open(new File("Book PDFs\\" + info[x][7]));
                           break;
                        }
            
                     }
                   
                  }
               catch(Exception ex)
               {
                  for(int x = 0; x < max; x++)
                  {
                     if(itemToPrint.trim().equals(info[x][0]))
                     {
                        JOptionPane.showMessageDialog(panel, "Could not locate a PDF for '" + info[x][0].trim() + "' in resources (folder: 'Book PDFs').", "PDF Unavailable", JOptionPane.ERROR_MESSAGE);
                        break;
                     }
                  
                  
                  } 
                  
                  
               }
            }
            
         }
      });
      
      //Create an editable textfield
      JTextArea reviewTextArea = new JTextArea(20, 20);
      reviewTextArea.setText(review);
      reviewTextArea.setLineWrap(true);
      reviewTextArea.setWrapStyleWord(true);
      reviewTextArea.setEditable(false);
      JScrollPane reviewTextAreaScrollPane = new JScrollPane(reviewTextArea);
      reviewTextAreaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      reviewTextAreaScrollPane.setBounds(10, 390, 500, 100);
      
      //Add items to the panel
		panel.add(goback);
      panel.add(cover);
      panel.add(title);
      panel.add(author);
      panel.add(datePublished);
      panel.add(genre);
      panel.add(areview);
      panel.add(reviewTextAreaScrollPane);
      panel.add(descriptionHeader);
      panel.add(descriptionScrollPane);
      
   
      
      
      //Add panel to the frame and adjust frame as well
		menu.add(panel);
		menu.setSize(900,600);
      //menu.getContentPane().add(title);
		menu.setLocationRelativeTo(null);
		menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menu.setVisible(true);
 
      
}
      
      /**The actionPerformed(ActionEvent e) method determines which button a user presses and follows a certain task */
      public void actionPerformed(ActionEvent e)
      {
         String str = e.getActionCommand();  //Find out which button the user pressed
         if(str.equals("Exit"))
            System.exit(0);
      }

      /*public static void main(String str[]) {
         String placeHolder[][] = new String[1][1];
         new LibraryTemp(placeHolder, "", 1);
      }*/
      
}

/** The ImagePanel class handles drawing background onto the JPanels that are used in this program.*/
class ImagePanel extends JPanel {

  private Image img;

  public ImagePanel(String img) {
    this(new ImageIcon(img).getImage());
  }

  public ImagePanel(Image img) {
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }

  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
  }

}