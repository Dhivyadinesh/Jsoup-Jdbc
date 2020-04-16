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

public class Product50 {
	public static void main(String[] args) throws IOException, SQLException {

        //productUrl stored in text file	
		File file = new File("/home/dinesh/Documents/50product.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				//url stored in productUrl 
				String productUrl = scan.nextLine();
				//connecting to productUrl by jsoup
				Document doc = Jsoup.connect(productUrl).get();
				//print productUrl
				System.out.println(productUrl);
				String productName;
				boolean SWScore = false, Description = false, Awards = false,
				        Features = false,TechnicalDetails = false,Pricing = false,FAQ = false,
					RelatedCategories= false ,Alternatives = false,	Reviews = false,
				        Videos= false,	Screenshots = false ,Articles = false,
				        Downloads = false,Customers = false,Integrations = false;
				
				//Getting productName from productUrl
				productName = doc.getElementsByClass("h-title").text();
				System.out.println(productName);
		
				//checking SWScore is persent/not from productUrl
		                try {
		        	SWScore = doc.getElementsByClass("pop_score_d").hasText();
					System.out.println("The product has SWScore topic found");
				} catch (Exception e) {
					System.out.println("The product has SWScore topic not found");
				}
				
		                //checking Description is persent/not from productUrl
				try {
					Description = doc.getElementById("sass-desc").hasText();
					System.out.println("The product has Description topic found ");
				} catch (Exception e) {
					System.out.println("The product has Description topic not found");
				}
	
				//checking Awards is persent/not from productUrl
				try {
					Awards = doc.getElementById("awards").hasText();
					System.out.println("The product has Awards topic found");;
				} catch (Exception e) {
					System.out.println("The product has Awards topic not found");
				}
	
				//checking Features is persent/not from productUrl
				try {
					Features = doc.getElementById("features").hasText();
					System.out.println("The product has productVendorscreenshot topic found ");
				} catch (Exception e) {
					System.out.println("The product has Features topic not found");
				}
				
				//checking TechnicalDetails is persent/not from productUrl
		                try {
		        	TechnicalDetails = doc.getElementById("technical-details").hasText();
					System.out.println("The product has TechnicalDetails topic found ");
				} catch (Exception e) {
					System.out.println("The product has TechnicalDetails topic not found");
				}
				
				//checking Pricing is persent/not from productUrl
				try {
					Pricing = doc.getElementById("pricing").hasText();
					System.out.println("The product has Pricing topic found ");
				} catch (Exception e) {
					System.out.println("The product has Pricing topic not found");
				}
	
				//checking FAQ is persent/not from productUrl
				try {
					FAQ = doc.getElementsByClass("faq").hasText();
					System.out.println("The product has FAQ topic found ");
				} catch (Exception e) {
					System.out.println("The product has FAQ topic not found");
				}
	
				//checking RelatedCategories is persent/not from productUrl
				try {
					RelatedCategories = doc.getElementById("related").hasText();
					System.out.println("The product has RelatedCategories topic found ");
				} catch (Exception e) {
					System.out.println("The product has RelatedCategories topic not found");
				}
                 
				//checking Alternatives is persent/not from productUrl
		                try {
		        	Alternatives = doc.getElementById("alternatives").hasText();
					System.out.println("The product has Alternatives topic found ");
				} catch (Exception e) {
					System.out.println("The product has Alternatives topic not found");
				}
				
				//checking Reviews is persent/not from productUrl
				try {
					Reviews = doc.getElementById("reviews").hasText();
					System.out.println("The product has Reviews topic found ");
				} catch (Exception e) {
					System.out.println("The product has Reviews topic not found");
				}
				
				//checking Videos is persent/not from productUrl
				try {
					Videos = doc.getElementsByClass("videos").hasText();
					System.out.println("The product has Videos topic found");
				} catch (Exception e) {
					System.out.println("The product has Videos topic not found");
				}
	
				//checking Screenshots is persent/not from productUrl
				try {
					Screenshots = doc.getElementById("screenshots").hasText();
					System.out.println("The product has Screenshots topic found ");
				} catch (Exception e) {
					System.out.println("The product has Screenshots topic not found");
				}

				//checking Articles is persent/not from productUrl
		                try {
		        	Articles = doc.getElementById("reviews").hasText();
					System.out.println("The product has Articles topic found ");
				} catch (Exception e) {
					System.out.println("The product has Articles topic not found");
				}
				
				//checking Awards is persent/not from productUrl
				try {
					Downloads = doc.getElementById("downloads").hasText();
					System.out.println("The product has Downloads topic found");
				} catch (Exception e) {
					System.out.println("The product has Downloads topic not found");
				}
	
				//checking Customers is persent/not from productUrl
				try {
					Customers = doc.getElementsByClass("customers").hasText();
					System.out.println("The product has Customers topic found");
				} catch (Exception e) {
					System.out.println("The product has Customers topic not found");
				}
	
				//checking Integrations is persent/not from productUrl
				try {
					Integrations = doc.getElementById("integration").hasText();
					System.out.println("The product has Integrations topic found ");
				} catch (Exception e) {
					System.out.println("The product has Integrations topic not found");
				}
				
				//Mysql database 
				String url = "jdbc:mysql://localhost:3306/saas";    
				String username = "root";                           
				String password = "dineshdd";
				//establish connection with the Mysql database  
				Connection conn = DriverManager.getConnection(url, username, password);
				String sql;
				//query for inserting data
				sql = "INSERT INTO 50product (productName, productUrl, SWScore , Description , Awards ,"
						     + "Features, TechnicalDetails ,Pricing, FAQ , RelatedCategories ,Alternatives ,"
						+ "Reviews ,Videos,Screenshots	,Articles ,Downloads ,Customers ,Integrations)"
						+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
				//Create a statement
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1,productName );
				statement.setString(2,productUrl );
                		statement.setBoolean(3,SWScore );
				statement.setBoolean(4,Description );
				statement.setBoolean(5,Awards );
				statement.setBoolean(6,Features );
				statement.setBoolean(7,TechnicalDetails );
				statement.setBoolean(8,Pricing );
				statement.setBoolean(9,FAQ );
				statement.setBoolean(10,RelatedCategories );
				statement.setBoolean(11,Alternatives );
				statement.setBoolean(12,Reviews );
				statement.setBoolean(13,Videos );
				statement.setBoolean(14,Screenshots );
				statement.setBoolean(15,Articles );
				statement.setBoolean(16, Downloads);
				statement.setBoolean(17,Customers );
				statement.setBoolean(18,Integrations);
				//execute statement
				statement.executeUpdate();
				conn.close();
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
