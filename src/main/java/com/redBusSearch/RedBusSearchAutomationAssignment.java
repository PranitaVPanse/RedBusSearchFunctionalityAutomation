package com.redBusSearch;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBusSearchAutomationAssignment {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions chromeoptions = new ChromeOptions();
		
		//to maximize the window
		chromeoptions.addArguments("--start-maximized");
		
		//	Launch the browser
		WebDriver driver = new ChromeDriver(chromeoptions);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 	//Polling time - that is the time driver is going to wait before rechecking if the element is visible or not, this is 500ms.
																					//This is going to handle synchronization issue
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//Navigate to the website
		driver.get("https://www.redbus.in/");
		
		//this can also be used to maximize the window
		//driver.manage().window().maximize(); 		
		
		//From Button on HomeScreen
		By fromButtonLocator = By.xpath("//div[contains(@class,\'srcDestWrapper\') and @role=\'button\']");
		WebElement fromButton = wait.until(ExpectedConditions.visibilityOfElementLocated(fromButtonLocator));
		fromButton.click();
		
		By suggesionsWrapperLocator = By.xpath("//div[contains(@class,\'suggestionsWrapper\')]");
		WebElement suggestionsWapper = wait.until(ExpectedConditions.visibilityOfElementLocated(suggesionsWrapperLocator));
		
		searchLocation(driver, wait, "Mumbai");		//from location
		searchLocation(driver, wait, "Pune");		//to location
		
		By searchButtonLocator = By.xpath("//button[contains(@class,\"primaryButton\")]");
		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
		searchButton.click();
		
		By primoBusButtonLocator = By.xpath("//div[contains(text(),\"Primo Bus\")]");
		WebElement primoBusButton = wait.until(ExpectedConditions.elementToBeClickable(primoBusButtonLocator));
		primoBusButton.click();
		
		By eveningButtonLocator = By.xpath("//div[contains(text(),\"18:00-24:00\")]");
		WebElement eveningButton = wait.until(ExpectedConditions.elementToBeClickable(eveningButtonLocator));
		eveningButton.click();
				
		By busesFoundTextLocator = By.xpath("//div[contains(@class,\"busesFoundText\")]");
		WebElement busesFoundText = null;
				
				
		if(wait.until(ExpectedConditions.textToBePresentInElementLocated(busesFoundTextLocator, "buses found"))) {
			busesFoundText = wait.until(ExpectedConditions.visibilityOfElementLocated(busesFoundTextLocator));
		}
		
		System.out.println(busesFoundText.getText());
		
		
		By tuppleWrapperLocator = By.xpath("//li[contains(@class,\"tupleWrapper\")]");
		By travelsNameLocator = By.xpath(".//div[contains(@class,\"travelsName\")]");
		
		while(true) {
			List<WebElement> travelsNameList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tuppleWrapperLocator));
			List<WebElement> endOfList = driver.findElements(By.xpath("//span[contains(text(),\'End of list\')]"));
			
			if(!endOfList.isEmpty()) {
				break;
			}
			
			js.executeScript("arguments[0].scrollIntoView({behavior : 'smooth'})", travelsNameList.get(travelsNameList.size()-3));
		}
		
		List<WebElement> travelsNameList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tuppleWrapperLocator));
		System.out.println(travelsNameList.size());
		for(WebElement travels : travelsNameList) {
			String travelName = travels.findElement(travelsNameLocator).getText();
			System.out.println(travelName);
		}
	}

	private static void searchLocation(WebDriver driver, WebDriverWait wait, String locationToBeSearched) {
		//As the dropdown disappears on moving the cursor, on using activeElement the active text box will be accessible  
		WebElement fromSearchTextBox = driver.switchTo().activeElement();
		fromSearchTextBox.sendKeys(locationToBeSearched);
		
		By fromSearchCategoryLocator = By.xpath("//div[contains(@class,'searchCategory')]");
		List<WebElement> fromSearchCategoryList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(fromSearchCategoryLocator, 1));
		System.out.println(fromSearchCategoryList.size());
		
		WebElement fromLocatorSearchResult = fromSearchCategoryList.get(0);
		By fromLocationNameLocator = By.xpath(".//div[contains(@class,\'listHeader\')]");				//. at the start of xpath is added because, this is WebElement chaining and due to . it will consider only from the WebElement that is connected to this WebElement. 
		List<WebElement> fromLocationList = fromLocatorSearchResult.findElements(fromLocationNameLocator);
		System.out.println("fromLocationList " +fromLocationList.size());
		
		for(WebElement location : fromLocationList) {
			String locationName = location.getText();
			
			if(locationName.equalsIgnoreCase(locationToBeSearched)) {
				location.click();
				break;
			}
		}
	}

}
