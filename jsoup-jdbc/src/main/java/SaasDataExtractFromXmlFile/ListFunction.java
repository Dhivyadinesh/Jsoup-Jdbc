package SaasDataExtractFromXmlFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ListFunction {
		
	public static void main(String[] args) throws IOException {
		boolean linkedin = false, twitter = false, facebook = false, instagram = false, youtube = false;
		String productName = "0", productUrl = "0";

		String linkedinCount = "0", twitterCount = "0", facebookCount = "0", instagramCount = "0",
				youtubeCount = "0";
		String linkedinUrl = "null", twitterUrl = "null", facebookUrl = "null", instagramUrl = "null",
				youtubeUrl = "null";
		try {
			File file = new File("/home/dinesh/Documents/50product.txt");
			Scanner scan;
			scan = new Scanner(file);
			ArrayList<String> urlList = new ArrayList<String>();
			ArrayList<String> urlCount = new ArrayList<String>();
		    while (scan.hasNextLine()) {
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
						urlList.add(linkedinUrl);
						urlCount.add(linkedinCount);
						System.out.println("Product has linkedin " + linkedin);
						System.out.println("linkedin url is" + linkedinUrl);
						System.out.println("linkedinCount" + linkedinCount);
					}

					if (Url.contains("twitter")) {
						twitter = Url.contains("twitter");
						twitterUrl = element.attr("href");
						twitterCount = element.getElementsByTag("span").text();
						urlList.add(twitterUrl);
						urlCount.add(twitterCount);
						System.out.println(twitter);
						System.out.println(twitterUrl);
						System.out.println(twitterCount);
					}

					if (Url.contains("facebook")) {
						facebook = Url.contains("facebook");
						facebookUrl = element.attr("href");
						facebookCount = element.getElementsByTag("span").text();
						urlList.add(facebookUrl);
						urlCount.add(facebookCount);
						System.out.println(facebook);
						System.out.println(urlList.add(facebookUrl));
						System.out.println(urlCount.add(facebookCount));
					}

					if (Url.contains("instagram")) {
						instagram = Url.contains("instagram");
						instagramUrl = element.attr("href");
						instagramCount = element.getElementsByTag("span").text();
						urlList.add(instagramUrl);
						urlCount.add(instagramCount);
						System.out.println(instagram);
						System.out.println(instagramUrl);
						System.out.println(instagramCount);

					}

					if (Url.contains("youtube")) {
						youtube = Url.contains("youtube");
						youtubeUrl = element.attr("href");
						youtubeCount = element.getElementsByTag("span").text();
						System.out.println(youtube);
						urlList.add(youtubeUrl);
						urlCount.add(youtubeCount);
						System.out.println(youtubeUrl);
						System.out.println(youtubeCount);

					}
				}

		    }
		}catch(Exception e) {
			System.out.println(0);
		}
		
	}
	public static void ListContent(ArrayList<String> s,ArrayList<String> count) {
		int i;
		for(i=0;i<s.size();i++) {
			System.out.println("url"+s.get(i)+",count"+count.get(i));
		}
		
	} 
}
