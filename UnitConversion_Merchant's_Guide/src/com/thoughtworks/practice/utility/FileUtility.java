package com.thoughtworks.practice.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.practice.constants.RomanDecimalConstants;

public class FileUtility {

	private static StringBuilder sb = new StringBuilder("");
	private static Map<String,String> intergalacticToRomanMap = new HashMap<>();
	private static Map<String,Double> metalCredits = new HashMap<>();
	/**
	 * This method is used to read input from given filePath.
	 * @param filepath Path from where input is to be read.
	 * @return The entire file content as String.
	 */
	public static String readInput( String filepath ) {
		try(BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			String currentLine ;
			while( ( currentLine = br.readLine() )!=null ) {
				if( currentLine.split(" ").length == 3 ) {
					InterGalacticDataStore.getIntergalacticValues().add(currentLine.trim());
					//String[] units = currentLine.split(" ");
					//intergalacticToRomanMap.put(units[0].trim(), units[2].trim());
				}
				else if( currentLine.endsWith("?") ) {
					String requiredUnits = currentLine.split(" is ")[1];
					if(null != requiredUnits )
					InterGalacticDataStore.getRequiredConversions().add(requiredUnits);
					//printConvertedUnits(requiredUnits.substring(0, requiredUnits.length()-1));
				}
				else if ( currentLine.contains(" is ") ) {
					InterGalacticDataStore.getMetalsList().add(currentLine.trim());
					//setMetalCredits(currentLine);
				}
				sb.append(currentLine);
			}
		} catch (FileNotFoundException fn) {
			System.out.println("File not found");
			fn.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("Error reading file");
			ioe.printStackTrace();
		}
		
		return sb.toString();
	}
	private static void printConvertedUnits(String convertUnitLine) {
		StringBuilder romanNumber = new StringBuilder(""); 
		String currentMetal = null;
		String[] units = convertUnitLine.split(" ");
		for( int i = 0 ; i < units.length;i++ ) {
			if(intergalacticToRomanMap.containsKey(units[i]) ) {
				System.out.print(units[i]+" ");
				romanNumber.append(intergalacticToRomanMap.get(units[i]));
			}
			else if(metalCredits.containsKey(units[i])){
				currentMetal = units[i];
			}
		}
		int metalUnitsCount =  getIntegerFromRoman(romanNumber.toString());
		if( null != currentMetal )
			System.out.print(currentMetal+" is "+metalCredits.get(currentMetal)*metalUnitsCount + " Credits");
		else
			System.out.print("is "+ metalUnitsCount);
		System.out.println();
	}
	private static void setMetalCredits(String currentLine) {
		StringBuilder romanMetalCredits = new StringBuilder("");
		
		String[] units = currentLine.split(" is ");
		String[] intergalacticUnits = units[0].split(" ");
		// we will iterate only upto intergalactic numerals so last item which is metal(Silver,gold,etc) should be ignored
		for( int i = 0 ; i < intergalacticUnits.length -1 ;i++ ) {
			romanMetalCredits.append( intergalacticToRomanMap.get(intergalacticUnits[i]) );
		}
		int numberOfMetalUnits = getIntegerFromRoman(romanMetalCredits.toString());
		double givenMetalCreditvalue = Double.valueOf( units[1].split(" ")[0] );
		double metalCreditvalue = givenMetalCreditvalue/numberOfMetalUnits; 
		String metalName = intergalacticUnits[intergalacticUnits.length -1];
		metalCredits.put(metalName, metalCreditvalue);
	}
	private static int getIntegerFromRoman(String romanNumber) {
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
