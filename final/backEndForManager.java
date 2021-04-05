
import java.util.*;
/** Will provide special handlings in the case that it is a bank manager that logs in and not a regular user
 *
 */
public class backEndForManager {
    /**Prints a main menu for bank managers and calls upon the other necessary functions
     * @param dataBaseIn the database the manager is going to be accessing
     * @return int to indicate user exit
     */
    public static int homeScreen(PersonDatabase dataBaseIn){
        boolean actionCompleted = false;
        //while loop lets the bank manager return main again and again until they want to exit
        //when they exit a -1 is returned terminating the program according to the logic of runBank.java
        while(!actionCompleted) {
            try {
                System.out.println("Hello manager what would you like to do today?");
                System.out.println("1.)Check an account's balance");
                System.out.println("2.) Print all Account Balances");
                System.out.println("3.) Create A Bank Statement");
                System.out.println("4.) Exit ");

                //creates a scanner to handle user input that will be used throughout this method
                Scanner userInput = new Scanner(System.in);
                int action = userInput.nextInt();
                actionCompleted = bankManagerActions(action, dataBaseIn);

            }
            catch (Exception e) {
                System.out.println("Sorry I don't understand that command, please try again.");
                writeUserActions.logUserAction("Bank Manager entered an invalid action.");

            }
        }
        return 0;
    }

    /** Lets the bank manager print all the info on an account
     * @param dataBaseIn the database the manager is going to be searching in
     *
     */
    public static void checkAnAccountsBalance(PersonDatabase dataBaseIn){
        boolean actionCompleted = false;
        while(!actionCompleted){

            Scanner userInput = new Scanner(System.in);
            String accountName;
            try{
                System.out.println("Please enter the name of the person whose account you'd like to know the balance of. (Enter -1 to exit)");
                accountName = userInput.nextLine();
                Person user = backEnd.findAccount(accountName, dataBaseIn);

                //Allows the bank manager to return to the main menu
                if (accountName.equals("-1")){
                    break;
                }
                //null indicates that there's no account with that number and there was an error
                if (user == null) {
                    //null indicates an error
                    //in the case of putting in something that doesn't work an error message is printed
                    //and the loop repeats so that the user can still enter their account number
                    System.out.println("Sorry we couldn't find that account, please try again. (Or Enter -1 to exit)");
                    backEnd.noAccountFound();
                    continue;
                }
                //Prints a message of success including the balance
                System.out.println("Here is all the information related to that account.");
                user.printPersonInfo();
                //Logs the bank managers Action
                writeUserActions.logUserAction("Bank Manager checked the balance of " +user.getFirstName() + " " + user.getLastname() + "'s " + " Account Balances");
                actionCompleted = true;
        }
            catch(Exception e){

                System.out.println("Sorry we could not find an account associated with that number. Please try again (Or enter -1 to exit) ");
                continue;
            }
    }
    }

    /** checks if an account submitted to it is a checking/savings/credit account based on account number
     * @param accountIn the account he bank manager wants to print info for
     * @return a String indicating the account type
     */
    public static String checkIfCheckingSavingOrDebit(Account accountIn){
        if (accountIn.get_Account_Number() < 2000){
            return "Checking";
        }
        else if(accountIn.get_Account_Number() < 3000){
            return "Savings";
        }
        else{
            return "Credit";
        }
    }

    /**prints all the account info for all of the accounts
     * @param databaseIn tje database the bank manager wants to print
     */
    public static void printAllAccountsBalances(PersonDatabase databaseIn){
        databaseIn.print_Data_Base();
    }

    /**alows the bank manager to create a bank statement
     * @param databaseIn the database of people the bank manager can create a statement for
     */
    public static void createABankStatement(PersonDatabase databaseIn){
        boolean actionCompleted = false;
        while(!actionCompleted){

            Scanner userInput = new Scanner(System.in);
            String accountName;
            try{
                System.out.println("Please enter the name of the person whose account you'd like to know the balance of. (Enter -1 to exit)");
                accountName = userInput.nextLine();
                Customer user = backEnd.findAccount(accountName, databaseIn);

                //Allows the bank manager to return to the main menu
                if (accountName.equals("-1")){
                    break;
                }
                //null indicates that there's no account with that number and there was an error
                if (user == null) {
                    //null indicates an error
                    //in the case of putting in something that doesn't work an error message is printed
                    //and the loop repeats so that the user can still enter their account number
                    System.out.println("Sorry we couldn't find that account, please try again. (Or Enter -1 to exit)");
                    backEnd.noAccountFound();
                    continue;
                }
                while (true) {
                System.out.println("Which of their accounts would you like to create a statement for? Enter -1 to exit.");
                System.out.println("1.) Checking");
                System.out.println("2.) Savings");
                System.out.println("3.) Credit");
                accountName = userInput.nextLine();
                if (accountName.equals(-1))
                    break;

                    switch (accountName) {
                        case "1":
                            writeUserActions.createBankStatement(user.getFirstName() +" "+ user.getLastname(), user, "1");
                            break;
                        case "2":
                            writeUserActions.createBankStatement(user.getFirstName() + " "+user.getLastname(), user, "2");
                            break;
                        case "3":
                            writeUserActions.createBankStatement(user.getFirstName() + " "+user.getLastname(), user, "3");
                            break;
                        default:
                            System.out.println("Sorry I don't recognize that option. Try something else.");
                            writeUserActions.logUserAction("Bank Manager entered an invalid input.");
                            continue;
                    }
                    break;
                }

                //Logs the bank managers Action
                writeUserActions.logUserAction("Bank Manager created a bank statement for" +user.getFirstName() + " " + user.getLastname());
                actionCompleted = true;
            }
            catch(Exception e){

                System.out.println("Sorry we could not find an account associated with that number. Please try again (Or enter -1 to exit) ");
                continue;
            }
        }

    }

    /** Calls appropriate methods depending on what action the manager wants to take
     * @param actionIn int indicating what the bank manager wants to do
     * @param dataBaseIn the database the bank manager will be handling
     * @return a boolean indicating whether or not the user has exited or will return to the main menu
     */
    public static boolean bankManagerActions(int actionIn, PersonDatabase dataBaseIn){

        switch (actionIn){
            case 1:
                checkAnAccountsBalance(dataBaseIn);
                return false;
            case 2:
                printAllAccountsBalances(dataBaseIn);
                writeUserActions.logUserAction("Bank Manager printed all the database information.");
                return false;
            case 3:
                createABankStatement(dataBaseIn);
                return false;
            case 4:
                writeUserActions.logUserAction("Bank Manager has exited");
                return true;

            default:
                System.out.println("Sorry I don't understand that command, please try again.");
                writeUserActions.logUserAction("Bank Manager entered an invalid action.");
        }
        return false;
    }
}
