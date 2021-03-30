/** This class is meant to create a database for Accounts by using a hashTable
 *
 */
public class hashTable {
    private int size;
    private Account [][] table;

    //Constructor

    /** constructor
     * @param counter
     * Will create a 2d array depending on the size given
     * assumes that the size needed will be given
     * makes an array bigger than accounts just in case
     */
    public hashTable(int counter) {
        //below we actually create the hash table with more buckets than we need
        //I do this because the key was going to be between 0 and 100, but there would only be 17 used
        //I figured it best practive to create more buckets than I need
        this.size = counter*30;
        this.table = new Account[this.size][1];
    }

    //Getter//

    /**Returns the database itself
     * @return an 2d array of accounts representing the database
     */
    public Account[][] get_table(){
        return this.table;
    }

    //will allow a new account to be added to the hash table

    /**Allows new accounts to be added to the database
     * @param accountIn
     * will create an appropriate key based off the account number
     * checking acocunts begin at 0
     * savings account begin at 1900
     * credit accounts begin at 2800
     */
    public void add_Account(Account accountIn){
        //this line creates a very rudimentary key, only created it this way because i noticed all account numbers begin with 1000, 2000, or 3000
        int key = 0;
        if (accountIn.get_Account_Number()  < 2000) {
            key = (int) (accountIn.get_Account_Number() - 1000);
        }
         //creates the key if the account is a savings account
        else if (accountIn.get_Account_Number() < 3000){key = (int)(accountIn.get_Account_Number()-1900);}
        //creates the key if the account is a credit account
        else {key = (int)(accountIn.get_Account_Number()-2800);}
        //System.out.println(key);
        //the line below uses the key to assign the checking account to the appropriate bucket
        this.table[key][0] = accountIn;

    }


    //prints the whole database in case it needs to be seen for whatever reason

    /**Will print all the info for all the accounts in an easy to read format
     *
     */
    public void print_Data_Base(){
        for(int i = 0; i < (this.table.length); i++){
            //line below runs a check to make sure the bucket isn't empty
            if (table[i][0] != null){
                table[i][0].print_Account_info();
                System.out.println("/////////////////////////////////////");
            }}
    }

}
