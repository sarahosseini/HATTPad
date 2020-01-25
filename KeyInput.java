import java.io.*;

/** A class that handles creating new entires into the HATTPAD. 
   * @author    Sara Hosseini, Nanziba Tasneem, Bilal Ahmed, Hermann Tamnou*/
   
class KeyInput{
            static String txt, input;
            static String items[] = new String[6];
            /** entries() allows the user to input their information, which will later be stored into a new record for the database.
             * @return  Returns the Name that is entered by the user. 
            */
            public static String entries() {
                DataInput keyboard = new DataInputStream(System.in);
            try{
                 System.out.println("Enter the Book number (Ex. 11), 12), 13), etc.)");
                 System.out.println("*Note: After inserting any new entries, you must restart the program!");
                 input = keyboard.readLine();
                 txt = input; // Store name
                 items[0] = input;
                 int index =1; // Get next item; item index 1 to 5
                 while ((input !=null) && index< 4){
                 switch (index){
                 case 1: System.out.println("Enter the Book Name");
                         break;
                 case 2: System.out.println("Enter the Author");
                         break;
                 case 3: System.out.println("Enter a Rating (Ex. **, ***, ****)");
                         break;
                 /*case 4: System.out.println ("Enter Time-hh: mm");
                         break;
                 case 5: System.out.println ("Enter Period- period x");
                         break;*/
               } // end switch
               input = keyboard.readLine();
               txt += "#" + input;
               items[index] = input;
               index++;
               }// end while
               System.out.println("\nNew record complete.");
               System.out.println(txt);
               }catch (Exception e){}
               return txt;
               } // end entries
               }

class KeyInput2{
            static String txt, input;
            static String items[] = new String[8];
            /** entries() allows the user to input their information, which will later be stored into a new record for the database.
             * @return  Returns the Name that is entered by the user. 
            */
            public static String entries() {
                DataInput keyboard = new DataInputStream(System.in);
            try{
                 System.out.println("Enter the Book Name (Ex. The Bible, etc.)");
                 System.out.println("*Note: After inserting any new entries, you must restart the program!");
                 input = keyboard.readLine();
                 txt = input; // Store name
                 items[0] = input;
                 int index =1; // Get next item; item index 1 to 5
                 while ((input !=null) && index< 7){
                 switch (index){
                 case 1: System.out.println("Enter the Autor");
                         break;
                 case 2: System.out.println("Enter the Date it was Published (Ex. 05 May 2015, etc.)");
                         break;
                 case 3: System.out.println("Enter the Genre of the Book (Ex. Classics, Horror, Action, Romance, etc.)");
                         break;
                 case 4: System.out.println ("Enter a Description of the Book. Please DO NOT worry about paragraphs, simply copy a description into one long line!)");
                         break;
                 case 5: System.out.println ("Enter a review for the book");
                         break;
                 case 6: System.out.println ("Enter the file name of the poster of a book (Ex. TheBible.jpg). This image must be placed in the 'Text' folder");
                         break;
               } // end switch
               input = keyboard.readLine();
               txt += "#" + input;
               items[index] = input;
               index++;
               }// end while
               System.out.println("\nNew record complete.");
               System.out.println(txt);
               }catch (Exception e){}
               return txt;
               } // end entries
               }

              