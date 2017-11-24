package com.kqtp2t;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * LotoScraper
 *
 */
public class App 
{
	static LottoJdbc lottoJdbc = new LottoJdbc();
	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");
	private static final DateFormat indateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss");		
    public static void main( String[] args ) throws IOException, SQLException, ParseException
    {
    	for(int a=0; a<3/*22*/;a++) {
    		String page = a==0?"":"?page="+a;
    		Document doc = Jsoup.connect("http://www.philstar.com/lotto"+page).timeout(0).get();
            Elements trs = doc.getElementsByTag("tr");
            for(Element tr: trs) {
            	Elements val = tr.getElementsByTag("p");
            	for(Element lotto: val) {
            		String ieie = dd.split("\\+")[0];
            		System.out.println(dateFormat.format(dateFormat.parse(new Date().gett)));
            		sorter(lotto.text().toLowerCase(),tr.getElementsByTag("span").attr("content"));
            	}
            }
    	}
        System.out.println("Done");
    }
    
    public static void sorter(String val, String pickDate) throws SQLException {
    	if(val.contains("6/58")||val.contains("ultralotto")||val.contains("ultra lotto")) {
    		lottoJdbc.insertToDb("6/58", getDigits(val, Regex.SIX_DOUBLE_DIGITS_COMMA.getRegex(),Regex.SIX_DOUBLE_DIGITS.getRegex()), pickDate);
    	}else if(val.contains("6/55")||val.contains("grandlotto")||val.contains("grand lotto")){
    		lottoJdbc.insertToDb("6/55", getDigits(val, Regex.SIX_DOUBLE_DIGITS_COMMA.getRegex(),Regex.SIX_DOUBLE_DIGITS.getRegex()), pickDate);
    	}else if(val.contains("6/49")||val.contains("superlotto")||val.contains("super lotto")){
    		lottoJdbc.insertToDb("6/49", getDigits(val, Regex.SIX_DOUBLE_DIGITS_COMMA.getRegex(),Regex.SIX_DOUBLE_DIGITS.getRegex()), pickDate);
    	}else if(val.contains("6/45")||val.contains("megalotto")||val.contains("mega lotto")){
    		lottoJdbc.insertToDb("6/45", getDigits(val, Regex.SIX_DOUBLE_DIGITS_COMMA.getRegex(),Regex.SIX_DOUBLE_DIGITS.getRegex()), pickDate);
    	}else if(val.contains("6/42")){
    		lottoJdbc.insertToDb("6/42", getDigits(val, Regex.SIX_DOUBLE_DIGITS_COMMA.getRegex(),Regex.SIX_DOUBLE_DIGITS.getRegex()), pickDate);
    	}else if(val.contains("3 digit")||val.contains("3-digit")||val.contains("3 digits")||val.contains("3-digits")){
    		lottoJdbc.insertToDb("3 digit", getDigits(val, Regex.THREE_DIGITS.getRegex(),Regex.THREE_DIGITS_COMMA.getRegex()), pickDate);
    	}else if(val.contains("4 digit")||val.contains("4-digit")||val.contains("4 digits")||val.contains("4-digits")){
    		lottoJdbc.insertToDb("4 digit", getDigits(val, Regex.FOUR_DIGITS.getRegex(),Regex.FOUR_DIGITS_COMMA.getRegex()), pickDate);
    	}else if(val.contains("6 digit")||val.contains("6-digit")||val.contains("6 digits")||val.contains("6-digits")){
    		lottoJdbc.insertToDb("6 digit", getDigits(val, Regex.SIX_DIGITS.getRegex(),Regex.SIX_DIGITS_COMMA.getRegex()), pickDate);
    	}
    }
    
    public static String getDigits(String val,String pat,String pat2) {
    	String result= "";
    	Pattern regex = Pattern.compile(pat);
    	Matcher m = regex.matcher(val);
    	if (m.find()) {
    	    result = m.group();
    	}
    	if(result == null && pat2 != null) {
    		regex = Pattern.compile(pat2);
    		m = regex.matcher(val);
    		if (m.find()) {
        	    result = m.group();
        	}
    	}
    	return result.trim().replaceAll(" ", ",");
    } 
}