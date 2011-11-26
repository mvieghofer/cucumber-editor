package com.cucumber.editor.server.databasemanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * filename: Common.ConfigParser.java 
 * 
 * @date: 06.05.2011
 * @author: Markus Vieghofer
 * 
 */
public class ConfigParser {

	private String path;
	private boolean configFileParsed = false;
	private Map<String, String> propMap = new HashMap<String, String>();

	/**
	 * @param path
	 */
	public ConfigParser(String path) {
		this.path = path;
	}

	/**
	 * @param value
	 * @return
	 */
	public String getProperty(String value) {
		if (!configFileParsed) {
			parseConfigFile();
		}
		return propMap.get(value);
	}

	/**
	 * 
	 */
	private void parseConfigFile() {
		configFileParsed = true;
		XMLInputFactory factory = XMLInputFactory.newFactory();
		try {
			InputStream in = new FileInputStream(path);
			XMLEventReader rdr = factory.createXMLEventReader(in);
			while (rdr.hasNext()) {
				XMLEvent event = rdr.nextEvent();
				if (event.isStartElement())
					readConfigNode(event);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param nextTag
	 */
	@SuppressWarnings("rawtypes")
	private void readConfigNode(XMLEvent tag) {
		if (tag.isStartElement()) {
			StartElement element = tag.asStartElement();
			Iterator attributes = element.getAttributes();
			String key = "";
			String value = "";
			while (attributes.hasNext()) {
				Attribute next = (Attribute) attributes.next();
				if (next.getName().toString().equals("key")) {
					key = next.getValue();
				} else if (next.getName().toString().equals("value")) {
					value = next.getValue();
				}
				if (!key.equals("") && !value.equals("")) {
					propMap.put(key, value);
					key = "";
					value = "";
				}
			}
		}
	}

}
