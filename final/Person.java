//Nicole Avila
// CS3331
// 1/29/21
// Dr. Mejia
// PA4
// Abstract person class
//I confirm that the work of this assignment is completely my own. By turning in this assignment, I declare that I did not receive unauthorized assistance. Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by me alone.I confirm that the work of this assignment is completely my own. By turning in this assignment, I declare that I did not receive unauthorized assistance. Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by me alone.
public abstract class Person {
    private String firstName;
    private String lastname;
    private String DOB;
    private String address;
    private String phoneNumber;
    private String idNumber;

    /***
     *
     * @param firstName first name
     * @param lastname last name
     * @param DOB date of birth
     * @param address address
     * @param phoneNumber phone number
     * @param idNumber id number
     */
    public Person(String firstName, String lastname, String DOB, String address, String phoneNumber, String idNumber) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.DOB = DOB;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
    }

    /***
     * General constructor
     */
    public Person(){

    }

    /***
     * getter for id number
     * @return id number
     */
    public String getIdNumber() {
        return idNumber;
    }

    /***
     * setter for id number
     * @param idNumber id number
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /***
     * getter for first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /***
     * setter for first name
     * @param firstName first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /***
     * getter for last name
     * @return last name
     */
    public String getLastname() {
        return lastname;
    }

    /***
     * setter for last name
     * @param lastname last name
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /***
     * getter for date of birth
     * @return date of birth
     */
    public String getDOB() {
        return DOB;
    }

    /**
     * setter for date of birth
     * @param DOB date of birth
     */
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    /***
     * getter for address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /***
     * setter for address
     * @param address address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /***
     * getter for phone number
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /***
     * setter for phone number
     * @param phoneNumber phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * @return gives ID num
     */
    public long getIDNum() {
        return Long.parseLong(this.idNumber);
    }

    /***
     * Creates a string of all the information in the person class
     * @return string of all the infomation
     */
    public String printPersonInfo(){
        return(firstName+" "+lastname+" "+DOB+" "+address+" "+phoneNumber+" Account ID number "+ idNumber+" ");
    }

}
