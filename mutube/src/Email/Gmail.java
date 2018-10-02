package Email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator{

	@Override

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("fltndnjs1234@gmail.com","Fltndnjs1234@");
    }
}
