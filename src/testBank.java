import org.junit.jupiter.api.DisplayName;
 import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**this method is meant to show that I can use junit and my bank returns appropriate parameters
 *
 */
public class testBank {
    /**Test to make sure that I am creating a database
     *success
     */
     @Test
     @DisplayName("Tests #1 for readFile.createAccountDatabase")
     public void testCreateAccountDatabase1(){
         assertNotNull(readFile.createAccountDatabase());

         }

    /** Test to make sure that the database I'm creating contains the info it should
     *success
     */
    @Test
    @DisplayName("Tests #2 for readFile.createAccountDatabase")
    public void testCreateAccountDatabase2(){
        assertEquals("Mickey", readFile.createAccountDatabase().get_table()[0][0].get_First_Name());
    }

    /**Test to make sure the database contains the account number it should
     */
    @Test
    @DisplayName("Test #3 for readFile.creatAccountDatabase")
    public void testCreatAccountDatabase3(){
        assertEquals(1000,readFile.createAccountDatabase().get_table()[0][0].get_Account_Number());
    }

    /**Test to make sure the database is setting the right balances
     */
    @Test
    @DisplayName("Test #4 for readFile.creatAccountDatabase")
    public void testCreatAccountDatabase4(){
        assertEquals(812.85,readFile.createAccountDatabase().get_table()[0][0].get_Starting_Balance());
    }

    /** test to make sure that Credit accounts begin in the space I think they do [200][0]
     *success
     */
    @Test
    @DisplayName("Test 1 for backEnd.findAccount")
    public void testFindAccount1(){
         hashTable testDatabase = readFile.createAccountDatabase();
         long testAccountNumber = 3000;
         assertEquals(testDatabase.get_table()[200][0], backEnd.findAccount(testAccountNumber, testDatabase));

    }

    /** test to make sure that checking accounts begin where I think they do [0][0]
     *Success
     */
    @Test
    @DisplayName("Test 2 for backed.findAccount")
    public void testFindAccount2(){
        hashTable testDatabase = readFile.createAccountDatabase();
        long testAccountNumber = 1000;
        assertEquals(testDatabase.get_table()[0][0], backEnd.findAccount(testAccountNumber, testDatabase));
    }

    /** test to make sure that savings accounts begin where I think they do [100][0]
     *Success
     */
    @Test
    @DisplayName("Test 3 for backed.findAccount")
    public void testFindAccount3(){
        hashTable testDatabase = readFile.createAccountDatabase();
        long testAccountNumber = 2000;
        assertEquals(testDatabase.get_table()[100][0], backEnd.findAccount(testAccountNumber, testDatabase));
    }

    /** test to make sure that find account is returning the right info
     *Success
     */
    @Test
    @DisplayName("Test 4 for backed.findAccount")
    public void testFindAccount4(){
        hashTable testDatabase = readFile.createAccountDatabase();
        long testAccountNumber = 1000;
        assertEquals(testDatabase.get_table()[0][0].get_First_Name(), backEnd.findAccount(testAccountNumber, testDatabase).get_First_Name());
    }

    /** test to make sure that there is a -1 in case of user exit
     *Success
     */
    @Test
    @DisplayName("Test 1 for backEnd.userAction()")
    public void testUserAction1(){
        hashTable testDatabase = readFile.createAccountDatabase();
        Account testAccount = testDatabase.get_table()[0][0];
        assertEquals(-1, backEnd.userAction(5, testAccount, testDatabase));
    }

    /**test to make sure there is a 0 in case of successful balance checking
     *Success
     */
    @Test
    @DisplayName("Test 2 for backEnd.userAction()")
    public void testUserAction2(){
        hashTable testDatabase = readFile.createAccountDatabase();
        Account testAccount = testDatabase.get_table()[0][0];
        assertEquals(0, backEnd.userAction(4, testAccount, testDatabase));
    }

    /**test to make sure there is a 0 in case of successful balance checking
     *Success
     */
    @Test
    @DisplayName("Test 3 for backEnd.userAction()")
    public void testUserAction3(){
        hashTable testDatabase = readFile.createAccountDatabase();
        Account testAccount = testDatabase.get_table()[0][0];
        assertEquals(0, backEnd.userAction(4, testAccount, testDatabase));
    }

    /**test to make sure there is a 0 in case of successful balance checking
     *Success
     */
    @Test
    @DisplayName("Test 4 for backEnd.userAction()")
    public void testUserAction4(){
        hashTable testDatabase = readFile.createAccountDatabase();
        Account testAccount = testDatabase.get_table()[0][0];
        assertEquals(0, backEnd.userAction(4, testAccount, testDatabase));
    }


    /**Test to make sure that the set first name works in Customer class
     */
    @Test
    @DisplayName("Test 1 for customer class")
    public void testCustomer1(){
        Customer tempCustomer = new Customer();
        tempCustomer.setFirstName("Hello");
        assertEquals("Hello", tempCustomer.getFirstName());
    }


    /**Test to make sure that the set last name works in Customer class
     */
    @Test
    @DisplayName("Test 2 for customer class")
    public void testCustomer2(){
        Customer tempCustomer = new Customer();
        tempCustomer.setLastName("Goodbye");
        assertEquals("Goodbye", tempCustomer.getLastName());
    }


    /**Test to make sure that the set Address balance works in Customer class
     */
    @Test
    @DisplayName("Test 3 for customer class")
    public void testCustomer3(){
        Customer tempCustomer = new Customer();
        tempCustomer.setAddress("1500 Ahehim Drive, New York, TX 79915");
        assertEquals("1500 Ahehim Drive, New York, TX 79915", tempCustomer.getAddress());
    }


    /**Test to make sure that the set checking account works in Customer class
     */
    @Test
    @DisplayName("Test 4 for customer class")
    public void testCustomer4(){
        Customer tempCustomer = new Customer();
        Checking tempChecking = new Checking();
        tempCustomer.setCheck(tempChecking);
        assertNotNull(tempCustomer.getCheck());
    }
     }


