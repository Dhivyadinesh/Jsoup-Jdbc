package DataExtractFromFiletoGoogle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Getdatafromfilesinglepage {

	public static void main(String[] args) throws IOException {
		
		File file = new File("/home/dinesh/Documents/MobileList.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String searchkey = scan.nextLine();
				Document doc = Jsoup.connect(searchkey).get();
				Elements source1 = doc.getElementById("center_col").select("a[href]");
				for (Element result1 : source1) {
					boolean ad = result1.getElementsByClass("VqFMTc p8AiDd").hasText();
					String title = result1.select("h3").text().trim();
					String link = result1.selectFirst("a").attr("href");
					System.out.println("ad::" + ad);
					System.out.println("Text::" + title);
					System.out.println("link::" + link);
			}
		   }
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
		
	    

	    

