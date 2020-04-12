package DataExtractFromFiletoGoogle;

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

public class Getdatafromfileuptopagination {
	public static void main(String[] args) throws IOException, SQLException {
		String url = "jdbc:mysql://localhost:3306/datafromfile";
		String username = "root";
		String password = "dineshdd";
		File file = new File("/home/dinesh/Documents/MobileFullList.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String searchkey = scan.nextLine();
				String searchKeyWithQ = searchkey + "&start=";
				for(int i=0 ; i<30 ;i=i+10) { 
					String urllink = searchKeyWithQ +i;
					System.out.println(urllink);
					int pageno = (i / 10) + 1;
					System.out.println(pageno);
					Document doc = Jsoup.connect(urllink).get();
					Elements source1 = doc.getElementById("center_col").select("a[href]");
					for (Element result1 : source1) {
						boolean ad = result1.getElementsByClass("VqFMTc p8AiDd").hasText();
						String title = result1.select("h3").text().trim();
						String link = result1.selectFirst("a").attr("href");
						System.out.println("ad::" + ad);
						System.out.println("Text::" + title);
						System.out.println("link::" + link);
						
						Connection conn = DriverManager.getConnection(url, username, password);
						String sql1 = "INSERT INTO text2(ad,pageno,title, link) VALUES (?, ?,? ,?) ";
						PreparedStatement statement1 = conn.prepareStatement(sql1);
						statement1.setBoolean(1, ad);
						statement1.setInt(2, pageno);
						statement1.setString(3, title);
						statement1.setString(4, link);
							
						if (title.length() > 0) {
							statement1.executeUpdate();
						}
						conn.close();
						}
					}
				}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}