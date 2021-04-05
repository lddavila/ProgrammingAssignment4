/**Back end for transaction reader, as transactionReader the exact same functions but implmeneted in new ways
 */
public class backEndForTransactionReader {

    /**
     * @param fromAccount the account from which the funds will be taken
     * @param toAccount the funds where the funds will be placed
     * @param amount the amount that will be transferred/paid between the 2 accounts
     */
    public static void transfer(Account fromAccount, Account toAccount, double amount){
        double successOrFailure =fromAccount.withdrawal(amount);
        if(successOrFailure == -1){
            System.out.println("Transfer/Pays from " + fromAccount.get_First_Name() + " " + fromAccount.get_Last_Name() + "'s " + checkAccountType(fromAccount)
                    + " to " + toAccount.get_First_Name() + " " + toAccount.get_Last_Name() +"'s " + checkAccountType(toAccount) + " failed due to insufficient funds");
        }
        else {
            toAccount.deposit(amount);

        }
        }

    /**Withdraws money from the account
     * @param accountIn the account funds are gonna be transferred from
     * @param amount  the amount that's going to be withdrawn
     */
    public static void withdraw(Account accountIn, double amount){
        double successOrFailure = accountIn.withdrawal(amount);
        if(successOrFailure == -1){
            System.out.println("Withdrawal from " + accountIn.get_First_Name() + " " + accountIn.get_Last_Name() + "'s " + checkAccountType(accountIn) +" failed due to insufficient funds");
        }
    }

    /**Deposits money into the account
     * @param accountIn the account funds are gonna be transferred from
     * @param amount  the amount that's going to be withdrawn
     */
    public static void deposit(Account accountIn, double amount){
        accountIn.deposit(amount);
    }

    /**utility function that tells you whether a given account is a checking/savings account
     * @param accountIn the account we ae checking the type for
     * @return a string that that states the account type checking/savings
     */
    public static String checkAccountType(Account accountIn){
        if(accountIn.get_Account_Number() < 2000)
            return "Checking";
        else if(accountIn.get_Account_Number() < 3000)
            return "Savings";
        else if (accountIn.get_Account_Number() > 3000 && accountIn.get_Account_Number() < 4000)
            return "Credit";
        return "Unavailable";
    }

    /**utility function that tells you whether a given account is a checking/savings account
     * @param accountIn the account we ae checking the type for
     * @return a string that that states the account type checking/savings
     */
    public static String checkAccountType(long accountIn){
        if(accountIn < 2000)
            return "Checking";
        else if(accountIn< 3000)
            return "Savings";
        else if (accountIn> 3000 && accountIn< 4000)
            return "Credit";
        return "Unavailable";
    }
}
