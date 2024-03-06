package com.app.util;

public class MoneyConverterUtil {
	
	public static void main(String[] args) {
		
		System.out.println(MoneyConverterUtil.getMoneyIntoWords(2935012.216));
	}
	
	public static final String[] units = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
			"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
			"Nineteen" };

	public static final String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
			"Ninety" };

	public static String getMoneyIntoWords(final double money) {
		long rupees = (long) money;
		long paisa = Math.round((money - rupees) * 100);
		if (money == 0D) {
			return "";
		}
		String ruppesPart = "";
		if (rupees > 0) {
			ruppesPart = convert(rupees) + " Rupees" + (rupees == 1 ? "" : "");
		}
		String paisaPart = "";
		if (paisa > 0) {
			if (ruppesPart.length() > 0) {
				paisaPart = " and ";
			}
			paisaPart += convert(paisa) + " Paisa" + (paisa == 1 ? "" : " Only");
		}
		return "*** "+ruppesPart + (paisaPart == "" ? " Only" : paisaPart);
	}

	public static String convert(final long n) {
		if (n < 0) {
			return "Minus " + convert(-n);
		}
		if (n < 20) {
			return units[(int) n];
		}
		if (n < 100) {
			return tens[(int) (n / 10)] + ((n % 10 != 0) ? " " : "") + units[(int) (n % 10)];
		}
		if (n < 1000) {
			return units[(int) (n / 100)] + " Hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
		}
		if (n < 100000) {
			return convert(n / 1000) + " Thousand" + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
		}
		if (n < 10000000) {
			return convert(n / 100000) + " Lakh" + ((n % 100000 != 0) ? " " : "") + convert(n % 100000);
		}
		return convert(n / 10000000) + " Crore" + ((n % 10000000 != 0) ? " " : "") + convert(n % 10000000);
	}
}
