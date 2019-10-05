package com.thoughtworks.practice.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InterGalacticDataStore {

	private static List<String> intergalacticValues = new ArrayList<>();
	private static List<String> metalsList = new ArrayList<>();
	private static List<String> requiredConversions = new ArrayList<>();

	public static Map<String, String> getIntergalacticValuesasMap() {
		return InterGalacticConverter.convertInterGalacticValuesAsMap(intergalacticValues);
	}

	public static void setIntergalacticValues(List<String> intergalacticValues) {
		InterGalacticDataStore.intergalacticValues = intergalacticValues;
	}

	public static List<String> getIntergalacticValues() {
		return intergalacticValues;
	}

	public static List<String> getMetalsList() {
		return metalsList;
	}

	public static void setMetalsList(List<String> metalsList) {
		InterGalacticDataStore.metalsList = metalsList;
	}

	public static List<String> getRequiredConversions() {
		return requiredConversions;
	}

	public static void setRequiredConversions(List<String> requiredConversions) {
		InterGalacticDataStore.requiredConversions = requiredConversions;
	}

	public static Map<String, Double> getMetalsCreditListasMap() {
		return InterGalacticConverter.convertMetalsCreditAsMap(metalsList);
	}

}
