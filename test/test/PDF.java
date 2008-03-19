/* $Id$
 * (K) 2008 All Rites Reversed -- Reprint what you like.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so,  subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package test;

import java.io.File;
import java.io.IOException;

import net.sourceforge.javafpdf.Coordinate;
import net.sourceforge.javafpdf.FPDF;
import net.sourceforge.javafpdf.ImageType;

/**
 * DOCME
 * 
 * @author pluma
 * @since 14 Mar 2008
 * @version $Revision$
 */
public class PDF extends FPDF {

	/**
	 * @see net.sourceforge.javafpdf.FPDF#Footer()
	 */
	@Override
	public void Footer() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see net.sourceforge.javafpdf.FPDF#Header()
	 */
	@Override
	public void Header() {
		try {
			this.Image("ic-logo.png", //$NON-NLS-1$
					new Coordinate(10, 10), 100, 100, ImageType.PNG, 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * DOCME
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException {
		PDF pdf = new PDF();
		pdf.addPage();
		pdf.output(new File("test.pdf")); //$NON-NLS-1$
	}
}
