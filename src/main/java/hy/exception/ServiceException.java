package hy.exception;

public class ServiceException extends Exception{
	public ServiceException() {
		// TODO Auto-generated constructor stub
	}
	public ServiceException(String msg) {
		super(msg);
	}
	public ServiceException(String msg,Throwable e) {
		super(msg,e);
	}
}
