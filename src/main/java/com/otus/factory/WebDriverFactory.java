package com.otus.factory;

import com.otus.exeptions.DriverNotSupportedException;
import com.otus.factory.impl.IBrouserOptions;
import com.otus.factory.impl.ChromeDriverOptions;
import com.otus.factory.impl.FirefoxDriverOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

//    1. Фабрику (WebDriverFactory), которая будет получать значение из окружения и запускать соответствующий браузер
//    Браузеры: Chrome, Firefox, Opera
public class WebDriverFactory implements IFactory {

  private String brouserName = System.getProperty("browser", "chrome");

  @Override
  public WebDriver newDriver(String brouserName) throws DriverNotSupportedException {
    if (brouserName == null) {
      throw new DriverNotSupportedException(brouserName);
    }

    switch (brouserName) {
      case "chrome": {
        WebDriverManager.chromedriver().setup();
        IBrouserOptions options = new ChromeDriverOptions();
        return new ChromeDriver((ChromeOptions) options.getOptions());
      }
      case "firefox": {
        WebDriverManager.firefoxdriver().setup();
        IBrouserOptions options = new FirefoxDriverOptions();
        return new FirefoxDriver((FirefoxOptions) options.getOptions());
      }
    }
    return null;
  }
}
