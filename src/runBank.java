



import java.util.Scanner;

/**
 * @author Luis David Davila
 * @author LDDAVILA@miners.utep.edu
 * This class runs the whole file and contains the user interface for the normal users
 * also calls the user interface for the bank manager
 */
public class runBank {

    /**
     * The main method will call readFile to create a database of People and a database of Accounts
     * It will store those databases to be used later
     * Using a while loop which will only terminate in the case of user exit, this method will ask the user for an account number
     * or ask for indication that the user is a bank manager
     * if they are a bank manager then it will call backEndForManager.java which will have a special menu and implementation
     * If they have a checking/saving account then they will get full functionality and the regular menu and the function will call backEnd.userAccountActions()
     * If they have a credit account then they will get a special menu and backEnd.creditAccountActions()
     * If no account can be found then it will give a failure message and ask for new input
     * Will terminate once a user has logged in and decided to exit
     * @param args beginner
     */
    public static void main(String[] args) {
        int actionIn = 0;
        //the while loop makes it so that the user can try put in the account number multiple times
        writeUserActions.makeLog("userActions.txt");
        PersonDatabase people = readFile.createPersonDatabase();
        //hashTable dataBase = readFile.createAccountDatabase();
        transactionReader.reader(people);


        while (actionIn != -1) {


            try {
                Scanner userInput = new Scanner(System.in);


                //asks user for account number.
                System.out.println("Please Enter Your First and Last Name Separated By a Space. If you're a bank manager please enter 9. If you want to create a new account enter 8. Enter & to close the program.");

                String userName = userInput.nextLine();

                //calls backEndForManager so the user can use the manager functionality
                if (userName.equals("9")){
                    actionIn = backEndForManager.homeScreen(people);
                    continue;
                }
                //Calls createANewAccount so that the user can create a new account from the console
                if(userName.equals("8")){
                    createANewAccount.createNew(people);
                    continue;
                }
                if(userName.equals("&")){
                    break;}

                Person user = backEnd.findAccount(userName, people);



                if (user == null) {
                    //null indicates an error
                    //in the case of putting in something that doesn't work an error message is printed
                    //and the loop repeats so that the user can still enter their account number
                    System.out.println("Sorry we couldn't find your account, please try again");
                    backEnd.noAccountFound();
                    continue;
                }
                while (actionIn == 0) {
                    //updated this so print menu is done in the backend file which is called below
                    actionIn = backEnd.printInitialMenu(user, people);

                }
                actionIn =0;



            }
            catch (Exception e) {
                writeUserActions.logUserAction("Someone input an invalid account number.");
                writeUserActions.logNewBalances(people);
                continue;
            }
            //creates the new balance sheet

            //exit message so the user can know they're logged out


        }
        writeUserActions.logNewBalances(people);

        System.out.println("Thank you for banking with us have a nice day.");
    }
}
