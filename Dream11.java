import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dream11 {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
	     
		System.setProperty("webdriver.chrome.driver","E:\\Dream 11 bot\\chromedriver.exe");
		//Set default browser
		ChromeOptions options= new ChromeOptions();
	    options.setExperimentalOption("debuggerAddress", "localhost:9222");
	    
		//web drive
		WebDriver driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		
		driver.navigate().to("https://www.dream11.com/cricket/leagues/ICC%20Men's%20T20%20World%20Cup%202021/2178/34457");
		
		//Mouse click action
	    Actions act =  new Actions(driver);
	    
	    JavascriptExecutor js = (JavascriptExecutor)driver;  
	    js.executeScript(" window.scrollBy(0,250) ", args);
	         //read data from given file path
	  		FileInputStream file=new FileInputStream("C://test.xlsx");
	  		//poi access excel
	  		XSSFWorkbook workbook= new XSSFWorkbook(file);
	  		//Reads sheet1...sheetN
	  		XSSFSheet sheet=workbook.getSheet("sheet1");
	  	 
	  		// driver.get("");
		  		Thread.sleep(1000);
	  		/*
	  		 * |      |******| |******| |******|
	  		 * |      |      | |      | |______|      
	  		 * |      |      | |      | | 
	  		 * |_____ |______| |______| | 
	  		 * 
	  		 * 
	  		 *
	  		 * 
	  		 * 
	  		 * */
	  	
	  		
	  		
	  		
	  	for(int team=0;team<=10;team++) {
	  			
	 
	     Thread.sleep(2000);
	    //Clicks on Create team	 for first time
	     if(team==0) {
	    	  Thread.sleep(4000);
				act.moveToElement(driver.findElement(By.xpath("//*[text()='Create Team' or text()='My Teams']"))).click().perform();
	     }
	   ///clicks on My teams for second --12 times
	     if(team!=0) {
	   	Thread.sleep(4000);
	    act.moveToElement(driver.findElement(By.xpath("//*[text()='Create Team' or text()='My Teams']"))).click().perform();
	  
	    Thread.sleep(3000);
		act.moveToElement(driver.findElement(By.xpath("//*[@class='js--my-teams__create-team-btn' and text()='Create Team']"))).click().perform();
	     }  
		int update=1,cpvp=5;
		update=update+team*6;
		cpvp=cpvp+team*6;
        for(int row=update;row<=update+3;row++) {
        	XSSFRow currentrow=sheet.getRow(row);
        	String rowHeading=currentrow.getCell(0).toString();
        	Thread.sleep(3000);
        	 js.executeScript(" window.scrollBy(0,100) ", args);
        	 act.moveToElement(driver.findElement(By.xpath("//div[@class='createTeamTabTitle_99a5a' and text()='"+rowHeading+"']"))).click().perform();

        	
          	for(int col=1;col<7;col++) {
        	
          		try{String value=currentrow.getCell(col).toString();
        	       		 if(value.length()==0) {
        			   	break;}
        	        		 else {
                     Thread.sleep(1000);
        			 act.moveToElement(driver.findElement(By.xpath("//div[@class='playerName_73cad' and text()='"+value+"']"))).click().perform();
           	   	
        		 }}catch(Exception e) {}
        	
        	}
        	
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div/button")).click();
        //SET CAP AND VICE CAP
        XSSFRow lastStep=sheet.getRow(cpvp);
    	String cap=lastStep.getCell(1).toString();
    	 String vice=lastStep.getCell(2).toString();
    	 
    	 
    
    	//CAPTAIN
    	
         Thread.sleep(1000);
        act.moveToElement(driver.findElement(By.xpath("//div[@class='playerName_73cad' and text()='"+cap+"']"))).moveByOffset(370,0).click().perform();
       

        Thread.sleep(2000);
       act.moveToElement(driver.findElement(By.xpath("//div[@class='playerName_73cad' and text()='"+cap+"']"))).moveByOffset(370,0).click().perform();
    
       Thread.sleep(1000);

       act.moveToElement(driver.findElement(By.xpath("//div[@class='playerName_73cad' and text()='"+cap+"']"))).moveByOffset(370,0).click().perform();
    	
       
       
       //VICE CAPTAIN

        Thread.sleep(1000);
         act.moveToElement(driver.findElement(By.xpath("//div[@class='playerName_73cad' and text()='"+vice+"']"))).moveByOffset(420,0).click().perform();
       
   
         Thread.sleep(2000);
        act.moveToElement(driver.findElement(By.xpath("//div[@class='playerName_73cad' and text()='"+vice+"']"))).moveByOffset(420,0).click().perform();
      
  
         Thread.sleep(1000);
         act.moveToElement(driver.findElement(By.xpath("//div[@class='playerName_73cad' and text()='"+vice+"']"))).moveByOffset(420,0).click().perform();
         
         //SAVE TEAM AND CONTINUE
         WebElement save=driver.findElement(By.xpath("//div/button[text()='SAVE TEAM']"));
         act.moveToElement(save).click().perform();
         Thread.sleep(3000);
      
        System.out.println((team+1)+" --> is Deployed Successfully!!");
        
  
	
     driver.quit();
	
	
	  		}
	}
}


