/** extension of the Account Class, with additional attributes
 *
 */
public class Credit extends Account{
    private BankStatement bankStatement;
    /**default constructor
     */
    public Credit(){
        this.bankStatement = new BankStatement();
    }

    /**Constructor which takes a String array and takes info from it according to array position
     * @param bankAccountData
     * Assumes that I would always have perfectly formatted data
     */
    public Credit(String[] bankAccountData) {    }

    /** This method is created to override the deposit function located in the Account class
     * @param depositAmount amount the user wants to deposit into their creidt account
     * @return
     * created specifically so that they cannot deposit more funds than they owe
     * This is to not cause errors
     */
    @Override
    public double deposit(double depositAmount){
        if (depositAmount < 0){
            System.out.println("Sorry you can't deposit a negative amount. Please enter another amount or exit.");
            this.bankStatement.addUserTransaction("You tried to pay back a negative amount on " + java.time.LocalDate.now());
            //returns a -1 so that backEnd knows there was an error and can alert the user of this so they can try again or exit as appropriate
            return -1;
        }
        if (this.get_Starting_Balance() + depositAmount > 0){
            System.out.println("Sorry you can't pay back more than is owed on a credit account. Please enter a smaller amount or exit.");
            this.bankStatement.addUserTransaction("You tried to pay back more than you owe on " + java.time.LocalDate.now());
            //returns a -1 to indicate error
            return -1;
        }
        this.bankStatement.addUserTransaction("You paid $" + depositAmount + " " + java.time.LocalDate.now());
        writeUserActions.logUserAction(this.get_First_Name()+ " "+ this.get_Last_Name() + " deposited $" + depositAmount+ " into their credit account.");
        this.set_Starting_Balance(this.get_Starting_Balance() + depositAmount);
        //returns a 0 to indicate that there was a successful transaction and allow the user to get the confirmation message and return to main menu
        return 0;
    }

    /** This method is created to override the deposit function located in the parent class
     * @param i worthless int just done by necessity
     * @return an either 0 for success or -1 for faliure
     * created specifically to not allow this action
     * Users shouldn't be able to take funds out a credit account
     */
    @Override
    public double withdrawal(double i){
        System.out.println("Sorry you can't withdraw from a credit account.");
        writeUserActions.logUserAction("User: " + this.get_First_Name() + this.get_Last_Name() + "tried to withdrawal from their credit account. ");
        return -1;

    }
}
