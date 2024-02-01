package com.otus.pageobject;

import com.otus.support.GuiceScooped;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbsPageObject {
  protected WebDriver driver;
  protected WebDriverWait webDriverWait;
  protected Actions actions;
  protected GuiceScooped guiceScooped;

  public AbsPageObject(WebDriver driver) {
    this.guiceScooped = guiceScooped;
    this.driver = guiceScooped.getDriver();
    this.actions = new Actions(driver);
    this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

    PageFactory.initElements(guiceScooped.getDriver(), this);
  }
}
