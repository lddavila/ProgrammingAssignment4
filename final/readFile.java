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
        //columnPosition[4] = DOB
        //columnPosition[5] = Checking Account Number
        //columnPosition[6] = Credit Account Number
        //columnPosition[7] = Phone Number
        //columnPosition[8] = Checking Starting Balance
        //columnPosition[9] = Saving Starting Balance
        //columnPosition[10] = Credit Max
        //columnPosition[11] = Credit Starting Balance
        //ColumnPosition[12] = first name
        //ColumnPosition[13] = Email
        //ColumnPosition[14] = password
        //columnPosition[15] = Address
        //columnPosition[16] = Address
        //ColumnPosition[17] = Address

         */


        int [] columnPosition = new int[columnInfo.length+3];
        for(int i = 0; i < columnPosition.length;i++){
            columnPosition[i] = -111111;
        }

        System.out.println("///////////////////////////");
        System.out.println(columnPosition.length);
        System.out.println("///////////////////////////");
        int counter = 0;

        for (int i = 0; i < columnPosition.length; i++ ){
            //I use this switch case to find which column each piece of info is stored in
            //I then store those positions into an array which I then use to make the appropriate calls to build
            //the databases

            switch(columnInfo[counter]){
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
                    columnPosition[4] = i+1;
                    i+=1;
                    break;
                case "Checking Account Number":
                    columnPosition[5] = i;
                    break;
                case "Credit Account Number":
                    columnPosition[6] = i;
                    break;
                case "Phone Number":
                    columnPosition[7] = i;
                    break;
                case "Checking Starting Balance":
                    columnPosition[8] = i;
                    break;
                case "Savings Starting Balance":
                    columnPosition[9] = i;
                    break;
                case "Credit Max":
                    columnPosition[10] = i;
                    break;
                case "Credit Starting Balance":
                    columnPosition[11] = i;
                    break;
                case "First Name":
                    columnPosition[12] = i;
                    break;
                case "Email":
                    columnPosition[13] = i;
                    break;
                case "Password":
                    columnPosition[14] = i;
                    break;
                case "Address":
                    columnPosition[15] = i;
                    columnPosition[16] = i+1;
                    columnPosition[17] = i+2;
                    i+=2;
                    //we do this here because the address actually takes up 3 columns cause of how
                    //it is formatted in the csv
                    break; }
                    counter++;


        }

        /*
        System.out.println("Savings Account Number: " + columnPosition[0]);
        System.out.println("ID Number: " + columnPosition[1]);
        System.out.println("Last NAme: " + columnPosition[2]);
        System.out.println("Date Of Birth: " + columnPosition[3]);
        System.out.println("Date Of Birth: " + columnPosition[4]);
        System.out.println("Checking Account Number: " + columnPosition[5]);
        System.out.println("Credit Account Number: " + columnPosition[6]);
        System.out.println("Phone Number: " + columnPosition[7]);
        System.out.println("Checking Starting Balance: " + columnPosition[8]);
        System.out.println("Saving Starting Balance: " + columnPosition[9]);
        System.out.println("Credit Max: " + columnPosition[10]);
        System.out.println("Credit Starting Balance: " + columnPosition[11]);
        System.out.println("first name: " + columnPosition[12]);
        System.out.println("Email: " + columnPosition[13]);
        System.out.println("Password: " + columnPosition[14]);
        System.out.println("Address: " + columnPosition[15]);
        System.out.println("Address: " + columnPosition[16]);
        System.out.println("Address: " + columnPosition[17]);

         */
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
                //the loop below lets us print the data, so we can see exactly how it's formatted in future lab iterations, don't delete
                /*
                for (int i = 0; i < bankAccountData.length; i++){
                    System.out.println(i + ": "+ bankAccountData[i]);
                }
                 */

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
