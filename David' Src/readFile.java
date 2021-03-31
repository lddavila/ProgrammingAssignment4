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

    /**Creates a Checking account with the appropriate info given by dataPositions
     * @param bankAccountData an array of strings which contains all the info in the file line
     * @param dataPositions an array of ints that contains the column positions
     * @return a Checking Account with the correct data
     */
    public static Checking createChecking(String [] bankAccountData, int [] dataPositions){
        Checking tempChecking = new Checking();
        tempChecking.set_First_Name(bankAccountData[dataPositions[14]]);
        tempChecking.set_Last_Name(bankAccountData[dataPositions[2]]);
        tempChecking.set_Starting_Balance(Double.parseDouble(bankAccountData[dataPositions[7]]));
        tempChecking.set_Account_Number(Long.parseLong(bankAccountData[dataPositions[4]]));
        tempChecking.getBankStatement().setStartingBalance(Double.parseDouble(bankAccountData[dataPositions[7]]));
        return tempChecking;
    }
    /**Creates a Savings account with the appropriate info given by dataPositions
     * @param bankAccountData an array of strings which contains all the info in the file line
     * @param dataPositions an array of ints that contains the column positions
     * @return a Savings Account with the correct data
     */
    public static Savings createSavings(String [] bankAccountData, int [] dataPositions){
        Savings tempSavings = new Savings();
        tempSavings.set_First_Name(bankAccountData[dataPositions[14]]);
        tempSavings.set_Last_Name(bankAccountData[dataPositions[2]]);
        tempSavings.set_Starting_Balance(Double.parseDouble(bankAccountData[dataPositions[8]]));
        tempSavings.set_Account_Number(Long.parseLong(bankAccountData[dataPositions[0]]));
        tempSavings.getBankStatement().setStartingBalance(Double.parseDouble(bankAccountData[dataPositions[8]]));
        return tempSavings;
    }
    /**Creates a Credit account with the appropriate info given by dataPositions
     * @param bankAccountData an array of strings which contains all the info in the file line
     * @param dataPositions an array of ints that contains the column positions
     * @return a Credit Account with the correct data
     */
    public static Credit createCredit(String [] bankAccountData, int [] dataPositions){
        Credit tempCredit = new Credit();

        tempCredit.set_First_Name(bankAccountData[dataPositions[14]]);
        tempCredit.set_Last_Name(bankAccountData[dataPositions[2]]);
        tempCredit.set_Starting_Balance(Double.parseDouble(bankAccountData[dataPositions[10]]));
        tempCredit.set_Account_Number(Long.parseLong(bankAccountData[dataPositions[5]]));
        tempCredit.getBankStatement().setStartingBalance(Double.parseDouble(bankAccountData[dataPositions[10]]));
        //below we create a Customer class with all the other info we have in bankAccountData and in []dataPositions

        return tempCredit;
    }

    /**Creates a customer with the appropriate info given by dataPositions
     * @param bankAccountData an array of strings which contains all the info in the file line
     * @param dataPositions  an array of ints that contains the column positions
     * @return a customer appropriately populated with the data from bankAccountData and the positions in dataPositions
     */
    public static Customer createCustomer(String []bankAccountData, int[] dataPositions){
        Customer tempCustomer = new Customer();
        tempCustomer.setFirstName(bankAccountData[dataPositions[14]]);
        tempCustomer.setLastName(bankAccountData[dataPositions[2]]);
        tempCustomer.setDateOfBirth(bankAccountData[dataPositions[3]]);
        tempCustomer.setIDNum(Long.parseLong(bankAccountData[dataPositions[1]]));
        tempCustomer.setCreditMax(Double.parseDouble(bankAccountData[dataPositions[9]]));
        tempCustomer.setCheck(createChecking(bankAccountData,dataPositions));
        tempCustomer.setSave(createSavings(bankAccountData,dataPositions));
        tempCustomer.setCred(createCredit(bankAccountData,dataPositions));
        tempCustomer.setAddress(bankAccountData[dataPositions[11]] + bankAccountData[dataPositions[12]] + bankAccountData[dataPositions[13]].replaceAll("^\"|\"$", ""));
        tempCustomer.setPhoneNumber(bankAccountData[dataPositions[6]]);
        return tempCustomer;
    }
    
    /**Creates a database of Person
     * @return a hashTable filled with Person class
     */
    public static PersonDatabase createPersonDatabase(){
        //counter is going to be used so we know how big to make the hash table
        int counter = 0;
        //meant to store the different accounts after they're created

        ArrayList<Person> listOfCustomers = new ArrayList<Person>();
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
                Customer tempCust = createCustomer(bankAccountData, columnPositions);
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
