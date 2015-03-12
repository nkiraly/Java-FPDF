/* $Id: Charwidths.java,v 1.3 2008/03/06 17:48:04 ashmodai Exp $
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Character widths.
 * 
 * @author Alan Plum
 * @since 4 Mar 2008
 * @version $Revision: 1.3 $
 */
class Charwidths {
	private final Properties	props;

	/**
	 * Constructor. Creates a Charwidths object for a core font.
	 * 
	 * @param name
	 *            name of the character widths file.
	 * @throws IOException
	 *             if an error occurred when reading from the file.
	 */
	public Charwidths(final String name) throws IOException {
		InputStream stream = this.getClass().getResourceAsStream(
				"fonts/" + name + ".widths"); //$NON-NLS-1$//$NON-NLS-2$
		this.props = new Properties();
		this.props.load(stream);
		stream.close();
	}

	/**
	 * Constructor. Creates a Charwidths object from a local file.
	 * 
	 * @param file
	 *            file containing the character widths info.
	 * @throws IOException
	 *             if an error occurred when reading from the file.
	 */
	public Charwidths(final File file) throws IOException {
		InputStream stream = new FileInputStream(file);
		this.props = new Properties();
		this.props.load(stream);
		stream.close();
	}

	/**
	 * Get the width of the given character.
	 * 
	 * @param c
	 *            a character
	 * @return the width of that character.
	 */
	public int get(final char c) {
		String str = this.props.getProperty(Integer.valueOf(c).toString());
		if (str == null) {
			return 600;
		}
		return Integer.parseInt(str);
	}
}
