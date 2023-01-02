package com.punojsoft.solidprinciple.dependencyinversion.end;

public interface Formatter {
	
	public String format(Message message) throws FormatException;
	
}
