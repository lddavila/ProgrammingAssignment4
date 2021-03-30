/** extends the Person class
 *
 */
public class Customer extends Person{
    /**Default constructor
     */
    public Customer(){}

    /**Constructor which takes a String array and takes info from it according to array position
     * @param customerInfo
     * Assumes that I would always have perfectly formatted data
     */
    public Customer(String [] customerInfo){

        this.setFirstName(customerInfo[0]);
        this.setLastName(customerInfo[1]);
        this.setDateOfBirth(customerInfo[2]+customerInfo[3]);
        this.setIDNum(Long.parseLong(customerInfo[4].replace("-", "")));
        this.setAddress(customerInfo[5]+customerInfo[6]+customerInfo[7]);
        this.setPhoneNumber(customerInfo[8]);



    }





}
