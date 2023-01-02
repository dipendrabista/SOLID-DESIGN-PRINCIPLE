package com.punojsoft.solidprinciple.dependencyinversion.begin;

//Common interface for classes formatting Message object
public interface Formatter {
	
	public String format(Message message) throws FormatException;
	
}
