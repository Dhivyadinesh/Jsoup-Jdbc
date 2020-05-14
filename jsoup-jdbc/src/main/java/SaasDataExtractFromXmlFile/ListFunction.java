package SaasDataExtractFromXmlFile;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
	

	public class ListFunction {
		
	public static void main(String[] args) {
		
		boolean linkedin = false, twitter = false, facebook = false, instagram = false, youtube = false;
		
     	String linkedinCount = "0", twitterCount = "0", facebookCount = "0", instagramCount = "0",
				youtubeCount = "0";
		String linkedinUrl = "null", twitterUrl = "null", facebookUrl = "null", instagramUrl = "null",
				youtubeUrl = "null";
	
		String productName = "0", productUrl = "0";
		File file = new File("/home/dinesh/Documents/50product.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				productUrl = scan.nextLine();
				Document doc = Jsoup.connect(productUrl).get();
				System.out.println(productUrl);
				productName = doc.getElementsByClass("h-title").text();
				System.out.println(productName);
				String url;
				Elements followers = doc.getElementsByClass("right_followers").select("a");
				for (Element element : followers) {
					url = element.attr("abs:href");
      				linkedinMethod(url,linkedin,element,linkedinUrl,linkedinCount);
      				twitterMethod(url,twitter,element,twitterUrl,twitterCount);
      				facebookMethod(url,facebook,element,facebookUrl,facebookCount);
      				instagramMethod(url,instagram,element,instagramUrl,instagramCount);
      				youtubeMethod(url,youtube,element,youtubeUrl,youtubeCount);
                	}
				}	
	   }catch(Exception e) {
		   System.out.println("No url Found");
	   }
	}

	private static void linkedinMethod(String url, boolean linkedin,Element element,String linkedinUrl,String linkedinCount) {
		if (url.contains("linkedin")) {
			linkedin = url.contains("linkedin");
			linkedinUrl = element.attr("href");
			linkedinCount = element.getElementsByTag("span").text();
			System.out.println("Product has linkedin " + linkedin);
			System.out.println("linkedin url is" + linkedinUrl);
			System.out.println("linkedinCount" + linkedinCount);
	    }
	}
	private static void twitterMethod(String url, boolean twitter,Element element,String twitterUrl,String twitterCount) {
			if (url.contains("twitter")) {
				twitter = url.contains("twitter");
				twitterUrl = element.attr("href");
				twitterCount = element.getElementsByTag("span").text();
				System.out.println(twitter);
				System.out.println(twitterUrl);
				System.out.println(twitterCount);
		    }
		}
		private static void facebookMethod(String url, boolean facebook,Element element,String facebookUrl,String facebookCount) {
			if (url.contains("facebook")) {
				facebook = url.contains("facebook");
				facebookUrl = element.attr("href");
				facebookCount = element.getElementsByTag("span").text();
				System.out.println(facebook);
				System.out.println(facebookUrl);
				System.out.println(facebookCount);
		    }
		}
		private static void instagramMethod(String url, boolean instagram,Element element,String instagramUrl,String instagramCount) {
			if (url.contains("instagram")) {
				instagram = url.contains("instagram");
				instagramUrl =element.attr("href");
				instagramCount = element.getElementsByTag("span").text();
				System.out.println(instagram);
				System.out.println(instagramUrl);
				System.out.println(instagramCount);
		    }
		}
		private static void youtubeMethod(String url, boolean youtube,Element element,String youtubeUrl,String youtubeCount) {
			if (url.contains("youtube")) {
				youtube = url.contains("youtube");
				youtubeUrl = element.attr("href");
				youtubeCount = element.getElementsByTag("span").text();
				System.out.println(youtube);
				System.out.println(youtubeUrl);
				System.out.println(youtubeCount);
		    }
		}
	
}
