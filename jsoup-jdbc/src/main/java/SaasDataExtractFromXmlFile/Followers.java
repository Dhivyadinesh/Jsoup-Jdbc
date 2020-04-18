package SaasDataExtractFromXmlFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Followers {

	public static void main(String[] args) throws IOException, SQLException {

		File file = new File("/home/dinesh/Documents/50product.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);

			while (scan.hasNextLine()) {
				String productUrl = scan.nextLine();
				Document doc = Jsoup.connect(productUrl).get();
				System.out.println(productUrl);
				String productName;
				productName = doc.getElementsByClass("h-title").text();
				System.out.println(productName);
				boolean linkedin = false, twitter, facebook, instagram, youtube;
				String linkedinCount, twitterCount, facebookCount, instagramCount, youtubeCount;
				Elements linkedinUrl, twitterUrl, facebookUrl, instagramUrl, youtubeUrl;

				Elements followers = doc.getElementsByClass("right_followers").select("a");
				for(Element element : followers) {
					boolean String = false;
					String url = element.attr("abs:href");
					System.out.println(url);
					String count = element.getElementsByTag("span").text();
					System.out.println(count);
					}
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
