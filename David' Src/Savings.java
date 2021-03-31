/** extension of the Account Class, with additional attributes
 *
 */
public class Savings extends Account{
    /**default constructor
     */
    public Savings(){

    }
    /**Constructor which takes a String array and takes info from it according to array position
     * @param bankAccountData
     * Assumes that I would always have perfectly formatted data
     */
    public Savings(String[] bankAccountData) {

    }
    
    
    public Savings(String [] bankAccountData, int [] dataPositions){
        super.this.set_First_Name(bankAccountData[dataPositions[14]]);
        super.this.set_Last_Name(bankAccountData[dataPositions[2]]);
        super.this.set_Starting_Balance(Double.parseDouble(bankAccountData[dataPositions[8]]));
        super.this.set_Account_Number(Long.parseLong(bankAccountData[dataPositions[0]]));
        super.this.getBankStatement().setStartingBalance(Double.parseDouble(bankAccountData[dataPositions[8]]));
    }

}
