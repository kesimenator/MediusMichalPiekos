/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleniumwebdriverex1;


import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Michal
 * Oczywiście warto znajdywanie elementów opakować w testy w stylu:
 * 
 * public boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
 * 
 */


public class SeleniumWebDriverEx1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        WebDriver driver = new FirefoxDriver();

     
        driver.get("http://demo.opencart.com/");
     
        
        //2. Klinkac w walute funt
        WebElement currency = driver.findElement(By.xpath(".//*[@id='currency']/div/button"));
        currency.click();
       
        WebElement pound = driver.findElement(By.xpath(".//*[@id='currency']/div/ul/li[2]/button"));
        pound.click();
       
       //3. Search for “iPod” using text search
        WebElement element = driver.findElement(By.name("search"));
        element.sendKeys("iPod");
       
        try {
            //WebElement buttonSearch = driver.findElement(By.cssSelector("button[type=\"button\"]"));
            WebElement buttonSearch = driver.findElement(By.cssSelector("button[class=\"btn btn-default btn-lg\"]"));
        buttonSearch.click();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        //4.	Add all iPods returned in search results to product comparison
      

        List<WebElement> resultsDiv = driver.findElements(By.xpath(".//*[@class='button-group']/button[3]"));
        //WebElement addToCompare;
        for (int i=0; i<resultsDiv.size(); i++)
        {            
           resultsDiv.get(i).click();

        }
        int iProductCount = resultsDiv.size();
        
        //5. View product comparison page
        WebElement goToCompare = driver.findElement(By.xpath(".//*[@id='compare-total']"));
        goToCompare.click();
        

        
        //6. Remove the one that is 'Out Of Stock' from comparison
            //find where is out of stock
        int iChose = 0;
        for (int i=1; i <=iProductCount; i++)
        {
            String value = driver.findElement(By.xpath("//div[@id='content']/table/tbody/tr[6]/td["+i+"]")).getText();
            iChose = i;
            if(value.equalsIgnoreCase("Out Of Stock"))
            {
                WebElement button = driver.findElement(By.xpath("//div[@id='content']/table/tbody[2]/tr[1]/td["+iChose+"]/a"));
                button.click(); //remove the chosen
                iProductCount--; //need it later for point 7.
            }
            //    break;
        }
               
        
        
        //7.	Add a random available one to shopping cart
        Random r = new Random(); 
        int a = r.nextInt(iProductCount)+1;
        a++; // first col is empty
        WebElement randomBuying = driver.findElement(By.xpath("//div[@id='content']/table/tbody[2]/tr[1]/td["+a+"]/input"));
        randomBuying.click();
        
        
        
        //8.	Go to shopping cart and verify that total price matches the one from comparison page for selected product
        
        //Już nie zdążyłem :(
        
        
        System.out.println("Page title is: " + driver.getTitle());
        
        
        //Close the browser
        driver.quit();
        
        
    }
    
}


