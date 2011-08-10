package net.sourceforge.javafpdf;

/**
 * Page format with rectangular dimensions.
 * 
 * @author Alan Plum
 * @since 4 Mar 2008
 * @version $Revision: 1.3 $
 */
public class Format {
	protected static final float	mm			= FPDF.MM;

	/** ISO A0 */
	public static final Format		A0			= new Format(841 * mm,
														1189 * mm);

	/** ISO A1 */
	public static final Format		A1			= new Format(594 * mm, 841 * mm);

	/** ISO A2 */
	public static final Format		A2			= new Format(420 * mm, 594 * mm);

	/** ISO A3 */
	public static final Format		A3			= new Format(297 * mm, 420 * mm);

	/** ISO A4 */
	public static final Format		A4			= new Format(210 * mm, 297 * mm);

	/** ISO A5 */
	public static final Format		A5			= new Format(148 * mm, 210 * mm);

	/** ISO A6 */
	public static final Format		A6			= new Format(105 * mm, 148 * mm);

	/** ISO A7 */
	public static final Format		A7			= new Format(74 * mm, 105 * mm);

	/** ISO A8 */
	public static final Format		A8			= new Format(52 * mm, 74 * mm);

	/** ISO A9 */
	public static final Format		A9			= new Format(37 * mm, 52 * mm);

	/** ISO A10 */
	public static final Format		A10			= new Format(26 * mm, 37 * mm);

	/** ISO B0 */
	public static final Format		B0			= new Format(1000 * mm,
														1414 * mm);

	/** ISO B1 */
	public static final Format		B1			= new Format(707 * mm,
														1000 * mm);

	/** ISO B2 */
	public static final Format		B2			= new Format(500 * mm, 707 * mm);

	/** ISO B3 */
	public static final Format		B3			= new Format(353 * mm, 500 * mm);

	/** ISO B4 */
	public static final Format		B4			= new Format(250 * mm, 353 * mm);

	/** ISO B5 */
	public static final Format		B5			= new Format(176 * mm, 250 * mm);

	/** ISO B6 */
	public static final Format		B6			= new Format(125 * mm, 176 * mm);

	/** ISO B7 */
	public static final Format		B7			= new Format(88 * mm, 125 * mm);

	/** ISO B8 */
	public static final Format		B8			= new Format(62 * mm, 88 * mm);

	/** ISO B9 */
	public static final Format		B9			= new Format(44 * mm, 62 * mm);

	/** ISO B10 */
	public static final Format		B10			= new Format(31 * mm, 44 * mm);

	/** ISO C0 */
	public static final Format		C0			= new Format(917 * mm,
														1297 * mm);

	/** ISO C1 */
	public static final Format		C1			= new Format(648 * mm, 917 * mm);

	/** ISO C2 */
	public static final Format		C2			= new Format(458 * mm, 648 * mm);

	/** ISO C3 */
	public static final Format		C3			= new Format(324 * mm, 458 * mm);

	/** ISO C4 */
	public static final Format		C4			= new Format(229 * mm, 324 * mm);

	/** ISO C5 */
	public static final Format		C5			= new Format(162 * mm, 229 * mm);

	/** ISO C6 */
	public static final Format		C6			= new Format(114 * mm, 162 * mm);

	/** ISO C7 */
	public static final Format		C7			= new Format(81 * mm, 114 * mm);

	/** ISO C8 */
	public static final Format		C8			= new Format(57 * mm, 81 * mm);

	/** ISO C9 */
	public static final Format		C9			= new Format(40 * mm, 57 * mm);

	/** ISO C10 */
	public static final Format		C10			= new Format(28 * mm, 40 * mm);

	/** American Letter */
	public static final Format		LETTER		= new Format(612f, 792f);

	/** American Legal */
	public static final Format		LEGAL		= new Format(612f, 1008f);

	/** American Ledger */
	public static final Format		LEDGER		= new Format(1224f, 792f);

	/** American Tabloid */
	public static final Format		TABLOID		= new Format(792f, 1224f);

	/** American Government-Letter */
	public static final Format		GOVERNMENT	= new Format(792f, 575f);

	/** American Executive */
	public static final Format		EXECUTIVE	= new Format(756f, 522f);

	/** American Broadsheet */
	public static final Format		BROADSHEET	= new Format(1224f, 1584f);

	private final float				width;

	private final float				height;

	/**
	 * Constructor.
	 * 
	 * @param width
	 *            width in points
	 * @param height
	 *            height in points
	 */
	public Format(final float width, final float height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Gets the height of the format.
	 * 
	 * @return the format's height.
	 */
	public float getHeight() {
		return this.height;
	}

	/**
	 * Gets the width of the format.
	 * 
	 * @return the format's width.
	 */
	public float getWidth() {
		return this.width;
	}
}