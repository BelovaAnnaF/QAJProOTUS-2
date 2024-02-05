package com.otus.pageobject;

import com.otus.support.GuiceScooped;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbsPageObject {
  protected WebDriver driver;
  public AbsPageObject(GuiceScooped guiceScooped) {
    this.driver = guiceScooped.driver;

    PageFactory.initElements(driver, this);
  }
}
