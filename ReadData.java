
/** The ReadData class handles reading an external file "records.txt" and displaying it on the I/O window. 
* Retrieved from the LTS assignment*/

import java.io.*;
class ReadData { //Read data from external file using readFile() method

      /** Constructor for the ReadData method. This method reads each line of data from the records.txt file and outputs it into a single, 1-dimensional, array.
        * @param fname  The file records.txt that is being read.
        * @param max    The maximum number of records. 
        * @return    Return the data that is read from records.txt.
        */
      public String[] readFile(String fname, int max) { // fname= "records.txt"
      String data[] = new String[max];  // max = maximum number of records
      for(int k = 0; k < max; k++)  // Initialize data array to store max records
            data[k] = "";
      int i = 0; // Start counting records
      try {
            DataInput in = new DataInputStream(new FileInputStream(fname));
            String txt = in.readLine();
            while (txt != null) {
                     data[i++] = txt; // Store record in array, data; and increment i
                     txt = in.readLine();
            } // end while
      } catch (Exception e){};
      return data;   // Return the entire array of records
      } // end readFile
} // end readData

