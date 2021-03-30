
public class Saving extends Account{

    public Saving(int accountNum, double balance) {
        accountNum = super.accountNumber;
        balance = super.balance;
    }
    public Saving(){}
    public void add(double ammount){
        balance+=ammount;
    }
    public void subtract(double ammount){
        if (ammount <= balance) {
            balance -= ammount;
        }
        else{
            System.out.println("Sorry insufficent funds");
        }
    }

}
