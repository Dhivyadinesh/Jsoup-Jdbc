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

public class TwoProduct {

	public static void main(String[] args) throws IOException, SQLException {

		File file = new File("/home/dinesh/Documents/50product.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);

			while (scan.hasNextLine()) {
				// url stored in productUrl
				String productUrl = scan.nextLine();
				// connecting to productUrl by jsoup
				Document doc = Jsoup.connect(productUrl).get();
				// print productUrl
				System.out.println(productUrl);
				String productName;
				productName = doc.getElementsByClass("h-title").text();
				System.out.println(productName);
				boolean linkedin, twitter, facebook, instagram, youtube;
				String linkedinCount, twitterCount, facebookCount, instagramCount, youtubeCount;
				Elements linkedinUrl, twitterUrl, facebookUrl, instagramUrl, youtubeUrl;

				linkedinUrl = doc.select(".right_followers > a:nth-child(2)");
				for (Element element : linkedinUrl) {
					String main = element.attr("abs:href");
					linkedinCount = element
							.select(".right_followers > a:nth-child(2) > div:nth-child(1) > span:nth-child(2)").text();
					System.out.println(linkedinCount);
					System.out.println(main);
				}
				twitterUrl = doc.select(".right_followers > a:nth-child(3)");
				for (Element element : twitterUrl) {
					String main = element.attr("abs:href");
					twitterCount = doc
							.select(".right_followers > a:nth-child(3) > div:nth-child(1) > span:nth-child(2)").text();
					System.out.println(twitterCount);
					System.out.println(main);
				}

				facebookUrl = doc.select(".right_followers > a:nth-child(4)");
				for (Element element : facebookUrl) {
					facebookCount = doc
							.select(".right_followers > a:nth-child(4) > div:nth-child(1) > span:nth-child(2)").text();
					System.out.println(facebookCount);
					String main = element.attr("abs:href");
					System.out.println(main);
				}
				instagramUrl = doc.select(".right_followers > a:nth-child(5)");
				for (Element element : instagramUrl) {
					instagramCount = doc
							.select(".right_followers > a:nth-child(5) > div:nth-child(1) > span:nth-child(2)").text();
					System.out.println(instagramCount);
					String main = element.attr("abs:href");
					System.out.println(main);
				}
				youtubeUrl = doc.select(".right_followers > a:nth-child(6)");
				for (Element element : youtubeUrl) {
					youtubeCount = doc
							.select(".right_followers > a:nth-child(6) > div:nth-child(1) > span:nth-child(2)").text();
					System.out.println(youtubeCount);
					String main = element.attr("abs:href");
					System.out.println(main);
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
