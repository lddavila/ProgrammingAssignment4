public class CreditException  extends Exception{
    CreditException(){
        super("Balance cant be positive.");
    }
}
