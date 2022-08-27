package hy.exception;

public class DataAccessException extends RuntimeException {
	public DataAccessException() {
		
	}
	public DataAccessException(String msg) {
		super(msg);
	}
	public DataAccessException(String msg,Throwable e) {
		super(msg,e);
	}
}
