/** extension of the Account Class, with additional attributes
 *
 */
public class Checking extends Account{

    /**default constructor
     */
    public Checking(){}

    /**Constructor which takes a String array and takes info from it according to array position
     * @param bankAccountData
     * Assumes that I would always have perfectly formatted data
     */
    public Checking(String[] bankAccountData) {
    }

        /**Creates a Checking account with the appropriate info given by dataPositions
     * @param bankAccountData an array of strings which contains all the info in the file line
     * @param dataPositions an array of ints that contains the column positions
     * @return a Checking Account with the correct data
     */
    public Checking(String [] bankAccountData, int [] dataPositions){
        super.this.set_First_Name(bankAccountData[dataPositions[14]]);
        super.this.set_Last_Name(bankAccountData[dataPositions[2]]);
        super.this.set_Starting_Balance(Double.parseDouble(bankAccountData[dataPositions[7]]));
        super.this.set_Account_Number(Long.parseLong(bankAccountData[dataPositions[4]]));
        super.this.getBankStatement().setStartingBalance(Double.parseDouble(bankAccountData[dataPositions[7]]));
    }

}
