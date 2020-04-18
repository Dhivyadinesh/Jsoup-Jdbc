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
		String url = "jdbc:mysql://localhost:3306/saas";
		String username = "root";
		String password = "dineshdd";
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
				boolean linkedin = false,twitter, facebook, instagram, youtube;
				String linkedinCount, twitterCount, facebookCount, instagramCount, youtubeCount;
				String linkedinUrl, twitterUrl, facebookUrl, instagramUrl, youtubeUrl;
				Elements followers = doc.getElementsByClass("right_followers").select("a");
					for(Element element : followers) {
						linkedinUrl = element.attr("abs:href");
						if(linkedinUrl.contains("linkedin")){
							linkedin = element.hasText();
					    System.out.println(linkedin);
						System.out.println(linkedinUrl);
						linkedinCount = element.getElementsByTag("span").text();
						System.out.println(linkedinCount);
						}
					}
					for(Element element : followers) {
						twitterUrl = element.attr("abs:href");
						if(twitterUrl.contains("twitter")){
							twitter = element.hasText();
						    System.out.println(twitter);
						System.out.println(twitterUrl);
						twitterCount = element.getElementsByTag("span").text();
						System.out.println(twitterCount);
						}
					}
					for(Element element : followers) {
						facebookUrl = element.attr("abs:href");
						if(facebookUrl.contains("facebook")){
							facebook = element.hasText();
						    System.out.println(facebook);
						System.out.println(facebookUrl);
						facebookCount = element.getElementsByTag("span").text();
						System.out.println(facebookCount);
						}
					}
					for(Element element : followers) {
						instagramUrl = element.attr("abs:href");
						if(instagramUrl.contains("instagram")){
							instagram = element.hasText();
						    System.out.println(instagram);
						System.out.println(instagramUrl);
						instagramCount = element.getElementsByTag("span").text();
						System.out.println(instagramCount);
						}
					}
				
					for(Element element : followers) {
						youtubeUrl = element.attr("abs:href");
						if(youtubeUrl.contains("youtube")){
							youtube = element.hasText();
						    System.out.println(youtube);
						System.out.println(youtubeUrl);
						youtubeCount = element.getElementsByTag("span").text();
						System.out.println(youtubeCount);
						}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
