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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadSaasProductListXmlFileAndUploadInDB {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, SQLException {
		String url = "jdbc:mysql://localhost:3306/saas";
		String username = "root";
		String password = "dineshdd";
		File xmlFile = new File("/home/dinesh/Downloads/product_sitemap_google.xml");
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlFile);
			doc.getDocumentElement().normalize();
				NodeList nodeList = doc.getElementsByTagName("url");
				for (int i = 0; i < nodeList.getLength(); i++) {
					try {
					Node node = nodeList.item(i);
					Element eElement = (Element) node;
					String productUrl = eElement.getElementsByTagName("loc").item(0).getTextContent();
					org.jsoup.nodes.Document docm = Jsoup.connect(productUrl).get();
					String productTitle;
					boolean productReview = false, productAwards = false, productScreenshot = false,
					           productVendorscreenshot = false;

					productTitle = docm.getElementsByClass("h-title").text();
					System.out.println(productTitle);
					Connection conn = DriverManager.getConnection(url, username, password);
					String sql;
					sql = "INSERT INTO datafromxml (productTitle,productUrl,productReview,productAwards,productScreenshot,productVendorscreenshot) VALUES (?,?,?,?,?,?) ";
					PreparedStatement statement = conn.prepareStatement(sql);
					statement.setString(1, productTitle);
			        statement.setString(2, productUrl);
			
			        try {
						productReview = docm.getElementById("reviews").hasText();
						System.out.println("The product has review topic " + productReview);
		
					} catch (Exception e) {
						System.out.println(0);
					}
					
					try {
						productAwards = docm.getElementById("awards").hasText();
						System.out.println("The product has awards topic " + productAwards);
		
					} catch (Exception e) {
						System.out.println(0);
					}
		
					try {
						productScreenshot = docm.getElementsByClass("container_wrpr brd-bot slid-bx-height").hasText();
						System.out.println("The product has productScreenshot topic " + productScreenshot);
		
					} catch (Exception e) {
						System.out.println(0);
					}
		
					try {
						productVendorscreenshot = docm.getElementById("pricing_screenshot").hasText();
						System.out.println("The product has productVendorscreenshot topic " + productVendorscreenshot);
		
					} catch (Exception e) {
						System.out.println(0);
					}
					
					statement.setBoolean(3, productReview);
					statement.setBoolean(4, productAwards);
					statement.setBoolean(5, productScreenshot);
					statement.setBoolean(6, productVendorscreenshot);
					statement.executeUpdate();
			  }catch(Exception e) {
			  System.out.println("The Product Url not Found");
		   }	
		  }
		 }catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
