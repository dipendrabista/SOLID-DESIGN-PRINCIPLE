package com.punojsoft.solidprinciple.dependencyinversion.end;

import java.io.IOException;

public class FormatException extends IOException {
	
	public FormatException(Exception cause) {
		super(cause);
	}
}
