
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**This class will log all user actions and create the necessary files
 */
public class writeUserActions {

    /**
     * Creates the files if they don't exist
     *
     * @param fileName the name the .txt files should be
     */
    public static void makeLog(String fileName) {
        try {
            //creates the file for the user log
            //by making it false we make sure that a new file is created every time
            File log = new File(fileName);
            log.createNewFile();
            FileWriter logger = new FileWriter("userActions.txt", false);
            //creates the file for the updated balance sheet
            //by making it false we make sure a new file is created every time
            File newBalances = new File("Generated Updated Balance Sheet.csv");
            FileWriter a = new FileWriter("Generated Updated Balance Sheet.csv", false);
        } catch (Exception e) {
            System.out.println("Couldn't create file");
        }
    }

    /**
     * Will write user actions to .txt file
     *
     * @param userAction action the user wants to take
     */
    public static void logUserAction(String userAction) {

        try {
            //If you don't put true then you'll keep overwriting, learned this the hard way over the course of an hour
            FileWriter logger = new FileWriter("userActions.txt", true);

            logger.write(userAction + "\n");
            // \n puts each new written line on its own line

            logger.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

    }


    /**
     * Will write new balances to the .csv file
     *
     * @param accountsIn the database of accounts
     *                   columnPosition[0] = Savings Account number
     *                   columnPosition[1] = ID number
     *                   columnPosition[2] =  Last Name
     *                   columnPosition[3] = DOB
     *                   columnPosition[4] = Checking Account Number
     *                   columnPosition[5] = Credit Account Number
     *                   columnPosition[6] = Phone Number
     *                   columnPosition[7] = Checking Starting Balance
     *                   columnPosition[8] = Saving Starting Balance
     *                   columnPosition[9] = Credit Max
     *                   columnPosition[10] = Credit Starting Balance
     *                   columnPosition[11] = Address
     *                   columnPosition[12] = Address
     *                   ColumnPosition[13] = Address
     *                   ColumnPosition[14] = first name
     */
    public static void logNewBalances(PersonDatabase accountsIn) {

        //peopleIn.print_Data_Base();
        Customer[][] localA = accountsIn.get_table();


        try {

            /*I use the lines below to get the first line of the Updated Balance Sheet
             *This is helpful because I already wrote the first line previously and it contains the properly formatted
             * Columns which we can then use to call findColumnPosition to get the same array we used earlier to print things
             * in the proper format
             */
            File givenFile = new File("src/CS 3331 - Bank Users.csv");
            Scanner reader = new Scanner(givenFile);
            String[] columnTitles = reader.nextLine().split(",");

            FileWriter logger = new FileWriter("Generated Updated Balance Sheet.csv", true);

            logger.write(
                    "Identification Number," +
                    "First Name," +
                    "Last Name," +
                    "Phone Number," +
                    "Email," +
                     "Password," +
                     "Address," +
                    "Date of Birth," +
                    "Credit Max," +
                    "Checking Account Number," +
                    "Checking Starting Balance," +
                    "Savings Account Number," +
                    "Savings Starting Balance," +
                    "Credit Account Number," +
                    "Credit Starting Balance, \n");
            /*
            for (int i = 0; i < columnTitles.length; i++) {
                if (i < columnTitles.length - 1) {
                    logger.write(columnTitles[i] + ",");
                } else {
                    logger.write(columnTitles[i]);
                }
            }
            logger.write("\n");
             */


            for (int i = 0; i < localA.length; i++) {
                if (localA[i][0] != null) {
                    //The below for loop will format the customer's information into the .csv file that will be written
                    //in the end
                    logger.write(String.valueOf(
                            localA[i][0].getIDNum()+","+
                            localA[i][0].getFirstName()+","+
                            localA[i][0].getLastName())+","+
                            localA[i][0].getPhoneNumber()+","+
                            localA[i][0].getEmail()+","+
                            localA[i][0].getPassword()+","+
                            localA[i][0].getAddress()+","+
                            localA[i][0].getDOB()+","+
                            localA[i][0].getCreditMax()+","+
                            localA[i][0].getCheck().get_Account_Number()+","+
                            localA[i][0].getCheck().get_Starting_Balance()+","+
                            localA[i][0].getSave().get_Account_Number()+","+
                            localA[i][0].getSave().get_Starting_Balance()+","+
                            localA[i][0].getCred().get_Account_Number()+","+
                            localA[i][0].getCred().get_Starting_Balance()+","+
                            "\n"
                    );
                } else {
                    break;
                }

            }
            logger.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("could not write FOol");

        }
    }

    /**
     * Writes success messages to the .txt file
     *
     * @param accountIn the account that has successfully completed the action
     * @param action    the action taken
     * @param exitIn    the amount deposited
     */
    public static void printSuccessMessages(Account accountIn, String action, double exitIn) {
        if (action.equals("deposited")) {
            if (accountIn.get_Account_Number() < 2000) {
                System.out.println(accountIn.get_First_Name() + " " + accountIn.get_Last_Name() + " has successfully " + action + ": $" + exitIn + " into their Checking Account.");
                logUserAction(accountIn.get_First_Name() + " " + accountIn.get_Last_Name() + " has successfully " + action + ": " + exitIn + " into their Checking Account.");

            } else if (accountIn.get_Account_Number() < 3000) {
                System.out.println(accountIn.get_First_Name() + " " + accountIn.get_Last_Name() + " has successfully " + action + ": $" + exitIn + " into their Savings Account.");
                logUserAction(accountIn.get_First_Name() + " " + accountIn.get_Last_Name() + " has successfully " + action + ": $" + exitIn + " into their Savings Account.");
            } else {
                System.out.println(accountIn.get_First_Name() + " " + accountIn.get_Last_Name() + " has successfully " + action + ": $" + exitIn + " into their Credit Account.");
                logUserAction(accountIn.get_First_Name() + " " + accountIn.get_Last_Name() + " has successfully " + action + ": $" + exitIn + " into their Credit Account.");
            }

        } else {
            if (accountIn.get_Account_Number() < 2000) {
                System.out.println(accountIn.get_First_Name() + " " + accountIn.get_Last_Name() + " has successfully " + action + ": $" + exitIn + " from their Checking Account.");
                logUserAction(accountIn.get_First_Name() + " " + accountIn.get_Last_Name() + " has successfully " + action + ": $" + exitIn + " from Checking Account.");

            } else if (accountIn.get_Account_Number() < 3000) {
                System.out.println(accountIn.get_First_Name() + " " + accountIn.get_Last_Name() + " has successfully " + action + ": $" + exitIn + " from their Savings Account.");
                logUserAction(accountIn.get_First_Name() + " " + accountIn.get_Last_Name() + " has successfully " + action + ": $" + exitIn + " from their Savings Account.");
            }


        }
    }

    /**Writes the bank statement
     * @param name the name of the person youre writing the bank statement for
     * @param personIn the person who youre writing bank statement for
     * @param accountType an int representing the account type (1 = checking, 2=savings, 3= credit)
     */
    public static void createBankStatement(String name, Customer personIn, String accountType) {
        String bankStatementType = "";

        switch (accountType){
            case "1":
                bankStatementType = "'s Checking Account Bank Statement.txt";
                break;
            case "2":
                bankStatementType = "'s Savings Account Bank Statement.txt";
                break;
            case  "3":
                bankStatementType = "'s Credit Account Bank Statement.txt";
                break;
            default:
                System.out.println("Something went very wrong went assigning the nonsense");
        }

        try {
            File log = new File(name + bankStatementType);
            log.createNewFile();
            FileWriter logger = new FileWriter(name + bankStatementType, false);

            logger.write("DATE: \t\t\t\t" + java.time.LocalDate.now() + "\n");
            logger.write("NAME: \t\t\t\t" + personIn.getFirstName() + " " + personIn.getLastName() + "\n");
            logger.write("ADDRESS: \t\t\t\t" + personIn.getAddress() + "\n");
            logger.write("PHONE NUMBER: \t\t\t\t" +personIn.getPhoneNumber() +"\n");
            logger.write("DATE OF BIRTH: \t\t\t\t" + personIn.getDateOfBirth()+"\n");
            switch (accountType){
                case "1":
                    logger.write("ACCOUNT NUMBER: \t\t\t\t" + personIn.getCheck().get_Account_Number() + "\n");
                    break;
                case "2":
                    logger.write("ACCOUNT NUMBER: \t\t\t\t" + personIn.getSave().get_Account_Number() + "\n");
                    break;
                case "3":
                    logger.write("ACCOUNT NUMBER: \t\t\t\t" + personIn.getCred().get_Account_Number() + "\n");
                    break;
                default:
                    System.out.println("Something went wrong with writing the account number.");
            }
            logger.write("CREDIT MAX: \t\t\t\t" + personIn.getCreditMax() + "\n");
            logger.write("______________________________________________________________________" + "\n");
            logger.write("\n");
            logger.write("User Transactions \n");
            logger.write("________________________________________________________________________ \n");

            switch (accountType){
                case "1":
                    logger.write("Starting Balance: \t\t\t\t$" + personIn.getCheck().getBankStatement().getStartingBalance() +"\n");
                    for(int i = 0; i < personIn.getCheck().getBankStatement().getUserActions().size(); i++){
                        logger.write("\t\t\t\t\t\t\t\t"+personIn.getCheck().getBankStatement().getUserActions().get(i) +"\n");
                    }
                    logger.write("Ending Balance: \t\t\t\t$" + personIn.getCheck().getBankStatement().getEndingBalance() + "\n");
                    break;
                case "2":
                    logger.write("Starting Balance: \t\t\t\t$" + personIn.getSave().getBankStatement().getStartingBalance() +"\n");
                    for(int i = 0; i < personIn.getSave().getBankStatement().getUserActions().size(); i++){
                        logger.write("\t\t\t\t\t\t\t\t" +personIn.getSave().getBankStatement().getUserActions().get(i) + "\n");
                    }
                    logger.write("Ending Balance: \t\t\t\t$" + personIn.getSave().getBankStatement().getEndingBalance() + "\n");
                    break;
                case  "3":
                    logger.write("Starting Balance: \t\t\t\t$" + personIn.getCred().getBankStatement().getStartingBalance() +"\n");
                    for(int i = 0; i < personIn.getCred().getBankStatement().getUserActions().size(); i++){
                        logger.write("\t\t\t\t\t\t\t\t" + personIn.getCred().getBankStatement().getUserActions().get(i) +"\n");
                    }
                    logger.write("Ending Balance: \t\t\t\t$" + personIn.getCred().getBankStatement().getEndingBalance() + "\n");
                    break;
                default:
                    System.out.println("Something went very wrong when writing the actions");
            }

            logger.flush();
            logger.close();

        } catch (Exception e) {
        }

    }
}
