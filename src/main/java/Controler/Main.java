package Controler;
import View.*;

public class Main {
    public static void main(String []args)
    {
        LoginView login = new LoginView();
        RegisterView register = new RegisterView();
        AdminView adminView = new AdminView();
        AdminProfileView adminProfileView = new AdminProfileView();
        CasierView casierView = new CasierView();
        Controller controller = new Controller(login,register,adminView, adminProfileView, casierView);

    }



}
