package SaasPage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	public static void main(String args[]) throws IOException, SQLException {

		String url = "jdbc:mysql://localhost:3306/checkswscoreinlistpage";
		String username = "root";
		String password = "dineshdd";

		String page = "https://www.saasworthy.com/";
		Document doc = Jsoup.connect(page).get();
		Elements links = doc.getElementsByClass("allcatgry_wrp");
		Elements links1 = links.select("a");
		String linkpageurl = null;
		int listcount;
		Document doc1 = null;
		String productname = null;
		boolean productswcore = false;
		listcount = links1.size();
		System.out.println(listcount);

		for (Element link : links1) {
			linkpageurl = link.absUrl("href");
			System.out.println(linkpageurl);
			try {
				doc1 = Jsoup.connect(linkpageurl).get();
				int counttitle = doc1.getElementsByClass("fndr-title").size();
				System.out.println(counttitle);
				Elements first = doc1.select(".fndr-right");
				for (Element four : first) {
					productname = four.select(".fndr-title").text();
					productswcore = four.select(".rating_box").hasText();
					System.out.println(productname + "--" + productswcore);
					Connection conn = DriverManager.getConnection(url, username, password);
					String sql1 = "INSERT INTO checkswscoreinlistpage(linkpageurl,productname, productswcore) VALUES (?, ?,? ) ";
					PreparedStatement statement1 = conn.prepareStatement(sql1);
					statement1.setString(1, linkpageurl);
					statement1.setString(2, productname);
					statement1.setBoolean(3, productswcore);
					statement1.executeUpdate();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
