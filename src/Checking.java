//Nicole Avila
// CS3331
// 1/29/21
// Dr. Mejia
// PA2
// Checking account
//I confirm that the work of this assignment is completely my own. By turning in this assignment, I declare that I did not receive unauthorized assistance. Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by me alone.I confirm that the work of this assignment is completely my own. By turning in this assignment, I declare that I did not receive unauthorized assistance. Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by me alone.
public class Checking extends Account{
    /***
     * The constructor for the checking
     * @param accountNum the account number
     * @param balance the money in the account
     */
    public Checking(int accountNum, double balance) {
        accountNum = super.accountNumber;
        balance = super.balance;
    }

    /***
     * A general constructor
     */
    public Checking(){}

    /***
     * Adds amounts to the balance
     * @param ammount the ammount to be added
     */
    public void add(double ammount){
        balance+=ammount;
    }

    /***
     * Subtracts amounts from balance
     * @param ammount the ammount to be subtracted
     */
    public void subtract(double ammount){
        if (-ammount <= balance) {
            balance -= ammount;
        }
        else{
            System.out.println("Sorry insufficent funds");
        }
    }




}


