import java.io.Serializable;

public class IllegalMoveException extends Exception implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public IllegalMoveException(String message)
	{
		super(message);
	}
}
