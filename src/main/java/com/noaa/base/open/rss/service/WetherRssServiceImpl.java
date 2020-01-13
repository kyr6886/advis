package com.noaa.base.open.rss.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.noaa.base.open.dto.WetherRssDto;

@Service("wetherRssService")
public class WetherRssServiceImpl implements IWetherRssService {
	@Value("${rss.wether.url}")
	private String wetherUrl;

	@Override
	public List<WetherRssDto> list(String paramAdmCode) {
		if(paramAdmCode==null) return null;
		
		List<WetherRssDto> rs = new ArrayList<WetherRssDto>();
		try {

			String title = null;
			String category = null;
			String tm = null;
			String ts = null;
			String x = null;
			String y = null;
			
			Document document = DocumentBuilderFactory
					.newInstance()
					.newDocumentBuilder()
					.parse(new InputSource(new ByteArrayInputStream(getDFSRSS(paramAdmCode).getBytes("utf-8"))));
			document.getDocumentElement().normalize();

			NodeList nodeList = document.getElementsByTagName("item");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i); // data엘리먼트 노드
				Element fstElmnt = (Element) node;
				category = getValue(fstElmnt, "category");
				title = getValue(fstElmnt, "title");
			}

			nodeList = document.getElementsByTagName("header");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i); // data엘리먼트 노드
				Element fstElmnt = (Element) node;
				tm = getValue(fstElmnt, "tm");
				ts = getValue(fstElmnt, "ts");
				x = getValue(fstElmnt, "x");
				y = getValue(fstElmnt, "y");
			}
			
			nodeList = document.getElementsByTagName("data");

			for (int i = 0; i < nodeList.getLength(); i++) {
				WetherRssDto data = new WetherRssDto();

				Node node = nodeList.item(i); // data엘리먼트 노드
				Element fstElmnt = (Element) node;
					
				data.setHour(getValue(fstElmnt, "hour"));
				data.setTemp(getValue(fstElmnt, "temp"));
				data.setTmx(getValue(fstElmnt, "tmx"));
				data.setTmn(getValue(fstElmnt, "tmn"));
				data.setSky(getValue(fstElmnt, "sky"));
				data.setPty(getValue(fstElmnt, "pty"));
				data.setWfKor(getValue(fstElmnt, "wfKor"));
				data.setWfEn(getValue(fstElmnt, "wfEn"));
				data.setPop(getValue(fstElmnt, "pop"));
				data.setR12(getValue(fstElmnt, "r12"));
				data.setS12(getValue(fstElmnt, "s12"));
				data.setWs(getValue(fstElmnt, "ws"));
				data.setWd(getValue(fstElmnt, "wd"));
				data.setWdKor(getValue(fstElmnt, "wdKor"));
				data.setWdEn(getValue(fstElmnt, "wdEn"));
				data.setReh(getValue(fstElmnt, "reh"));
				data.setR06(getValue(fstElmnt, "r06"));
				data.setS06(getValue(fstElmnt, "s06"));
				data.setDay(getValue(fstElmnt,"day"));
				data.setTitle(title);
				data.setCategory(category);
				data.setTm(tm);
				data.setTs(ts);
				data.setX(x);
				data.setY(y);
				
				rs.add(data);
			}

		} catch (Exception ex) {
			rs=null;
		}
		return rs;
	}

	private String getValue(Element element, String paramName) {
		NodeList nameList = element.getElementsByTagName(paramName);
		Element nameElement = (Element) nameList.item(0);
		nameList = nameElement.getChildNodes();
		return ((Node) nameList.item(0)).getNodeValue();
	}

	private String getDFSRSS(String paramAdmCode) {
		String queryURL = wetherUrl + "?zone=" + paramAdmCode;
		StringBuilder body = new StringBuilder();
		try {
			URL url = new URL(queryURL);
			URLConnection urlConnection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));

			boolean loop = true;
			while (loop) {
				String data = in.readLine();
				if (data != null) {
					body.append(data);
				} else {
					loop = false;
				}
			}
			in.close();

		} catch (Exception ex) {
			body = null;
		}
		return body.toString();
	}

}
