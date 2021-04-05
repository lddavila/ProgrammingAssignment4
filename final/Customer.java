import java.util.LinkedList;

//Nicole Avila
// CS3331
// 2/20/21
// Dr. Mejia
// PA3
// Customer class
//I confirm that the work of this assignment is completely my own. By turning in this assignment, I declare that I did not receive unauthorized assistance. Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by me alone.I confirm that the work of this assignment is completely my own. By turning in this assignment, I declare that I did not receive unauthorized assistance. Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by me alone.
public class Customer extends Person{
    int CheckingAN;
    int SavingAN;
    int CreditAN;
    double CheckingABalance;
    double SavingABalance;
    double CreditABalance;
    double creditMax;
    Checking CheckingAcc = new Checking();
    Savings SavingAcc = new Savings();
    Credit CreditAcc = new Credit();




    public Customer(String []bankAccountData, int[] dataPositions){


        this.setFirstName(bankAccountData[dataPositions[14]]);
        this.setLastname(bankAccountData[dataPositions[2]]);
        this.setDOB(bankAccountData[dataPositions[3]]);
        this.setAddress(bankAccountData[dataPositions[11]] + bankAccountData[dataPositions[12]] + bankAccountData[dataPositions[13]].replaceAll("^\"|\"$", ""));
        this.setPhoneNumber(bankAccountData[dataPositions[6]]);
        this.setIdNumber((bankAccountData[dataPositions[1]]));
        this.setCreditMax(Double.parseDouble(bankAccountData[dataPositions[9]]));
        this.CreditABalance = Double.parseDouble(bankAccountData[dataPositions[10]]);
        this.SavingABalance = Double.parseDouble(bankAccountData[dataPositions[8]]);
        this.CheckingABalance = Double.parseDouble(bankAccountData[dataPositions[7]]);

        createClass(bankAccountData, dataPositions);
    }




    public Customer(){

    }

    /***
     * Creates the checking saving and credit accounts object
     */
    public void createClass(String []bankAccountData, int[] dataPositions){

        Customer tempCustomer = new Customer();
        tempCustomer.setCreditMax(Double.parseDouble(bankAccountData[dataPositions[9]]));
        tempCustomer.setCheck(CheckingAcc);
        tempCustomer.setSave(SavingAcc);
        tempCustomer.setCred(CreditAcc);



        CheckingAcc.set_Account_Number(Long.parseLong(bankAccountData[dataPositions[4]]));
        CheckingAcc.set_Starting_Balance(Double.parseDouble(bankAccountData[dataPositions[7]]));
        CheckingAcc.set_First_Name(bankAccountData[dataPositions[14]]);
        CheckingAcc.set_Last_Name(bankAccountData[dataPositions[2]]);
        CheckingAcc.getBankStatement().setStartingBalance(Double.parseDouble(bankAccountData[dataPositions[7]]));

        SavingAcc.set_Account_Number(Long.parseLong(bankAccountData[dataPositions[0]]));
        SavingAcc.set_Starting_Balance(Double.parseDouble(bankAccountData[dataPositions[8]]));
        SavingAcc.set_First_Name(bankAccountData[dataPositions[14]]);
        SavingAcc.set_Last_Name(bankAccountData[dataPositions[2]]);
        SavingAcc.getBankStatement().setStartingBalance(Double.parseDouble(bankAccountData[dataPositions[8]]));

        CreditAcc.set_Account_Number(Long.parseLong(bankAccountData[dataPositions[5]]));
        CreditAcc.set_Starting_Balance(Double.parseDouble(bankAccountData[dataPositions[10]]));
        CreditAcc.set_First_Name(bankAccountData[dataPositions[14]]);
        CreditAcc.set_Last_Name(bankAccountData[dataPositions[2]]);
        CreditAcc.getBankStatement().setStartingBalance(Double.parseDouble(bankAccountData[dataPositions[10]]));




    }
    /**Sets the checking account
     * @param checkingIn the checking account
     */
    public void setCheck(Checking checkingIn){this.CheckingAcc = checkingIn;}
    /**sets the savings account
     * @param savingsIn the savings acconut
     */
    public void setSave(Savings savingsIn){this.SavingAcc= savingsIn;}
    /**sets the credit account
     * @param creditIn the user's credit account
     */
    public void setCred(Credit creditIn){this.CreditAcc= creditIn;}
    /**Sets the last name
     * @param lastName string with the last name
     */
    public void setLastName(String lastName) {
        this.setLastname(lastName);
    }
    /**Sets the birthdate
     * @param dateOfBirth string with the DOB
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.setLastname(dateOfBirth);
    }
    /**sets the ID number
     * @param IDNum Long with the Id Num
     */
    public void setIDNum(String IDNum) {
        this.setIdNumber(IDNum);
    }
    /**
     * @param creditMaxIn sets the credit max
     */
    public void setCreditMax(double creditMaxIn){
        this.creditMax = creditMaxIn;
    }



    //getters

    /**
     * @return gives the first name
     */


    /**
     * @return gives the last name
     */
    public String getLastName() {
        if (this.getLastname() == null)
            return "Info Unavailable";
        return this.getLastname();
    }

    /**
     * @return Gives the DOB
     */
    public String getDateOfBirth() {
        if (this.getDOB() == null)
            return "Info Unavailable";
        return this.getDOB();
    }



    /**
     * @return Gives this person's credit max
     */
    public double getCreditMax(){return this.creditMax;}

    /**
     * @return this person's checking Account
     */
    public Checking getCheck(){return this.CheckingAcc;}

    /**
     * @return this person's savings account
     */
    public Savings getSave(){return this.SavingAcc;}

    /**
     * @return this person's credit acount
     */
    public Credit getCred(){return this.CreditAcc;}






    /**Prints only the account info not all the person's info
     */
    public void printAccountInfo(){
        System.out.println(this.getFirstName()+" "+ this.getLastname());
        try {
            System.out.println("Checking Account Balance: $" + this.CheckingAcc.get_Starting_Balance());
        }
        catch (Exception e){}
        try {
            System.out.println("Savings Account Balance: $" + this.SavingAcc.get_Starting_Balance());
        }
        catch (Exception e){}
        try {
            System.out.println("Credit Account Balance: $" + this.CreditAcc.get_Starting_Balance());
        }
        catch (Exception e){}
    }


}








