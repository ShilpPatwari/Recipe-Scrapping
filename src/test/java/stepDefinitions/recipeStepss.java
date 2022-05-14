package stepDefinitions;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class recipeStepss {
	
	public WebDriver driver;
	public	String strXpath, title, nutrientValues, recipeLink, ingrediant, methods;
  	public 	String i1, s1, category, imagelink, searchtxt;
	public ArrayList<Object[]> recipedata = new ArrayList<Object[]>();	
	
	ChromeOptions chromeOptions = new ChromeOptions();

	@Given("User visits tarladalal website home page")
   public void user_visits_tarladalal_website_home_page() {
    
	
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\patwa\\Documents\\chromedriver.exe");
	   
	chromeOptions.setHeadless(true);
	chromeOptions.addArguments("--headless");
	chromeOptions.addArguments("window-size=1200x600");
	driver = new ChromeDriver(chromeOptions);
//	driver.manage().window().maximize();
	
	driver.get("https://www.tarladalal.com/");		
	
    }
	
			@When("User enters https:\\/\\/tarladalal.com and search {string}")
	     public void user_enters_https_tarladalal_com_and_search(String recipename) throws Exception {
	   
				chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(50));
				
				//driver.manage().timeouts().setScriptTimeout(50, TimeUnit.SECONDS);
				driver.manage().timeouts().scriptTimeout(Duration.of(50, ChronoUnit.SECONDS));
				driver.manage().timeouts().pageLoadTimeout(Duration.of(50, ChronoUnit.SECONDS));
				
				driver.findElement(By.id("ctl00_txtsearch")).sendKeys(recipename);
				driver.findElement(By.id("ctl00_imgsearch")).click();
							
				
			int noofpages = driver.findElements(By.xpath("//a[@class='respglink']")).size();
			    System.out.println("noofpages : "+noofpages);
			    
				for(int i=1; i<=noofpages; i++) {
					
					 int noofrecipes = driver.findElements(By.xpath("//div[@class='rcc_rcpcore']")).size();
					System.out.println("no of recipes :" + noofrecipes);
		
					strXpath = "//a[starts-with(@class,'res')]["+i+"]";
					driver.findElement(By.xpath(strXpath)).click();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
			
				  WebElement recipecards = driver.findElement(By.xpath("//div[@class='rcc_rcpcore']"));
				  int cardcount = recipecards.findElements(By.xpath("//span[@class='rcc_recipename']/a")).size();
				    System.out.println("cardcount :"+cardcount);
				  
					for(int j=1; j<=cardcount; j++) {
					
				WebElement singlerecipe = driver.findElement(By.xpath("(//span[@class='rcc_recipename'])["+j+"]/a"));
				title = singlerecipe.getText();
				recipeLink = singlerecipe.getAttribute("href");
				singlerecipe.click();
				
				WebElement categoryElement = driver.findElement(By.xpath("(//span[@itemprop='name'])[5]"));
				category = categoryElement.getText();
				
				WebElement imageLinkElement = driver.findElement(By.xpath("(//img[@id='ctl00_cntrightpanel_imgRecipe'])[1]"));
				imagelink = imageLinkElement.getAttribute("src");
				
				WebElement NutrientvaluesElement = driver.findElement(By.xpath("//*[@id='accompaniments']"));
					nutrientValues = NutrientvaluesElement.getText();
				
				WebElement Ingrediant = driver.findElement(By.xpath("//*[@id='rcpinglist']"));
				ingrediant = Ingrediant.getText();
				
		    	WebElement Methods = driver.findElement(By.xpath("//*[@id='recipe_small_steps']"));
				        methods = Methods.getText();
		      try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}   
		      	driver.navigate().back();
			
					Thread.sleep(3000);
		
				System.out.println(title);
						
			recipedata.add(new Object[] {title,category,ingrediant,methods,nutrientValues,imagelink,recipeLink});
					}
			
			    }
						
				ExcelFunctionss ef1 = new ExcelFunctionss();
				ef1.saveDataToExcel(recipedata, recipename);
			    driver.manage().deleteAllCookies();
				
	    }
			
			
			@Then("page with recipe title should be displayed")
		      public void page_with_recipe_title_should_be_displayed() {

		        System.out.println("Scraped all the pages");
		            driver.close();	    
		       }
	



}
