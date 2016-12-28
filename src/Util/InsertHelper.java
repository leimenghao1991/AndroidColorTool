package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class InsertHelper {
	private static final String colorFileEnd = "</resources>";
	private static final String styleDayColorStart = "<style name=\"Day\">";
	private static final String styleNightColorStart = "<style name=\"Night\">";
	public static final String UTF8 = "UTF-8";
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	private String charsetStr;
	private CharsetEncoder charset; //编码
	private static InsertHelper insertHelper;
	
	public static InsertHelper getInstance(){
		if (insertHelper == null) {
			insertHelper = new InsertHelper();
		}
		return insertHelper;
	}
	
	private InsertHelper(){
		charsetStr = UTF8;
		setCharset();
	}
	
	public boolean insertToColor(String colorFilePath, String colorName, String colorValue){
		String dayColorLine = "    <color name = \"" + colorName + "\">" + colorValue + "</color>";
		return writeToFile(colorFilePath, dayColorLine, colorFileEnd, true);
	}
	
	public boolean insertToAttr(String attrFilePath, String colorName){
		String colorLine = "    <attr name = \"" + colorName + "\" format=\"color\"/>";
		return writeToFile(attrFilePath, colorLine, colorFileEnd, true);
	}
	
	public boolean insertToStyle(String styleFilePath, String colorName){
		String dayColorLine = "        <item name=\"" + colorName + "\">@color/" + colorName + "</item>";
		boolean addDayColorSuc = writeToFile(styleFilePath, dayColorLine, styleDayColorStart, false);
		if (!addDayColorSuc) {
			return false;
		}
		String darkColorName = generateNightColorName(colorName);
		String nightColorLine = "        <item name=\"" + colorName + "\">@color/" + darkColorName + "</item>";
		boolean addNightColorSuc = writeToFile(styleFilePath, nightColorLine, styleNightColorStart, false);
		return addNightColorSuc;
	}
	
	/**
	 * 将writeContent插入到filePath指向的文件，插入位置有replaceStr指定
	 * @param filePath
	 * @param writeContent
	 * @param locateStr file文件中的一部分内容，用来指定插入位置，根据insertToAbove在来决定是插入到locateStr前一行还是下一行
	 * @param insertToAbove
	 * @return
	 */
	private boolean writeToFile(String filePath, String writeContent, String locateStr, boolean insertToAbove){
		BufferedReader reader = null;
		BufferedWriter writer = null; 
		StringBuilder buf = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(filePath));

			String readLine = reader.readLine();
			while (readLine != null) {
				if (readLine.contains(locateStr)) {
					if (!insertToAbove) {
						buf.append(readLine);
						buf.append(LINE_SEPARATOR);
						buf.append(writeContent);
						buf.append(LINE_SEPARATOR);
					}else {
						buf.append(writeContent);
						buf.append(LINE_SEPARATOR);
						buf.append(locateStr);
					}
				}else {
					buf.append(readLine);
					buf.append(LINE_SEPARATOR);
				}
				readLine = reader.readLine();
			}
			
			writer = new BufferedWriter(new FileWriter(filePath));
			writer.write(buf.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return true;
	}
	
	public boolean insert(String colorFilePath, String attrFilePath, String styleFilePath, String colorName, String dayColorValue, String nightColorValue){
		File colorFile = new File(colorFilePath);
		if (!colorFile.exists()) {
			return false;
		}
		File attrFile = new File(attrFilePath);
		if (!attrFile.exists()) {
			return false;
		}
		File styleFile = new File(styleFilePath);
		if (!styleFile.exists()) {
			return false;
		}
		
		insertToColor(colorFilePath, colorName, dayColorValue);
		
		String nightColorName = generateNightColorName(colorName);
		insertToColor(colorFilePath, nightColorName, nightColorValue);
		
		insertToAttr(attrFilePath, colorName);
		
		insertToStyle(styleFilePath, colorName);
		return true;
	
	}
	
	public void setCharset(){
		charset = Charset.forName(charsetStr).newEncoder();
	}
	
	private String generateNightColorName(String dayColorName){
		return dayColorName + "_dark";
	}
}
