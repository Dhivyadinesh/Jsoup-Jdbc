package SaasDataExtractFromXmlFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Followers {

	public static void main(String[] args) throws IOException, SQLException {
		String url = "jdbc:mysql://localhost:3306/saas";
		String username = "root";
		String password = "dineshdd";
		File file = new File("/home/dinesh/Documents/50product.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
			while (scan.hasNextLine()) {

				String linkedinCount = "0", twitterCount = "0", facebookCount = "0", instagramCount = "0",
						youtubeCount = "0";
				String linkedinUrl = "null", twitterUrl = "null", facebookUrl = "null", instagramUrl = "null",
						youtubeUrl = "null";
				String productName = "0", productUrl = "0";

				productUrl = scan.nextLine();
				Document doc = Jsoup.connect(productUrl).get();
				System.out.println(productUrl);
				productName = doc.getElementsByClass("h-title").text();
				System.out.println(productName);
				Elements followers = doc.getElementsByClass("right_followers").select("a");
String linkedin;
				for (Element element : followers) {
				 public void Follow(String Url ,String linkedin) {
					 if(Url.contains("linkedin")) {
						 
					 }
					 return;
				 }
					
				} 
				Connection conn = DriverManager.getConnection(url, username, password);
				String sql;
				sql = "INSERT INTO followers (productUrl,productName,linkedin,twitter,facebook,instagram,youtube,linkedinCount,twitterCount,facebookCount, instagramCount,youtubeCount,linkedinUrl,twitterUrl,facebookUrl,instagramUrl,youtubeUrl ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, productUrl);
				statement.setString(2, productName);
				statement.setBoolean(3, linkedin);
				statement.setBoolean(4, twitter);
				statement.setBoolean(5, facebook);
				statement.setBoolean(6, instagram);
				statement.setBoolean(7, youtube);
				statement.setString(8, linkedinCount);
				statement.setString(9, twitterCount);
				statement.setString(10, facebookCount);
				statement.setString(11, instagramCount);
				statement.setString(12, youtubeCount);
				statement.setString(13, linkedinUrl);
				statement.setString(14, twitterUrl);
				statement.setString(15, facebookUrl);
				statement.setString(16, instagramUrl);
				statement.setString(17, youtubeUrl);
				statement.executeUpdate();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}