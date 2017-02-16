import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class JSONValidator {

	static String environment = null;
	static String url = null;
	static String json = "";


	public static void readJSON() throws IOException {
		URL abc = new URL(url);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(abc.openStream()));
        String inputLine = "";
        while ((inputLine = in.readLine()) != null)
        	json = json + inputLine;
        in.close();
	}

	public static void main(String[] args) throws IOException, org.json.simple.parser.ParseException {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(System.getProperty("user.dir") + "/src/environments.properties");
			
			//load a properties file
			prop.load(input);
			
			//Get Execution environment
			environment = prop.getProperty("ExecutionEnvironment");			
			XSSFSheet mySheet = null;		
			File myFile = new File(System.getProperty("user.dir") + "/src/EnvironmentsData.xlsx");
	        FileInputStream fis = new FileInputStream(myFile);
	        // Finds the workbook instance for XLSX file
	        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
	        if(environment.equals("TestEnvironment")){
		        // Return first sheet from the XLSX workbook
	        	mySheet = myWorkBook.getSheetAt(0);	        	
	        }
		    else{
		        mySheet = myWorkBook.getSheetAt(1);
	        }
	        // Get iterator to all the rows in current sheet
	        Iterator<Row> rowIterator = mySheet.iterator();
	        String dataMapping = null;
	        // Traversing over each row of XLSX file
	        while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();	            
	            Cell cell = row.getCell(0);
	            if(row.getRowNum()==0){
	            	dataMapping = cell.getStringCellValue();
	        	    continue; //just skip the rows if row number is 0
	        	}
	            try{
		            if(cell.getStringCellValue().equals("")){
		            	break;
		            }
	            }catch(Exception e){
	            	break;
	            }
	            System.out.print(dataMapping +" " + cell.getStringCellValue());
	            //Get URL
	            url = cell.getStringCellValue();           	    		
	    		readJSON();
	    		System.out.println(json);
	    		parseJSON(dataMapping);
	    		json = "";
	        }
	        // Get iterator to all the rows in current sheet
	        rowIterator = mySheet.iterator();
	        // Traversing over each row of XLSX file
	        while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();
	            Cell cell = row.getCell(1);
	            if(row.getRowNum()==0){
	            	dataMapping = cell.getStringCellValue();
	            	continue; //just skip the rows if row number is 0
	        	}	  
	            try{
		            if(cell.getStringCellValue().equals("")){
		            	break;
		            }
	            }catch(Exception e){
	            	break;
	            }
	            System.out.print(dataMapping +" " + cell.getStringCellValue());
	            //Get URL
	            url = cell.getStringCellValue();     
	            // TODO Auto-generated method stub	    		
	    		readJSON();
	    		System.out.println(json);
	    		parseJSON(dataMapping);
	    		json = "";
	        }
			
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}	
	}	
	
	public static void parseJSON(String dataMapping) throws org.json.simple.parser.ParseException {
		JSONParser parser = new JSONParser();
		Properties prop = new Properties();
		InputStream input = null;
		 try {			 	
			 	if(dataMapping.equals("Morning Data")){
			 		input = new FileInputStream(System.getProperty("user.dir") + "/src/mornings_program.properties");		 		
			 	}
			 	else{
			 		input = new FileInputStream(System.getProperty("user.dir") + "/src/afternoon_program.properties");
			 	}			 	
	            Object obj = parser.parse(json);

				//load a properties file
				prop.load(input);
				
				//Get Execution environment
				String entity = prop.getProperty("entity");	
				String arid = prop.getProperty("arid");	
				String title = prop.getProperty("title");	
				String mini_synopsis = prop.getProperty("mini_synopsis");	
				String short_synopsis = prop.getProperty("short_synopsis");	
				String medium_synopsis = prop.getProperty("medium_synopsis");	
				String created_utc = prop.getProperty("created_utc");
				String last_updated_utc = prop.getProperty("last_updated_utc");
				String service_airport_code = prop.getProperty("service_airport_code");
				if(service_airport_code.equals("null"))
					service_airport_code = null;
	            	
	            JSONObject jsonObject = (JSONObject) obj;	            	
	            
	            Assert.assertEquals(entity,(String) jsonObject.get("entity"));
	            Assert.assertEquals(arid,(String) jsonObject.get("arid"));
	            Assert.assertEquals(title,(String) jsonObject.get("title"));
	            Assert.assertEquals(mini_synopsis,(String) jsonObject.get("mini_synopsis"));
	            Assert.assertEquals(short_synopsis,(String) jsonObject.get("short_synopsis"));
	            Assert.assertEquals(medium_synopsis,(String) jsonObject.get("medium_synopsis"));
	            Assert.assertEquals(created_utc,(String) jsonObject.get("created_utc"));
	            Assert.assertEquals(last_updated_utc,(String) jsonObject.get("last_updated_utc"));
	            Assert.assertEquals(service_airport_code,jsonObject.get("service_airport_code"));
	                        
	            System.out.println("Build Success");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		}
}