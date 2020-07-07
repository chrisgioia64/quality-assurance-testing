package commons;

import org.apache.log4j.Logger;
import pageObjects.ForgotPasswordPage;

import javax.rmi.CORBA.Util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

  private static String configFileLocation = "./src/test/resources/config.properties";
  private static Logger logger = Logger.getLogger(ReadPropertyFile.class);

  private static String readProperty(String file, String key) {
    String value = null;
    try {
      Properties prop = new Properties();
      prop.load(new FileInputStream(file));

      value = prop.getProperty(key);
    } catch (IOException ex) {
      ex.printStackTrace();
      logger.info("Could not locate the property file");
    }
    logger.info("Value in property file for key " + key + " is " + value);
    return value;
  }

  public static String getConfigPropertyVal(final String key) {
    String configPropertyVal = readProperty(configFileLocation, key);
    if (configPropertyVal != null) {
      return configPropertyVal.trim();
    } else {
      return null;
    }
  }

//  public static void main(String[] args) {
//    ReadPropertyFile file = new ReadPropertyFile();
//    file.getConfigPropertyVal("ok");
//    file.getConfigPropertyVal("browser");
//
//  }

}
