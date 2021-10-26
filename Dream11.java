import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Dream11 {

	public static void main(String[] args) throws IOException, InterruptedException ,NoSuchSessionException {
	    //setup type of driver and location of driver
		System.setProperty("webdriver.chrome.driver","E:\\Dream 11 bot\\chromedriver.exe");
		
		//To open current browser session on perticular port
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("debuggerAddress","localhost:9222");
		
   		//initializing chrome driver
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Make browser window max
		driver.manage().window().maximize();
		
		//Mouse click action
	    Actions act =  new Actions(driver);
	    
	    //javascript executor for scroll up and down
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript(" window.scrollBy(0,250) ", args);
	    
	    //Read data from given path
	    FileInputStream file=new FileInputStream("E://Dream 11 bot//test.xlsx");
	    
	    //poi access excel
	    XSSFWorkbook workbook= new XSSFWorkbook(file);
	    
	   //Reads sheet1...sheetN
  		XSSFSheet sheet=workbook.getSheet("sheet1");
  		
  		Thread.sleep(1000);
  		
  		
  		/*
  		 * |      |******| |******| |******|
  		 * |      |      | |      | |______|      
  		 * |      |      | |      | | 
  		 * |_____ |______| |______| |   of dream11
  		 * 
  		 * 
  		 * */
  		
  		// This loop will run until 11 team get created
	for(int team=0;team<=10;team++) {
  			  			 
  		     Thread.sleep(2000);
  		     
  		    // Clicks on create team for first time
  		     if(team==0) {
  		    	 
  		  act.moveToElement(driver.findElement(By.xpath("//*[text()='Create Team' or text()='My Teams']"))).click().perform();
  		  Thread.sleep(3000);
  		  
// this is optional
//  	  	   act.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Create Team')]"))).click().perform();
//  	  	    Thread.sleep(3000);
  		     }
  		     
  		   //for next teams 
  		     if(team!=0) {
  		   	Thread.sleep(4000);
  		    act.moveToElement(driver.findElement(By.xpath("//*[text()='My Teams']"))).click().perform();
  		  
  		  Thread.sleep(4000);
  	    	act.moveToElement(driver.findElement(By.xpath("//span[text()='Create Team')]"))).click().perform();
  	    
  		     }  
  		     
  		     //Access excel row cell 0 is negative space, 1-WK,2-BAT,3-AR,4-BOWL, every time team++ the update changes
  			int update=1,cpvp=5;
  			update=update+team*6;
  			
  			//Excel row cell 5- CP & VC
  			cpvp=cpvp+team*6;
  			
  			
  		  
  	        for(int row=update;row<=update+3;row++) {
  	        	
  	        	//Access a row update+3
  	        	XSSFRow currentrow=sheet.getRow(row);
  	        	
  	        	// get row heading , getCell(0) --> column & im converting that to string
  	        	String rowHeading=currentrow.getCell(0).toString();
  	        	Thread.sleep(2000);
  	        	js.executeScript(" window.scrollBy(0,-1000) ", args);
  	        	Thread.sleep(1000);
  	        	
  	        	//injecting rowheading inside chropath query,then click event
  	        	act.moveToElement(driver.findElement(By.xpath("//div[@class='createTeamTabTitle_99a5a' and text()='"+rowHeading+"']"))).click().perform();

  	        	//col will always allow maximum 6 players in BAT,BOWL,AR,WK
  	          	for(int col=1;col<7;col++) {
  	          	js.executeScript(" window.scrollBy(0,-1000) ", args);
  	          		
  	          	try{
  	          			
  	          		String value=currentrow.getCell(col).toString();
  	          		   Thread.sleep(1000);
  	          			js.executeScript(" window.scrollBy(0,100) ", args);
	        	         
 	        	       		//exit loop if value is 0
  	          		      if(value.length()==0) {
  	        			   	break;
  	        			   	}
  	        	          else {
  	                     
  	        	      
  	        			 act.moveToElement(driver.findElement(By.xpath("//div[@class='playerName_73cad' and text()='"+value+"']"))).click().perform();
  	           	   	
  	        		 }} catch(Exception e) {}
  	        	
  	        	}
  	        	
  	        }
  	        Thread.sleep(2000);
  	        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div/button")).click();
  	        
  	        
  	        //SET CAP AND VICE CAP
  	        XSSFRow lastStep=sheet.getRow(cpvp);
  	    	String cap=lastStep.getCell(1).toString();
  	    	 String vice=lastStep.getCell(2).toString();
  	    	 
  	    	 
  	    
  	    	//CAPTAIN
  	    	js.executeScript(" window.scrollBy(0,-1000) ", args);
  	         Thread.sleep(1000);
  	        act.moveToElement(driver.findElement(By.xpath("//div[@class='playerName_73cad' and text()='"+cap+"']"))).moveByOffset(370,0).click();
  	    
  	       
  	        js.executeScript(" window.scrollBy(0,300) ", args);
  	        Thread.sleep(2000);
  	       act.moveToElement(driver.findElement(By.xpath("//div[@class='playerName_73cad' and text()='"+cap+"']"))).moveByOffset(370,0).click();
  	    
  	        js.executeScript(" window.scrollBy(0,200) ", args);
	        Thread.sleep(1000);
	       act.moveToElement(driver.findElement(By.xpath("//div[@class='playerName_73cad' and text()='"+cap+"']"))).moveByOffset(370,0).click();
	    
  	    	
  	       
  	       
  	       //VICE CAPTAIN
  	     js.executeScript(" window.scrollBy(0,-1500) ", args);
  	        Thread.sleep(1000);
  	         act.moveToElement(driver.findElement(By.xpath("//div[@class='playerName_73cad' and text()='"+vice+"']"))).moveByOffset(420,0).click();
  	       
  	       js.executeScript(" window.scrollBy(0,300) ", args);
  	         Thread.sleep(2000);
  	        act.moveToElement(driver.findElement(By.xpath("//div[@class='playerName_73cad' and text()='"+vice+"']"))).moveByOffset(420,0).click();
  	     
  	      js.executeScript(" window.scrollBy(0,200) ", args);
	         Thread.sleep(1000);
	        act.moveToElement(driver.findElement(By.xpath("//div[@class='playerName_73cad' and text()='"+vice+"']"))).moveByOffset(420,0).click();
	     
  	       
	        Thread.sleep(500);
  	         //SAVE TEAM AND CONTINUE
  	         WebElement save=driver.findElement(By.xpath("//div/button[text()='SAVE TEAM']"));
  	         act.moveToElement(save).click().perform();
  	         Thread.sleep(2500);
  	      
  	        System.out.println("Team"+(team+1)+" --> is Deployed Successfully!!");
  	        
  	  			     		
  		
  		  		}
        
	}

}
