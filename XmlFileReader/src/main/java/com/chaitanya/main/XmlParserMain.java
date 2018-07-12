package com.chaitanya.main;

import com.chaitanya.model.XmlParsingImpl;

public class XmlParserMain {
	
	 private static XmlParsingImpl xmlParser = new XmlParsingImpl();
	 
	 public static void main(String[] args) {
		 xmlParser.XmlReader("Data.xml");
	 }

}
