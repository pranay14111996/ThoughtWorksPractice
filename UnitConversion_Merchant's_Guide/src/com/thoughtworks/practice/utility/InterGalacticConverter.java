package com.thoughtworks.practice.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.practice.constants.InputConstants;

public class InterGalacticConverter {

	public static Map<String,String> convertInterGalacticValuesAsMap(List<String> interGalacticValues) {
		Map<String,String> map = new HashMap<>();
		for( String interGalacticValue : interGalacticValues ) {
			String[] items = interGalacticValue.split(" ");
			if( items.length == 3 ) {
				map.put(items[0].trim(), items[2].trim());
			}
		}
		return map;
	}

	public static Map<String, Double> convertMetalsCreditAsMap(List<String> metalsListData) {
		Map<String,Double> map = new HashMap<>();
		for(String metalData: metalsListData) {
			setMetalCredits(map,metalData);
		}
		return map;
	}
	private static Map<String,String> intergalacticToRomanMap =InterGalacticDataStore.getIntergalacticValuesasMap();
	private static void setMetalCredits(Map<String,Double> map, String metalData) {
		StringBuilder romanMetalCredits = new StringBuilder("");
		
		String[] galacticUnits = metalData.split(InputConstants.IS);
		String[] intergalacticUnits = galacticUnits[0].split(InputConstants.SPACE);
		// we will iterate only upto intergalactic numerals so last item which is metal(Silver,gold,etc) should be ignored
		for( int i = 0 ; i < intergalacticUnits.length -1 ;i++ ) {
			if(intergalacticToRomanMap.containsKey(intergalacticUnits[i])) {
			romanMetalCredits.append( intergalacticToRomanMap.get(intergalacticUnits[i]) );
			}
		}
		int numberOfMetalUnits = RomanToDecimalConverter.getDecimalFromRoman(romanMetalCredits.toString());
		double givenMetalCreditvalue = Double.valueOf( galacticUnits[1].split(" ")[0] );
		double metalCreditvalue = givenMetalCreditvalue/numberOfMetalUnits; 
		String metalName = intergalacticUnits[intergalacticUnits.length -1];
		map.put(metalName, metalCreditvalue);
	}

}
