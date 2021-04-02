
import java.io.IOException;
import java.util.*;
/**
 *This Class is meant to handle the "heavy lifting" of the PA and call the appropriate functions depending on what action
 * the user wants to undertake.
 */
public class backEnd {
    /**This method assumes that an account number and a hashTable full of Accounts have been provided and will use the
     * account number to conduct a search in the hashTable and return an account corresponding with the account number
     * @param accountNumberIn The user's account number.
     * @param databaseIn The database that the user's account exists in, assuming it does exist
     * @return An Account object
     */
    public static Account findAccount(Long accountNumberIn, hashTable databaseIn){
        try {
            int key = -1;
            if(accountNumberIn < 2000) {key = (int) (accountNumberIn -1000);}
            else if(accountNumberIn < 3000){key = (int) (accountNumberIn-1900);}
            else{key = (int)(accountNumberIn-2800);}
            return databaseIn.get_table()[key][0];
        }
        catch(Exception e){
            return null;
        }
    }

    /**this method finds the account based off name
     * @param name the user's name
     * @param databaseIn the database of people
     * @return will return a Person which will will be able to provide printable account info
     */
    public static Person findAccount(String name, PersonDatabase databaseIn){
        String [] formattedName = name.split(" ");
        for(int i = 0; i < databaseIn.get_table().length;i++){
            if(databaseIn.get_table()[i][0] != null){
               //System.out.println((databaseIn.get_table()[i][0].getFirstName()+ " " +databaseIn.get_table()[i][0].getLastName()));
               //System.out.println(name);
                if ((databaseIn.get_table()[i][0].getFirstName()+ " " +databaseIn.get_table()[i][0].getLastName()).equalsIgnoreCase(name.toLowerCase())){
                    return databaseIn.get_table()[i][0];
                }
                else {
                    continue;
                }
            }
            else{
                continue;
            }
        }
        return null;
    }



    public static void deposit(String dep, Person person, double dammount){
        switch (dep) {
            case "1":
                person.getCheck().deposit(dammount);
                System.out.println(person.getFirstName() +" deposited $"+ dammount +" into checking new balance is $" +person.getCheck().get_Starting_Balance());


                try {//writes transaction to log
                    //FileWriter out = new FileWriter("transLog.txt", false);
                    writeUserActions.logUserAction(person.getFirstName() +" deposited $"+ dammount +" into checking new balance is $" +person.getCheck().get_Starting_Balance());
                    writeUserActions.logUserAction("\r\n");

                }
                catch(Exception e){
                    System.out.println("Error in translog");
                }
                break;
            case "2":
                person.getSave().deposit(dammount);
                System.out.println(person.getFirstName() +" deposited $"+ dammount +" into saving new balance is $" +person.getSave().get_Starting_Balance());


                try {//writes transaction to log
                    //FileWriter out = new FileWriter("transLog.txt", false);
                    writeUserActions.logUserAction(person.getFirstName() +" deposited $"+ dammount +" into saving new balance is $" +person.getSave().get_Starting_Balance());
                    writeUserActions.logUserAction("\r\n");

                }
                catch(Exception e){
                    System.out.println("Error in translog");
                }
                break;
            case "3":
                if(-dammount >= person.getCred().get_Starting_Balance() ) {

                    person.getCred().deposit(dammount);
                    System.out.println(person.getFirstName()+ " deposited $" + dammount + " into credit new balance is $" + person.getCred().get_Starting_Balance());


                    try {//writes transaction to log
                        //FileWriter out = new FileWriter("transLog.txt", false);
                        writeUserActions.logUserAction(person.getFirstName() +" deposited $"+ dammount +" into credit new balance is $" +person.getCred().get_Starting_Balance());
                        writeUserActions.logUserAction("\r\n");

                    }
                    catch(Exception e){
                        System.out.println("Error in translog");
                    }
                }
                else{
                    System.out.println("Amount is too large to deposit in credit ");
                }
                break;
            default:
                System.out.println("Sorry that isnt an option");
                break;
        }
    }

    //handles withdrawals uses the exact same logic as deposit(), but just changes the method you call
    /**withdrawal(Account checkingIn) will allow the user to withdrawal funds from their account provided it's a checking/savings account
     * uses the exact same logic as findAccount just calls a different method
     * @param  checkingIn the checking account that needs to be withdrawn from
     * @return int will indicate the user is finished with this paticular action. Either through successful execution or exiting.
     */
    public static int withdrawal(Account checkingIn){

        Scanner userInput = new Scanner(System.in);
        //this boolean will be used to control the while loop and will remain false until the action has been completed or cancelled
        //once it becomes true the while loop will terminate and the user will return to main menu
        boolean actionCompleted = false;
        //will continue to allow the user to try and enter an amount until they either complete their transaction
        //or else choose to exit back to main menu
        while(!actionCompleted ) {
            System.out.println("How much would you like to withdrawal today? (Enter -1 to exit back to main menu)");
            try {
                //if the user chooses to exist by putting in -1 they return to the main menu where they can enter a new action
                double exit = userInput.nextDouble();
                if (exit == -1){
                    return 0;
                }
                //calls the checking account's deposit function and updates
                double newBalance = checkingIn.withdrawal(exit);
                //if they try to input a negative number or try to withdraw more than they have it'll give them an appropriate message and restart the loop as to
                //give them another try
                if(newBalance == -1){continue; }
                writeUserActions.printSuccessMessages(checkingIn, "withdrawn", exit);
                //breaks loop now that the action has been successfully completed
                actionCompleted = true;
            } catch (Exception e) {
                //error message for the benefit of the user
                System.out.println("Sorry I didn't understand that. Please enter a dollar amount or exit.");
                //logs the invalid command
                invalidCommand(checkingIn);
                //resets scanner to be used in next iteration
                userInput.nextLine();
                //breaks the while loop and returns the user to the begining of the deposit menu
                continue;
            }
        }
        return 0;
    }

    /**Invalid command for when you have an bank account and not a Person
     * @param accountIn the account of the user
     */
    public static void invalidCommand(Account accountIn){
        writeUserActions.logUserAction("User: " + accountIn.get_First_Name() + " " + accountIn.get_Last_Name() +" entered an invalid command.");
    }

    /**Handles transfers between 2 individuals in the case where we only have a people database
     * @param checkingIn the account the user is transferring from
     * @param people the database that all the bank users are stored in
     * @return retirms an int that will return the user to the main menu
     */
    public static int transfer(Account checkingIn, PersonDatabase people) {
        boolean actionCompleted = false;
        boolean amountUnderstood = false;
        double amountToBeDeposited = 0.00;
        double checkIfTheyHaveTheFunds = -2;
        double checkIfCreditBalanceIsTooSmall = -2;


        while (actionCompleted == false) {
            try {
                Scanner otherAccountInfo = new Scanner(System.in);
                System.out.println("Please enter the name of the person that you would like to transfer your funds to. (Enter -1 to exit)");
                String userInput = otherAccountInfo.nextLine();
                //this if statement lets the user exit
                if (userInput.equals("-1")) {
                    return 0;
                }
                Person otherUser = findAccount(userInput, people);
                //this if statement makes sure that we actually found the other person, otherwise it starts over
                if (otherUser == null) {
                    //null indicates account was not found and transfer cannot be performed
                    System.out.println("Sorry there's no user with that name. Please Try again or exit.");
                    //continue goes to the next iteration of the loop and lets the user try again or exit
                    continue;
                }
                while (actionCompleted == false) {
                    System.out.println("What account belonging to " + otherUser.getFirstName() + " " + otherUser.getLastName() + " would you like to deposit into?");
                    System.out.println("1.) Their Checking Account");
                    System.out.println("2.) Their Savings Account.");
                    System.out.println("3.) Their Credit Account");
                    try {
                        userInput = otherAccountInfo.nextLine();

                        if (userInput.equals("1")) {
                            if (otherUser.getCheck().get_Account_Number() == checkingIn.get_Account_Number()) {
                                //user is not going to be allowed to transfer money form their own account into their own account
                                System.out.println("Sorry you cannot transfer funds from this account into this account");
                                writeUserActions.logUserAction(checkingIn.get_First_Name() + " " + checkingIn.get_Last_Name() + " tried to transfer funds from account: " + checkingIn.get_Account_Number() + "to account: " + otherUser.getCheck().get_Account_Number());
                                continue;
                            }
                        } else if (userInput.equals("2")) {
                            if (otherUser.getSave().get_Account_Number() == checkingIn.get_Account_Number()) {
                                //user is not going to be allowed to transfer money form their own account into their own account
                                System.out.println("Sorry you cannot transfer funds from this account into this account");
                                writeUserActions.logUserAction(checkingIn.get_First_Name() + " " + checkingIn.get_Last_Name() + " tried to transfer funds from account: " + checkingIn.get_Account_Number() + "to account: " + otherUser.getSave().get_Account_Number());
                                continue;
                            }
                        } else if (userInput.equals("3")) {
                            if (otherUser.getCred().get_Account_Number() == checkingIn.get_Account_Number()) {
                                //user is not going to be allowed to transfer money form their own account into their own account
                                System.out.println("Sorry you cannot transfer funds from this account into this account");
                                writeUserActions.logUserAction(checkingIn.get_First_Name() + " " + checkingIn.get_Last_Name() + " tried to transfer funds from account: " + checkingIn.get_Account_Number() + "to account: " + otherUser.getCred().get_Account_Number());
                                continue;
                            }
                        }


                        switch (userInput) {
                            case "1":
                                while (amountUnderstood == false) {
                                    try {
                                        //gets the amount the user wants to transfer
                                        System.out.println("Please enter the amount you would like to transfer to " + otherUser.getFirstName() + " " + otherUser.getLastName() + " Enter -1 to cancel");
                                        amountToBeDeposited = otherAccountInfo.nextDouble();
                                        //line below lets user cancel transaction
                                        if (amountToBeDeposited == -1) {
                                            return 0;
                                        }
                                        //resets the  scanner
                                        otherAccountInfo.nextLine();
                                        //uses the previously created withdrawal method to avoid rewrites :)
                                        checkIfTheyHaveTheFunds = checkingIn.withdrawal(amountToBeDeposited);
                                        //the below if statement is meant to catch if the account holder trying to transfer funds even has enough money
                                        //if they don't then it breaks the loop and allows them to either try again or exit
                                        if (checkIfTheyHaveTheFunds == -1) {
                                            continue;
                                        }
                                        amountUnderstood = true;
                                    } catch (Exception e) {
                                        otherAccountInfo.nextLine();
                                        invalidCommand(checkingIn);
                                        System.out.println("Sorry I don't recognize that amount, please try again or enter -1 to exit.");
                                        continue;
                                    }
                                }

                                System.out.println(amountToBeDeposited + " has been successfully withdrawn from account number: " + checkingIn.get_Account_Number() + " belonging to " + checkingIn.get_First_Name() + " " + checkingIn.get_Last_Name());
                                //writes to file
                                writeUserActions.logUserAction(amountToBeDeposited + " has been successfully withdrawn from account number: " + checkingIn.get_Account_Number() + " belonging to " + checkingIn.get_First_Name() + " " + checkingIn.get_Last_Name());

                                System.out.println(amountToBeDeposited + " has been successfully deposited into account number: " + otherUser.getCheck().get_Account_Number() + " belonging to " + otherUser.getFirstName() + " " + otherUser.getLastName());
                                //writes to file
                                writeUserActions.logUserAction(amountToBeDeposited + " has been successfully deposited into account number: " + otherUser.getCheck().get_Account_Number() + " belonging to " + otherUser.getFirstName() + " " + otherUser.getLastName());
                                //uses the previously created deposit method to avoid rewrites :)
                                otherUser.getCheck().deposit(amountToBeDeposited);
                                actionCompleted = true;
                                break;
                            case "2":
                                while (amountUnderstood == false) {
                                    try {
                                        //gets the amount the user wants to transfer
                                        System.out.println("Please enter the amount you would like to transfer to " + otherUser.getFirstName() + " " + otherUser.getLastName() + " Enter -1 to cancel");
                                        amountToBeDeposited = otherAccountInfo.nextDouble();
                                        //line below lets user cancel transaction
                                        if (amountToBeDeposited == -1) {
                                            return 0;
                                        }
                                        //resets the  scanner
                                        otherAccountInfo.nextLine();
                                        //uses the previously created withdrawal method to avoid rewrites :)
                                        checkIfTheyHaveTheFunds = checkingIn.withdrawal(amountToBeDeposited);
                                        //the below if statement is meant to catch if the account holder trying to transfer funds even has enough money
                                        //if they don't then it breaks the loop and allows them to either try again or exit
                                        if (checkIfTheyHaveTheFunds == -1) {
                                            continue;
                                        }
                                        amountUnderstood = true;
                                    } catch (Exception e) {
                                        otherAccountInfo.nextLine();
                                        invalidCommand(checkingIn);
                                        System.out.println("Sorry I don't recognize that amount, please try again or enter -1 to exit.");
                                        continue;
                                    }
                                }

                                System.out.println(amountToBeDeposited + " has been successfully withdrawn from account number: " + checkingIn.get_Account_Number() + " belonging to " + checkingIn.get_First_Name() + " " + checkingIn.get_Last_Name());
                                //writes to file
                                writeUserActions.logUserAction(amountToBeDeposited + " has been successfully withdrawn from account number: " + checkingIn.get_Account_Number() + " belonging to " + checkingIn.get_First_Name() + " " + checkingIn.get_Last_Name());

                                System.out.println(amountToBeDeposited + " has been successfully deposited into account number: " + otherUser.getSave().get_Account_Number() + " belonging to " + otherUser.getFirstName() + " " + otherUser.getLastName());
                                //writes to file
                                writeUserActions.logUserAction(amountToBeDeposited + " has been successfully deposited into account number: " + otherUser.getSave().get_Account_Number() + " belonging to " + otherUser.getFirstName() + " " + otherUser.getLastName());
                                //uses the previously created deposit method to avoid rewrites :)
                                otherUser.getSave().deposit(amountToBeDeposited);
                                actionCompleted = true;
                                break;
                            case "3":
                                while (amountUnderstood == false) {
                                    try {
                                        //gets the amount the user wants to transfer
                                        System.out.println("Please enter the amount you would like to transfer to " + otherUser.getFirstName() + " " + otherUser.getLastName() + " Enter -1 to cancel");
                                        amountToBeDeposited = otherAccountInfo.nextDouble();
                                        //line below lets user cancel transaction
                                        if (amountToBeDeposited == -1) {
                                            return 0;
                                        }
                                        //resets the  scanner
                                        otherAccountInfo.nextLine();
                                        //uses the previously created withdrawal method to avoid rewrites :)
                                        checkIfTheyHaveTheFunds = checkingIn.withdrawal(amountToBeDeposited);
                                        //the below if statement is meant to catch if the account holder trying to transfer funds even has enough money
                                        //if they don't then it breaks the loop and allows them to either try again or exit
                                        if (checkIfTheyHaveTheFunds == -1) {
                                            continue;
                                        }
                                        checkIfCreditBalanceIsTooSmall = otherUser.getCred().deposit(amountToBeDeposited);
                                        if (checkIfCreditBalanceIsTooSmall == -1){
                                            continue;
                                        }
                                        amountUnderstood = true;
                                    } catch (Exception e) {
                                        System.out.println("Sorry I don't recognize that amount, please try again or enter -1 to exit.");
                                        otherAccountInfo.nextLine();
                                        invalidCommand(checkingIn);
                                        continue;
                                    }
                                }

                                System.out.println(amountToBeDeposited + " has been successfully withdrawn from account number: " + checkingIn.get_Account_Number() + " belonging to " + checkingIn.get_First_Name() + " " + checkingIn.get_Last_Name());
                                //writes to file
                                writeUserActions.logUserAction(amountToBeDeposited + " has been successfully withdrawn from account number: " + checkingIn.get_Account_Number() + " belonging to " + checkingIn.get_First_Name() + " " + checkingIn.get_Last_Name());

                                System.out.println(amountToBeDeposited + " has been successfully deposited into account number: " + otherUser.getCred().get_Account_Number() + " belonging to " + otherUser.getFirstName() + " " + otherUser.getLastName());
                                //writes to file
                                writeUserActions.logUserAction(amountToBeDeposited + " has been successfully deposited into account number: " + otherUser.getCred().get_Account_Number() + " belonging to " + otherUser.getFirstName() + " " + otherUser.getLastName());
                                //uses the previously created deposit method to avoid rewrites :)
                                otherUser.getCred().deposit(amountToBeDeposited);
                                actionCompleted = true;
                                break;
                            default:
                                System.out.println("Sorry I didn't understand that please try again or enter -1 to exit.");
                                continue;

                        }
                    } catch (Exception e) {
                        System.out.println("Sorry I didn't understand that. Please try again or exit.");
                        invalidCommand(checkingIn);
                        continue;
                    }

                }
            }
            catch(Exception e){
                System.out.println("Sorry I didn't understand that. Please try again or exit.");
                invalidCommand(checkingIn);
                continue;
            }

        }
        return 0;
    }



    /** Writes an error to a file by calling upon writeUserActions.logUserActions()
     * @param accountIn The account that entered the invalid command
     * Uses account holder's account to get info like first name, last name, and account type
     */
    //handles invalid commands
    public static void invalidCommand(Person accountIn){
        writeUserActions.logUserAction("User: " + accountIn.getFirstName() + " " + accountIn.getLastName() +" entered an invalid command.");
    }



    //handles no account found

    /**Writes to the action long that someone entered an incorrect account number
     *
     */
    public static void noAccountFound(){
        writeUserActions.logUserAction("Someone put in an invalid account number." );
    }


    /**Writes user exit to the action log
     *
     * @param user
     * Takes an account object to log which user specifically exited
     */
    public static void userExit(Person user){
        writeUserActions.logUserAction("User : " + user.getFirstName() + " "+ user.getLastName() + " has exited.");

    }
    
    /**Prints the appropriate secondary menu and calls the appropriate method
     * @param user the person who is currently trying to do things to their account
     * @param actionIn the action the person wants to take
     * @param people the database of people
     * @return returns an int that allows the user to continue or exit depending on the user's desire
     */
    public static int secondaryMenu(Person user, int actionIn, PersonDatabase people) {
        Scanner userInput = new Scanner(System.in);
        String accountAction  = "-10000";
        double amount = 0;
        while (!accountAction.equals("-1")){
            try {

                switch (actionIn) {
                    //handles deposits
                    case 1:
                        System.out.println("What Account would you like to deposit into? (Enter -1 to Exit)");
                        System.out.println("1.) Checking");
                        System.out.println("2.) Savings");
                        System.out.println("3.) Credit");
                        accountAction = userInput.nextLine();
                        if (accountAction.equals("-1"))
                            return 0;
                        if(accountAction.equals("1") || accountAction.equals("2") || accountAction.equals("3")) {
                            System.out.println("How much would you like to deposit? (Enter -1 to Exit)");
                            amount = userInput.nextDouble();
                            if (accountAction.equals("-1"))
                                return 0;
                            try {

                                deposit(accountAction, user, amount);
                                return 0;
                            } catch (Exception e) {
                                System.out.println("Sorry you either entered an invalid amount or an invalid action, please try again.");
                                writeUserActions.logUserAction(user.getFirstName() + user.getLastName() + "entered an invalid action/amount.");
                                continue;
                            }
                        }
                        else if(accountAction.equals(-1)){
                            return 0;
                        }
                        else{
                            System.out.println("Sorry that's an invalid action. Please Try again");
                            writeUserActions.logUserAction(user.getFirstName() + user.getLastName() + "entered an invalid action.");
                            continue;
                        }

                        //returns 0 after action completed to return them to main menu
                        //handles withdrawals
                    case 2:
                        System.out.println("What Account would you like to withdraw from? (Enter -1 to Exit)");
                        System.out.println("1.) Checking");
                        System.out.println("2.) Savings");
                        accountAction = userInput.nextLine();
                        switch (accountAction) {
                            case "1":
                                if(user.getCheck() == null){
                                    System.out.println("Sorry it looks like you don't have a checking account. Please select something else. ");
                                    return 0;
                                }
                                return withdrawal(user.getCheck());
                            case "2":
                                return withdrawal(user.getSave());
                            case "-1":
                                return 0;
                            default:
                                System.out.println("Sorry please enter a valid action or exit by entering -1.");
                                continue;
                        }
                        //returns 0 after action completed to return them to main menu
                        //handles transfers
                    case 3:
                        System.out.println("What Account would you like to transfer From?");
                        System.out.println("1.) Checking");
                        System.out.println("2.) Savings");

                        accountAction = userInput.nextLine();
                        switch (accountAction) {
                            case "1":
                                if(user.getCheck() == null){
                                    System.out.println("Sorry it looks like you don't have a checking account.");
                                    return 0;
                                }
                                return transfer(user.getCheck(), people);
                            case "2":
                                return transfer(user.getSave(), people);
                            case "-1":
                                return 0;
                            default:
                                System.out.println("Sorry please enter a valid action or exit by entering -1.");
                                continue;

                        }

                        //returns 0 after action completed to return them to main menu
                    case 4:
                        //handles checking account balance
                        user.printAccountInfo();
                        writeUserActions.logUserAction(user.getFirstName() + " " + user.getLastName() + " checked their account balances.");
                        //for this one I have to manually return a 0 so that they can go back to main menu
                        return 0;
                    //handles exiting the program
                    case 5:
                        userExit(user);
                        //returns a -1 because the userInterface will terminate if it is returned a negative 1 all other values will just return to main menu
                        return -1;
                }
            } catch (Exception e) {
                System.out.println("Sorry I didn't understand that command please try again or enter -1 to exit.");
                invalidCommand(user);
                userInput.nextLine();
                continue;
            }
        }
        return 0;
    }

    /**Prints the menu, moved this action to the backend for improved functionality
     * @param user the person who has logged into the account
     * @param people the database of people
     * @return an int representing whether or not the user wants to continue or exit
     */
    public static int printInitialMenu(Person user, PersonDatabase people){
        Scanner userInput = new Scanner(System.in);
        try {

            System.out.println("Hello, " + user.getFirstName() + " " + user.getLastName() + " what would you like to do? (Please enter a number)");
            System.out.println("1.) Deposit");
            System.out.println("2.) Withdrawal");
            System.out.println("3.) Transfer Funds");
            System.out.println("4.) Check Balance");
            System.out.println("5.) Exit");

            int actionIn = userInput.nextInt();
            actionIn = secondaryMenu(user, actionIn, people);

            return actionIn;
        }catch(Exception e){
            System.out.println("Sorry I didn't understand that action please try again.");
            return 0;
        }
    }
}
