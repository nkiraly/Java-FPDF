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
 * @author Alan Plum
 * @since 5 Mar 2008
 * @version $Rev$
 */
public class LineStyle {
	/**
	 * Cap style.
	 * 
	 * @author Alan Plum
	 * @since 5 Mar 2008
	 * @version $Rev$
	 */
	public static enum Cap {
		/** Butt. */
		BUTT(0),
		/** Round. */
		ROUND(1),
		/** Square. */
		SQUARE(2);

		private final int	style;

		private Cap(final int style) {
			this.style = style;
		}

		/**
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return String.valueOf(this.style);
		}
	}

	/**
	 * Join style.
	 * 
	 * @author Alan Plum
	 * @since 5 Mar 2008
	 * @version $Rev$
	 */
	public static enum Join {
		/** Miter. */
		MITER(0),
		/** Round. */
		ROUND(1),
		/** Bevel. */
		BEVEL(2);

		private final int	style;

		private Join(final int style) {
			this.style = style;
		}

		/**
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return String.valueOf(this.style);
		}
	}

	private Float			width;

	private Cap				cap;

	private Join	join;

	private float[]			dashes;

	private float			phase;

	private Color			color;

	/**
	 * Constructor.
	 */
	public LineStyle() {
		this.width = null;
		this.cap = null;
		this.join = null;
		this.dashes = null;
		this.phase = 0f;
		this.color = null;
	}

	/**
	 * Get the line width.
	 * 
	 * @return the line width
	 */
	public Float getWidth() {
		return this.width;
	}

	/**
	 * Set the line width.
	 * 
	 * @param width
	 *            the line width to set
	 */
	public void setWidth(final Float width) {
		this.width = width;
	}

	/**
	 * Get the cap style.
	 * 
	 * @return the cap
	 */
	public Cap getCap() {
		return this.cap;
	}

	/**
	 * Set the cap style.
	 * 
	 * @param cap
	 *            the cap style to set
	 */
	public void setCap(final Cap cap) {
		this.cap = cap;
	}

	/**
	 * Get the join style.
	 * 
	 * @return the join style
	 */
	public Join getJoin() {
		return this.join;
	}

	/**
	 * Set the join style.
	 * 
	 * @param join
	 *            the join style to set
	 */
	public void setJoin(final Join join) {
		this.join = join;
	}

	/**
	 * Get the dashes.
	 * 
	 * @return the dashes
	 */
	public float[] getDashes() {
		return this.dashes;
	}

	/**
	 * Set the dash pattern. The dash pattern is given as an array of length
	 * values for the dashes and gaps. <br>
	 * For example: <code>{1.0f}</code> represents <code>1.0f</code> long
	 * dashes with <code>1.0f</code> long gaps;<br>
	 * <code>{2.0f, 1.0f}</code> represents <code>2.0f</code> long dashes
	 * with <code>1.0f</code> long gaps; etc.
	 * 
	 * @param dashes
	 *            the dashes to set
	 */
	public void setDashes(final float[] dashes) {
		this.dashes = dashes;
	}

	/**
	 * Get the dash phase.
	 * 
	 * @return the phase
	 */
	public float getPhase() {
		return this.phase;
	}

	/**
	 * Set the dash phase. The phase value shifts the point at which the dash
	 * pattern starts.
	 * 
	 * @param phase
	 *            the phase to set
	 */
	public void setPhase(final float phase) {
		this.phase = phase;
	}

	/**
	 * Get the color.
	 * 
	 * @return the color
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Set the color.
	 * 
	 * @param color
	 *            the color to set
	 */
	public void setColor(final Color color) {
		this.color = color;
	}
}
