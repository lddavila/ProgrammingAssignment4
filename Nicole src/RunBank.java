//Nicole Avila
// CS3331
// 2/20/21
// Dr. Mejia
// PA3
// A very simple banking system that will make accounts for users and adjust balances
//I confirm that the work of this assignment is completely my own. By turning in this assignment, I declare that I did not receive unauthorized assistance. Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by me alone.I confirm that the work of this assignment is completely my own. By turning in this assignment, I declare that I did not receive unauthorized assistance. Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by me alone.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.*;


public class RunBank {

    /***
     * takes a file name then turns the data in the file customer objects then places them in a hashmap
     * @return a Hash table that is now the bank records
     */
    public static Hashtable<String, Customer> readFiles() {// takes a file name then turns the data in the file checking objects then places them in a hashmap
        Hashtable<String, Customer> bank = new Hashtable<String, Customer>();
        try {//makes sure file can be found
            String fileName = "src/CS 3331 - Bank Users 3(7) (2).csv";//replace with src/filename
            File excel = new File(fileName);
            Scanner scan = new Scanner(excel);


            String first;
            String last;
            String DOB;
            String IDNumber;
            String Address;
            String PhoneNumber;
            int CheckingAN;
            int SavingAN;
            int CreditAN;
            double CheckingABalance;
            double SavingABalance;
            double CreditABalance;
            double CreditMax;
            int newid=0;

            String curent = scan.nextLine();
            //System.out.println(curent);
//
            while (curent!=null) {
                //System.out.println(curent);
                var account = curent.split(",");
                //System.out.println(account[0]);
                //System.out.println(account[account.length-1]);
                if (account[0].equalsIgnoreCase("Savings Account Number")) {//makes sure the labels are put into an checking object
                }
                else {
                    if (account.length == 15) {//assumes that attributes will be in the correct order

                        first = account[14];
                        last = account[2];
                        DOB = account[3] ;
                        IDNumber = account[1];
                        Address = account[11] + account[12] + account[13];
                        PhoneNumber = account[6];
                        CheckingAN = Integer.parseInt(account[4]);
                        SavingAN = Integer.parseInt(account[0]);
                        CreditAN = Integer.parseInt(account[5]);
                        CheckingABalance = Double.parseDouble(account[7]);
                        SavingABalance = Double.parseDouble(account[8]);
                        CreditABalance = Double.parseDouble(account[10]);
                        CreditMax = Double.parseDouble(account[9]);

                        Customer newCustomer = new Customer(first, last, DOB, Address, PhoneNumber, IDNumber, CheckingAN, SavingAN, CreditAN, CheckingABalance, SavingABalance, CreditABalance, CreditMax);
                        newCustomer.createClass();//makes the accounts
                        if( newid < Integer.parseInt(IDNumber)){
                            newid = Integer.parseInt(IDNumber);
                        }
                        LinkedList<String> transactions= new LinkedList<>();
                        bank.put(IDNumber, newCustomer);

                    } else {
                        System.out.println("Account is missing information");

                    }

                }
                if(scan.hasNextLine()){
                    curent = scan.nextLine();
                }
                else{
                    break;
                }

            }
            Customer updateKey = new Customer("-1","-1","-1","-1","-1",""+newid,-1,-1,-1,-1,-1,-1,-1);
            bank.put("update", updateKey);

        } catch (FileNotFoundException e) {
            System.out.println("File not found ");
        }
        return bank;
    }

    public static void newUser(Hashtable<String, Customer> bank, String firstN, String lastN, String DOB, String adress, String PhoneNum, String AccountNum, double SavingABalance, double creditABalance, double CheckingABalance, double CreditMax){
        int updatedANum = Integer.parseInt(AccountNum);
        Customer newCustomer = new Customer(firstN,lastN,DOB,adress,PhoneNum,AccountNum,999+updatedANum,1999+updatedANum,2999+updatedANum,CheckingABalance,SavingABalance,CheckingABalance, CreditMax);
        newCustomer.createClass();
        bank.put(AccountNum,newCustomer);
    }
    public static Customer findCustomer(Hashtable<String,Customer> bank,String first, String last) {
        Set<String> accountsKeys = bank.keySet();
        Iterator<String> cur = accountsKeys.iterator();
        while (cur.hasNext()) {// finds account with name
            String currBA = cur.next();
            if (bank.get(currBA).firstName.equalsIgnoreCase(first)) {
                if (bank.get(currBA).lastname.equalsIgnoreCase(last)) {
                    return bank.get(currBA);

                }
                if (!cur.hasNext()) {
                    System.out.println("Sorry account could be found");
                    return null;
                }
            }

        }
        return null;
    }




    /***
     * takes the bank (hash table) the two ids and teh amount needed to be paid and subtracts from the first user and adds to the second users desired account
     * @param ammount the amount to be paid
     * @param bank the hash table records
     * @param type which accounts are being used
     * @param id1 the person sending the money
     * @param id2 the person its being sent to
     * @param out used to created txt log
     */

    public static void pay(double ammount, Hashtable<String, Customer> bank, String type, String id1, String id2, FileWriter out) {//takes two ids so the first id can pay the second id
            if (type.equalsIgnoreCase("a")) {// if statements used to see which accounts should be used
                if(bank.get(id1).CheckingAcc.balance>= ammount) {
                    bank.get(id1).CheckingAcc.subtract(ammount);
                    bank.get(id2).CheckingAcc.add(ammount);



                    bank.get(id1).stat.setEndCh(bank.get((id1)).CheckingAcc.balance);
                    bank.get(id2).stat.setEndCh(bank.get((id2)).CheckingAcc.balance);

                    bank.get(id1).stat.addTrans(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                    bank.get(id2).stat.addTrans(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);


                    System.out.println(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                    System.out.println(bank.get(id1).firstName +" new checking amount is $"+bank.get(id1).CheckingAcc.balance+" "+ bank.get(id2).firstName +" new checking amount is $"+bank.get(id2).CheckingAcc.balance);
                    try {//writes transaction to log

                        out.write(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                        out.write("\r\n");
                        out.write((bank.get(id1).firstName +" new checking amount is $"+bank.get(id1).CheckingAcc.balance+" "+ bank.get(id2).firstName +" new checking amount is $"+bank.get(id2).CheckingAcc.balance));
                        out.write("\r\n");

                    }
                    catch(IOException e){
                        System.out.println("Error in translog");
                    }

                }
                else{
                    System.out.println("Sorry insufficient funds ");
                }
            }

            else if (type.equalsIgnoreCase("b")) {
                if(bank.get(id1).CheckingAcc.balance>= ammount) {
                    bank.get(id1).CheckingAcc.subtract(ammount);
                    bank.get(id2).SavingAcc.add(ammount);
                    bank.get(id1).stat.addTrans(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                    System.out.println(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                    System.out.println(bank.get(id1).firstName +" new checking amount is $"+bank.get(id1).CheckingAcc.balance+" "+ bank.get(id2).firstName +" new saving amount is $"+bank.get(id2).SavingAcc.balance);

                    bank.get(id1).stat.setEndCh(bank.get((id1)).CheckingAcc.balance);
                    bank.get(id2).stat.setEndS(bank.get((id2)).SavingAcc.balance);
                    bank.get(id1).stat.addTrans(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                    bank.get(id2).stat.addTrans(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);


                    try {//writes transaction to log

                        out.write(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                        out.write("\r\n");
                        out.write(bank.get(id1).firstName +" new checking amount is $"+bank.get(id1).CheckingAcc.balance+" "+ bank.get(id2).firstName +" new saving amount is $"+bank.get(id2).SavingAcc.balance);
                        out.write("\r\n");

                    }
                    catch(IOException e){
                        System.out.println("Error in translog");
                    }
                }
                else{
                    System.out.println("Sorry insufficient funds ");
                }
            }
            else if (type.equalsIgnoreCase("c")) {
                if(bank.get(id1).CheckingAcc.balance>= ammount && bank.get(id2).CreditAcc.balance<= -ammount) {
                    bank.get(id1).CheckingAcc.subtract(ammount);
                    bank.get(id2).CreditAcc.add(ammount);
                    System.out.println(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                    System.out.println(bank.get(id1).firstName +" new checking amount is $"+bank.get(id1).CheckingAcc.balance+" "+ bank.get(id2).firstName +" new credit amount is $"+bank.get(id2).CreditAcc.balance);

                    bank.get(id1).stat.setEndCh(bank.get((id1)).CheckingAcc.balance);
                    bank.get(id2).stat.setEndCr(bank.get((id2)).CreditAcc.balance);
                    bank.get(id1).stat.addTrans(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                    bank.get(id2).stat.addTrans(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);


                    try {//writes transaction to log
                        //FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                        out.write("\r\n");
                        out.write(bank.get(id1).firstName +" new checking amount is $"+bank.get(id1).CheckingAcc.balance+" "+ bank.get(id2).firstName +" new credit amount is $"+bank.get(id2).CreditAcc.balance);
                        out.write("\r\n");

                    }
                    catch(IOException e){
                        System.out.println("Error in translog");
                    }
                }
                else{
                    System.out.println("Sorry insufficient funds or amount is too large ");
                }
            }
            else if (type.equalsIgnoreCase("d")) {
                if(bank.get(id1).SavingAcc.balance>= ammount) {
                    bank.get(id1).SavingAcc.subtract(ammount);
                    bank.get(id2).CheckingAcc.add(ammount);
                    System.out.println(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                    System.out.println(bank.get(id1).firstName +" new saving amount is $"+bank.get(id1).SavingAcc.balance+" "+ bank.get(id2).firstName +" new checking amount is $"+bank.get(id2).CheckingAcc.balance);

                    bank.get(id1).stat.setEndS(bank.get((id1)).SavingAcc.balance);
                    bank.get(id2).stat.setEndCh(bank.get((id2)).CheckingAcc.balance);
                    bank.get(id1).stat.addTrans(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                    bank.get(id2).stat.addTrans(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);


                    try {//writes transaction to log
                        //FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                        out.write("\r\n");
                        out.write(bank.get(id1).firstName +" new saving amount is $"+bank.get(id1).SavingAcc.balance+" "+ bank.get(id2).firstName +" new checking amount is $"+bank.get(id2).CheckingAcc.balance);
                        out.write("\r\n");

                    }
                    catch(IOException e){
                        System.out.println("Error in translog");
                    }
                }
                else{
                    System.out.println("Sorry insufficient funds ");
                }
            }
            else if (type.equalsIgnoreCase("e")) {
                if(bank.get(id1).SavingAcc.balance>= ammount) {
                    bank.get(id1).SavingAcc.subtract(ammount);
                    bank.get(id2).SavingAcc.add(ammount);
                    System.out.println(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                    System.out.println(bank.get(id1).firstName +" new saving amount is $"+bank.get(id1).SavingAcc.balance+" "+ bank.get(id2).firstName +" new saving amount is $"+bank.get(id2).SavingAcc.balance);

                    bank.get(id1).stat.setEndS(bank.get((id1)).SavingAcc.balance);
                    bank.get(id2).stat.setEndS(bank.get((id2)).SavingAcc.balance);
                    bank.get(id1).stat.addTrans(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                    bank.get(id2).stat.addTrans(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);


                    try {//writes transaction to log
                       // FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                        out.write("\r\n");
                        out.write(bank.get(id1).firstName +" new saving amount is $"+bank.get(id1).SavingAcc.balance+" "+ bank.get(id2).firstName +" new saving amount is $"+bank.get(id2).SavingAcc.balance);
                        out.write("\r\n");

                    }
                    catch(IOException e){
                        System.out.println("Error in translog");
                    }
                }
                else{
                    System.out.println("Sorry insufficient funds ");
                }
            }
            else if (type.equalsIgnoreCase("f")) {
                if(bank.get(id1).SavingAcc.balance>= ammount && bank.get(id2).CreditAcc.balance<= -ammount) {
                    bank.get(id1).SavingAcc.subtract(ammount);
                    bank.get(id2).CreditAcc.add(ammount);
                    System.out.println(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                    System.out.println(bank.get(id1).firstName +" new saving amount is $"+bank.get(id1).SavingAcc.balance+" "+ bank.get(id2).firstName +" new credit amount is $"+bank.get(id2).CreditAcc.balance);

                    bank.get(id1).stat.setEndS(bank.get((id1)).SavingAcc.balance);
                    bank.get(id2).stat.setEndCr(bank.get((id2)).CreditAcc.balance);
                    bank.get(id1).stat.addTrans(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                    bank.get(id2).stat.addTrans(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);


                    try {//writes transaction to log
                        //FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                        out.write("\r\n");
                        out.write(bank.get(id1).firstName +" new saving amount is $"+bank.get(id1).SavingAcc.balance+" "+ bank.get(id2).firstName +" new credit amount is $"+bank.get(id2).CreditAcc.balance);
                        out.write("\r\n");
                    }
                    catch(IOException e){
                        System.out.println("Error in translog");
                    }
                }
                else{
                    System.out.println("Sorry insufficient funds or ammoount is too larger");
                }
            }
            else if (type.equalsIgnoreCase("g")){
                if(bank.get(id1).CreditAcc.balance-ammount >= -bank.get(id1).CreditAcc.max) {

                    bank.get(id1).CreditAcc.subtract(ammount);
                    bank.get(id2).CheckingAcc.add(ammount);
                    System.out.println(bank.get(id1).firstName + " paid " + bank.get(id2).firstName + " $" + ammount);
                    System.out.println(bank.get(id1).firstName + " new credit amount is $" + bank.get(id1).CreditAcc.balance + " " + bank.get(id2).firstName + " new checking amount is $" + bank.get(id2).CheckingAcc.balance);

                    bank.get(id1).stat.setEndCr(bank.get((id1)).CreditAcc.balance);
                    bank.get(id2).stat.setEndCh(bank.get((id2)).CheckingAcc.balance);
                    bank.get(id1).stat.addTrans(bank.get(id1).firstName + " paid " + bank.get(id2).firstName + " $" + ammount);
                    bank.get(id2).stat.addTrans(bank.get(id1).firstName + " paid " + bank.get(id2).firstName + " $" + ammount);


                    try {//writes transaction to log
                        //FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(bank.get(id1).firstName + " paid " + bank.get(id2).firstName + " $" + ammount);
                        out.write("\r\n");
                        out.write(bank.get(id1).firstName + " new credit amount is $" + bank.get(id1).CreditAcc.balance + " " + bank.get(id2).firstName + " new checking amount is $" + bank.get(id2).CheckingAcc.balance);
                        out.write("\r\n");
                    } catch (IOException e) {
                        System.out.println("Error in translog");
                    }
                }
                System.out.println("sorry credit max");
            }
            else if (type.equalsIgnoreCase("h")) {
                if(bank.get(id1).CreditAcc.balance-ammount >= -bank.get(id1).CreditAcc.max) {
                    bank.get(id1).CreditAcc.subtract(ammount);
                    bank.get(id2).SavingAcc.add(ammount);
                    System.out.println(bank.get(id1).firstName + " paid " + bank.get(id2).firstName + " $" + ammount);
                    System.out.println(bank.get(id1).firstName + " new credit amount is $" + bank.get(id1).CreditAcc.balance + " " + bank.get(id2).firstName + " new saving amount is $" + bank.get(id2).SavingAcc.balance);

                    bank.get(id1).stat.setEndCr(bank.get((id1)).CreditAcc.balance);
                    bank.get(id2).stat.setEndS(bank.get((id2)).SavingAcc.balance);
                    bank.get(id1).stat.addTrans(bank.get(id1).firstName + " paid " + bank.get(id2).firstName + " $" + ammount);
                    bank.get(id2).stat.addTrans(bank.get(id1).firstName + " paid " + bank.get(id2).firstName + " $" + ammount);

                    try {//writes transaction to log
                        //FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(bank.get(id1).firstName + " paid " + bank.get(id2).firstName + " $" + ammount);
                        out.write("\r\n");
                        out.write(bank.get(id1).firstName + " new credit amount is $" + bank.get(id1).CreditAcc.balance + " " + bank.get(id2).firstName + " new saving amount is $" + bank.get(id2).SavingAcc.balance);
                        out.write("\r\n");

                    } catch (IOException e) {
                        System.out.println("Error in translog");
                    }
                }
                System.out.print("Sorry credit max");
            }
            else if (type.equalsIgnoreCase("i")) {
                if(bank.get(id2).CreditAcc.balance>= -ammount && bank.get(id1).CreditAcc.balance-ammount >= -bank.get(id1).CreditAcc.max) {
                    bank.get(id1).CreditAcc.subtract(ammount);
                    bank.get(id2).CreditAcc.add(ammount);
                    System.out.println(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                    System.out.println(bank.get(id1).firstName +" new credit amount is $"+bank.get(id1).CreditAcc.balance+" "+ bank.get(id2).firstName +" new credit amount is $"+bank.get(id2).CreditAcc.balance);

                    bank.get(id1).stat.setEndCr(bank.get((id1)).CreditAcc.balance);
                    bank.get(id2).stat.setEndCr(bank.get((id2)).CreditAcc.balance);
                    bank.get(id1).stat.addTrans(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                    bank.get(id2).stat.addTrans(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);


                    try {//writes transaction to log
                       // FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(bank.get(id1).firstName +" paid "+ bank.get(id2).firstName +" $"+ammount);
                        out.write("\r\n");
                        out.write(bank.get(id1).firstName +" new credit amount is $"+bank.get(id1).CreditAcc.balance+" "+ bank.get(id2).firstName +" new credit amount is $"+bank.get(id2).CreditAcc.balance);
                        out.write("\r\n");

                    }
                    catch(IOException e){
                        System.out.println("Error in translog");
                    }
                }
                else{
                    System.out.println("Sorry ammount is too large ");
                }
            }
            else{
                System.out.println("Sorry that isnt an option");
            }
    }
    public static void deposit(String dep, Hashtable<String,Customer>bank, Customer person, double dammount, FileWriter out ){
        switch (dep) {
            case "3.a":
                person.CheckingAcc.add(dammount);
                System.out.println(person.firstName +" deposited $"+ dammount +" into checking new balance is $" +person.CheckingAcc.balance);
                person.stat.setEndCh(person.CheckingAcc.balance);
                person.stat.addTrans(person.firstName +" deposited $"+ dammount);
                try {//writes transaction to log
                    //FileWriter out = new FileWriter("transLog.txt", false);
                    out.write(person.firstName +" deposited $"+ dammount +" into checking new balance is $" +person.CheckingAcc.balance);
                    out.write("\r\n");

                }
                catch(IOException e){
                    System.out.println("Error in translog");
                }
                break;
            case "3.b":
                person.SavingAcc.add(dammount);
                System.out.println(person.firstName +" deposited $"+ dammount +" into saving new balance is $" +person.SavingAcc.balance);
                person.stat.setEndCh(person.SavingAcc.balance);
                person.stat.addTrans(person.firstName +" deposited $"+ dammount);
                try {//writes transaction to log
                    //FileWriter out = new FileWriter("transLog.txt", false);
                    out.write(person.firstName +" deposited $"+ dammount +" into saving new balance is $" +person.SavingAcc.balance);
                    out.write("\r\n");

                }
                catch(IOException e){
                    System.out.println("Error in translog");
                }
                break;
            case "3.c":
                if(-dammount >= person.CreditABalance ) {

                    person.CreditAcc.add(dammount);
                    System.out.println(person.firstName + " deposited $" + dammount + " into credit new balance is $" + person.CreditAcc.balance);
                    person.stat.setEndCh(person.CreditAcc.balance);
                    person.stat.addTrans(person.firstName +" deposited $"+ dammount);
                    try {//writes transaction to log
                        //FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(person.firstName +" deposited $"+ dammount +" into credit new balance is $" +person.CreditAcc.balance);
                        out.write("\r\n");

                    }
                    catch(IOException e){
                        System.out.println("Error in translog");
                    }
                }
                else{
                    System.out.println("Amount is too large to deposit in credit ");
                }
                break;
            default:
                System.out.println("Sorry that isnt an option");
                break;
        }
    }
    public static void withdraw(String with, Hashtable<String,Customer> bank,double wammount, Customer person,FileWriter out){
        switch (with) {
            case "4.a":
                if (person.CheckingAcc.balance>= wammount) {
                    person.CheckingAcc.subtract(wammount);

                    person.stat.setEndCh(person.CheckingAcc.balance);
                    person.stat.addTrans(person.firstName +" deposited $"+ wammount);

                    System.out.println(person.firstName + " withdrew $" + wammount + " from checking new balance is $" + person.CheckingAcc.balance);
                    try {//writes transaction to log
                        //FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(person.firstName + " withdrew $" + wammount + " from checking new balance is $" + person.CheckingAcc.balance);
                        out.write("\r\n");

                    }

                    catch(IOException e){
                        System.out.println("Error in translog");
                    }

                }
                else{
                    System.out.println("Amount is to larger to withdraw");

                }
                break;
            case "4.b":
                if(person.SavingAcc.balance>=wammount) {
                    person.SavingAcc.subtract(wammount);
                    person.stat.setEndCh(person.SavingAcc.balance);
                    person.stat.addTrans(person.firstName +" deposited $"+ wammount);

                    System.out.println(person.firstName + " withdrew $" + wammount + " from saving new balance is $" + person.SavingAcc.balance);
                    try {//writes transaction to log
                        //FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(person.firstName + " withdrew $" + wammount + " from saving new balance is $" + person.SavingAcc.balance);
                        out.write("\r\n");

                    } catch (IOException e) {
                        System.out.println("Error in translog");
                    }

                }
                else{
                    System.out.println("Amount is to large to withdraw.");

                }
                break;
            case "4.c":
                if(-person.CreditAcc.max <= person.CreditAcc.balance-wammount) {
                    System.out.println(person.firstName + " withdrewl $" + wammount + " from credit new balance is $" + person.CreditAcc.balance);
                    person.CreditAcc.subtract(wammount);
                    person.stat.setEndCh(person.CreditAcc.balance);
                    person.stat.addTrans(person.firstName +" withdrew $"+ wammount);
                    try {//writes transaction to log
                        //FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(person.firstName + " withdrewl $" + wammount + " from credit new balance is $" + person.CreditAcc.balance);
                        out.write("\r\n");

                    } catch (IOException e) {
                        System.out.println("Error in translog");
                    }

                }
                else{
                    System.out.println("Sorry that amount is over the credit max");

                }
                break;
            default:
                System.out.println("Sorry that isnt an option");

        }
    }

    /***
     * Takes the bank(hash table) the id of the user and the adds to one account and subtracts from the other account
     * @param bank the bank records
     * @param id the id to find the person
     * @param type which acounts to use
     * @param ammount teh amount of moeny being transfer
     * @param out used to create txt log
     */
    public static void transfer(Hashtable<String, Customer> bank, String id, int type, double ammount, FileWriter out) {//takes the id of teh user and transfers their money from one account to another
        switch (type) {//used to see which accounts should be used
            case 1:
                if(bank.get(id).CheckingAcc.balance >= ammount&& bank.get(id).CreditAcc.balance <=-ammount) {
                    bank.get(id).CheckingAcc.subtract(ammount);
                    bank.get(id).CreditAcc.add(ammount);

                    bank.get(id).stat.setEndCh(bank.get(id).CheckingAcc.balance);
                    bank.get(id).stat.setEndCr(bank.get(id).CreditAcc.balance);

                    bank.get(id).stat.addTrans(bank.get(id).firstName+" transfered $ "+ammount+" to checking to credit.");

                    System.out.println(bank.get(id).firstName+" transfered $ "+ammount+" to checking to credit. New balances : $" + bank.get(id).CheckingAcc.balance+ " $"+bank.get(id).CreditAcc.balance);
                    try {//writes transaction to log
                        //FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(bank.get(id).firstName+" transfered $ "+ammount+"to checking to credit. New balances : $" + bank.get(id).CheckingAcc.balance+ " $"+bank.get(id).CreditAcc.balance);
                        out.write("\r\n");

                    }

                    catch(IOException e){
                        System.out.println("Error in translog");
                    }
                }
                else{
                    System.out.println("Sorry insufficient funds or amount is too large  ");
                }
                break;
            case 2:
                if(bank.get(id).CheckingAcc.balance >= ammount) {
                    bank.get(id).CheckingAcc.subtract(ammount);
                    bank.get(id).SavingAcc.add(ammount);

                    bank.get(id).stat.setEndCh(bank.get(id).CheckingAcc.balance);
                    bank.get(id).stat.setEndS(bank.get(id).SavingAcc.balance);

                    bank.get(id).stat.addTrans(bank.get(id).firstName+" transfered $ "+ammount+" to checking to saving.");

                    System.out.println(bank.get(id).firstName+" transfered $ "+ammount+"to checking to saving. New balances : $" + bank.get(id).CheckingAcc.balance+ " $"+bank.get(id).SavingAcc.balance);
                    try {//writes transaction to log
                        //FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(bank.get(id).firstName+" transfered $ "+ammount+"to checking to saving. New balances : $" + bank.get(id).CheckingAcc.balance+ " $"+bank.get(id).SavingAcc.balance);
                        out.write("\r\n");

                    }
                    catch(IOException e){
                        System.out.println("Error in translog");
                    }
                }
                else{
                    System.out.println("Sorry insufficient funds ");
                }
                break;
            case 3:
                if(bank.get(id).CreditAcc.balance-ammount> bank.get(id).CreditAcc.max) {
                    bank.get(id).CreditAcc.subtract(ammount);
                    bank.get(id).CheckingAcc.add(ammount);
                    System.out.println(bank.get(id).firstName + " transfered $ " + ammount + "to credit to checking. New balances : $" + bank.get(id).CreditAcc.balance + " $" + bank.get(id).CheckingAcc.balance);

                    bank.get(id).stat.setEndCr(bank.get(id).CreditAcc.balance);
                    bank.get(id).stat.setEndCh(bank.get(id).CheckingAcc.balance);

                    bank.get(id).stat.addTrans(bank.get(id).firstName + " transfered $ " + ammount + " to credit to checking.");

                    try {//writes transaction to log
                        // FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(bank.get(id).firstName + " transfered $ " + ammount + "to credit to checking. New balances : $" + bank.get(id).CreditAcc.balance + " $" + bank.get(id).CheckingAcc.balance);
                        out.write("\r\n");

                    } catch (IOException e) {
                        System.out.println("Error in translog");
                    }
                }
                else {
                    System.out.println("sorry over credit max");
                }
                break;
            case 4:
                if(bank.get(id).CreditAcc.balance-ammount> bank.get(id).CreditAcc.max) {
                    bank.get(id).CreditAcc.subtract(ammount);
                    bank.get(id).SavingAcc.add(ammount);
                    System.out.println(bank.get(id).firstName + " transfered $ " + ammount + "to credit to saving. New balances : $" + bank.get(id).CreditAcc.balance + " $" + bank.get(id).SavingAcc.balance);

                    bank.get(id).stat.setEndCr(bank.get(id).CreditAcc.balance);
                    bank.get(id).stat.setEndS(bank.get(id).SavingAcc.balance);

                    bank.get(id).stat.addTrans(bank.get(id).firstName + " transfered $ " + ammount + " to credit to saving.");

                    try {//writes transaction to log
                        //FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(bank.get(id).firstName + " transfered $ " + ammount + "to credit to saving. New balances : $" + bank.get(id).CreditAcc.balance + " $" + bank.get(id).SavingAcc.balance);
                        out.write("\r\n");

                    } catch (IOException e) {
                        System.out.println("Error in translog");
                    }
                }
                else{
                    System.out.println("Sorry over credit max.");
                }
                break;
            case 5:
                if(bank.get(id).SavingAcc.balance >= ammount) {
                    bank.get(id).SavingAcc.subtract(ammount);
                    bank.get(id).CheckingAcc.add(ammount);
                    System.out.println(bank.get(id).firstName+" transfered $ "+ammount+"to saving to checking. New balances : $" + bank.get(id).SavingAcc.balance+ " $"+bank.get(id).CheckingAcc.balance);

                    bank.get(id).stat.setEndS(bank.get(id).SavingAcc.balance);
                    bank.get(id).stat.setEndCh(bank.get(id).CheckingAcc.balance);

                    bank.get(id).stat.addTrans(bank.get(id).firstName+" transfered $ "+ammount+" to saving to checking.");

                    try {//writes transaction to log
                        //FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(bank.get(id).firstName+" transfered $ "+ammount+"to saving to checking. New balances : $" + bank.get(id).SavingAcc.balance+ " $"+bank.get(id).CheckingAcc.balance);
                        out.write("\r\n");

                    }
                    catch(IOException e){
                        System.out.println("Error in translog");
                    }
                }
                else{
                    System.out.println("Sorry insufficient funds ");
                }
                break;
            case 6:
                if(bank.get(id).SavingAcc.balance >= ammount && bank.get(id).CreditAcc.balance <= -ammount) {
                    bank.get(id).SavingAcc.subtract(ammount);
                    bank.get(id).CreditAcc.add(ammount);
                    System.out.println(bank.get(id).firstName+" transfered $ "+ammount+"to saving to credit. New balances : $" + bank.get(id).SavingAcc.balance+ " $"+bank.get(id).CreditAcc.balance);

                    bank.get(id).stat.setEndS(bank.get(id).SavingAcc.balance);
                    bank.get(id).stat.setEndCr(bank.get(id).CreditAcc.balance);

                    bank.get(id).stat.addTrans(bank.get(id).firstName+" transfered $ "+ammount+" to saving to credit.");

                    try {//writes transaction to log
                        //FileWriter out = new FileWriter("transLog.txt", false);
                        out.write(bank.get(id).firstName+" transfered $ "+ammount+"to saving to credit. New balances : $" + bank.get(id).SavingAcc.balance+ " $"+bank.get(id).CreditAcc.balance);
                        out.write("\r\n");

                    }
                    catch(IOException e){
                        System.out.println("Error in translog");
                    }
                }
                else{
                    System.out.println("Sorry insufficient funds or amount is too large");
                }
                break;
            default:
                System.out.println("sorry that isn't an option");
        }

    }
    public static void test(Hashtable<String,Customer>bank) {
        try {//makes sure file can be found
            String log = "src/Transaction Actions(7) (1).csv";
            File actions = new File(log);
            Scanner scan = new Scanner(actions);

            String curent = scan.nextLine();
            //System.out.println(curent);
//
            while (curent != null) {
                //System.out.println(curent);
                var account = curent.split(",");
                System.out.println(account.length);
                curent = scan.nextLine();
            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void planedTrans(Hashtable<String, Customer>bank,FileWriter out) {
        try {//makes sure file can be found
            String log = "src/Transaction Actions(7) (1).csv";
            File actions = new File(log);
            Scanner scan = new Scanner(actions);

            String curent = scan.nextLine();
            //System.out.println(curent);
//
            while (curent != null) {
                //System.out.println(curent);
                var account = curent.split(",");
                if (account[0].equalsIgnoreCase("From First Name")) {//makes sure the labels are put into an checking object
                }
                else {
                    //System.out.println(account[0]);
                    //int len = account.length;
                    //System.out.println(len);
                    switch(account[3]){
                        case "inquires":
                            if(findCustomer(bank,account[0],account[1]) ==null) {
                            }
                            else{
                                Customer InquireAccount = findCustomer(bank,account[0],account[1]);
                                System.out.println(InquireAccount.printPersonInfo()+" Saving: "+InquireAccount.SavingAcc.print()+" Checking: "+InquireAccount.CheckingAcc.print()+" Credit: "+InquireAccount.CreditAcc.print());
                            }


                            break;
                        case "withdraws":
                            Customer withAccount = findCustomer(bank, account[0],account[1]);
                            String change ="0";
                            if (account[2].equalsIgnoreCase("checking")){
                                change = "4.a";
                            }
                            else if(account[2].equalsIgnoreCase("saving")){
                                change = "4.b";
                            }
                            else if(account[2].equalsIgnoreCase("credit")){
                                change = " 4.c";
                            }
                            if(!change.equalsIgnoreCase("0")) {
                                double am = Double.parseDouble(account[7]);
                                withdraw(change,bank, am, withAccount, out);
                            }



                            break;
                        case"deposits":
                            Customer depAccount = findCustomer(bank, account[0],account[1]);
                            String Dchange ="0";
                            if (account[2].equalsIgnoreCase("checking")){
                                Dchange = "3.a";
                            }
                            else if(account[2].equalsIgnoreCase("saving")){
                                Dchange = "3.b";
                            }
                            else if(account[2].equalsIgnoreCase("credit")){
                                Dchange = " 3.c";
                            }
                            if(!Dchange.equalsIgnoreCase("0")) {
                                double am = Double.parseDouble(account[7]);
                                withdraw(Dchange,bank, am, depAccount, out);
                            }
                            break;
                        case "pays":
                            Customer from = findCustomer(bank,account[0],account[1]);
                            Customer to = findCustomer(bank,account[4],account[5]);
                            if(from ==null || to == null){
                                System.out.println("sorry account not found");
                            }
                            else {

                                String op = "0";
                                if (account[2].equalsIgnoreCase("checking") && account[6].equalsIgnoreCase("checking")) {
                                    op = "a";
                                } else if (account[2].equalsIgnoreCase("checking") && account[6].equalsIgnoreCase("saving")) {
                                    op = "b";
                                } else if (account[2].equalsIgnoreCase("checking") && account[6].equalsIgnoreCase("credit")) {
                                    op = "c";
                                } else if (account[2].equalsIgnoreCase("saving") && account[6].equalsIgnoreCase("checking")) {
                                    op = "d";
                                } else if (account[2].equalsIgnoreCase("saving") && account[6].equalsIgnoreCase("saving")) {
                                    op = "e";
                                } else if (account[2].equalsIgnoreCase("saving") && account[6].equalsIgnoreCase("credit")) {
                                    op = "f";
                                } else if (account[2].equalsIgnoreCase("credit") && account[6].equalsIgnoreCase("checking")) {
                                    op = "g";
                                } else if (account[2].equalsIgnoreCase("credit") && account[6].equalsIgnoreCase("saving")) {
                                    op = "h";
                                } else if (account[2].equalsIgnoreCase("credit") && account[6].equalsIgnoreCase("credit")) {
                                    op = "i";
                                }
                                if (!op.equalsIgnoreCase("0")) {
                                    pay(Double.parseDouble(account[7]), bank, op, from.idNumber, to.idNumber, out);
                                }
                            }
                            break;
                        case "transfers":
                            Customer Tfrom = findCustomer(bank,account[0],account[1]);
                            Customer Tto = findCustomer(bank,account[4],account[5]);
                            if(Tfrom ==null || Tto == null){
                                System.out.println("sorry account not found");
                            }
                            else{
                                int ch =0;
                                if (account[2].equalsIgnoreCase("checking") && account[6].equalsIgnoreCase("credit")){
                                    ch=1;
                                }
                                else if(account[2].equalsIgnoreCase("checking") && account[6].equalsIgnoreCase("saving")){
                                    ch=2;
                                }
                                else if(account[2].equalsIgnoreCase("credit") && account[6].equalsIgnoreCase("checking")){
                                    ch=3;
                                }
                                else if(account[2].equalsIgnoreCase("credit") && account[6].equalsIgnoreCase("saving")){
                                    ch=4;
                                }
                                else if(account[2].equalsIgnoreCase("saving") && account[6].equalsIgnoreCase("checking")){
                                    ch=5;
                                }
                                else if(account[2].equalsIgnoreCase("saving") && account[6].equalsIgnoreCase("credit")){
                                    ch=6;
                                }
                                if(ch != 0){
                                    transfer(bank,Tto.idNumber,ch,Double.parseDouble(account[7]),out);
                                }


                                }
                            break;
                        default:
                            System.out.println("Sorry action cannot be preformed information is missing");
                            break;
                    }

                }

                if(scan.hasNextLine()){
                    curent = scan.nextLine();
                }
                else{
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Hashtable<String, Customer> bank = new Hashtable<String, Customer>();
        bank = readFiles();
        Scanner scan = new Scanner(System.in);
        if (!bank.isEmpty()) {// makes sure the bank isnt empty
            try {
                FileWriter out = new FileWriter("transLog.txt", false);

                //System.out.println(bank.get("update").getIdNumber());
                boolean asking = true;
                while (asking) {

                    System.out.println("Please type your account number, 0 for bank manger, T for transaction reader, and -1 to exist");//if bank manger type 0
                    String person = scan.next();

                    if (person.equals("0")) {
                        boolean bankMan = true;
                        while (bankMan) {
                            System.out.println("A Inquire account by name");
                            System.out.println("B Inquire account by type/number");
                            System.out.println("C Print All");
                            System.out.println("D. Create Bankstatement");
                            System.out.println("E. Create new user");
                            System.out.println("F. LogOut");
                            String bankOp = scan.next();
                            switch (bankOp) {
                                case "A":
                                    System.out.println("What is the first name of the account you would like to inquire about?");
                                    String name = scan.next();
                                    System.out.println("What is the last name of the account you would like to inquire about?");
                                    String last = scan.next();
                                    Set<String> accountsKeys = bank.keySet();
                                    Iterator<String> cur = accountsKeys.iterator();
                                    while (cur.hasNext()) {// finds account with name
                                        String currBA = cur.next();
                                        if (bank.get(currBA).firstName.equalsIgnoreCase(name)) {
                                            if (bank.get(currBA).lastname.equalsIgnoreCase(last))
                                                System.out.println(bank.get(currBA).printPersonInfo() + " Credit: " + (bank.get(currBA).CreditAcc.print()) + " Saving: " + (bank.get(currBA).SavingAcc.print()) + "Checking:" + (bank.get(currBA).CheckingAcc.print()));
                                        }
                                        if (!cur.hasNext()) {
                                            System.out.println("Sorry account could be found");
                                        }
                                    }

                                    break;
                                case "B":
                                    System.out.println("What account type?");
                                    String type = scan.next();
                                    System.out.println("What is the account number?");
                                    String idNum = scan.next();
                                    if (bank.get(idNum) != null) {//finds account with id
                                        System.out.println((bank.get(idNum).printPersonInfo()) + " Credit: " + (bank.get(idNum).CreditAcc.print()) + " Saving: " + (bank.get(idNum).SavingAcc.print()) + "Checking:" + (bank.get(idNum).CheckingAcc.print()));
                                    } else {
                                        System.out.println("User cant be found");
                                    }
                                    break;
                                case "C":
                                    Set<String> allkeys = bank.keySet();
                                    Iterator<String> goingThr = allkeys.iterator();
                                    //int count = 0;
                                    while (goingThr.hasNext()) {//prints out all the information
                                        //count++;
                                        String currentAcc = goingThr.next();
                                        System.out.println(bank.get(currentAcc).printPersonInfo() + " Checking: " + bank.get(currentAcc).CheckingAcc.print() + " Saving: " + bank.get(currentAcc).SavingAcc.print() + " Credit: " + bank.get(currentAcc).CreditAcc.print());
                                    }
                                    break;
                                //System.out.println(count);
                                case "D":
                                    System.out.println("Please enter the first name.");
                                    String fname = scan.next();
                                    System.out.println("please enter the last name.");
                                    String lname = scan.next();
                                    LinkedList<Customer> list = new LinkedList<Customer>();
                                    list.add(findCustomer(bank,fname,lname));
                                    findCustomer(bank,fname,lname).stat.printBalanceStat();
                                    break;

                                case "E":
                                    int newid;
                                    newid = Integer.parseInt(bank.get("update").getIdNumber());
                                    System.out.println("what is your first name?");
                                    String f = scan.next();
                                    System.out.println("what is your last name?");
                                    String l = scan.next();
                                    System.out.println("what is your birthday ?");
                                    String date = scan.next();
                                    System.out.println("what is your address?");
                                    String ad = scan.next();
                                    System.out.println("what is your phone number?");
                                    String num = scan.next();
                                    System.out.println("Please enter starting saving balance");
                                    try {
                                        double Sban = scan.nextDouble();

                                    System.out.println("Open a checking if yes please enter starting balance enter -1 if no.");
                                    double Chban = scan.nextDouble();
                                    System.out.println("Open up a credit account if yes enter starting balance enter 1 if no ");
                                    double Cred = scan.nextDouble();
                                    if (Cred > 0) {
                                        System.out.println("What is the credit max?");
                                        double max = scan.nextDouble();
                                        if (Sban > 0 && Cred < 0) {
                                            newUser(bank, f, l, date, ad, num, "" + newid, Sban, Cred, Chban, max);
                                        } else {
                                            newUser(bank, f, l, date, ad, num, "" + newid, -1, Cred, Chban, max);
                                        }

                                    } else {
                                        newUser(bank, f, l, date, ad, num, "" + newid, Sban, -1, Chban, -1);
                                    }
                                    bank.get("update").setIdNumber("" + newid);
                                    } catch(Exception e){
                                        System.out.println("sorry cant input value");
                                    }
                                    break;

                                case "F":
                                    bankMan = false;
                                    break;
                                default:
                                    System.out.println("Sorry that isnt an option");
                                    break;

                            }
                        }
                    }
                    else if (person.equalsIgnoreCase("T")) {
                        planedTrans(bank, out);
                    }
                    else if (bank.get(person) != null) {
                        boolean loggedIn = true;
                        while (loggedIn) {//allows users to pick which option they would like

                            System.out.println("Please choose an action:");
                            System.out.println("1: Inquire Balance");
                            System.out.println("2: Make Payment");
                            System.out.println("3: Deposit");
                            System.out.println("4: Withdraw");
                            System.out.println("5: Transfer money");
                            System.out.println("6: Log out");
                            try {
                                int choice = scan.nextInt();
                                switch (choice) {// lets user decide what to do
                                    case 1:
                                        try {//writes transaction to log
                                            //FileWriter out = new FileWriter("transLog.txt", false);
                                            out.write(bank.get(person).firstName + " made an inquire about their balances");
                                            out.write("\r\n");
                                            out.write((bank.get(person).printPersonInfo()) + " Credit: " + (bank.get(person).CreditAcc.print()) + " Saving: " + (bank.get(person).SavingAcc.print()));
                                            out.write("\r\n");

                                        } catch (IOException e) {
                                            System.out.println("Error in translog");
                                        }

                                        System.out.println(bank.get(person).firstName + " made an inquire about their balances");
                                        System.out.println((bank.get(person).printPersonInfo()) + " Credit: " + (bank.get(person).CreditAcc.print()) + " Saving: " + (bank.get(person).SavingAcc.print()) + "Checking:" + (bank.get(person).CheckingAcc.print()));
                                        break;
                                    case 2:

                                        System.out.println("Please enter account number of person you are paying");
                                        String recieve = scan.next();
                                        if (bank.get(recieve) != null && !recieve.equalsIgnoreCase(person)) {
                                            System.out.println("Please enter ammount");
                                            double ammount = scan.nextDouble();
                                            System.out.println("Would you like to pay:");
                                            System.out.println("a Checking to checking");
                                            System.out.println("b Checking to savings");
                                            System.out.println("c Checking to credit");
                                            System.out.println("d savings to checking");
                                            System.out.println("e saving to saving");
                                            System.out.println("f saving to credit");
                                            System.out.println("g credit to checking");
                                            System.out.println("h credit to saving");
                                            System.out.println("i credit to credit");
                                            String op = scan.next();// which accounts to be used
                                            pay(ammount, bank, op, person, recieve, out);
                                        } else {
                                            System.out.println("Sorry that account number cant be accepted");
                                        }
                                        break;
                                    case 3:
                                        System.out.println("please enter amount");
                                        double dammount = scan.nextDouble();
                                        if (dammount <= 0) {
                                            System.out.println("Sorry cannot deposit that");
                                        } else {
                                            System.out.println("3.a checking");
                                            System.out.println("3.b savings");
                                            System.out.println("3.c credit");
                                            String dep = scan.next();
                                            deposit(dep, bank, bank.get(person), dammount, out);
                                        }
                                        break;
                                    case 4:
                                        System.out.println("please enter amount");
                                        double wammount = scan.nextDouble();
                                        if (wammount <= 0) {
                                            System.out.println("Sorry cannot withdraw that amount");
                                        } else {
                                            System.out.println("4.a checking");
                                            System.out.println("4.b savings");
                                            System.out.println("4.c credit");
                                            String with = scan.next();
                                            withdraw(with, bank, wammount, bank.get(person), out);

                                        }
                                        break;
                                    case 5:
                                        System.out.println("please enter ammount");
                                        double trans = scan.nextDouble();
                                        System.out.println("please choose an option");
                                        System.out.println("1.Checking to credit");
                                        System.out.println("2.Checking to saving");
                                        System.out.println("3.Credit to Checking");
                                        System.out.println("4.Credit to Saving");
                                        System.out.println("5.Saving to Checking");
                                        System.out.println("6.Saving to credit");
                                        int opTrans = scan.nextInt();
                                        transfer(bank, person, opTrans, trans, out);
                                        break;
                                    case 6:
                                        loggedIn = false;
                                        break;
                                    default:
                                        System.out.println("Sorry that isnt an option");


                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Sorry that is not an option, you are being logged out  ");
                                break;
                            }


                        }
                    }
                    else if (person.equals("-1")){
                        try {
                            FileWriter newcsv = new FileWriter("Updated.csv");//creates the new csv
                            Set<String> allkeys = bank.keySet();
                            Iterator<String> goingThr = allkeys.iterator();
                            //int count = 0;
                            while (goingThr.hasNext()) {
                                //count++;
                                String currentAcc = goingThr.next();
                                newcsv.append(bank.get(currentAcc).printPersonInfo() + " Checking: " + bank.get(currentAcc).CheckingAcc.print() + " Saving: " + bank.get(currentAcc).SavingAcc.print() + " Credit: " + bank.get(currentAcc).CreditAcc.print());
                                newcsv.append("\n");

                            }
                            newcsv.flush();
                            newcsv.close();
                        } catch (IOException e) {


                        }
                        out.close();
                        out.flush();
                        asking = false;
                    }
                    else {
                        System.out.println("Sorry incorrect number");
                    }
                }
                out.close();
                out.flush();


            }
            catch(IOException e){
               //System.out.println("Error in trans log");
            }
            }

        }
}

