/**
 * This method is to create a Person class
 * Abstract
 * Not meant to implement person
 */
public abstract class Person {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private long IDNum;
    private String address;
    private String phoneNumber;
    private double creditMax;
    private Checking check;
    private Savings save;
    private Credit cred;


    /** default constructor
     *
     */
    public Person(){}
    /**Useful constructor that will build the class
     * @param personInfo
     * Takes the array and uses it to assign appropriate place in array to the correct attribute
     * Assumes that info will be perfectly formatted with nothing missing
     *
     */
    public Person(String [] personInfo) {
        this.firstName = personInfo[0];
        this.lastName = personInfo[1];
        this.dateOfBirth = personInfo[2] + personInfo[3];
        this.IDNum = Long.parseLong(personInfo[4].replace("-", ""));
        this.address = personInfo[5] +personInfo[6] + personInfo[7];
        this.phoneNumber = personInfo[8];
    }

    //setters

    /**Sets the checking account
     * @param checkingIn the checking account
     */
    public void setCheck(Checking checkingIn){this.check = checkingIn;}
    /**sets the savings account
     * @param savingsIn the savings acconut
     */
    public void setSave(Savings savingsIn){this.save = savingsIn;}
    /**sets the credit account
     * @param creditIn the user's credit account
     */
    public void setCred(Credit creditIn){this.cred = creditIn;}
    /**Sets the first name
     * @param firstName string containing the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**Sets the last name
     * @param lastName string with the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**Sets the birthdate
     * @param dateOfBirth string with the DOB
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    /**sets the ID number
     * @param IDNum Long with the Id Num
     */
    public void setIDNum(Long IDNum) {
        this.IDNum = IDNum;
    }
    /**
     * @param creditMaxIn sets the credit max
     */
    public void setCreditMax(double creditMaxIn){
        this.creditMax = creditMaxIn;
    }
    /**
     * @param phoneNumber sets the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /** sets the address
     * @param address String with the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    //getters

    /**
     * @return gives the first name
     */
    public String getFirstName() {

        if (this.firstName == null)
            return "Info Unavailable";
        else
            return firstName;
    }

    /**
     * @return gives the last name
     */
    public String getLastName() {
        if (this.lastName == null)
            return "Info Unavailable";
        return lastName;
    }

    /**
     * @return Gives the DOB
     */
    public String getDateOfBirth() {
        if (this.dateOfBirth == null)
            return "Info Unavailable";
        return dateOfBirth;
    }

    /**
     * @return gives ID num
     */
    public long getIDNum() {
        return IDNum;
    }

    /**
     * @return Gives this person's credit max
     */
    public double getCreditMax(){return this.creditMax;}

    /**
     * @return this person's checking Account
     */
    public Checking getCheck(){return this.check;}

    /**
     * @return this person's savings account
     */
    public Savings getSave(){return this.save;}

    /**
     * @return this person's credit acount
     */
    public Credit getCred(){return this.cred;}


    /**
     * @return gives the address
     */
    public String getAddress() {

        if (this.getFirstName() == null)
            return "Info Unavailable";
        return address;
    }



    /**
     * @return gets the phone number
     */
    public String getPhoneNumber() {

        if (this.getFirstName() == null)
            return "Info Unavailable";
        return phoneNumber;
    }



    /**Prints all the Person's info
     */
    public  void printPersonInfo(){
        System.out.println("Savings Account Number: " + this.save.get_Account_Number());
        System.out.println("Id Num: " + this.IDNum);
        System.out.println(this.lastName);
        System.out.println(this.dateOfBirth);
        System.out.println("Checking Account Number: " + this.check.get_Account_Number());
        System.out.println("Credit Account Number: " + this.cred.get_Account_Number());
        System.out.println(this.phoneNumber);
        System.out.println("Checking Account Balance: " + this.check.get_Starting_Balance());
        System.out.println("Savings Account Balance: " + this.save.get_Starting_Balance());
        System.out.println("Max Credit: " + this.creditMax);
        System.out.println("Credit Account Balance: " + this.cred.get_Starting_Balance());
        System.out.println(this.address);
        System.out.println(this.firstName);
    }

    /**Prints only the account info not all the person's info
     */
    public void printAccountInfo(){
        System.out.println(this.firstName +" "+ this.lastName);
        try {
            System.out.println("Checking Account Balance: $" + this.check.get_Starting_Balance());
        }
        catch (Exception e){}
        try {
            System.out.println("Savings Account Balance: $" + this.save.get_Starting_Balance());
        }
        catch (Exception e){}
        try {
            System.out.println("Credit Account Balance: $" + this.cred.get_Starting_Balance());
        }
        catch (Exception e){}
    }


}
