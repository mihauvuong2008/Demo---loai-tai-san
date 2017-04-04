package CONFIG;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Application.Setting.HostSetting;
import Application.Setting.userSetting;

public class ConfigReader {

	File UserFile;
	Document UserConfig_doc;
	File HostFile;
	Document HostConfig_doc;

	public ConfigReader(String UserCfg, String HostCfg) {
		try {
			HostFile = new File(HostCfg);
			DocumentBuilderFactory dbFactory2 = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder2 = dbFactory2.newDocumentBuilder();
			HostConfig_doc = dBuilder2.parse(HostFile);

			UserFile = new File(UserCfg);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			UserConfig_doc = dBuilder.parse(UserFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public userSetting getUserSetting() {
		userSetting rs = null;
//		Element doc = HostConfig_doc.getDocumentElement();
		rs = new userSetting();
		return rs;
	}

	public HostSetting getHostSetting() {
		HostSetting rs = null;
		Element doc = HostConfig_doc.getDocumentElement();
		rs = new HostSetting(getTextValue(doc, "ip"), Integer.valueOf(getTextValue(doc, "port")),
				Boolean.valueOf(getTextValue(doc, "ssl")), Integer.valueOf(getTextValue(doc, "TimeDelay")),
				getTextValue(doc, "password"), getTextValue(doc, "password_session"));
		return rs;
	}

	public void SaveHostSetting(HostSetting hs) {
		NodeList nl;
		nl = HostConfig_doc.getElementsByTagName("ip");
		if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
			nl.item(0).getFirstChild().setNodeValue(hs.getIp());
		}
		nl = HostConfig_doc.getElementsByTagName("port");
		if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
			nl.item(0).getFirstChild().setNodeValue(hs.getPort() + "");
		}
		nl = HostConfig_doc.getElementsByTagName("ssl");
		if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
			nl.item(0).getFirstChild().setNodeValue(String.valueOf(hs.isSsl()));
		}
		nl = HostConfig_doc.getElementsByTagName("TimeDelay");
		if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
			nl.item(0).getFirstChild().setNodeValue(hs.getTimeDelay() + "");
		}
		nl = HostConfig_doc.getElementsByTagName("password");
		if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
			nl.item(0).getFirstChild().setNodeValue(hs.getPassword());
		}
		nl = HostConfig_doc.getElementsByTagName("password_session");
		if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
			nl.item(0).getFirstChild().setNodeValue(hs.getInitVector());
		} else {

		}
		try {
			Transformer tr = TransformerFactory.newInstance().newTransformer();
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			tr.setOutputProperty(OutputKeys.METHOD, "xml");
			tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			// tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "host.xml");
			tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			// send DOM to file
			tr.transform(new DOMSource(HostConfig_doc), new StreamResult(HostFile));

		} catch (TransformerException te) {
			System.out.println(te.getMessage());
		}
	}

	public void savePassword(String cipherText) {
		HostConfig_doc.getDocumentElement().normalize();
		System.out.println("Root element :" + HostConfig_doc.getDocumentElement().getNodeName());
		NodeList nList = HostConfig_doc.getElementsByTagName("host");
		System.out.println("---------------------------------------------");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			// System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				System.out.println(cipherText);
				((Node) eElement.getElementsByTagName("password").item(0)).setTextContent(cipherText);
			}
		}
	}

	private String getTextValue(Element doc, String tag) {
		String value = "";
		NodeList nl;
		nl = doc.getElementsByTagName(tag);
		if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
			value = nl.item(0).getFirstChild().getNodeValue();
			value = nl.item(0).getFirstChild().getTextContent();
		}
		System.out.println("value " + value);
		return value;
	}
}
