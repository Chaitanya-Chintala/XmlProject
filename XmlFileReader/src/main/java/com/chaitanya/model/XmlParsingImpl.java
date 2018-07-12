package com.chaitanya.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
 
/* This class implement the XmlParser interface and override the XmlReader() method to
 * read the content of any XML file and display to the user Element by element.
 * It returns "values" of ArrayList<String> type which contains all the values 
 * associated with the Elements in XML file.
 */

public class XmlParsingImpl implements XmlParser {
	
	public static ArrayList<String> childElement = new ArrayList<String>();
	public static ArrayList<String> values = new ArrayList<String>();
	public static ArrayList<String> parentElement = new ArrayList<String>();
	
	/* Read the content of XML file and display the Nodes and Values.
	 * 
	 */
	public ArrayList<String> XmlReader(String filename) {
		try {
			//Loading the XML file named "Data.xml" which is in the resources folder of project.
			ClassLoader classLoader = getClass().getClassLoader();
			File xmlFile = new File(classLoader.getResource(filename).getFile());
			FileReader fr = new FileReader(xmlFile);
			BufferedReader bf = new BufferedReader(fr);
			String xml_data = "";
			int numberOfLines = 0;
			int numberOfNodes =0;
			StringBuilder sb = new StringBuilder();
			
			//Parsing through the XML file and appending each line to the String Builder Object
			while ((xml_data = bf.readLine()) != null) {
				numberOfLines = numberOfLines + 1;
				sb.append(xml_data);
			}
			String data = sb.toString();
			
			for (int i = 0; i <= data.length(); i++) {
				
				int start = data.indexOf("<");
				int end = data.indexOf(">");

				String attribute_data = data.substring(start + 1, end);
				
				//Fetching the data between the start("<") and end(">") tags
				data = data.substring(end + 1);
				String data_dummy = data;
				int find = data.indexOf("</" + attribute_data + ">");
				
				//Checks for the Parent Nodes in the XML file
				if (find > -1) {
					if (data_dummy.startsWith("<")) {
						
						//Adding the Parent Elements to "parentElement" ArrayList
						parentElement.add(attribute_data);
						numberOfNodes = numberOfNodes+1;
					} 
					
					//Checks for the Child Nodes in the XML file
					else {
						if (attribute_data.startsWith("/")) {
						} 
						else {
							
							//Adding the Child Elements to "ChildElement" ArrayList
							childElement.add(attribute_data);
							numberOfNodes = numberOfNodes+1;
						}
					}
				}
				
				//Checks for the values between the elements and stores them in a variable
				if (data.length() > 0) {
					if (data.startsWith("<")) {
					} else {
						int filter = data.indexOf("<");
						if (filter > -1) {
							String split_value = data.substring(0, filter);
							
							//Adding the Values to "values" ArrayList
							values.add(split_value);
							System.out.println(attribute_data + "=" + split_value);
						}
					}
				}
			}
			
			System.out.println("No of nodes in XMl file:"+numberOfNodes);
			System.out.println("No of lines in XML file:" + numberOfLines);
			bf.close();
		} 
		catch (Exception e) {
			System.out.println("Error is :" + e.toString());
			throw new RuntimeException("FileNotFoundException");
		}
		return values;
	}

}
