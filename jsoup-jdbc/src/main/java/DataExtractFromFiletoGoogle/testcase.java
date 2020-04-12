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

public class testcase {

	public static void main(String[] args) throws IOException, SQLException {

		String url = "jdbc:mysql://localhost:3306/datafromfile";
		String username = "root";
		String password = "dineshdd";
		File xmlFile = new File("/home/dinesh/Downloads/product_sitemap_google.xml");
		Scanner scan;
		try {
			scan = new Scanner(xmlFile);
			while (scan.hasNextLine()) {
				String searchkey = scan.nextLine();
					
				Connection conn = DriverManager.getConnection(url, username, password);
				String sql1 = "INSERT INTO text3(searchkey,ad,pageno,title, link) VALUES (?,?, ?,? ,?) ";
				PreparedStatement statement1 = conn.prepareStatement(sql1);
    			statement1.setString(1,searchkey);
 		       conn.close();
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
	}
  }
}