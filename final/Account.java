
/** This class is the parent class for all account types
 * @author Luis David Davila
 * Has attributes for a first name, a last name, and account number, and a starting balance
 */
public abstract class Account {
    private String firstName;
    private String lastName;
    private Long accountNumber;
    private double startingBalance;
    private BankStatement bankStatement = new BankStatement();




    //////////////////////Constructor////////////////

    /** default constuctor
     *
     */
    public Account(){
    }

    /**Useful constructor that will build the class
     * @param bankAccountData
     * Takes the array and uses it to assign appropriate place in array to the correct attribute
     * Assumes that info will be perfectly formatted with nothing missing
     *
     */
    public Account(String[] bankAccountData) {
        this.firstName = bankAccountData[0];
        this.lastName = bankAccountData[1];
        //converts the string into the expected long
        this.accountNumber = Long.parseLong(bankAccountData[2]);
        //converts string into expected double
        this.startingBalance = Double.parseDouble(bankAccountData[5]);
        //converts string into expected double, also removes the percent that is in the file

    }





    //////////////////SETTERS//////////////////////////

    /**sets the first name
     * @param name_In the first name of the user in the account
     */
    public void set_First_Name(String name_In){
        this.firstName = name_In;
    }

    /** sets the last name
     *
     * @param last_Name_In the last name of the user in the account
     */
    public void set_Last_Name(String last_Name_In){
        this.lastName = last_Name_In;
    }

    /**sets the account number
     *
     * @param account_Number_In the account number of the account
     */
    public void set_Account_Number(long account_Number_In){
        this.accountNumber = account_Number_In;
    }

    /**sets the balance
     * @param in_Starting_Balance the balance of the account
     */
    public void set_Starting_Balance(double in_Starting_Balance){
        this.startingBalance = in_Starting_Balance;
    }




    ////////////////GETTERS///////////////////////

    /** returns the first name
     *
     * @return String with the first name
     */
    public String get_First_Name(){
        return this.firstName;
    }
    /** returns the last name
     *
     * @return string with the last name
     */
    public String get_Last_Name(){
        return this.lastName;
    }
    /** returns the account number
     *
     * @return a long with the account number
     */
    public long get_Account_Number(){
        return this.accountNumber;
    }
    /** returns the first name
     *
     * @return a double with the balance
     */
    public double get_Starting_Balance(){
        return this.startingBalance;
    }

    /**returns the bank statement
     * @return the bank statement
     */
    public BankStatement getBankStatement(){return  this.bankStatement;}


    /** will print all of the info in the acocunt including all attributes
     *
     */
    public void print_Account_info(){
        System.out.print("Owner: " + this.firstName + " ");
        System.out.println(this.lastName);
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("Current Balance: $" + this.startingBalance);

    }

    /**checks the account balance
     * @return a double that has the account balance
     */
    public double check_Balance(){

        System.out.println("Your Current Balance Is: " +this.startingBalance);
        this.bankStatement.addUserTransaction("You checked your account balance on " + java.time.LocalDate.now());
        writeUserActions.logUserAction(this.firstName + " "+ this.lastName + " checked their account balances.");
        return this.get_Starting_Balance();
    }

    /**will reduce account balance
     *
     * @param withdrawalAmount the amount that the user wants to withdraw
     * @return a double to indicate faliure/success
     * Does not allow for withdrawing more than the user has
     * Updates balance
     * Prints error message
     */
    public double withdrawal(double withdrawalAmount){
        if (withdrawalAmount < 0){
            //returns a -1 so that backEnd knows there was an error and can alert the user of this so they can try again or exit as appropriate
            System.out.println("Sorry you can't withdraw a negative amount. Please Try again or exit.");
            this.bankStatement.addUserTransaction("You tried to withdraw a negative amount on " + String.valueOf(java.time.LocalDate.now()));
            return -1;
        }
        if (this.startingBalance - withdrawalAmount < 0){
            System.out.println("Sorry you don't have the funds to complete this transaction. Please try a lower amount or exit. ");
            this.bankStatement.addUserTransaction("You tried to withdraw more funds than you had on " + String.valueOf(java.time.LocalDate.now()));
            //returns a -1 so that backEnd knows there was an error and can alert the user of this so they can try again or exit as appropriate
            return -1;
        }
        this.startingBalance = this.startingBalance- withdrawalAmount;
        //returns a 0 to indicate that there was a successful transaction and allow the user to get the confirmation message and return to main menu
        this.bankStatement.addUserTransaction("You withdrew $"+ withdrawalAmount + " on " + String.valueOf(java.time.LocalDate.now()));
        writeUserActions.logUserAction(this.firstName + " "+ this.lastName + " withdrew $" + withdrawalAmount + " from their "+ backEndForTransactionReader.checkAccountType(this.accountNumber) + " account.");
        this.bankStatement.setEndingBalance(this.startingBalance);
        return 0;
    }



    /** will allow the user to deposit funds into their acocunt
     * @param depositAmount amount the user wants to deposit
     * @return
     * Does not allow for depositing negative amounts
     * Updates balance
     * Will print error message if it occurs
     */
    public double deposit(double depositAmount){
        if (depositAmount < 0){
            System.out.println("Sorry you can't deposit a negative amount. Please enter another mount or exit.");
            this.bankStatement.addUserTransaction("You tried to deposit a negative amount on " + String.valueOf(java.time.LocalDate.now()));
            writeUserActions.logUserAction(this.firstName + " "+ this.lastName + " failed to deposit into their "+ backEndForTransactionReader.checkAccountType(this.accountNumber) + " account because they entered a negative amount.");
            this.bankStatement.setEndingBalance(this.startingBalance);
            //returns a -1 so that backEnd knows there was an error and can alert the user of this so they can try again or exit as appropriate
            return -1;
        }
        this.startingBalance = this.startingBalance + depositAmount;
        this.bankStatement.addUserTransaction("You deposited $"+ depositAmount + " on " + String.valueOf(java.time.LocalDate.now()));
        writeUserActions.logUserAction(this.firstName + " "+ this.lastName + " deposited $" + depositAmount+ " into their "+ backEndForTransactionReader.checkAccountType(this.accountNumber) + " account.");
        this.bankStatement.setEndingBalance(this.startingBalance);
        //returns a 0 to indicate that there was a successful transaction and allow the user to get the confirmation message and return to main menu
        return 0;
    }




}
