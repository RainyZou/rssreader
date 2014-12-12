package com.lks.rss_saxparser.saxparser;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.lks.rss_saxparser.entity.RssFeed;
import com.lks.rss_saxparser.service.RssHandler;

public class RssFeed_SAXParser {

	public RssFeed getFeed(String urlStr) throws ParserConfigurationException, SAXException, IOException{
		URL url = new URL(urlStr);
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance(); //����SAX��������
		SAXParser saxParser = saxParserFactory.newSAXParser(); //������������������
		XMLReader xmlReader = saxParser.getXMLReader(); //ͨ��saxParser����xmlReader�Ķ���
		
		RssHandler rssHandler=new RssHandler();
		xmlReader.setContentHandler(rssHandler);
		//ʹ��url��������������Ϊ xmlReader����������Դ������
		InputSource inputSource = new InputSource(url.openStream());
		xmlReader.parse(inputSource);
		
		return rssHandler.getRssFeed();
	}
}