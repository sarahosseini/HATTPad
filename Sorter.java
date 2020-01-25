
/** The Sorter class sorts all records by names, IDs, homeforms and periods.
* Retrieved from the LTS assignment*/

class Sorter {

      /** Constructor for the sort method. This method sorts the records.txt data into what the user specified (i.e. by Name, ID, etc.)
        * @param list [][]  The file records.txt.
        * @param item   The item that is being sorted (Name, ID, Homeform, etc.).
        * @return  The new sorted list is returned.       
        */
        
      public static String [][] sort (String list [][], int item) { // Input records; item = name etc
                   
            String temp;
            int len = list.length - 1;
            for (int i = len; i > 1; i--)
                  for ( int j = 0; j < i; j++)
                           if ( list[j][item].compareTo( list[j+1][item] ) > 0) {
                           for (int k = 0; k < 6; k++)
                                    swap(list, j, k); // swapping all 6 items
                           } // end if
                  return list; // return sorted list
      } // end sort
      
      /** Constructor for the swap method. This method is a constituent to the sort method. 
        * @param list [][]  The file records.txt.
        * @param item   The item that is being swapped.
        * @param index  The other item that is being swapped.       
        */
      
      static void swap(String list[][], int index, int item) {   
      
            String temp;
            temp = list[index][item];
            list[index][item] = list[index + 1][item];
            list[index + 1][item] = temp;
      } // end swap
} // end Sorter
   
                                   