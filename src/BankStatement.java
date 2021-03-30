import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class BankStatement {

    public String firstName;
    public String lastname;
    public String DOB;
    public String address;
    public String phoneNumber;
    public String idNumber;
    double Sstart;
    double Chstart;
    double Crstart;
    double endS ;
    double endCh;
    double endCr;
    LinkedList<String> transactions;

    public BankStatement(String firstName, String lastname, String DOB, String address, String phoneNumber, String idNumber, double sstart, double chstart, double crstart, double endS, double endCh, double endCr, LinkedList<String> transactions) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.DOB = DOB;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
        Sstart = sstart;
        Chstart = chstart;
        Crstart = crstart;
        this.endS = endS;
        this.endCh = endCh;
        this.endCr = endCr;
        this.transactions = transactions;
    }

    public BankStatement() {
    }

    public double getSstart() {
        return Sstart;
    }

    public void setSstart(double sstart) {
        Sstart = sstart;
    }

    public double getChstart() {
        return Chstart;
    }

    public void setChstart(double chstart) {
        Chstart = chstart;
    }

    public double getCrstart() {
        return Crstart;
    }

    public void setCrstart(double crstart) {
        Crstart = crstart;
    }


    public double getEndS() {
        return endS;
    }

    public void setEndS(double endS) {
        this.endS = endS;
    }

    public double getEndCh() {
        return endCh;
    }

    public void setEndCh(double endCh) {
        this.endCh = endCh;
    }

    public double getEndCr() {
        return endCr;
    }

    public void setEndCr(double endCr) {
        this.endCr = endCr;
    }

    public LinkedList<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(LinkedList<String> transactions) {
        this.transactions = transactions;
    }

    public void addTrans(String action){
        transactions.add(action);
    }

    public void printBalanceStat() throws IOException {

        File statemnet = new File(firstName+" "+lastname+" BankStatment.txt");
        FileWriter write= new FileWriter(statemnet);
        write.write(firstName+" "+lastname+" "+DOB+" "+address+" "+phoneNumber+" Account ID number "+ idNumber+" ");
        write.write("\r\n");
        write.write("****************************************************************************************************");
        write.write("\r\n");
        write.write("Saving Account starting balance $"+ Sstart);
        write.write("\r\n");
        write.write("Saving account ending balance $"+ endS+" ");
        if(Crstart<=0) {
            write.write("\r\n");
            write.write("Credit Account starting balance  $"+ Crstart);
            write.write("\r\n");
            write.write("Credit account ending balance $"+ endCr+" ");
            write.write("\r\n");
        }
        if(Chstart >=0) {
            write.write("Checking Account starting balance $" + Chstart);
            write.write("\r\n");
            write.write("Checking account ending balance $"+ endCh+" ");
            write.write("\r\n");
        }
        write.write("\r\n");
        write.write("****************************************************************************************************");
        write.write("\r\n");

        write.write("Transactions: ");
        write.write("\r\n");

       for(int i =0; i< transactions.size();i++){
            write.write(transactions.get(i));
            write.write("\r\n");

        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date current = new Date(System.currentTimeMillis());
        write.write(""+current);
        write.flush();
        write.close();
    }

}
