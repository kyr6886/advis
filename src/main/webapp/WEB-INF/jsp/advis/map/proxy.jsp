<%@ page language="java" import="java.io.*,java.net.*,java.util.regex.*" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// WMS GetfeatureInfo Proxy 대행자 처리부분 
	String buffer = "";
	BufferedReader in = null  ; 
	String str=null;
	try {
		
		
 		str =request.getParameter("url");
 		str=str.replace("&amp;", "&");
 		
 		str=str.replace("61.105.196.70","192.168.0.70");
 		URL url = new URL(str); 
		String str_url = null;
		String url_proxy = null;
		String change_Str= request.getRequestURL().toString();
		String proxyEncoding = "UTF-8";
		System.out.println(str);

		URLConnection connection = url.openConnection();

		connection.setRequestProperty("CONTENT-TYPE", "text/html");
		in = new BufferedReader(new InputStreamReader(url.openStream(),proxyEncoding));
		String inputLine=null;
		while ((inputLine = in.readLine()) != null) {
			System.out.println("buffer:::"+ inputLine.trim());
			buffer += inputLine.trim();
		}
		
	} catch (IOException e) {
	} finally {
		in.close();
	}
%>
<%=buffer%>