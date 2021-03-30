//Nicole Avila
// CS3331
// 1/29/21
// Dr. Mejia
// PA2
// Abstract account class
//I confirm that the work of this assignment is completely my own. By turning in this assignment, I declare that I did not receive unauthorized assistance. Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by me alone.I confirm that the work of this assignment is completely my own. By turning in this assignment, I declare that I did not receive unauthorized assistance. Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by me alone.
    public abstract class Account {

        //public String id;
        public int accountNumber;
        public double balance;

        /***
         * The constructor for account
         //* @param id the id of the user
         * @param accountNumber teh account number
         * @param balance the money in the account
         */
        public Account(int accountNumber, double balance) {
            //this.id = id;
            this.accountNumber = accountNumber;
            this.balance = balance;
        }

        /***
         *A general constructor for the abstract accoutn class
         */
        public Account(){}

        /***
         * getter for id
         * @return teh id of the user
         */
        //public String getId() {
        //    return id;
      //  }

        /***
         * setter for id
         * @param id the id of the user
         */
        //public void setId(String id) {
          //  this.id = id;
        //}

        /***
         * getter for Account Number
         * @return teh account number
         */
        public int getAccountNumber() {
            return accountNumber;
        }

        /***
         * setter for account number
         * @param accountNumber the account number
         */
        public void setAccountNumber(int accountNumber) {
            this.accountNumber = accountNumber;
        }

        /***
         * getter for balance
         * @return the balance
         */
        public double getBalance() {
            return balance;
        }

        /***
         * setter for balance
         * @param balance the money in the account
         */

        public void setBalance(double balance) {
            this.balance = balance;
        }

        /***
         * A print fuction to print all the information
         * @return a string of the information such as account number and balance
         */
        public String print(){
            return("account number "+ accountNumber+" account balance $"+ balance+" ");
        }
    }


