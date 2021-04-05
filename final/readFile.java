import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/** This class is meant to read the given files and create the appropriate databases
 */


public class readFile {

    /**Dynamically finds what column each piece of info DOB, First Name, etc. is stored in
     * @param columnInfo an array of strings containing the titles of the columns
     * @return will return an array columnInfo which stores the appropriate position of each column
     *         //columnPosition[0] = Savings Account number
     *         //columnPosition[1] = ID number
     *         //columnPosition[2] =  Last Name
     *         //columnPosition[3] = DOB
     *         //columnPosition[4] = Checking Account Number
     *         //columnPosition[5] = Credit Account Number
     *         //columnPosition[6] = Phone Number
     *         //columnPosition[7] = Checking Starting Balance
     *         //columnPosition[8] = Saving Starting Balance
     *         //columnPosition[9] = Credit Max
     *         //columnPosition[10] = Credit Starting Balance
     *         //columnPosition[11] = Address
     *         //columnPosition[12] = Address
     *         //ColumnPosition[13] = Address
     *         //ColumnPosition[14] = first name
     */
    public static int [] findColumnPosition(String [] columnInfo){

        /*13
        //columnPosition[0] = Savings Account number
        //columnPosition[1] = ID number
        //columnPosition[2] =  Last Name
        //columnPosition[3] = DOB
        //columnPosition[4] = Checking Account Number
        //columnPosition[5] = Credit Account Number
        //columnPosition[6] = Phone Number
        //columnPosition[7] = Checking Starting Balance
        //columnPosition[8] = Saving Starting Balance
        //columnPosition[9] = Credit Max
        //columnPosition[10] = Credit Starting Balance
        //columnPosition[11] = Address
        //columnPosition[12] = Address
        //ColumnPosition[13] = Address
        //ColumnPosition[14] = first name

         */


        int [] columnPosition = new int[columnInfo.length+2];
        for(int i = 0; i < columnPosition.length;i++){
            columnPosition[i] = -111111;
        }


        for (int i = 0; i < columnInfo.length; i++ ){
            //I use this switch case to find which column each piece of info is stored in
            //I then store those positions into an array which I then use to make the appropriate calls to build
            //the databases

            switch(columnInfo[i]){
                case "Savings Account Number":
                    columnPosition[0] = i;
                    break;
                case "Identification Number":
                    columnPosition[1] = i;
                    break;
                case "Last Name":
                    columnPosition[2] = i;
                    break;
                case "Date of Birth":
                    columnPosition[3] = i;
                    break;
                case "Checking Account Number":
                    columnPosition[4] = i;
                    break;
                case "Credit Account Number":
                    columnPosition[5] = i;
                    break;
                case "Phone Number":
                    columnPosition[6] = i;
                    break;
                case "Checking Starting Balance":
                    columnPosition[7] = i;
                    break;
                case "Savings Starting Balance":
                    columnPosition[8] = i;
                    break;
                case "Credit Max":
                    columnPosition[9] = i;
                    break;
                case "Credit Starting Balance":
                    columnPosition[10] = i;
                    break;
                case "Address":
                    columnPosition[11] = i;
                    columnPosition[12] = i+1;
                    columnPosition[13] = i+2;
                    //we do this here because the address actually takes up 3 columns cause of how
                    //it is formatted in the csv
                    break;
                case "First Name":
                    columnPosition[14] = i+2;
                    break;



            }

        }
        for(int i = 0; i < columnPosition.length; i++){

        }
        return columnPosition;
    }


    /**Creates a database of Person
     * @return a hashTable filled with Person class
     */
    public static PersonDatabase createPersonDatabase(){
        //counter is going to be used so we know how big to make the hash table
        int counter = 0;
        //meant to store the different accounts after they're created

        ArrayList<Customer> listOfCustomers = new ArrayList<Customer>();
        try {
            //Find the file
            //need to fix this so that it only accepts the file name
            File givenFile = new File("src/CS 3331 - Bank Users.csv");
            Scanner reader = new Scanner(givenFile);
            //retrieves the first line, which contains the titles, from the file
            String [] columnOrder = reader.nextLine().split(","); //Gets the first line

            //The below line of code will create the first line of the new user log, we need to do this to properly format the info later

            //each position of columnPositions contains an int that represents what column each piece of info is located in
            int [] columnPositions = findColumnPosition(columnOrder);
            while (reader.hasNextLine()) {

                //Takes the String and turns it to an array to take advantage of a constructor in the Checkings class which accepts an array
                String [] bankAccountData = reader.nextLine().split(",");
                //creates a temp customer to add to database
                Customer tempCust = new Customer(bankAccountData, columnPositions);
                //adds temporary customer to array list to preserve it
                listOfCustomers.add(tempCust);
                //we update counter on each line of the file to keep track of the size we need the hash table to be
                counter+=1;

            }
            reader.close();
        }

        catch (FileNotFoundException e){
            System.out.println("Could not find file");
        }

        //creates the databases that the rest of the lab will read from
        PersonDatabase customers = new PersonDatabase(counter);
        //adds to the  database from the array lists created earlier
        for(int i = 0; i < listOfCustomers.size(); i++){
            customers.add_Person(listOfCustomers.get(i));
        }

        //dataBase.print_Data_Base();
        return customers;
    }

}