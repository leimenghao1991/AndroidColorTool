package model;

import java.io.Serializable;

public class Color implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3513019943842869405L;
	private String colorName;
	private String colorValue;

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getColorValue() {
		return colorValue;
	}

	public void setColorValue(String colorValue) {
		this.colorValue = colorValue;
	}

}
