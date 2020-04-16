package SaasDataExtractFromXmlFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DataFromtitle {

	public static void main(String[] args)
			throws IOException, ParserConfigurationException, SAXException, SQLException, InterruptedException {
		String url = "jdbc:mysql://localhost:3306/saas";
		String username = "root";
		String password = "dineshdd";
		File xmlFile = new File("/home/dinesh/Downloads/product_sitemap_google.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(xmlFile);
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName("url");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
				Element eElement = (Element) node;
				String productUrl = eElement.getElementsByTagName("loc").item(0).getTextContent();
				org.jsoup.nodes.Document docm = Jsoup.connect(productUrl).get();
				System.out.println(productUrl);
				String productName = docm.getElementsByClass("h-title").text();
				Elements ProductTitles = docm.body().getElementsByClass("h-titles");
				String productTitlesNames = null;
				for (org.jsoup.nodes.Element element : ProductTitles) {
					productTitlesNames = element.text();

					Connection conn = DriverManager.getConnection(url, username, password);
					String sql;
					sql = "INSERT INTO datafromxml1 (productName,productUrl,productTitlesNames) VALUES (?,?,?) ";
					PreparedStatement statement = conn.prepareStatement(sql);
					statement.setString(1, productName);
					statement.setString(2, productUrl);
					statement.setString(3, productTitlesNames);
					statement.executeUpdate();
					conn.close();
					System.out.println(productName + "----" + productTitlesNames);
				}
		      } 
	}
}
