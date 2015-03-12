package net.sourceforge.javafpdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class PdfMultiCellTable extends FPDF {
	private List<Integer> widths = new ArrayList<>();
	private List<Alignment> alignments = new ArrayList<>();

	public void setWidths(List<Integer> w) {
		// Set the array of column widths
		this.widths = w;
	}

	public void setAligns(List<Alignment> a) {
		// Set the array of column alignments
		this.alignments = a;
	}

	public void row(List<String> data) throws IOException {
		if (widths.size() != data.size()) {
			throw new IllegalArgumentException("The number of rows (" + data.size()
					+ ") is not equal to the number of widths (" + widths.size() + ")");
		}
		if (alignments.size() != data.size()) {
			throw new IllegalArgumentException("The number of rows (" + data.size()
					+ ") is not equal to the number of alignments (" + alignments.size() + ")");
		}
		if (this.state != PDFCreationState.PAGE) {
			throw new PDFCreationError("Add rows are only availible in PAGE state");
		}
		// Calculate the height of the row
		float nb = 0;
		for (int i = 0; i < data.size(); i++)
			nb = Math.max(nb, this.NbLines(this.widths.get(i), data.get(i)));
		float height = 5 * nb;
		// Issue a page break first if needed
		this.CheckPageBreak(height);
		// Draw the cells of the row
		for (int i = 0; i < data.size(); i++) {
			float width = this.widths.get(i);
			Alignment a = this.alignments.get(i);
			// Save the current position
			float x = this.getX();
			float y = this.getY();
			// Draw the border
			this.Rect(new Coordinate(x, y), width, height, DrawMode.SHAPE);
			// Print the text
			String txt = data.get(i);
			if (txt == null) {
				txt = "";
			}
			this.MultiCell(width, 5, txt, new Borders(false, false, false, false), a, false);
			// Put the position to the right of the cell
			this.setXY(x + width, y);
		}
		// Go to the next line
		this.Ln(height);
	}

	public PdfMultiCellTable() {
		super();
	}

	public PdfMultiCellTable(float unit) {
		super(unit);
	}

	public PdfMultiCellTable(Format format) {
		super(format);
	}

	public PdfMultiCellTable(Orientation orientation, float unit, Format format) {
		super(orientation, unit, format);
	}

	public PdfMultiCellTable(Orientation orientation, float unit) {
		super(orientation, unit);
	}

	public PdfMultiCellTable(Orientation orientation, Format format) {
		super(orientation, format);
	}

	public PdfMultiCellTable(Orientation orientation) {
		super(orientation);
	}

	private void CheckPageBreak(float h) throws IOException {
		// If the height h would cause an overflow, add a new page immediately
		if (this.getY() + h > this.pageBreakTrigger)
			this.addPage(this.currentOrientation);
	}

	private int NbLines(float w, String txt) {
		if (this.currentFont == null) {
			throw new PDFCreationError("No default Font. Use SetFont to set a default Font.");
		}
		// Computes the number of lines a MultiCell of width w will take
		Font cw = this.currentFont;
		if (w == 0)
			w = this.w - this.rMargin - this.x;
		float wmax = (w - 2 * this.cMargin) * 1000 / this.fontSize;
		if (txt == null) {
			txt = "";
		}
		String s = txt.replaceAll("\r", "");
		int nb = s.length();
		if ((nb > 0) && (s.charAt(nb - 1) == '\n'))
			nb--;
		int sep = -1;
		int i = 0;
		int j = 0;
		int l = 0;
		int nl = 1;
		while (i < nb) {
			char c = s.charAt(i);
			if (c == '\n') {
				i++;
				sep = -1;
				j = i;
				l = 0;
				nl++;
				continue;
			}
			if (c == ' ')
				sep = i;
			l += cw.getCw().get(c);
			if (l > wmax) {
				if (sep == -1) {
					if (i == j)
						i++;
				} else
					i = sep + 1;
				sep = -1;
				j = i;
				l = 0;
				nl++;
			} else
				i++;
		}
		return nl;
	}
}