package SaasPage;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class checkscorelistpage {
	public static void main(String args[]) throws IOException {
		String url = "https://www.saasworthy.com/";
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.getElementsByClass("allcatgry_wrp");  
		Elements links1 = links.select("a");
		String listlink;
		int  listcount;
		listcount = links.size();
	    System.out.println(listcount);
		try {
		for (Element link : links1) {  
			listlink = link.absUrl("href");
		    System.out.println(listlink);
		    Document doc1 = Jsoup.connect(listlink).get();
		    Elements first = doc1.select(".fndr-right");
		    Elements second = first.select(".fndr-title");
		    for (Element third : second) {
				String productName = third.text();
				System.out.println(productName);
			}
		    Elements four = first.select(".rating_box");
		    for (Element five : four) {
				boolean productName = five.hasText();
				System.out.println(productName);
			}
			}
		}catch(Exception e) {
			System.out.println("no url");
		}
		}
}
	
