package User.Exception;

import javax.servlet.ServletException;

public class DeleteFailException extends ServletException{
	
	public DeleteFailException(String message) {
		super(message);
	}
}
