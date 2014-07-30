import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: PinyinUtil
 * @Description: 拼音操作工具类
 * @author XiMing.Fu
 * @date 2014-3-11 下午04:42:19
 * 
 */
public class PinyinUtil {

	private static final Log logger = LogFactory.getLog(PinyinUtil.class
			.getName());

	/**
	 * 汉字转拼音的方法
	 * 
	 * @param name
	 *            汉字
	 * @return 拼音
	 */
	public static String HanyuToPinyin(String name) {
		String pinyinName = "";
		char[] nameChar = name.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0];
				} catch (Exception e) {
					logger.error(e);
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	public static void main(String[] args) {
		System.out.println(HanyuToPinyin("付细明"));
	}

}
