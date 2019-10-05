package com.thoughtworks.practice.utility;

import com.thoughtworks.practice.constants.RomanDecimalConstants;

public class RomanToDecimalConverter {
	public static int getDecimalFromRoman(String romanNumber) {
		int result = 0 ;
		
		char[] romanNumberChar = romanNumber.toCharArray();
		for(int i = 0 ; i < romanNumberChar.length;i++ ) {
			int currentValue = RomanDecimalConstants.getDecimalFromRomanLiteral(romanNumberChar[i]);
			if( i+1 < romanNumberChar.length ) {
				int nextValue = RomanDecimalConstants.getDecimalFromRomanLiteral(romanNumberChar[i+1]);
				if( currentValue >= nextValue ) {
					result += currentValue;
				}
				else{
					result = result + nextValue - currentValue;
					i++;
				}
			}
			else {
				result += currentValue;
			}
		}
		return result ;
	}
}
