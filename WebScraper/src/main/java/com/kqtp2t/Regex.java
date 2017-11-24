package com.kqtp2t;

public enum Regex {
	SIX_DOUBLE_DIGITS("\\d{1,2}\\s\\d{1,2}\\s\\d{1,2}\\s\\d{1,2}\\s\\d{1,2}\\s\\d{1,2}"),
	SIX_DOUBLE_DIGITS_COMMA("\\d{1,2},\\d{1,2},\\d{1,2},\\d{1,2},\\d{1,2},\\d{1,2}"),
	SIX_DIGITS("\\d{1}\\s\\d{1}\\s\\d{1}\\s\\d{1}\\s\\d{1}\\s\\d{1}"),
	SIX_DIGITS_COMMA("\\d{1},\\d{1},\\d{1},\\d{1},\\d{1},\\d{1}"),
	THREE_DIGITS("\\d{1}\\s\\d{1}\\s\\d{1}"),
	THREE_DIGITS_COMMA("\\d{1},\\d{1},\\d{1}"),
	FOUR_DIGITS("\\d{1}\\s\\d{1}\\s\\d{1}\\s\\d{1}"),
	FOUR_DIGITS_COMMA("\\d{1},\\d{1},\\d{1},\\d{1}");
	
	private final String reg;
	
	Regex(String reg){
		this.reg =reg;
	}
	
	public String getRegex() {
		return reg;
	}
}