package com.thoughtworks.practice.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.practice.constants.InputConstants;

public class InterGalacticTransaltor {

	private static Map<String,String> intergalacticToRomanMap = new HashMap<>();
	private static Map<String,Double> metalsCreditValueMap = new HashMap<>();
	
	public static void main(String[] args) {
		String filepath = "D:\\SpringWorkspace_MySpaces\\ThoughtWorksPractice\\src\\InputText.txt";
		FileUtility.readInput(filepath);
		
		intergalacticToRomanMap = InterGalacticDataStore.getIntergalacticValuesasMap();
		metalsCreditValueMap = InterGalacticDataStore.getMetalsCreditListasMap();
		List<String> queries = InterGalacticDataStore.getRequiredConversions();
		for( String query : queries ) {
			resolveQuery(query);
		}
	}

	private static void resolveQuery(String query) {
		StringBuilder romanNumber = new StringBuilder(""); 
		String currentMetal = null;
		String[] items = query.split(InputConstants.SPACE);
		for( int i = 0 ; i < items.length;i++ ) {
			if(intergalacticToRomanMap.containsKey(items[i]) ) {
				System.out.print(items[i]+" ");
				romanNumber.append(intergalacticToRomanMap.get(items[i]));
			}
			else if(metalsCreditValueMap.containsKey(items[i])){
				currentMetal = items[i];
			}
		}
		int metalUnitsCount =  RomanToDecimalConverter.getDecimalFromRoman(romanNumber.toString());
		if( null != currentMetal )
			System.out.print(currentMetal+" is "+metalsCreditValueMap.get(currentMetal)*metalUnitsCount + " Credits");
		else
			System.out.print("is "+ metalUnitsCount);
		System.out.println();
	}
}
