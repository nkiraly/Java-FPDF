package net.sourceforge.javafpdf;

public class PDFCreationError extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	
	public PDFCreationError(String message) {
		super(message);
	}
}
