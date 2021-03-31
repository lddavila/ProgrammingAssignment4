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
    
    
    
    public Customer(String []bankAccountData, int[] dataPositions){
        super(firstName, lastName, DateOfBirth, IDNum)
        firstName = bankAccountData[dataPositions[14]];
        lastName = bankAccountData[dataPositions[2]];
        DateOfBirth = bankAccountData[dataPositions[3]];
        IDNum = Long.parseLong(bankAccountData[dataPositions[1]]);
    }


 

    public Customer(){
        
    }

    /***
     * Creates the checking saving and credit accounts object
     */
    public void createClass(){
        
        tempCustomer.setCreditMax();
        tempCustomer.setCheck(createChecking(bankAccountData,dataPositions));
        tempCustomer.setSave(createSavings(bankAccountData,dataPositions));
        tempCustomer.setCred(createCredit(bankAccountData,dataPositions));
        tempCustomer.setAddress(bankAccountData[dataPositions[11]] + bankAccountData[dataPositions[12]] + bankAccountData[dataPositions[13]].replaceAll("^\"|\"$", ""));
        tempCustomer.setPhoneNumber(bankAccountData[dataPositions[6]]);
        
        
        CheckingAcc.setAccountNumber(CheckingAN);
        CheckingAcc.setBalance(CheckingABalance);

        SavingAcc.setAccountNumber(SavingAN);
        SavingAcc.setBalance(SavingABalance);

        CreditAcc.setAccountNumber(CreditAN);
        CreditAcc.setBalance(CreditABalance);
        CreditAcc.setMax(Double.parseDouble(bankAccountData[dataPositions[9]]));

        LinkedList<String> transactions =new LinkedList<>();
        stat = new BankStatement(super.firstName,super.lastname,super.DOB,super.address,super.phoneNumber
        ,super.idNumber,SavingABalance,CheckingABalance,CreditABalance,SavingABalance,CheckingABalance,CreditABalance,transactions);

    }


}
