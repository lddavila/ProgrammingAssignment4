import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

/**Meant to read the transaction logs
 */
public class transactionReader {
    /**
     * @param columnTitles the titles of the column used to preserve order dynamically
     * @return an int array representing the column positions to dynamically store info
     * columnInfo[0] = From First
     * columnInfo[1] = From Last
     * columnInfo[2] = From where
     * columnInfo[3] = action
     * columnInfo[4] = to first name
     * columnInfo[5] = to last name
     * columnInfo[6] = to where
     * columnInfo[7] = action amount
     * */
    public static int[] columnPositions(String [] columnTitles){
        int [] columnInfo = new int[columnTitles.length];
        for(int i = 0; i < columnTitles.length; i++){

            switch (columnTitles[i]){
                case "From First Name":
                    columnInfo[0] = i;
                    break;
                case "From Last Name":
                    columnInfo[1] = i;
                    break;
                case "From Where":
                    columnInfo[2] = i;
                    break;
                case "Action":
                    columnInfo[3] = i;
                    break;
                case "To First Name":
                    columnInfo[4] = i;
                    break;
                case "To Last Name":
                    columnInfo[5] = i;
                    break;
                case "To Where":
                    columnInfo[6] = i;
                    break;
                case "Action Amount":
                    columnInfo[7] = i;
                    break;
            }
        }
        return columnInfo;
    }

    /**Cals the transfers method and performs the operation depending on input from Transactions Action File
     * @param people a database of all users
     * @param bankAccountData a String array holding the info
     * @param columnInfo an int array showing the positions of all the info stored in bank account data
     */
    public static void paysAndTransfers(PersonDatabase people, String [] bankAccountData, int [] columnInfo){
        //this if/else will use the previously available name if the line is blank
        Person fromCustomer = backEnd.findAccount(bankAccountData[columnInfo[0]] +" "+ bankAccountData[columnInfo[1]], people);

        Person toCustomer = backEnd.findAccount(bankAccountData[columnInfo[4]] + " " + bankAccountData[columnInfo[5]], people);


        //The following embedded switch case calls the transfer function in backEndForTransaction reader with the appropriate accounts
        switch (bankAccountData[columnInfo[2]]){
            case "Checking":
                switch (bankAccountData[columnInfo[6]]){
                    case "Checking":
                        backEndForTransactionReader.transfer(fromCustomer.getCheck(), toCustomer.getCheck(), Double.parseDouble(bankAccountData[columnInfo[7]]));
                    case "Savings":
                        backEndForTransactionReader.transfer(fromCustomer.getCheck(), toCustomer.getSave(), Double.parseDouble(bankAccountData[columnInfo[7]]));
                }
            case "Savings":
                try {
                    switch (bankAccountData[columnInfo[6]]) {
                        case "Checking":
                            backEndForTransactionReader.transfer(fromCustomer.getSave(), toCustomer.getCheck(), Double.parseDouble(bankAccountData[columnInfo[7]]));
                        case "Savings":
                            backEndForTransactionReader.transfer(fromCustomer.getSave(), toCustomer.getSave(), Double.parseDouble(bankAccountData[columnInfo[7]]));
                    }
                }catch (Exception e){

                }
        }
    }

    /**prints the account info
     * @param people the database we need to find the person in
     * @param bankAccountData A string array representing the action
     * @param columnInfo an int array representing the positions of the sata stored in bankAccountData
     */
    public static void inquires(PersonDatabase people, String [] bankAccountData, int [] columnInfo){
        //System.out.println(bankAccountData[columnInfo[0]]+bankAccountData[columnInfo[1]]);
        Person user = backEnd.findAccount(bankAccountData[columnInfo[0]] +" "+ bankAccountData[columnInfo[1]], people);

        user.printAccountInfo();
        writeUserActions.logUserAction(user.getFirstName() + " " + user.getLastName() + " checked their account balances.");
    }

    /**calls the deposit function with the right info
     * @param people the database we need to find the person in
     * @param bankAccountData A string array representing the action
     * @param columnInfo an int array representing the positions of the sata stored in bankAccountData
     */
    public static void deposit(PersonDatabase people, String [] bankAccountData, int [] columnInfo){
        Person fromCustomer = backEnd.findAccount(bankAccountData[columnInfo[4]] +" "+ bankAccountData[columnInfo[5]], people);
        switch(bankAccountData[columnInfo[2]]){
            case "Savings":
                backEndForTransactionReader.deposit(fromCustomer.getSave(), Double.parseDouble(bankAccountData[columnInfo[7]]));
                break;
            case "Checking":
                backEndForTransactionReader.deposit(fromCustomer.getCheck(), Double.parseDouble(bankAccountData[columnInfo[7]]) );
                break;
        }

    }
    /**Calls the withdrawal function with the right info
     * @param people the database we need to find the person in
     * @param bankAccountData A string array representing the action
     * @param columnInfo an int array representing the positions of the sata stored in bankAccountData
     */
    public static void withdrawal(PersonDatabase people, String[] bankAccountData, int [] columnInfo){
        Person fromCustomer = backEnd.findAccount(bankAccountData[columnInfo[0]] +" "+ bankAccountData[columnInfo[1]], people);
        switch(bankAccountData[columnInfo[2]]){
            case "Savings":
                backEndForTransactionReader.withdraw(fromCustomer.getSave(), Double.parseDouble(bankAccountData[columnInfo[7]]));
                break;
            case "Checking":
                backEndForTransactionReader.withdraw(fromCustomer.getCheck(), Double.parseDouble(bankAccountData[columnInfo[7]]) );
                break;
        }
    }



    /**
     * @param people the database that the info will be drawn from
     */
    public static void reader(PersonDatabase people) {

        //counter is going to be used so we know how big to make the hash table
        //int counter = 1;
        //meant to store the different accounts after they're created
        try {
            //System.out.println("Enters the try ");
            //Find the file
            File givenFile = new File("src/Transaction Actions.csv");
            Scanner reader = new Scanner(givenFile);
            String [] firstLine = reader.nextLine().split(",");
            int [] columnInfo = columnPositions(firstLine);
            String[] bankAccountData = reader.nextLine().split(",");


            while (reader.hasNextLine()) {
                bankAccountData = reader.nextLine().split(",");
               /* for (int i = 0; i < bankAccountData.length; i++)
                    System.out.print(bankAccountData[i] + ",");

                */
                switch (bankAccountData[columnInfo[3]]) {
                    case "pays":
                        paysAndTransfers(people, bankAccountData, columnInfo);
                        break;
                    case "transfers":
                        paysAndTransfers(people, bankAccountData, columnInfo);
                        break;
                    case "inquires":
                        inquires(people, bankAccountData, columnInfo);
                        break;
                    case "deposits":
                        deposit(people, bankAccountData, columnInfo);
                        break;
                    case "withdraws":
                        withdrawal(people, bankAccountData, columnInfo);
                        break;
                    default:
                        System.out.println("Hey it shouldn't get here.");
                }
                //counter++;
                //System.out.println(counter);




            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file");
        }
    }
}