/* $Id: Coordinate.java,v 1.1 2008/03/05 14:37:20 ashmodai Exp $
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
 * A two-dimensional coordinate.
 * 
 * @author Alan Plum
 * @since 5 Mar 2008
 * @version $Revision: 1.1 $
 */
public class Coordinate {
	private final float	x;

	private final float	y;

	/**
	 * Constructor.
	 * 
	 * @param x
	 *            the x position
	 * @param y
	 *            the y position
	 */
	public Coordinate(final float x, final float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor.
	 * 
	 * @param x
	 *            the x position
	 * @param y
	 *            the y position
	 */
	public Coordinate(final double x, final double y) {
		this.x = (float) x;
		this.y = (float) y;
	}

	/**
	 * DOCME
	 * 
	 * @return the x
	 */
	public float getX() {
		return this.x;
	}

	/**
	 * DOCME
	 * 
	 * @return the y
	 */
	public float getY() {
		return this.y;
	}
}
