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
							linkedin = element.hasAttr("href");
					    System.out.println(linkedin);
						System.out.println(linkedinUrl);
						}else {
							System.out.println(0);
						}	
						
						twitterUrl = element.attr("abs:href");
						if(twitterUrl.contains("twitter")){
							twitter = element.hasAttr("href");
						    System.out.println(twitter);
						System.out.println(twitterUrl);
						twitterCount = element.getElementsByTag("span").text();
						System.out.println(twitterCount);
						}else {
							System.out.println(0);
						}
						
						facebookUrl = element.attr("abs:href");
						if(facebookUrl.contains("facebook")){
							facebook = element.hasAttr("href");
						    System.out.println(facebook);
						System.out.println(facebookUrl);
						facebookCount = element.getElementsByTag("span").text();
						System.out.println(facebookCount);
						}else {
							System.out.println(0);
						}
						
						instagramUrl = element.attr("abs:href");
						if(instagramUrl.contains("instagram")){
							instagram = element.hasAttr("href");
						    System.out.println(instagram);
						System.out.println(instagramUrl);
						instagramCount = element.getElementsByTag("span").text();
						System.out.println(instagramCount);
						}else {
							System.out.println(0);
						}
				
						youtubeUrl = element.attr("abs:href");
						if(youtubeUrl.contains("youtube")){
							youtube = element.hasAttr("href");
						    System.out.println(youtube);
						System.out.println(youtubeUrl);
						youtubeCount = element.getElementsByTag("span").text();
						System.out.println(youtubeCount);
						}else {
							System.out.println(0);
						}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
