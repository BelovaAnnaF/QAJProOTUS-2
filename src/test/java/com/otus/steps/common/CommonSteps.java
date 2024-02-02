package com.otus.steps.common;

import com.google.inject.Inject;
import com.otus.exeptions.DriverNotSupportedException;
import com.otus.factory.WebDriverFactory;
import com.otus.pages.MainPage;
import com.otus.support.GuiceScooped;
import io.cucumber.java.ru.Пусть;
import org.openqa.selenium.WebDriver;

public class CommonSteps {
  @Inject
  public GuiceScooped guiceScooped;
  @Inject
  private MainPage mainPage;

  @Пусть("Открыта главная страница в браузере {string}")
  public void openBrowser(String brouserName) throws DriverNotSupportedException {
    guiceScooped.getDriver() = new WebDriverFactory().newDriver(brouserName);
    mainPage.open("/").mainPageWaitDownload("Популярные курсы");
  }
}
