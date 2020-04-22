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

				boolean linkedin = false, twitter = false, facebook = false, instagram = false, youtube = false;
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
				String Url;
				Elements followers = doc.getElementsByClass("right_followers").select("a");

				for (Element element : followers) {
					Url = element.attr("abs:href");
					if (Url.contains("linkedin")) {
						linkedin = Url.contains("linkedin");
						linkedinUrl = element.attr("href");
						linkedinCount = element.getElementsByTag("span").text();
						System.out.println("Product has linkedin " + linkedin);
						System.out.println("linkedin url is" + linkedinUrl);
						System.out.println("linkedinCount" + linkedinCount);
					}

					if (Url.contains("twitter")) {
						twitter = Url.contains("twitter");
						twitterUrl = element.attr("href");
						twitterCount = element.getElementsByTag("span").text();
						System.out.println(twitter);
						System.out.println(twitterUrl);
						System.out.println(twitterCount);
					}

					if (Url.contains("facebook")) {
						facebook = Url.contains("facebook");
						facebookUrl = element.attr("href");
						facebookCount = element.getElementsByTag("span").text();
						System.out.println(facebook);
						System.out.println(facebookUrl);
						System.out.println(facebookCount);
					}

					if (Url.contains("instagram")) {
						instagram = Url.contains("instagram");
						instagramUrl = element.attr("href");
						instagramCount = element.getElementsByTag("span").text();
						System.out.println(instagram);
						System.out.println(instagramUrl);
						System.out.println(instagramCount);

					}

					if (Url.contains("youtube")) {
						youtube = Url.contains("youtube");
						youtubeUrl = element.attr("href");
						youtubeCount = element.getElementsByTag("span").text();
						System.out.println(youtube);
						System.out.println(youtubeUrl);
						System.out.println(youtubeCount);
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