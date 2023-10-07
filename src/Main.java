
import controller.Manager;

public class Main {

    public static void main(String[] args) {
        String mChon[] = {"Create a new account.", "Login system."};
        Manager u = new Manager("==== USER MANAGEMENT SYSTEM ====", mChon,"Exit.");
        u.run();
        }
}
