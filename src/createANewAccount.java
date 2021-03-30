
import java.util.Scanner;
/**This class enables new accounts to be made from the console
 * @author Luis David Davila
 */
public class createANewAccount {
    /**Creates a new account and allows the user to choose to create multiple accounts or just a savings account
     * @param people the database that the new account will be added to
     */
    public static void createNew(PersonDatabase people){
        Scanner userInput = new Scanner(System.in);
        String userInfo = "";
        Customer tempCustomer = new Customer();
        while (!userInfo.equals(-1)){
            //gets the user's first name

            System.out.println("Thank you for choosing to open an account with us. (Enter -1 at any point to start over)");
            System.out.println("Please enter your first name.");
            userInfo = userInput.nextLine();
            if (userInfo.equals("-1"))
                continue;
            tempCustomer.setFirstName(userInfo);
            //gets the user's last name
            System.out.println("Thank you. Please enter your Last Name. (Enter -1 at any point to start over)");
            userInfo = userInput.nextLine();
            if (userInfo.equals("-1"))
                continue;
            //gets the user's DOB
            tempCustomer.setLastName(userInfo);
            System.out.println("Thank you. Please enter your Date of Birth in MM/DD/YYYY format. (Enter -1 at any point to start over)");
            userInfo = userInput.nextLine();
            if (userInfo.equals("-1"))
                continue;
            //gets the user's address
            tempCustomer.setDateOfBirth(userInfo);
            System.out.println("Thank you. Please enter your Address in Street, City, State Zip format. (Enter -1 at any point to start over)");
            userInfo = userInput.nextLine();
            if (userInfo.equals("-1"))
                continue;
            tempCustomer.setAddress(userInfo);


            //gets the user's phone number
            System.out.println("Thank you. Please enter your phone number. (Enter -1 at any point to start over)");
            userInfo = userInput.nextLine();
            if (userInfo.equals("-1"))
                continue;
            tempCustomer.setPhoneNumber(userInfo);
            long previousHighestIdPreserved = people.getHighestId();
            tempCustomer.setIDNum(people.getHighestId() +1);

            //creates some temporary accounts that we may or may not use
            Savings tempSavings = new Savings();
            Checking tempChecking = new Checking();
            Credit tempCredit = new Credit();

            //retrieves the savings account number from the previous largest ID, and creates a new one based on it
            //We need to subtract 1 so we don't get null
            tempSavings.set_Account_Number(people.get_table()[(int)previousHighestIdPreserved-1][0].getSave().get_Account_Number()+1);

            tempSavings.set_First_Name(tempCustomer.getFirstName());
            tempSavings.set_Last_Name(tempCustomer.getLastName());
            tempSavings.set_Starting_Balance(0.00);
            tempCustomer.setSave(tempSavings);
            //asks the user what they want to create
            System.out.println("Thank you so much for all that information. A courtesy savings account has been created for you.");
            System.out.println("What other account would you like to create?");
            System.out.println("1.) A Checking account.");
            System.out.println("2.) A Credit account.");
            System.out.println("3.) Both A Checking and a Credit account.");
            System.out.println("4.) No Other Account");
            userInfo = userInput.nextLine();
            switch (userInfo){
                case "1":
                    tempChecking.set_Account_Number(people.get_table()[(int)previousHighestIdPreserved-1][0].getCheck().get_Account_Number()+1);
                    tempChecking.set_Starting_Balance(0.00);
                    tempChecking.set_First_Name(tempCustomer.getFirstName());
                    tempChecking.set_Last_Name(tempCustomer.getLastName());
                    tempCustomer.setCheck(tempChecking);
                    people.add_Person(tempCustomer);
                    break;
                case "2":
                    tempCredit.set_Account_Number(people.get_table()[(int)previousHighestIdPreserved-1][0].getCheck().get_Account_Number()+1);
                    tempCredit.set_Starting_Balance(0.00);
                    tempCredit.set_First_Name(tempCustomer.getFirstName());
                    tempCredit.set_Last_Name(tempCustomer.getLastName());
                    tempCustomer.setCred(tempCredit);
                    people.add_Person(tempCustomer);
                    break;
                case "3":
                    tempChecking.set_Account_Number(people.get_table()[(int)previousHighestIdPreserved-1][0].getCheck().get_Account_Number()+1);
                    tempChecking.set_Starting_Balance(0.00);
                    tempChecking.set_First_Name(tempCustomer.getFirstName());
                    tempChecking.set_Last_Name(tempCustomer.getLastName());
                    tempCustomer.setCheck(tempChecking);
                    tempCredit.set_Account_Number(people.get_table()[(int)previousHighestIdPreserved-1][0].getCheck().get_Account_Number()+1);
                    tempCredit.set_Starting_Balance(0.00);
                    tempCredit.set_First_Name(tempCustomer.getFirstName());
                    tempCredit.set_Last_Name(tempCustomer.getLastName());
                    tempCustomer.setCred(tempCredit);
                    people.add_Person(tempCustomer);
                    break;
                case "4":
                    System.out.println("Your account was created successfully. You are now being securely logged out, you now access your account from the main menu.");
                    people.add_Person(tempCustomer);
                    break;
                default:
                    System.out.println("Sorry Something went wrong, we need to start over.");
                    continue;
            }
            System.out.println("Your accounts were created successfully. You are now being securely logged out, you now access your account from the main menu.");
            break;



        }


    }

}
