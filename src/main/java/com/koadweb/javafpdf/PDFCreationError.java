package com.koadweb.javafpdf;

public class PDFCreationError extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	
	public PDFCreationError(String message) {
		super(message);
	}
}
