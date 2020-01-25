
/** The Records class separates the data from records.txt and splits it into 6 components so that it can be displayed. 
* Retrieved from the LTS assignment*/

import java.util.StringTokenizer;
class Records { // Separate a line of record into 6 component parts

   /** Constructor for the getRecords method. This method separates each line in the records.txt file for easy viewing.  
        * @param data []    The data that is stored in this 1-dimensional array. 
        * @return  Returns the new record that is written into records.txt.
        */
        
   public String[][] getRecords(String data[]) {
      int n = data.length;
      String records[][] = new String[n][8]; // Store records in 2-dimensional array
      for(int p = 0; p < n; p++) // Initialize all records
            for (int q = 0; q <7; q++)
                     records[p][q]="";
      for (int line = 0; line < n; line++) {
         if (data[line].equals("")) break;
          StringTokenizer st = new StringTokenizer(data[line], "#");
          int i = 0;
         while (st.hasMoreTokens() && i < 8) {
                  records[line][i] = st.nextToken(); // Store each item of the record
                  System.out.print(records[line][i] + "  ");
                  i++;
         } // end while
         System.out.println();
      } // end for
      return records;  // Return the 2-dimensional array records
   } // end getRecords
} // end Records
     
 