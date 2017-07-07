package br.com.costa.credfiscal.util;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class NotaUtil {

	public static String replacesNfe(String xml){

		xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		xml = xml.replaceAll(" xmlns=\"\" xmlns:ns3=\"http://www.portalfiscal.inf.br/nfe\"", "");
		xml = xml.replaceAll(" xmlns=\"http://www.portalfiscal.inf.br/nfe\"", "");
		xml = xml.replaceAll("ns2:", "");
		xml = xml.replaceAll("ns3:", "");
		xml = xml.replaceAll("&lt;", "<");
		xml = xml.replaceAll("&amp;", "&");
		xml = xml.replaceAll("&gt;", ">");
		xml = xml.replaceAll("<Signature>", "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">");
		
		return xml;

	}
	
	public static <T> T xmlToObject(String xml, Class<T> classe) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(classe);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		return unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), classe).getValue();
	}
}
