package starter.exception;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = -662842511282111817L;

	public BusinessException() {
		super();
	}
	
	public BusinessException(Throwable t) {
		super(t);
	}
	
	public BusinessException(String msg) {
		super(msg);
	}
}