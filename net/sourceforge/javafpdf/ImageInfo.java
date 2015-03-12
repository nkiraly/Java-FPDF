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
 * @since 4 Mar 2008
 * @version $Rev$
 */
public class ImageInfo {
	protected static enum ColorSpace {
		/** Grayscale. */
		DEVICEGRAY("DeviceGray"), //$NON-NLS-1$
		/** RGB colors. */
		DEVICERGB("DeviceRGB"), //$NON-NLS-1$
		/** Indexed colors. */
		INDEXED("Indexed"); //$NON-NLS-1$
		private final String	string;

		private ColorSpace(final String string) {
			this.string = string;
		}

		/**
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return this.string;
		}
	}

	/** Image index. */
	private final int		i;

	/** Image number. */
	private int				n;

	/** Image width. */
	private final float		w;

	/** Image height. */
	private final float		h;

	/** Color Space. */
	private final String	cs;

	/** Palette. */
	private final String	pal;

	/** Bits per channel? */
	private final int		bpc;

	/** Filter? Known value: "FlateDecode" */
	private final String	f;

	/** DecodeParms */
	private final String	parms;

	/** Transparency mask. */
	private final int[]		trns;

	/** Image data. */
	private final char[]	data;

	/**
	 * DOCME Constructor.
	 * 
	 * @param i
	 * @param w
	 * @param h
	 * @param cs
	 * @param pal
	 * @param bpc
	 * @param f
	 * @param parms
	 * @param trns
	 * @param data
	 */
	public ImageInfo(final int i, final float w, final float h,
			final String cs, final String pal, final int bpc, final String f,
			final String parms, final int[] trns, final char[] data) {
		this.i = i;
		this.w = w;
		this.h = h;
		this.cs = cs;
		this.pal = pal;
		this.bpc = bpc;
		this.f = f;
		this.parms = parms;
		this.trns = trns;
		this.data = data;
	}

	/**
	 * DOCME
	 * 
	 * @return the n
	 */
	public int getN() {
		return this.n;
	}

	/**
	 * DOCME
	 * 
	 * @param n
	 *            the n to set
	 */
	public void setN(final int n) {
		this.n = n;
	}

	/**
	 * DOCME
	 * 
	 * @return the i
	 */
	public int getI() {
		return this.i;
	}

	/**
	 * DOCME
	 * 
	 * @return the w
	 */
	public float getW() {
		return this.w;
	}

	/**
	 * DOCME
	 * 
	 * @return the h
	 */
	public float getH() {
		return this.h;
	}

	/**
	 * DOCME
	 * 
	 * @return the cs
	 */
	public String getCs() {
		return this.cs;
	}

	/**
	 * DOCME
	 * 
	 * @return the pal
	 */
	public String getPal() {
		return this.pal;
	}

	/**
	 * DOCME
	 * 
	 * @return the bpc
	 */
	public int getBpc() {
		return this.bpc;
	}

	/**
	 * DOCME
	 * 
	 * @return the f
	 */
	public String getF() {
		return this.f;
	}

	/**
	 * DOCME
	 * 
	 * @return the parms
	 */
	public String getParms() {
		return this.parms;
	}

	/**
	 * DOCME
	 * 
	 * @return the trns
	 */
	public int[] getTrns() {
		return this.trns;
	}

	/**
	 * DOCME
	 * 
	 * @return the data
	 */
	public char[] getData() {
		return this.data;
	}
}
