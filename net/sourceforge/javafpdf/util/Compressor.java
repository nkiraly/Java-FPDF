package net.sourceforge.javafpdf.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Compressor {
	public static byte[] compress(byte[] content) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
			gzipOutputStream.write(content);
			gzipOutputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.printf("Compression ratio %f\n", (1.0f * content.length / byteArrayOutputStream.size()));
		return byteArrayOutputStream.toByteArray();
	}

	public static byte[] decompress(byte[] contentBytes) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			InputStream input = new GZIPInputStream(new ByteArrayInputStream(contentBytes));
			byte[] buf = new byte[1024];
			int len = -1;
			while ((len = input.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return out.toByteArray();
	}

	public static boolean notWorthCompressing(String contentType) {
		return contentType.contains("jpeg") || contentType.contains("pdf") || contentType.contains("zip")
				|| contentType.contains("mpeg") || contentType.contains("avi");
	}
}