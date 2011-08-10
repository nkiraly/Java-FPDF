package net.sourceforge.javafpdf;

/**
 * Font properties.
 * 
 * @author Alan Plum
 * @since 4 Mar 2008
 * @version $Revision: 1.2 $
 */
class Font {
	/**
	 * Font types.
	 * 
	 * @author Alan Plum
	 * @since 4 Mar 2008
	 * @version $Revision: 1.2 $
	 */
	protected static enum Type {
		/** Core font. */
		CORE,
		/** TrueType Font. */
		TTF;
	}

	/** Font index */
	private final int			i;

	/** Font number */
	private int					n;

	/** Font type. */
	private final Font.Type		type;

	/** Font name. */
	private final String		name;

	/** Underline position. */
	private final int			up;

	/** Underline thickness. */
	private final int			ut;

	/** Character widths. */
	private final Charwidths	cw;

	/**
	 * Constructor.
	 * 
	 * @param i
	 *            the index of the font
	 * @param type
	 *            the type of the font
	 * @param name
	 *            the name of the font
	 * @param up
	 *            the underline position
	 * @param ut
	 *            the underline thickness
	 * @param cw
	 *            the character widths
	 */
	public Font(final int i, final Font.Type type, final String name,
			final int up, final int ut, final Charwidths cw) {
		this.i = i;
		this.n = 0;
		this.type = type;
		this.name = name;
		this.up = up;
		this.ut = ut;
		this.cw = cw;
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
	 * @return the n
	 */
	public int getN() {
		return this.n;
	}

	/**
	 * DOCME
	 * 
	 * @param n
	 *            the n
	 */
	public void setN(final int n) {
		this.n = n;
	}

	/**
	 * DOCME
	 * 
	 * @return the type
	 */
	public Font.Type getType() {
		return this.type;
	}

	/**
	 * DOCME
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * DOCME
	 * 
	 * @return the up
	 */
	public int getUp() {
		return this.up;
	}

	/**
	 * DOCME
	 * 
	 * @return the ut
	 */
	public int getUt() {
		return this.ut;
	}

	/**
	 * DOCME
	 * 
	 * @return the cw
	 */
	public Charwidths getCw() {
		return this.cw;
	}
}