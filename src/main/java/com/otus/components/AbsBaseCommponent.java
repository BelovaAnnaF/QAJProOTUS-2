package com.otus.components;

import com.otus.support.GuiceScooped;
import org.openqa.selenium.WebDriver;
import com.otus.pageobject.AbsPageObject;

public abstract class AbsBaseCommponent extends AbsPageObject {
  public AbsBaseCommponent(WebDriver driver) {
    super((GuiceScooped) driver);
  }
}
