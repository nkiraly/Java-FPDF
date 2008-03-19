package buchungen;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Messages;
import util.Strings;

/**
 * DOCME
 * 
 * @author pluma
 * @since 16 Feb 2008
 * @version $Rev$
 */
public class BuchungBean {
	private static class JspException extends Exception {
		private static final long	serialVersionUID	= 1L;

		JspException(final Exception o) {
			super(o);
		}

		JspException(final String o) {
			super(o);
		}
	}

	private static Integer[]					legalYears;

	static {
		legalYears = new Integer[11];
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = 0; i < 11; i++) {
			legalYears[i] = Integer.valueOf(year + (i - 4));
		}
	}

	private Integer								id;

	private String								zustand;

	private Timestamp							eingang_anfrage;

	private Timestamp							angebot_gesendet;

	private Timestamp							buchung_bestaetigt;

	private Timestamp							rechnung_gesendet;

	private Timestamp							rechnung_bezahlt;

	private Timestamp							buchung_storniert;

	private String								firma;

	private String								name;

	private String								anschrift;

	private String								telefon;

	private String								mobil;

	private String								fax;

	private String								email;

	private Calendar							von_datum;

	/**
	 * The von_zeit + 1 second (to distinguish from MySQL null).
	 */
	private Calendar							von_zeit;

	private Calendar							bis_datum;

	/**
	 * The bis_zeit + 1 second (to distinguish from MySQL null).
	 */
	private Calendar							bis_zeit;

	private Integer								treffpunkt;

	private String								treffpunkt2;

	private String								endpunkt;

	private Integer								personenzahl;

	private String								gruppenart;

	private Integer								sprache;

	private Integer								programm;

	private String								programm2;

	private boolean								extras_stadtmuseum;

	private boolean								extras_dom;

	private String								bemerkungen;

	private BigDecimal							gruppenpreis;

	private String								zahlungsart;

	private String								rechnung_firma;

	private String								rechnung_name;

	private String								rechnung_anschrift;

	private boolean								angebot;

	private Calendar							angebot_bis;

	private boolean								kunde_meldet_sich;

	private Calendar							kunde_meldet_sich_bis;

	private boolean								buchung_bestaetigen_post;

	private boolean								buchung_bestaetigen_email;

	private Map<Integer, UserBestaetigungen>	users;

	/**
	 * DOCME
	 * 
	 * @author pluma
	 * @since 22 Feb 2008
	 * @version $Rev$
	 */
	public static class UserBestaetigungen {
		private boolean	bestaetigt_fax;

		private boolean	bestaetigt_post;

		private boolean	bestaetigt_email;

		private boolean	bestaetigt_direkt;

		/**
		 * DOCME
		 * 
		 * @return the bestaetigt_fax
		 */
		public boolean getBestaetigtFax() {
			return this.bestaetigt_fax;
		}

		/**
		 * DOCME
		 * 
		 * @param bestaetigt_fax
		 *            the bestaetigt_fax to set
		 */
		public void setBestaetigtFax(final boolean bestaetigt_fax) {
			this.bestaetigt_fax = bestaetigt_fax;
		}

		/**
		 * DOCME
		 * 
		 * @return the bestaetigt_post
		 */
		public boolean getBestaetigtPost() {
			return this.bestaetigt_post;
		}

		/**
		 * DOCME
		 * 
		 * @param bestaetigt_post
		 *            the bestaetigt_post to set
		 */
		public void setBestaetigtPost(final boolean bestaetigt_post) {
			this.bestaetigt_post = bestaetigt_post;
		}

		/**
		 * DOCME
		 * 
		 * @return the bestaetigt_email
		 */
		public boolean getBestaetigtEmail() {
			return this.bestaetigt_email;
		}

		/**
		 * DOCME
		 * 
		 * @param bestaetigt_email
		 *            the bestaetigt_email to set
		 */
		public void setBestaetigtEmail(final boolean bestaetigt_email) {
			this.bestaetigt_email = bestaetigt_email;
		}

		/**
		 * DOCME
		 * 
		 * @return the bestaetigt_direkt
		 */
		public boolean getBestaetigtDirekt() {
			return this.bestaetigt_direkt;
		}

		/**
		 * DOCME
		 * 
		 * @param bestaetigt_direkt
		 *            the bestaetigt_direkt to set
		 */
		public void setBestaetigtDirekt(final boolean bestaetigt_direkt) {
			this.bestaetigt_direkt = bestaetigt_direkt;
		}

		/**
		 * DOCME
		 * 
		 * @param bestaetigt
		 *            the bestaetigt
		 */
		public void setBestaetigtAsString(final String bestaetigt) {
			if (bestaetigt == null) {
				this.bestaetigt_direkt = false;
				this.bestaetigt_email = false;
				this.bestaetigt_fax = false;
				this.bestaetigt_post = false;
			} else {
				String[] values = bestaetigt.split(","); //$NON-NLS-1$
				for (String value : values) {
					if (value.equals("fax")) { //$NON-NLS-1$
						this.bestaetigt_fax = true;
					} else if (value.equals("post")) { //$NON-NLS-1$
						this.bestaetigt_post = true;
					} else if (value.equals("email")) { //$NON-NLS-1$
						this.bestaetigt_email = true;
					} else if (value.equals("direkt")) { //$NON-NLS-1$
						this.bestaetigt_direkt = true;
					}
				}
			}
		}
	}

	/**
	 * DOCME Constructor.
	 */
	public BuchungBean() {
		this.id = null;
		this.zustand = null;
		this.eingang_anfrage = null;
		this.angebot_gesendet = null;
		this.buchung_bestaetigt = null;
		this.rechnung_gesendet = null;
		this.rechnung_bezahlt = null;
		this.buchung_storniert = null;
		this.firma = null;
		this.name = null;
		this.anschrift = null;
		this.telefon = null;
		this.mobil = null;
		this.fax = null;
		this.email = null;
		this.von_datum = null;
		this.von_zeit = null;
		this.bis_datum = null;
		this.bis_zeit = null;
		this.treffpunkt = null;
		this.treffpunkt2 = null;
		this.endpunkt = null;
		this.personenzahl = null;
		this.gruppenart = null;
		this.sprache = null;
		this.programm = null;
		this.programm2 = null;
		this.extras_stadtmuseum = false;
		this.extras_dom = false;
		this.bemerkungen = null;
		this.gruppenpreis = null;
		this.zahlungsart = null;
		this.rechnung_firma = null;
		this.rechnung_name = null;
		this.rechnung_anschrift = null;
		this.angebot = false;
		this.angebot_bis = null;
		this.kunde_meldet_sich = false;
		this.kunde_meldet_sich_bis = null;
		this.buchung_bestaetigen_post = false;
		this.buchung_bestaetigen_email = false;
		this.users = new HashMap<Integer, UserBestaetigungen>();
	}

	/**
	 * DOCME
	 * 
	 * @return whether the buchung is valid.
	 */
	public Boolean getValid() {
		if (this.zustand == null) {
			return Boolean.FALSE;
		}
		if ((this.firma == null) && (this.name == null)
				&& (this.anschrift == null)) {
			return Boolean.FALSE;
		}
		if ((this.anschrift == null) && (this.telefon == null)
				&& (this.mobil == null) && (this.fax == null)
				&& (this.email == null)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * DOCME
	 * 
	 * @return a list containing the errors.
	 */
	public List<String> getErrors() {
		List<String> errors = new ArrayList<String>();
		if (this.zustand == null) {
			errors.add(Messages.getString("error.buchungen.zustand")); //$NON-NLS-1$
		}
		if ((this.firma == null) && (this.name == null)
				&& (this.anschrift == null)) {
			errors.add(Messages.getString("error.buchungen.name")); //$NON-NLS-1$
		}
		if ((this.anschrift == null) && (this.telefon == null)
				&& (this.mobil == null) && (this.fax == null)
				&& (this.email == null)) {
			errors.add(Messages.getString("error.buchungen.kontakt")); //$NON-NLS-1$
		}
		if (errors.isEmpty()) {
			return null;
		}
		return errors;
	}

	/**
	 * DOCME
	 * 
	 * @param conn
	 *            SQL Connection to use
	 * @throws SQLException
	 *             if the execution fails.
	 */
	public void load(final Connection conn) throws SQLException {
		PreparedStatement ps = null;
		ResultSet set = null;
		ResultSet set0 = null;

		try {
			String query = Strings.getString("sql.buchungen.load"); //$NON-NLS-1$
			ps = conn.prepareStatement(query);
			ps.setInt(1, this.id.intValue());

			set = ps.executeQuery();

			if (set.next()) {
				this.setZustand(set.getString("zustand")); //$NON-NLS-1$
				this.setEingangAnfrage(set.getTimestamp("eingang_anfrage")); //$NON-NLS-1$
				this.setAngebotGesendet(set.getTimestamp("angebot_gesendet")); //$NON-NLS-1$
				this.setBuchungBestaetigt(set
						.getTimestamp("buchung_bestaetigt")); //$NON-NLS-1$
				this.setRechnungGesendet(set.getTimestamp("rechnung_gesendet")); //$NON-NLS-1$
				this.setRechnungBezahlt(set.getTimestamp("rechnung_bezahlt")); //$NON-NLS-1$
				this.setBuchungStorniert(set.getTimestamp("buchung_storniert")); //$NON-NLS-1$
				this.setFirma(set.getString("firma")); //$NON-NLS-1$
				this.setName(set.getString("name")); //$NON-NLS-1$
				this.setAnschrift(set.getString("anschrift")); //$NON-NLS-1$
				this.setTelefon(set.getString("telefon")); //$NON-NLS-1$
				this.setMobil(set.getString("mobil")); //$NON-NLS-1$
				this.setFax(set.getString("fax")); //$NON-NLS-1$
				this.setEmail(set.getString("email")); //$NON-NLS-1$
				this.setVonDatum(set.getTimestamp("von")); //$NON-NLS-1$
				this.setVonZeit(set.getTimestamp("von")); //$NON-NLS-1$
				this.setBisDatum(set.getTimestamp("bis")); //$NON-NLS-1$
				this.setBisZeit(set.getTimestamp("bis")); //$NON-NLS-1$
				this.setTreffpunkt(Integer.valueOf(set.getInt("treffpunkt"))); //$NON-NLS-1$
				this.setTreffpunkt2(set.getString("treffpunkt2")); //$NON-NLS-1$
				this.setEndpunkt(set.getString("endpunkt")); //$NON-NLS-1$
				this.setPersonenzahl(Integer
						.valueOf(set.getInt("personenzahl"))); //$NON-NLS-1$
				this.setGruppenart(set.getString("gruppenart")); //$NON-NLS-1$
				this.setSprache(Integer.valueOf(set.getInt("sprache"))); //$NON-NLS-1$
				this.setProgramm(Integer.valueOf(set.getInt("programm"))); //$NON-NLS-1$
				this.setProgramm2(set.getString("programm2")); //$NON-NLS-1$
				this.setExtras(set.getString("extras")); //$NON-NLS-1$
				this.setBemerkungen(set.getString("bemerkungen")); //$NON-NLS-1$
				this.setGruppenpreis(set.getBigDecimal("gruppenpreis")); //$NON-NLS-1$
				this.setZahlungsart(set.getString("zahlungsart")); //$NON-NLS-1$
				this.setRechnungFirma(set.getString("rechnung_firma")); //$NON-NLS-1$
				this.setRechnungName(set.getString("rechnung_name")); //$NON-NLS-1$
				this.setRechnungAnschrift(set.getString("rechnung_anschrift")); //$NON-NLS-1$
				this.setAngebot(Boolean.valueOf(set
						.getString("angebot").equals("ja"))); //$NON-NLS-1$//$NON-NLS-2$
				this.setAngebotBis(set.getDate("angebot_bis")); //$NON-NLS-1$
				this.setKundeMeldetSich(Boolean.valueOf(set.getString(
						"kunde_meldet_sich").equals("ja"))); //$NON-NLS-1$//$NON-NLS-2$
				this
						.setKundeMeldetSichBis(set
								.getDate("kunde_meldet_sich_bis")); //$NON-NLS-1$
				this
						.setBuchungBestaetigen(set
								.getString("buchung_bestaetigen")); //$NON-NLS-1$

				query = Strings.getString("sql.buchungen.users.load"); //$NON-NLS-1$
				ps = conn.prepareStatement(query);
				ps.setInt(1, this.id.intValue());

				set0 = ps.executeQuery();
				while (set0.next()) {
					Integer user = Integer.valueOf(set0.getInt("user")); //$NON-NLS-1$
					UserBestaetigungen bestaetigungen = new UserBestaetigungen();
					bestaetigungen.setBestaetigtAsString(set0
							.getString("bestaetigt")); //$NON-NLS-1$
					this.users.put(user, bestaetigungen);
				}
			}
		} finally {
			if (set0 != null) {
				try {
					set0.close();
				} catch (SQLException e) {
					// noop
				}
				set0 = null;
			}
			if (set != null) {
				try {
					set.close();
				} catch (SQLException e) {
					// noop
				}
				set = null;
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// noop
				}
				ps = null;
			}
		}
	}

	/**
	 * DOCME
	 * 
	 * @param conn
	 *            SQL Connection to use
	 * @throws SQLException
	 *             if the execution fails.
	 */
	public void write(final Connection conn) throws SQLException {
		if (this.id == null) {
			this.create(conn);
		} else {
			this.update(conn);
		}
	}

	private void create(final Connection conn) throws SQLException {
		if (this.getValid().booleanValue()) {
			PreparedStatement ps = null;
			ResultSet set = null;

			try {
				String query = Strings.getString("sql.buchungen.create"); //$NON-NLS-1$
				ps = conn.prepareStatement(query);
				ps.setString(1, this.zustand);
				ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
				ps.setString(3, this.firma);
				ps.setString(4, this.name);
				ps.setString(5, this.anschrift);
				ps.setString(6, this.telefon);
				ps.setString(7, this.mobil);
				ps.setString(8, this.fax);
				ps.setString(9, this.email);
				Calendar von = this.getVon();
				if (von == null) {
					ps.setNull(10, Types.TIMESTAMP);
				} else {
					ps.setTimestamp(10, new Timestamp(von.getTimeInMillis()));
				}
				Calendar bis = this.getBis();
				if (bis == null) {
					ps.setNull(11, Types.TIMESTAMP);
				} else {
					ps.setTimestamp(11, new Timestamp(bis.getTimeInMillis()));
				}
				if (this.treffpunkt == null) {
					ps.setNull(12, Types.INTEGER);
				} else {
					ps.setInt(12, this.treffpunkt.intValue());
				}
				ps.setString(13, this.treffpunkt2);
				ps.setString(14, this.endpunkt);
				if (this.personenzahl == null) {
					ps.setNull(15, Types.INTEGER);
				} else {
					ps.setInt(15, this.personenzahl.intValue());
				}
				ps.setString(16, this.gruppenart);
				if (this.sprache == null) {
					ps.setNull(17, Types.INTEGER);
				} else {
					ps.setInt(17, this.sprache.intValue());
				}
				if (this.programm == null) {
					ps.setNull(18, Types.INTEGER);
				} else {
					ps.setInt(18, this.programm.intValue());
				}
				ps.setString(19, this.programm2);
				if (this.extras_stadtmuseum) {
					if (this.extras_dom) {
						ps.setString(20, "stadtmuseum,dom"); //$NON-NLS-1$
					} else {
						ps.setString(20, "stadtmuseum"); //$NON-NLS-1$
					}
				} else if (this.extras_dom) {
					ps.setString(20, "dom"); //$NON-NLS-1$
				} else {
					ps.setNull(20, Types.NULL);
				}
				ps.setString(21, this.bemerkungen);
				ps.setBigDecimal(22, this.gruppenpreis);
				ps.setString(23, this.zahlungsart);
				ps.setString(24, this.rechnung_firma);
				ps.setString(25, this.rechnung_name);
				ps.setString(26, this.rechnung_anschrift);
				ps.setString(27, (this.angebot ? "ja" //$NON-NLS-1$
						: "nein")); //$NON-NLS-1$
				if (this.angebot_bis == null) {
					ps.setNull(28, Types.DATE);
				} else {
					ps
							.setDate(28, new Date(this.angebot_bis
									.getTimeInMillis()));
				}
				ps.setString(29, (this.kunde_meldet_sich ? "ja" //$NON-NLS-1$
						: "nein")); //$NON-NLS-1$
				if (this.kunde_meldet_sich_bis == null) {
					ps.setNull(30, Types.DATE);
				} else {
					ps.setDate(30, new Date(this.kunde_meldet_sich_bis
							.getTimeInMillis()));
				}
				if (this.buchung_bestaetigen_post) {
					if (this.buchung_bestaetigen_email) {
						ps.setString(31, "post,email"); //$NON-NLS-1$
					} else {
						ps.setString(31, "post"); //$NON-NLS-1$
					}
				} else if (this.buchung_bestaetigen_email) {
					ps.setString(31, "email"); //$NON-NLS-1$
				} else {
					ps.setNull(31, Types.NULL);
				}
				ps.execute();
				set = ps.getGeneratedKeys();
				set.next();
				this.setId(Integer.valueOf(set.getInt(1)));
			} finally {
				if (set != null) {
					try {
						set.close();
					} catch (SQLException e) {
						// noop
					}
					set = null;
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// noop
					}
					ps = null;
				}
			}
		}
	}

	private void update(final Connection conn) throws SQLException {
		if (this.getValid().booleanValue()) {
			PreparedStatement ps = null;
			ResultSet set = null;

			try {
				String query = Strings.getString("sql.buchungen.update"); //$NON-NLS-1$
				ps = conn.prepareStatement(query);
				ps.setString(1, this.zustand);
				ps.setString(2, this.firma);
				ps.setString(3, this.name);
				ps.setString(4, this.anschrift);
				ps.setString(5, this.telefon);
				ps.setString(6, this.mobil);
				ps.setString(7, this.fax);
				ps.setString(8, this.email);
				Calendar von = this.getVon();
				if (von == null) {
					ps.setNull(9, Types.TIMESTAMP);
				} else {
					ps.setTimestamp(9, new Timestamp(von.getTimeInMillis()));
				}
				Calendar bis = this.getBis();
				if (bis == null) {
					ps.setNull(10, Types.TIMESTAMP);
				} else {
					ps.setTimestamp(10, new Timestamp(bis.getTimeInMillis()));
				}
				if (this.treffpunkt == null) {
					ps.setNull(11, Types.INTEGER);
				} else {
					ps.setInt(11, this.treffpunkt.intValue());
				}
				ps.setString(12, this.treffpunkt2);
				ps.setString(13, this.endpunkt);
				if (this.personenzahl == null) {
					ps.setNull(14, Types.INTEGER);
				} else {
					ps.setInt(14, this.personenzahl.intValue());
				}
				ps.setString(15, this.gruppenart);
				if (this.sprache == null) {
					ps.setNull(16, Types.INTEGER);
				} else {
					ps.setInt(16, this.sprache.intValue());
				}
				if (this.programm == null) {
					ps.setNull(17, Types.INTEGER);
				} else {
					ps.setInt(17, this.programm.intValue());
				}
				ps.setString(18, this.programm2);
				if (this.extras_stadtmuseum) {
					if (this.extras_dom) {
						ps.setString(19, "stadtmuseum,dom"); //$NON-NLS-1$
					} else {
						ps.setString(19, "stadtmuseum"); //$NON-NLS-1$
					}
				} else if (this.extras_dom) {
					ps.setString(19, "dom"); //$NON-NLS-1$
				} else {
					ps.setNull(19, Types.NULL);
				}
				ps.setString(20, this.bemerkungen);
				ps.setBigDecimal(21, this.gruppenpreis);
				ps.setString(22, this.zahlungsart);
				ps.setString(23, this.rechnung_firma);
				ps.setString(24, this.rechnung_name);
				ps.setString(25, this.rechnung_anschrift);
				ps.setString(26, (this.angebot ? "ja" //$NON-NLS-1$
						: "nein")); //$NON-NLS-1$
				if (this.angebot_bis == null) {
					ps.setNull(27, Types.DATE);
				} else {
					ps
							.setDate(27, new Date(this.angebot_bis
									.getTimeInMillis()));
				}
				ps.setString(28, (this.kunde_meldet_sich ? "ja" //$NON-NLS-1$
						: "nein")); //$NON-NLS-1$
				if (this.kunde_meldet_sich_bis == null) {
					ps.setNull(29, Types.DATE);
				} else {
					ps.setDate(29, new Date(this.kunde_meldet_sich_bis
							.getTimeInMillis()));
				}
				if (this.buchung_bestaetigen_post) {
					if (this.buchung_bestaetigen_email) {
						ps.setString(30, "post,email"); //$NON-NLS-1$
					} else {
						ps.setString(30, "post"); //$NON-NLS-1$
					}
				} else if (this.buchung_bestaetigen_email) {
					ps.setString(30, "email"); //$NON-NLS-1$
				} else {
					ps.setNull(30, Types.NULL);
				}
				ps.setInt(31, this.id.intValue());
				ps.execute();
			} finally {
				if (set != null) {
					try {
						set.close();
					} catch (SQLException e) {
						// noop
					}
					set = null;
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// noop
					}
					ps = null;
				}
			}
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the legal years.
	 */
	public Integer[] getLegalYears() {
		return legalYears;
	}

	/**
	 * DOCME
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * DOCME
	 * 
	 * @param id
	 *            the id
	 */
	public void setId(final Integer id) {
		if ((id == null) || (id.intValue() > 0)) {
			this.id = id;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the zustand;
	 */
	public String getZustand() {
		return this.zustand;
	}

	/**
	 * DOCME
	 * 
	 * @param zustand
	 *            the zustand
	 */
	public void setZustand(final String zustand) {
		if ((zustand != null) && (zustand.length() > 3)) {
			this.zustand = zustand;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the eingang_anfrage.
	 */
	public Timestamp getEingangAnfrage() {
		return this.eingang_anfrage;
	}

	/**
	 * DOCME
	 * 
	 * @param eingang_anfrage
	 *            the eingang_anfrage
	 */
	public void setEingangAnfrage(final Timestamp eingang_anfrage) {
		this.eingang_anfrage = eingang_anfrage;
	}

	/**
	 * DOCME
	 * 
	 * @return the eingang_anfrage as formatted date.
	 */
	public String getEingangAnfrageAsString() {
		if (this.eingang_anfrage == null) {
			return null;
		}
		return DateFormat.getDateTimeInstance().format(this.eingang_anfrage);
	}

	/**
	 * DOCME
	 * 
	 * @return the angebot_gesendet.
	 */
	public Timestamp getAngebotGesendet() {
		return this.angebot_gesendet;
	}

	/**
	 * DOCME
	 * 
	 * @param angebot_gesendet
	 *            the angebot_gesendet
	 */
	public void setAngebotGesendet(final Timestamp angebot_gesendet) {
		this.angebot_gesendet = angebot_gesendet;
	}

	/**
	 * DOCME
	 * 
	 * @return the buchung_bestaetigt.
	 */
	public Timestamp getBuchungBestaetigt() {
		return this.buchung_bestaetigt;
	}

	/**
	 * DOCME
	 * 
	 * @param buchung_bestaetigt
	 *            the buchung_bestaetigt
	 */
	public void setBuchungBestaetigt(final Timestamp buchung_bestaetigt) {
		this.buchung_bestaetigt = buchung_bestaetigt;
	}

	/**
	 * DOCME
	 * 
	 * @return the rechnung_gesendet.
	 */
	public Timestamp getRechnungGesendet() {
		return this.rechnung_gesendet;
	}

	/**
	 * DOCME
	 * 
	 * @param rechnung_gesendet
	 *            the rechnung_gesendet
	 */
	public void setRechnungGesendet(final Timestamp rechnung_gesendet) {
		this.rechnung_gesendet = rechnung_gesendet;
	}

	/**
	 * DOCME
	 * 
	 * @return the rechnung_bezahlt.
	 */
	public Timestamp getRechnungBezahlt() {
		return this.rechnung_bezahlt;
	}

	/**
	 * DOCME
	 * 
	 * @param rechnung_bezahlt
	 *            the rechnung_bezahlt
	 */
	public void setRechnungBezahlt(final Timestamp rechnung_bezahlt) {
		this.rechnung_bezahlt = rechnung_bezahlt;
	}

	/**
	 * DOCME
	 * 
	 * @return the buchung_storniert.
	 */
	public Timestamp getBuchungStorniert() {
		return this.buchung_storniert;
	}

	/**
	 * DOCME
	 * 
	 * @param buchung_storniert
	 *            the buchung_storniert
	 */
	public void setBuchungStorniert(final Timestamp buchung_storniert) {
		this.buchung_storniert = buchung_storniert;
	}

	/**
	 * DOCME
	 * 
	 * @return the firma.
	 */
	public String getFirma() {
		return this.firma;
	}

	/**
	 * DOCME
	 * 
	 * @param firma
	 *            the firma
	 */
	public void setFirma(final String firma) {
		if ((firma == null) || (firma.length() < 3)) {
			this.firma = null;
		} else {
			this.firma = firma;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * DOCME
	 * 
	 * @param name
	 *            the name
	 */
	public void setName(final String name) {
		if ((name == null) || (name.length() < 3)) {
			this.name = null;
		} else {
			this.name = name;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the kunde.
	 */
	public String getKunde() {
		if (this.firma != null) {
			StringBuilder sb = new StringBuilder();
			sb.append(this.firma);
			if (this.name != null) {
				sb.append(',');
				sb.append("<br>"); //$NON-NLS-1$
				sb.append(this.name);
			}
			return sb.toString();
		}
		return this.name;
	}

	/**
	 * DOCME
	 * 
	 * @return the anschrift.
	 */
	public String getAnschrift() {
		return this.anschrift;
	}

	/**
	 * DOCME
	 * 
	 * @param anschrift
	 *            the anschrift
	 */
	public void setAnschrift(final String anschrift) {
		if ((anschrift == null) || (anschrift.length() < 3)) {
			this.anschrift = null;
		} else {
			this.anschrift = anschrift;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the telefon.
	 */
	public String getTelefon() {
		return this.telefon;
	}

	/**
	 * DOCME
	 * 
	 * @param telefon
	 *            the telefon
	 */
	public void setTelefon(final String telefon) {
		if ((telefon == null) || (telefon.length() < 3)) {
			this.telefon = null;
		} else {
			this.telefon = telefon;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the anschrift.
	 */
	public String getMobil() {
		return this.mobil;
	}

	/**
	 * DOCME
	 * 
	 * @param mobil
	 *            the mobil
	 */
	public void setMobil(final String mobil) {
		if ((mobil == null) || (mobil.length() < 3)) {
			this.mobil = null;
		} else {
			this.mobil = mobil;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the fax.
	 */
	public String getFax() {
		return this.fax;
	}

	/**
	 * DOCME
	 * 
	 * @param fax
	 *            the fax
	 */
	public void setFax(final String fax) {
		if ((fax == null) || (fax.length() < 3)) {
			this.fax = null;
		} else {
			this.fax = fax;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the email.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * DOCME
	 * 
	 * @param email
	 *            the email
	 */
	public void setEmail(final String email) {
		if ((email == null) || (email.length() < 3)) {
			this.email = null;
		} else {
			this.email = email;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the von.
	 */
	public Calendar getVon() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		if (this.von_datum != null) {
			cal.set(Calendar.YEAR, this.von_datum.get(Calendar.YEAR));
			cal.set(Calendar.MONTH, this.von_datum.get(Calendar.MONTH));
			cal.set(Calendar.DAY_OF_MONTH, this.von_datum
					.get(Calendar.DAY_OF_MONTH));
		}
		if (this.von_zeit != null) {
			cal.set(Calendar.HOUR, this.von_zeit.get(Calendar.HOUR));
			cal.set(Calendar.MINUTE, this.von_zeit.get(Calendar.MINUTE));
			cal.set(Calendar.SECOND, this.von_zeit.get(Calendar.SECOND));
			cal.set(Calendar.AM_PM, this.von_zeit.get(Calendar.AM_PM));
		}
		if (cal.getTimeInMillis() == 0) {
			return null;
		}
		return cal;
	}

	/**
	 * DOCME
	 * 
	 * @return the von_datum.
	 */
	public Calendar getVonDatum() {
		return this.von_datum;
	}

	/**
	 * DOCME
	 * 
	 * @param von_datum
	 *            the von_datum
	 */
	public void setVonDatum(final Timestamp von_datum) {
		if (von_datum == null) {
			this.von_datum = null;
		} else {
			if (this.von_datum == null) {
				this.von_datum = Calendar.getInstance();
			}
			this.von_datum.setTime(von_datum);
			this.von_datum.set(Calendar.HOUR, 0);
			this.von_datum.set(Calendar.MINUTE, 0);
			this.von_datum.set(Calendar.SECOND, 0);
			this.von_datum.set(Calendar.MILLISECOND, 0);
			this.von_datum.set(Calendar.AM_PM, Calendar.AM);
			if (this.von_datum.getTimeInMillis() == 0) {
				this.von_datum = null;
			}
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the von_datum as formatted date.
	 */
	public String getVonDatumAsString() {
		if (this.von_datum == null) {
			return null;
		}
		return DateFormat.getDateInstance().format(this.von_datum.getTime());
	}

	/**
	 * DOCME
	 * 
	 * @param von_datum
	 *            the von_datum as YYYY-MM-DD
	 */
	public void setVonDatumAsString(final String von_datum) {
		if (von_datum == null) {
			this.von_datum = null;
			return;
		}
		try {
			if (this.von_datum == null) {
				this.von_datum = Calendar.getInstance();
			}
			this.von_datum.setTime(Date.valueOf(von_datum));
		} catch (IllegalArgumentException e) {
			this.von_datum = null;
			return;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the von_zeit.
	 */
	public Calendar getVonZeit() {
		return this.von_zeit;
	}

	/**
	 * DOCME
	 * 
	 * @param von_zeit
	 *            the von_zeit
	 */
	public void setVonZeit(final Timestamp von_zeit) {
		if (von_zeit == null) {
			this.von_zeit = null;
		} else {
			if (this.von_zeit == null) {
				this.von_zeit = Calendar.getInstance();
			}
			this.von_zeit.setTime(von_zeit);
			this.von_zeit.set(Calendar.YEAR, 1970);
			this.von_zeit.set(Calendar.MONTH, 0);
			this.von_zeit.set(Calendar.DAY_OF_MONTH, 1);
			if (this.von_zeit.getTimeInMillis() == 0) {
				this.von_zeit = null;
			}
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the von_zeit as formatted time.
	 */
	public String getVonZeitAsString() {
		if (this.von_zeit == null) {
			return null;
		}
		return DateFormat.getTimeInstance().format(this.von_zeit.getTime())
				.substring(0, 5);
	}

	/**
	 * DOCME
	 * 
	 * @param von_zeit
	 *            the von_zeit as HH:II:SS
	 */
	public void setVonZeitAsString(final String von_zeit) {
		if (von_zeit == null) {
			this.von_zeit = null;
			return;
		}
		try {
			if (this.von_zeit == null) {
				this.von_zeit = Calendar.getInstance();
			}
			this.von_zeit.setTime(Time.valueOf(von_zeit));
		} catch (IllegalArgumentException e) {
			this.von_zeit = null;
			return;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the vonD.
	 */
	public Integer getVonD() {
		if (this.von_datum == null) {
			return null;
		}
		return Integer.valueOf(this.von_datum.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * DOCME
	 * 
	 * @return the vonM.
	 */
	public Integer getVonM() {
		if (this.von_datum == null) {
			return null;
		}
		return Integer.valueOf(this.von_datum.get(Calendar.MONTH) + 1);
	}

	/**
	 * DOCME
	 * 
	 * @return the vonY.
	 */
	public Integer getVonY() {
		if (this.von_datum == null) {
			return null;
		}
		return Integer.valueOf(this.von_datum.get(Calendar.YEAR));
	}

	/**
	 * DOCME
	 * 
	 * @return the vonH.
	 */
	public Integer getVonH() {
		if (this.von_zeit == null) {
			return null;
		}
		return Integer.valueOf(this.von_zeit.get(Calendar.HOUR_OF_DAY));
	}

	/**
	 * DOCME
	 * 
	 * @return the vonI.
	 */
	public Integer getVonI() {
		if (this.von_zeit == null) {
			return null;
		}
		return Integer.valueOf(this.von_zeit.get(Calendar.MINUTE));
	}

	/**
	 * DOCME
	 * 
	 * @return the bis.
	 */
	public Calendar getBis() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		if (this.von_datum != null) {
			cal.set(Calendar.YEAR, this.bis_datum.get(Calendar.YEAR));
			cal.set(Calendar.MONTH, this.bis_datum.get(Calendar.MONTH));
			cal.set(Calendar.DAY_OF_MONTH, this.bis_datum
					.get(Calendar.DAY_OF_MONTH));
		}
		if (this.von_zeit != null) {
			cal.set(Calendar.HOUR, this.bis_zeit.get(Calendar.HOUR));
			cal.set(Calendar.MINUTE, this.bis_zeit.get(Calendar.MINUTE));
			cal.set(Calendar.SECOND, this.bis_zeit.get(Calendar.SECOND));
			cal.set(Calendar.AM_PM, this.bis_zeit.get(Calendar.AM_PM));
		}
		if (cal.getTimeInMillis() == 0) {
			return null;
		}
		return cal;
	}

	/**
	 * DOCME
	 * 
	 * @return the bis_datum.
	 */
	public Calendar getBisDatum() {
		return this.bis_datum;
	}

	/**
	 * DOCME
	 * 
	 * @param bis_datum
	 *            the bis_datum
	 */
	public void setBisDatum(final Timestamp bis_datum) {
		if (bis_datum == null) {
			this.bis_datum = null;
		} else {
			if (this.bis_datum == null) {
				this.bis_datum = Calendar.getInstance();
			}
			this.bis_datum.setTime(bis_datum);
			this.bis_datum.set(Calendar.HOUR, 0);
			this.bis_datum.set(Calendar.MINUTE, 0);
			this.bis_datum.set(Calendar.SECOND, 0);
			this.bis_datum.set(Calendar.MILLISECOND, 0);
			this.bis_datum.set(Calendar.AM_PM, Calendar.AM);
			if (this.bis_datum.getTimeInMillis() == 0) {
				this.bis_datum = null;
			}
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the bis_datum as formatted date.
	 */
	public String getBisDatumAsString() {
		if (this.bis_datum == null) {
			return null;
		}
		return DateFormat.getDateInstance().format(this.bis_datum.getTime());
	}

	/**
	 * DOCME
	 * 
	 * @param bis_datum
	 *            the bis_datum as YYYY-MM-DD
	 */
	public void setBisDatumAsString(final String bis_datum) {
		if (bis_datum == null) {
			this.bis_datum = null;
			return;
		}
		try {
			if (this.bis_datum == null) {
				this.bis_datum = Calendar.getInstance();
			}
			this.bis_datum.setTime(Date.valueOf(bis_datum));
		} catch (IllegalArgumentException e) {
			this.bis_datum = null;
			return;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the bis_zeit.
	 */
	public Calendar getBisZeit() {
		return this.bis_zeit;
	}

	/**
	 * DOCME
	 * 
	 * @param bis_zeit
	 *            the bis_zeit
	 */
	public void setBisZeit(final Timestamp bis_zeit) {
		if (bis_zeit == null) {
			this.bis_zeit = null;
		} else {
			if (this.bis_zeit == null) {
				this.bis_zeit = Calendar.getInstance();
			}
			this.bis_zeit.setTime(bis_zeit);
			this.bis_zeit.set(Calendar.YEAR, 1970);
			this.bis_zeit.set(Calendar.MONTH, 0);
			this.bis_zeit.set(Calendar.DAY_OF_MONTH, 1);
			if (this.bis_zeit.getTimeInMillis() == 0) {
				this.bis_zeit = null;
			}
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the bis_zeit as formatted time.
	 */
	public String getBisZeitAsString() {
		if (this.bis_zeit == null) {
			return null;
		}
		return DateFormat.getTimeInstance().format(this.bis_zeit.getTime())
				.substring(0, 5);
	}

	/**
	 * DOCME
	 * 
	 * @param bis_zeit
	 *            the bis_zeit as HH:II:SS
	 */
	public void setBisZeitAsString(final String bis_zeit) {
		if (bis_zeit == null) {
			this.bis_zeit = null;
			return;
		}
		try {
			if (this.bis_zeit == null) {
				this.bis_zeit = Calendar.getInstance();
			}
			this.bis_zeit.setTime(Time.valueOf(bis_zeit));
		} catch (IllegalArgumentException e) {
			this.bis_zeit = null;
			return;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the bisD.
	 */
	public Integer getBisD() {
		if (this.bis_datum == null) {
			return null;
		}
		return Integer.valueOf(this.bis_datum.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * DOCME
	 * 
	 * @return the bisM.
	 */
	public Integer getBisM() {
		if (this.bis_datum == null) {
			return null;
		}
		return Integer.valueOf(this.bis_datum.get(Calendar.MONTH) + 1);
	}

	/**
	 * DOCME
	 * 
	 * @return the bisY.
	 */
	public Integer getBisY() {
		if (this.bis_datum == null) {
			return null;
		}
		return Integer.valueOf(this.bis_datum.get(Calendar.YEAR));
	}

	/**
	 * DOCME
	 * 
	 * @return the bisH.
	 */
	public Integer getBisH() {
		if (this.bis_zeit == null) {
			return null;
		}
		return Integer.valueOf(this.bis_zeit.get(Calendar.HOUR_OF_DAY));
	}

	/**
	 * DOCME
	 * 
	 * @return the bisI.
	 */
	public Integer getBisI() {
		if (this.bis_zeit == null) {
			return null;
		}
		return Integer.valueOf(this.bis_zeit.get(Calendar.MINUTE));
	}

	/**
	 * DOCME
	 * 
	 * @return the treffpunkt ID.
	 */
	public Integer getTreffpunkt() {
		return this.treffpunkt;
	}

	/**
	 * DOCME
	 * 
	 * @param treffpunkt
	 *            the treffpunkt ID
	 */
	public void setTreffpunkt(final Integer treffpunkt) {
		if ((treffpunkt == null) || (treffpunkt.intValue() > 0)) {
			this.treffpunkt = treffpunkt;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the treffpunkt description.
	 */
	public String getTreffpunkt2() {
		return this.treffpunkt2;
	}

	/**
	 * DOCME
	 * 
	 * @param treffpunkt2
	 *            the treffpunkt description
	 */
	public void setTreffpunkt2(final String treffpunkt2) {
		if ((treffpunkt2 == null) || (treffpunkt2.length() < 3)) {
			this.treffpunkt2 = null;
		} else {
			this.treffpunkt2 = treffpunkt2;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the endpunkt description.
	 */
	public String getEndpunkt() {
		return this.endpunkt;
	}

	/**
	 * DOCME
	 * 
	 * @param endpunkt
	 *            the endpunkt description
	 */
	public void setEndpunkt(final String endpunkt) {
		if ((endpunkt == null) || (endpunkt.length() < 3)) {
			this.endpunkt = null;
		} else {
			this.endpunkt = endpunkt;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the personenzahl
	 */
	public Integer getPersonenzahl() {
		return this.personenzahl;
	}

	/**
	 * DOCME
	 * 
	 * @param personenzahl
	 *            the personenzahl
	 */
	public void setPersonenzahl(final Integer personenzahl) {
		this.personenzahl = personenzahl;
	}

	/**
	 * DOCME
	 * 
	 * @return the gruppenart
	 */
	public String getGruppenart() {
		return this.gruppenart;
	}

	/**
	 * DOCME
	 * 
	 * @param gruppenart
	 *            the gruppenart
	 */
	public void setGruppenart(final String gruppenart) {
		if ((gruppenart == null) || (gruppenart.length() < 3)) {
			this.gruppenart = null;
		} else {
			this.gruppenart = gruppenart;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the sprache ID.
	 */
	public Integer getSprache() {
		return this.sprache;
	}

	/**
	 * DOCME
	 * 
	 * @param sprache
	 *            the sprache ID
	 */
	public void setSprache(final Integer sprache) {
		if ((sprache == null) || (sprache.intValue() > 0)) {
			this.sprache = sprache;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the thema ID.
	 */
	public Integer getProgramm() {
		return this.programm;
	}

	/**
	 * DOCME
	 * 
	 * @param programm
	 *            the thema ID
	 */
	public void setProgramm(final Integer programm) {
		if ((programm == null) || (programm.intValue() > 0)) {
			this.programm = programm;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the programm description.
	 */
	public String getProgramm2() {
		return this.programm2;
	}

	/**
	 * DOCME
	 * 
	 * @param programm2
	 *            the programm description
	 */
	public void setProgramm2(final String programm2) {
		if ((programm2 == null) || (programm2.length() < 3)) {
			this.programm2 = null;
		} else {
			this.programm2 = programm2;
		}
	}

	/**
	 * DOCME
	 * 
	 * @param extras
	 *            the extras
	 */
	public void setExtras(final String extras) {
		if (extras == null) {
			this.extras_stadtmuseum = false;
			this.extras_dom = false;
		} else {
			String[] arr = extras.split(","); //$NON-NLS-1$
			for (String extra : arr) {
				if (extra.equals("stadtmuseum")) { //$NON-NLS-1$
					this.extras_stadtmuseum = true;
				} else if (extra.equals("dom")) { //$NON-NLS-1$
					this.extras_dom = true;
				}
			}
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the extras_stadtmuseum.
	 */
	public Boolean getExtrasStadtmuseum() {
		return Boolean.valueOf(this.extras_stadtmuseum);
	}

	/**
	 * DOCME
	 * 
	 * @param extras_stadtmuseum
	 *            the extras_stadtmuseum
	 */
	public void setExtrasStadtmuseum(final Boolean extras_stadtmuseum) {
		if ((extras_stadtmuseum == null) || !extras_stadtmuseum.booleanValue()) {
			this.extras_stadtmuseum = false;
		} else {
			this.extras_stadtmuseum = true;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the extras_dom.
	 */
	public Boolean getExtrasDom() {
		return Boolean.valueOf(this.extras_dom);
	}

	/**
	 * DOCME
	 * 
	 * @param extras_dom
	 *            the extras_dom
	 */
	public void setExtrasDom(final Boolean extras_dom) {
		if ((extras_dom == null) || !extras_dom.booleanValue()) {
			this.extras_dom = false;
		} else {
			this.extras_dom = true;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the bemerkungen.
	 */
	public String getBemerkungen() {
		return this.bemerkungen;
	}

	/**
	 * DOCME
	 * 
	 * @param bemerkungen
	 *            the bemerkungen
	 */
	public void setBemerkungen(final String bemerkungen) {
		if ((bemerkungen == null) || (bemerkungen.length() < 3)) {
			this.bemerkungen = null;
		} else {
			this.bemerkungen = bemerkungen;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the gruppenpreis.
	 */
	public BigDecimal getGruppenpreis() {
		return this.gruppenpreis;
	}

	/**
	 * DOCME
	 * 
	 * @param gruppenpreis
	 *            the gruppenpreis
	 */
	public void setGruppenpreis(final BigDecimal gruppenpreis) {
		this.gruppenpreis = gruppenpreis;
	}

	/**
	 * DOCME
	 * 
	 * @return the first part of the gruppenpreis.
	 */
	public String getGruppenpreisAsString() {
		if (this.gruppenpreis == null) {
			return null;
		}
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMinimumFractionDigits(2);
		return nf.format(this.gruppenpreis);
	}

	/**
	 * DOCME
	 * 
	 * @param gruppenpreis
	 *            the gruppenpreis
	 */
	public void setGruppenpreisAsString(final String gruppenpreis) {
		try {
			String gp = gruppenpreis.replace(" ", ""); //$NON-NLS-1$ //$NON-NLS-2$
			int index1l = gp.lastIndexOf('.');
			int index1f = gp.indexOf('.');
			int index2l = gp.lastIndexOf(',');
			int index2f = gp.indexOf(',');
			if ((index1l != -1) && (index1l == index1f)
					&& (index1l >= gp.length() - 3)) {
				// DOT as seperator (just remove commas)
				this.gruppenpreis = new BigDecimal(gp.replace(",", "")); //$NON-NLS-1$ //$NON-NLS-2$
			} else if ((index2l != -1) && (index2l == index2f)
					&& (index2l >= gp.length() - 3)) {
				// COMMA as seperator (remove dots, translate commas)
				this.gruppenpreis = new BigDecimal(gp.replace(".", "") //$NON-NLS-1$ //$NON-NLS-2$
						.replace(',', '.'));
			} else {
				// NO seperator (remove commas and dots)
				this.gruppenpreis = new BigDecimal(gp.replace(".", "") //$NON-NLS-1$ //$NON-NLS-2$
						.replace(",", "")); //$NON-NLS-1$ //$NON-NLS-2$

			}
		} catch (NumberFormatException e) {
			this.gruppenpreis = null;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the zahlungsart.
	 */
	public String getZahlungsart() {
		return this.zahlungsart;
	}

	/**
	 * DOCME
	 * 
	 * @param zahlungsart
	 *            the zahlungsart
	 */
	public void setZahlungsart(final String zahlungsart) {
		if ((zahlungsart == null) || (zahlungsart.length() < 3)) {
			this.zahlungsart = null;
		} else {
			this.zahlungsart = zahlungsart;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the angebot.
	 */
	public Boolean getAngebot() {
		return Boolean.valueOf(this.angebot);
	}

	/**
	 * DOCME
	 * 
	 * @param angebot
	 *            the angebot
	 */
	public void setAngebot(final Boolean angebot) {
		if ((angebot == null) || !angebot.booleanValue()) {
			this.angebot = false;
		} else {
			this.angebot = true;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the angebot_bis.
	 */
	public Calendar getAngebotBis() {
		return this.angebot_bis;
	}

	/**
	 * DOCME
	 * 
	 * @param angebot_bis
	 *            the angebot_bis
	 */
	public void setAngebotBis(final Date angebot_bis) {
		if (angebot_bis != null) {
			if (this.angebot_bis == null) {
				this.angebot_bis = Calendar.getInstance();
			}
			this.angebot_bis.setTime(angebot_bis);
			this.angebot = true;
		} else {
			this.angebot_bis = null;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the angebot_bis as formatted date.
	 */
	public String getAngebotBisAsString() {
		if (this.angebot_bis == null) {
			return null;
		}
		return DateFormat.getDateInstance().format(this.angebot_bis.getTime());
	}

	/**
	 * DOCME
	 * 
	 * @param angebot_bis
	 *            the angebot_bis as YYYY-MM-DD
	 */
	public void setAngebotBisAsString(final String angebot_bis) {
		if (angebot_bis == null) {
			this.angebot_bis = null;
			return;
		}
		try {
			if (this.angebot_bis == null) {
				this.angebot_bis = Calendar.getInstance();
			}
			Date date = Date.valueOf(angebot_bis);
			this.angebot_bis.setTime(date);
			this.angebot = true;
		} catch (IllegalArgumentException e) {
			this.angebot_bis = null;
			return;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the angebot_bisD.
	 */
	public Integer getAngebotBisD() {
		if (this.angebot_bis == null) {
			return null;
		}
		return Integer.valueOf(this.angebot_bis.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * DOCME
	 * 
	 * @return the angebot_bisM.
	 */
	public Integer getAngebotBisM() {
		if (this.angebot_bis == null) {
			return null;
		}
		return Integer.valueOf(this.angebot_bis.get(Calendar.MONTH) + 1);
	}

	/**
	 * DOCME
	 * 
	 * @return the angebot_bisY.
	 */
	public Integer getAngebotBisY() {
		if (this.angebot_bis == null) {
			return null;
		}
		return Integer.valueOf(this.angebot_bis.get(Calendar.YEAR));
	}

	/**
	 * DOCME
	 * 
	 * @return the kunde_meldet_sich.
	 */
	public Boolean getKundeMeldetSich() {
		return Boolean.valueOf(this.kunde_meldet_sich);
	}

	/**
	 * DOCME
	 * 
	 * @param kunde_meldet_sich
	 *            the kunde_meldet_sich
	 */
	public void setKundeMeldetSich(final Boolean kunde_meldet_sich) {
		if ((kunde_meldet_sich == null) || !kunde_meldet_sich.booleanValue()) {
			this.kunde_meldet_sich = false;
		} else {
			this.kunde_meldet_sich = true;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the kunde_meldet_sich_bis.
	 */
	public Calendar getKundeMeldetSichBis() {
		return this.kunde_meldet_sich_bis;
	}

	/**
	 * DOCME
	 * 
	 * @param kunde_meldet_sich_bis
	 *            the kunde_meldet_sich_bis
	 */
	public void setKundeMeldetSichBis(final Date kunde_meldet_sich_bis) {
		if (kunde_meldet_sich_bis != null) {
			if (this.kunde_meldet_sich_bis == null) {
				this.kunde_meldet_sich_bis = Calendar.getInstance();
			}
			this.kunde_meldet_sich_bis.setTime(kunde_meldet_sich_bis);
			this.kunde_meldet_sich = true;
		} else {
			this.kunde_meldet_sich_bis = null;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the kunde_meldet_sich_bis as formatted date.
	 */
	public String getKundeMeldetSichBisAsString() {
		if (this.kunde_meldet_sich_bis == null) {
			return null;
		}
		return DateFormat.getDateInstance().format(
				this.kunde_meldet_sich_bis.getTime());
	}

	/**
	 * DOCME
	 * 
	 * @param kunde_meldet_sich_bis
	 *            the kunde_meldet_sich_bis as YYYY-MM-DD
	 */
	public void setKundeMeldetSichBisAsString(final String kunde_meldet_sich_bis) {
		if (kunde_meldet_sich_bis == null) {
			this.kunde_meldet_sich_bis = null;
			return;
		}
		try {
			if (this.kunde_meldet_sich_bis == null) {
				this.kunde_meldet_sich_bis = Calendar.getInstance();
			}
			Date date = Date.valueOf(kunde_meldet_sich_bis);
			this.kunde_meldet_sich_bis.setTime(date);
			this.kunde_meldet_sich = true;
		} catch (IllegalArgumentException e) {
			this.kunde_meldet_sich_bis = null;
			return;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the kunde_meldet_sich_bisD.
	 */
	public Integer getKundeMeldetSichBisD() {
		if (this.kunde_meldet_sich_bis == null) {
			return null;
		}
		return Integer.valueOf(this.kunde_meldet_sich_bis
				.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * DOCME
	 * 
	 * @return the kunde_meldet_sich_bisM.
	 */
	public Integer getKundeMeldetSichBisM() {
		if (this.kunde_meldet_sich_bis == null) {
			return null;
		}
		return Integer
				.valueOf(this.kunde_meldet_sich_bis.get(Calendar.MONTH) + 1);
	}

	/**
	 * DOCME
	 * 
	 * @return the kunde_meldet_sich_bisY.
	 */
	public Integer getKundeMeldetSichBisY() {
		if (this.kunde_meldet_sich_bis == null) {
			return null;
		}
		return Integer.valueOf(this.kunde_meldet_sich_bis.get(Calendar.YEAR));
	}

	/**
	 * DOCME
	 * 
	 * @return the buchung_bestaetigen_post.
	 */
	public Boolean getBuchungBestaetigenPost() {
		return Boolean.valueOf(this.buchung_bestaetigen_post);
	}

	/**
	 * DOCME
	 * 
	 * @param buchung_bestaetigen_post
	 *            the buchung_bestaetigen_post
	 */
	public void setBuchungBestaetigenPost(final Boolean buchung_bestaetigen_post) {
		if ((buchung_bestaetigen_post == null)
				|| !buchung_bestaetigen_post.booleanValue()) {
			this.buchung_bestaetigen_post = false;
		} else {
			this.buchung_bestaetigen_post = true;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the buchung_bestaetigen_email.
	 */
	public Boolean getBuchungBestaetigenEmail() {
		return Boolean.valueOf(this.buchung_bestaetigen_email);
	}

	/**
	 * DOCME
	 * 
	 * @param buchung_bestaetigen_email
	 *            the buchung_bestaetigen_email
	 */
	public void setBuchungBestaetigenEmail(
			final Boolean buchung_bestaetigen_email) {
		if ((buchung_bestaetigen_email == null)
				|| !buchung_bestaetigen_email.booleanValue()) {
			this.buchung_bestaetigen_email = false;
		} else {
			this.buchung_bestaetigen_email = true;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return buchung_bestaetigen as String.
	 */
	public String getBuchungBestaetigenAsString() {
		if (this.buchung_bestaetigen_post) {
			if (this.buchung_bestaetigen_email) {
				return "per Post und eMail"; //$NON-NLS-1$
			}
			return "per Post"; //$NON-NLS-1$
		} else if (this.buchung_bestaetigen_email) {
			return "per eMail"; //$NON-NLS-1$
		} else {
			return null;
		}
	}

	/**
	 * DOCME
	 * 
	 * @param buchung_bestaetigen
	 *            the buchung_bestaetigen
	 */
	public void setBuchungBestaetigen(final String buchung_bestaetigen) {
		if (buchung_bestaetigen == null) {
			this.buchung_bestaetigen_email = false;
			this.buchung_bestaetigen_post = false;
		} else {
			String[] arr = buchung_bestaetigen.split(","); //$NON-NLS-1$
			for (String bestaetigung : arr) {
				if (bestaetigung.equals("post")) { //$NON-NLS-1$
					this.buchung_bestaetigen_post = true;
				} else if (bestaetigung.equals("email")) { //$NON-NLS-1$
					this.buchung_bestaetigen_email = true;
				}
			}
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the users
	 */
	public Map<Integer, UserBestaetigungen> getUsers() {
		if (this.users.isEmpty()) {
			return null;
		}
		return this.users;
	}

	/**
	 * DOCME
	 * 
	 * @param users
	 *            the users to set
	 */
	public void setUsers(final Map<Integer, UserBestaetigungen> users) {
		this.users = users;
	}

	/**
	 * DOCME
	 * 
	 * @return the user IDs.
	 */
	public Integer[] getUserIds() {
		Integer[] arr = new Integer[this.users.keySet().size()];
		return this.users.keySet().toArray(arr);
	}

	/**
	 * DOCME
	 * 
	 * @return the rechnung_firma
	 */
	public String getRechnungFirma() {
		return this.rechnung_firma;
	}

	/**
	 * DOCME
	 * 
	 * @param rechnung_firma
	 *            the rechnung_firma to set
	 */
	public void setRechnungFirma(final String rechnung_firma) {
		if ((rechnung_firma == null) || (rechnung_firma.length() < 3)) {
			this.rechnung_firma = null;
		} else {
			this.rechnung_firma = rechnung_firma;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the rechnung_name
	 */
	public String getRechnungName() {
		return this.rechnung_name;
	}

	/**
	 * DOCME
	 * 
	 * @param rechnung_name
	 *            the rechnung_name to set
	 */
	public void setRechnungName(final String rechnung_name) {
		if ((rechnung_name == null) || (rechnung_name.length() < 3)) {
			this.rechnung_name = null;
		} else {
			this.rechnung_name = rechnung_name;
		}
	}

	/**
	 * DOCME
	 * 
	 * @return the rechnung_anschrift
	 */
	public String getRechnungAnschrift() {
		return this.rechnung_anschrift;
	}

	/**
	 * DOCME
	 * 
	 * @param rechnung_anschrift
	 *            the rechnung_anschrift to set
	 */
	public void setRechnungAnschrift(final String rechnung_anschrift) {
		if ((rechnung_anschrift == null) || (rechnung_anschrift.length() < 3)) {
			this.rechnung_anschrift = null;
		} else {
			this.rechnung_anschrift = rechnung_anschrift;
		}
	}
}
