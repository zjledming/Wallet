import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

public class IOUtil {

	/**
	 * InputStream中读取string
	 * 
	 * @param stream
	 *            输入流
	 * @param encode
	 *            编码
	 * @return
	 */
	public static String inputStream2Str(InputStream stream, String encode) {
		StringWriter buffer = new StringWriter();
		try {
			IOUtils.copy(stream, buffer, encode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}

	/**
	 * InputStream中读取string，默认采用UTF-8编码
	 * 
	 * @param stream
	 *            输入流
	 * @return
	 */
	public static String inputStream2Str(InputStream stream) {
		return inputStream2Str(stream, "UTF-8");
	}

}
