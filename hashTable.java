
/** This class is meant to create a database for Person by using a hashTable
 *
 */
public class hashTable {
    private int size;
    private Person [][] table;
    private long highestID;

    //constructor
    /** constructor
     * @param counter
     * Will create a 2d array depending on the size given
     * assumes that the size needed will be given
     * makes an array bigger than accounts just in case
     */
    public PersonDatabase(int counter) {
        //below we actually create the hash table with more buckets than we need
        //I do this because the key was going to be between 0 and 100, but there would only be 17 used
        //I figured it best practive to create more buckets than I need
        this.size = counter*10;
        this.table = new Person[this.size][1];
    }

    //Getter//
    /**Returns the database itself
     * @return an 2d array of accounts representing the database
     */
    public Person[][] get_table(){
        return this.table;
    }

    //will allow a new account to be added to the hash table
    /**Allows new accounts to be added to the database
     * @param personIn
     * will create an appropriate key based off the account number
     * checking acocunts begin at 0
     * savings account begin at 1900
     * credit accounts begin at 2800
     */
    public void add_Person(Person personIn){
        //this line creates a very rudimentary key, only created it this way because i noticed all account numbers begin with 1000, 2000, or 3000
        int key = (int)personIn.getIDNum()-1;
        //the line below uses the key to assign the checking account to the appropriate bucket
        if(personIn.getIDNum() > this.highestID)
            this.highestID = personIn.getIDNum();
        this.table[key][0] = personIn;

    }

    /** Allows the highest id to be accessed in order for functionality
     * @return will return the highest ID
     */
    public long getHighestId(){
        return highestID;
    }

    //prints the whole database in case it needs to be seen for whatever reason
    /**Will print all the info for all the accounts in an easy to read format
     */
    public void print_Data_Base(){
        for(int i = 0; i < (this.table.length); i++){
            //line below runs a check to make sure the bucket isn't empty
            if (table[i][0] != null){

                table[i][0].printAccountInfo();
                System.out.println("/////////////////////////////////////");
            }}
    }

}
