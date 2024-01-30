package com.otus.factory;

import com.otus.exeptions.DriverNotSupportedException;
import com.otus.factory.impl.OperaDriverOptions;
import com.otus.factory.impl.ChromeDriverOptions;
import com.otus.factory.impl.FirefoxDriverOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

//    1. Фабрику (WebDriverFactory), которая будет получать значение из окружения и запускать соответствующий браузер
//    Браузеры: Chrome, Firefox, Opera
public class WebDriverFactory implements IFactory<EventFiringWebDriver> {

  private String brouserName = System.getProperty("browser", "chrome");

  @Override
  public EventFiringWebDriver newDriver() {
    if (brouserName == null) {
      throw new DriverNotSupportedException(brouserName);
    }

    switch (brouserName) {
      case "chrome": {
        return new EventFiringWebDriver(new ChromeDriverOptions().getOptions());
      }
      case "firefox": {
        return new EventFiringWebDriver(new FirefoxDriverOptions().getOptions());
      }
      case "opera": {
        return new EventFiringWebDriver(new OperaDriverOptions().getOptions());
      }
    }
    return null;
  }
}
