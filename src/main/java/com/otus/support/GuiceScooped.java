package com.otus.support;

import com.otus.factory.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class GuiceScooped {

  public WebDriver driver;

}
