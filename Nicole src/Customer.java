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
    Saving SavingAcc = new Saving();
    Credit CreditAcc = new Credit();
    BankStatement stat =new BankStatement();


    /***
     * Constructor for customer
     * @param firstName first name
     * @param lastname last name
     * @param DOB date of birth
     * @param address adress
     * @param phoneNumber phone number
     * @param idNumber id number for bank
     * @param checkingAN checking account number
     * @param savingAN saving account number
     * @param creditAN credit account number
     * @param checkingABalance checking balance
     * @param savingABalance saving balance
     * @param creditABalance credit balance
     */
    public Customer(String firstName, String lastname, String DOB, String address, String phoneNumber, String idNumber, int checkingAN, int savingAN, int creditAN, double checkingABalance, double savingABalance, double creditABalance, double creditMax) {
        super(firstName, lastname, DOB, address, phoneNumber, idNumber);
        CheckingAN = checkingAN;
        SavingAN = savingAN;
        CreditAN = creditAN;
        CheckingABalance = checkingABalance;
        SavingABalance = savingABalance;
        CreditABalance = creditABalance;
        this.creditMax = creditMax;
    }

    public Customer(){
    }

    /***
     * Creates the checking saving and credit accounts object
     */
    public void createClass(){
        CheckingAcc.setAccountNumber(CheckingAN);
        CheckingAcc.setBalance(CheckingABalance);

        SavingAcc.setAccountNumber(SavingAN);
        SavingAcc.setBalance(SavingABalance);

        CreditAcc.setAccountNumber(CreditAN);
        CreditAcc.setBalance(CreditABalance);
        CreditAcc.setMax(creditMax);

        LinkedList<String> transactions =new LinkedList<>();
        stat = new BankStatement(firstName,lastname,DOB,address,phoneNumber
        ,idNumber,SavingABalance,CheckingABalance,CreditABalance,SavingABalance,CheckingABalance,CreditABalance,transactions);

    }


}
