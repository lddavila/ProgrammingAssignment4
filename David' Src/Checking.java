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
        this.set_First_Name(bankAccountData[0]);
        this.set_Last_Name(bankAccountData[1]);
        //converts the string into the expected long
        this.set_Account_Number(Long.parseLong(bankAccountData[9]));
        //converts string into expected boolean

        //converts string into expected double
        this.set_Starting_Balance(Double.parseDouble(bankAccountData[12]));
    }


}
