package commons;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.lang.Thread.currentThread;
//import static org.testng.Assert.fail;

public class Utilities {
  private static Logger logger = Logger.getLogger(Utilities.class);

  public static class ConfigurationData {
    private String url;
    private String browser;
    private String username;
    private String password;

    @Override
    public String toString() {
      return url + ", " + browser + ", " + username + ", " + password;
    }

    public String getUrl() {
      return url;
    }

    public String getBrowser() {
      return browser;
    }

    public String getUsername() {
      return username;
    }

    public String getPassword() {
      return password;
    }
  }

  public static ConfigurationData readFromProperties(String configFile)  {
    try {
      Properties prop = new Properties();
      prop.load(new FileInputStream(configFile));

      ConfigurationData data = new ConfigurationData();
      data.browser = prop.getProperty("browser");
      data.url = prop.getProperty("url");
      data.username = prop.getProperty("username");
      data.password = prop.getProperty("password");
      return data;
    } catch (IOException e) {
      logger.info(e.getMessage());
    }
    return null;
  }

  public static ConfigurationData readFromExcel(String excelFile)  {
    try {
      Map<String, String> map = getMap(excelFile);
      ConfigurationData data = new ConfigurationData();

      data.browser = map.get("browser");
      data.url = map.get("url");
      data.username = map.get("username");
      data.password = map.get("password");
      return data;
    } catch (IOException e) {
      logger.info(e.getMessage());
    }
    return null;
  }

  private static Map<String, String> getMap(String excelFile) throws IOException {
    Map<String, String> map = new HashMap<String, String>();

      File f = new File(excelFile);
      FileInputStream stream = new FileInputStream(f);
      HSSFWorkbook workbook = new HSSFWorkbook(stream);
      Sheet sheet = workbook.getSheetAt(0);
      for (Row row : sheet) {
        Cell nameCell = row.getCell(0);
        Cell colorCell = row.getCell(1);
        map.put(nameCell.getStringCellValue(), colorCell.getStringCellValue());
      }

    return map;
  }



}
