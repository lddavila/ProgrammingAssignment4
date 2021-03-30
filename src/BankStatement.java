import java.util.ArrayList;

/**The BankStatement class 
 */
public class BankStatement {
    private Customer customerInfo;
    private Account customerAccount;
    private double startingBalance;
    private double endingBalance;
    private ArrayList<String> userActions = new ArrayList<String>();
    private String dateOfTransaction = String.valueOf(java.time.LocalDate.now());

    /**
     * @return getter to get the customer info
     */
    public Customer getCustomerInfo() {
        return customerInfo;
    }

    /**
     * @param customerInfo the customer's info
     */
    public void setCustomerInfo(Customer customerInfo) {
        this.customerInfo = customerInfo;
    }

    /**
     * @return returns the account info
     */
    public Account getCustomerAccount() {
        return customerAccount;
    }

    /**
     * @param customerAccount the customer's account
     */
    public void setCustomerAccount(Account customerAccount) {
        this.customerAccount = customerAccount;
    }

    /**
     * @return gets the user's starting balance
     */
    public double getStartingBalance() {
        return startingBalance;
    }

    /**
     * @param startingBalance sets the user's starting balance
     */
    public void setStartingBalance(double startingBalance) {
        this.startingBalance = startingBalance;
        this.endingBalance = this.startingBalance;
    }

    /**
     * @return returns the ending balance
     */
    public double getEndingBalance() {
        return endingBalance;
    }

    /**
     * @param endingBalance sets the ending balance
     */
    public void setEndingBalance(double endingBalance) {
        this.endingBalance = endingBalance;
    }

    /**
     * @return retyrns an array list of all the user's actions
     */
    public ArrayList<String> getUserActions() {
        return userActions;
    }

    /**Serves to add new actions to the bank statement
     * @param userActionIn the action the user took
     */
    public void addUserTransaction(String userActionIn){
        this.userActions.add(userActionIn);
    }
}
