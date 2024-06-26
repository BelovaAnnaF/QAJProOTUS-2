package com.otus.steps.common;

import com.google.inject.Inject;
import com.otus.exeptions.DriverNotSupportedException;
import com.otus.factory.WebDriverFactory;
import com.otus.pages.MainPage;
import com.otus.support.GuiceScooped;
import io.cucumber.java.ru.Пусть;
import io.qameta.allure.Step;

public class CommonSteps {
  @Inject
  public GuiceScooped guiceScooped;

  @Step
  @Пусть("Запускаем браузер {string}")
  public void openBrowser(String brouserName) throws DriverNotSupportedException {
    guiceScooped.driver = new WebDriverFactory().newDriver(brouserName);
  }
}
