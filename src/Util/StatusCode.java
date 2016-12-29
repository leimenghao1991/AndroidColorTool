package Util;

import java.util.HashMap;

public class StatusCode {
	public static final HashMap<Integer, String> codeMessageMap = new HashMap<Integer, String>(){
		{
			put(SUCCESS, "成功");
			put(INSERT_TO_COLOR_ERROR, "插入到color.xml失败");
			put(INSERT_TO_ATTR_ERROR, "插入到attr.xml失败");
			put(INSERT_TO_STYLES_ERROR, "插入到styles.xml失败");
			put(COLOR_XML_PATH_ERROR, "color.xml文件路径无效");
			put(ATTR_XML_PATH_ERROR, "attr.xml文件路径无效");
			put(STYLES_XML_PATH_ERROR, "styles.xml文件路径无效");
		}
	};
	
	public static final int SUCCESS = 0;
	public static final int INSERT_TO_COLOR_ERROR = 100;
	public static final int INSERT_TO_ATTR_ERROR = 101;
	public static final int INSERT_TO_STYLES_ERROR = 102;
	
	public static final int COLOR_XML_PATH_ERROR = 103;
	public static final int ATTR_XML_PATH_ERROR = 104;
	public static final int STYLES_XML_PATH_ERROR = 105;
}
