/* $Id: Borders.java,v 1.1 2008/03/05 14:37:20 ashmodai Exp $
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
package com.koadweb.javafpdf;

/**
 * Borders object. Tells FPDF which borders to draw.<br>
 * TODO: use LineStyle to define the styling of each border.
 * 
 * @author Alan Plum
 * @since 5 Mar 2008
 * @version $Revision: 1.1 $
 */
public class Borders {
	private final boolean	left;

	private final boolean	top;

	private final boolean	right;

	private final boolean	bottom;

	/**
	 * Constructor. Creates a Borders object with the given border settings.
	 * 
	 * @param left
	 *            whether to draw the left border
	 * @param top
	 *            whether to draw the top border
	 * @param right
	 *            whether to draw the right border
	 * @param bottom
	 *            whether to draw the bottom border
	 */
	public Borders(final boolean left, final boolean top, final boolean right,
			final boolean bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

	/**
	 * Constructor. Creates a Borders object with all borders set.
	 */
	public Borders() {
		this.left = true;
		this.top = true;
		this.right = true;
		this.bottom = true;
	}

	/**
	 * Gets the left border.
	 * 
	 * @return whether the left border is set.
	 */
	public boolean getLeft() {
		return this.left;
	}

	/**
	 * Gets the top border.
	 * 
	 * @return whether the top border is set.
	 */
	public boolean getTop() {
		return this.top;
	}

	/**
	 * Gets the right border.
	 * 
	 * @return whether the right border is set.
	 */
	public boolean getRight() {
		return this.right;
	}

	/**
	 * Gets the bottom border.
	 * 
	 * @return whether the bottom border is set.
	 */
	public boolean getBottom() {
		return this.bottom;
	}

	/**
	 * Get all borders.
	 * 
	 * @return whether all borders are set.
	 */
	public boolean getAll() {
		return this.left && this.top && this.right && this.bottom;
	}
}
