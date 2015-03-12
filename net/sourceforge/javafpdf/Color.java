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
package net.sourceforge.javafpdf;

/**
 * DOCME
 * 
 * @author pluma
 * @since 5 Mar 2008
 * @version $Rev$
 */
public class Color {
	private final int	r;

	private final int	g;

	private final int	b;

	/**
	 * Constructor.
	 * 
	 * @param r
	 *            the red
	 * @param g
	 *            the green
	 * @param b
	 *            the blue
	 */
	public Color(final int r, final int g, final int b) {
		this.r = r % 256;
		this.g = g % 256;
		this.b = b % 256;
	}

	/**
	 * Constructor. Creates a grayscale color.
	 * 
	 * @param v
	 *            the value
	 */
	public Color(final int v) {
		this.r = v % 256;
		this.g = v % 256;
		this.b = v % 256;
	}

	/**
	 * DOCME
	 * 
	 * @return the value
	 */
	public int getV() {
		return (this.r + this.g + this.b) / 3;
	}

	/**
	 * Checks whether the color is grayscale.
	 * 
	 * @return <code>true</code> if all three colors are identical;
	 *         <code>false</code> otherwise.
	 */
	public boolean isGrayscale() {
		return ((this.r == this.g) && (this.g == this.b));
	}

	/**
	 * DOCME
	 * 
	 * @return the red
	 */
	public int getR() {
		return this.r;
	}

	/**
	 * DOCME
	 * 
	 * @return the green
	 */
	public int getG() {
		return this.g;
	}

	/**
	 * DOCME
	 * 
	 * @return the blue
	 */
	public int getB() {
		return this.b;
	}
}
