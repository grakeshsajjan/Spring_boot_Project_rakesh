package com.webtask;

public class BaseException extends Exception{
	
	private String message = null;
	
	public BaseException()
	{
		super();
		
	}
	
	public BaseException(String message)
	{
		super(message);
		this.message = message;
		
	}
	
	@Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }

}
