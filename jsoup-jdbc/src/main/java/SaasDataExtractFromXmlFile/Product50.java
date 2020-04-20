package SaasDataExtractFromXmlFile;

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

public class Product50 {
	public static void main(String[] args) throws IOException, SQLException {

		// productUrl stored in text file
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
				String productName, categoryName;
				boolean SWScore = false, Description = false, Awards = false, Features = false,
						TechnicalDetails = false, Pricing = false, FAQ = false, RelatedCategories = false,
						Alternatives = false, Reviews = false, Videos = false, Screenshots = false, Articles = false,
						Downloads = false, Customers = false, Integrations = false;
				boolean linkedin = false, twitter = false, facebook = false, instagram = false, youtube = false;
				String linkedinCount = "0", twitterCount = "0", facebookCount = "0", instagramCount = "0",
						youtubeCount = "0";
				String linkedinUrl = "null", twitterUrl = "null", facebookUrl = "null", instagramUrl = "null",
						youtubeUrl = "null";
				String Url;

				// Getting productName from productUrl
				productName = doc.getElementsByClass("h-title").text();
				System.out.println(productName);

				categoryName = doc.select(".breadcrum > span:nth-child(3) > a:nth-child(1)").text();
				System.out.println(categoryName);

				// checking SWScore is persent/not from productUrl
				try {
					SWScore = doc.getElementsByClass("pop_score_d").hasText();
					System.out.println("The product has SWScore topic found");
				} catch (Exception e) {
					System.out.println("The product has SWScore topic not found");
				}

				// checking Description is persent/not from productUrl
				try {
					Description = doc.getElementById("sass-desc").hasText();
					System.out.println("The product has Description topic found ");
				} catch (Exception e) {
					System.out.println("The product has Description topic not found");
				}

				// checking Awards is persent/not from productUrl
				try {
					Awards = doc.getElementById("awards").hasText();
					System.out.println("The product has Awards topic found");
					;
				} catch (Exception e) {
					System.out.println("The product has Awards topic not found");
				}

				// checking Features is persent/not from productUrl
				try {
					Features = doc.getElementById("features").hasText();
					System.out.println("The product has productVendorscreenshot topic found ");
				} catch (Exception e) {
					System.out.println("The product has Features topic not found");
				}

				// checking TechnicalDetails is persent/not from productUrl
				try {
					TechnicalDetails = doc.getElementById("technical-details").hasText();
					System.out.println("The product has TechnicalDetails topic found ");
				} catch (Exception e) {
					System.out.println("The product has TechnicalDetails topic not found");
				}

				// checking Pricing is persent/not from productUrl
				try {
					Pricing = doc.getElementById("pricing").hasText();
					System.out.println("The product has Pricing topic found ");
				} catch (Exception e) {
					System.out.println("The product has Pricing topic not found");
				}

				// checking FAQ is persent/not from productUrl
				try {
					FAQ = doc.getElementById("faq").hasText();
					System.out.println("The product has FAQ topic found ");
				} catch (Exception e) {
					System.out.println("The product has FAQ topic not found");
				}

				// checking RelatedCategories is persent/not from productUrl
				try {
					RelatedCategories = doc.getElementById("related").hasText();
					System.out.println("The product has RelatedCategories topic found ");
				} catch (Exception e) {
					System.out.println("The product has RelatedCategories topic not found");
				}

				// checking Alternatives is persent/not from productUrl
				try {
					Alternatives = doc.getElementById("alternatives").hasText();
					System.out.println("The product has Alternatives topic found ");
				} catch (Exception e) {
					System.out.println("The product has Alternatives topic not found");
				}

				// checking Reviews is persent/not from productUrl
				try {
					Reviews = doc.getElementById("reviews").hasText();
					System.out.println("The product has Reviews topic found ");
				} catch (Exception e) {
					System.out.println("The product has Reviews topic not found");
				}

				// checking Videos is persent/not from productUrl
				try {
					Videos = doc.getElementById("videos").hasText();
					System.out.println("The product has Videos topic found");
				} catch (Exception e) {
					System.out.println("The product has Videos topic not found");
				}

				// checking Screenshots is persent/not from productUrl
				try {
					Screenshots = doc.getElementById("screenshots").hasText();
					System.out.println("The product has Screenshots topic found ");
				} catch (Exception e) {
					System.out.println("The product has Screenshots topic not found");
				}

				// checking Articles is persent/not from productUrl
				try {
					Articles = doc.getElementById("reviews").hasText();
					System.out.println("The product has Articles topic found ");
				} catch (Exception e) {
					System.out.println("The product has Articles topic not found");
				}

				// checking Awards is persent/not from productUrl
				try {
					Downloads = doc.getElementById("downloads").hasText();
					System.out.println("The product has Downloads topic found");
				} catch (Exception e) {
					System.out.println("The product has Downloads topic not found");
				}

				// checking Customers is persent/not from productUrl
				try {
					Customers = doc.getElementById("customers").hasText();
					System.out.println("The product has Customers topic found");
				} catch (Exception e) {
					System.out.println("The product has Customers topic not found");
				}

				// checking Integrations is persent/not from productUrl
				try {
					Integrations = doc.getElementById("integration").hasText();
					System.out.println("The product has Integrations topic found ");
				} catch (Exception e) {
					System.out.println("The product has Integrations topic not found");
				}
				
				Elements followers = doc.getElementsByClass("right_followers").select("a");

				for (Element element : followers) {
					Url = element.attr("abs:href");
					if (Url.contains("linkedin")) {
						linkedin = Url.contains("linkedin");
						linkedinUrl = element.attr("href");
						linkedinCount = element.getElementsByTag("span").text();
						System.out.println("Product has linkedin " + linkedin);
						System.out.println("linkedin url is" + linkedinUrl);
						System.out.println("linkedinCount" + linkedinCount);
					}

					if (Url.contains("twitter")) {
						twitter = Url.contains("twitter");
						twitterUrl = element.attr("href");
						twitterCount = element.getElementsByTag("span").text();
						System.out.println(twitter);
						System.out.println(twitterUrl);
						System.out.println(twitterCount);
					}

					if (Url.contains("facebook")) {
						facebook = Url.contains("facebook");
						facebookUrl = element.attr("href");
						facebookCount = element.getElementsByTag("span").text();
						System.out.println(facebook);
						System.out.println(facebookUrl);
						System.out.println(facebookCount);
					}

					if (Url.contains("instagram")) {
						instagram = Url.contains("instagram");
						instagramUrl = element.attr("href");
						instagramCount = element.getElementsByTag("span").text();
						System.out.println(instagram);
						System.out.println(instagramUrl);
						System.out.println(instagramCount);

					}

					if (Url.contains("youtube")) {
						youtube = Url.contains("youtube");
						youtubeUrl = element.attr("href");
						youtubeCount = element.getElementsByTag("span").text();
						System.out.println(youtube);
						System.out.println(youtubeUrl);
						System.out.println(youtubeCount);
					}
				}
				// Mysql database
				String url = "jdbc:mysql://localhost:3306/saas";
				String username = "root";
				String password = "dineshdd";
				// establish connection with the Mysql database
				Connection conn = DriverManager.getConnection(url, username, password);
				String sql;
				// query for inserting data
				sql = "INSERT INTO 50product (productName, productUrl,categoryName, SWScore , Description , Awards ,"
						             + "Features, TechnicalDetails ,Pricing, FAQ , RelatedCategories ,Alternatives ,"
						             + "Reviews ,Videos, Screenshots ,Articles ,Downloads ,Customers ,"
						             + "Integrations, linkedin, linkedinCount, linkedinUrl, twitter, twitterCount,"
						             + "twitterUrl, facebook, facebookCount, facebookUrl, instagram, instagramCount,"
						             + "instagramUrl, youtube, youtubeCount, youtubeUrl)"
						             + " VALUES (?,?,?,?,?,?,?,?,?,?,?,"
						             +          "?,?,?,?,?,?,?,?,?,?,?,"
						             +          "?,?,?,?,?,?,?,?,?,?,?,?) ";
				// Create a statement
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, productName);
				statement.setString(2, productUrl);
				statement.setString(3, categoryName);
				statement.setBoolean(4, SWScore);
				statement.setBoolean(5, Description);
				statement.setBoolean(6, Awards);
				statement.setBoolean(7, Features);
				statement.setBoolean(8, TechnicalDetails);
				statement.setBoolean(9, Pricing);
				statement.setBoolean(10, FAQ);
				statement.setBoolean(11, RelatedCategories);
				statement.setBoolean(12, Alternatives);
				statement.setBoolean(13, Reviews);
				statement.setBoolean(14, Videos);
				statement.setBoolean(15, Screenshots);
				statement.setBoolean(16, Articles);
				statement.setBoolean(17, Downloads);
				statement.setBoolean(18, Customers);
				statement.setBoolean(19, Integrations);
				statement.setBoolean(20, linkedin);
				statement.setString(21, linkedinCount);
				statement.setString(22, linkedinUrl);
				statement.setBoolean(23, twitter);
				statement.setString(24, twitterCount);
				statement.setString(25, twitterUrl);
				statement.setBoolean(26, facebook);
				statement.setString(27, facebookCount);
				statement.setString(28, facebookUrl);
				statement.setBoolean(29, instagram);
				statement.setString(30, instagramCount);
				statement.setString(31, instagramUrl);
				statement.setBoolean(32, youtube);
				statement.setString(33, youtubeCount);
				statement.setString(34, youtubeUrl);
				// execute statement
				statement.executeUpdate();
				conn.close();

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}