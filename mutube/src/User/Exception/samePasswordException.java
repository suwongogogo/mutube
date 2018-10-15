package User.Exception;

import javax.servlet.ServletException;

public class samePasswordException extends ServletException {

	public samePasswordException(String message) {
		super(message);
	}

}
