//Nicole Avila
// CS3331
// 2/20/21
// Dr. Mejia
// PA2
// Credit class
//I confirm that the work of this assignment is completely my own. By turning in this assignment, I declare that I did not receive unauthorized assistance. Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by me alone.I confirm that the work of this assignment is completely my own. By turning in this assignment, I declare that I did not receive unauthorized assistance. Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by me alone.
public class Credit extends Account{
    /***
     * Constructor for credit

     * @param accountNum account number
     * @param balance money in account
     */
    double max;
    public Credit(int accountNum, double balance, double max) {

        accountNum = super.accountNumber;
        balance = super.balance;
        this.max = max;
    }



    /***
     * general constructor
     */
    public Credit(){}

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    /***
     * Adds amounts to balance
     * @param ammount the amount ot be added
     */

    public void add(double ammount){
        if (ammount <= -balance) {
            balance += ammount;
        }
        else{
            System.out.println("Sorry ammount is too much");
        }
    }

    /***
     * subtracts amounts from balance
     * @param ammount the ammount to be subtracted
     */
    public void subtract(double ammount){
        balance-=ammount;
    }

    @Override
    public String print(){
        return ("account number "+ accountNumber+" account balance $"+ balance+" max $"+ max);
    }
}
