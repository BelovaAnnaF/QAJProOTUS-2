package com.otus.pages;

import com.otus.support.GuiceScooped;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.otus.pageobject.AbsPageObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;

public abstract class AbsBasePage<T> extends AbsPageObject {

  private String baseUrl = System.getProperty("base.url", "https://otus.ru");
  private String path = "";

  public AbsBasePage(GuiceScooped guiceScooped) {
    super(guiceScooped);
  }

  private String normaliseBaseUrl() {
    return baseUrl.endsWith("/")
            ? baseUrl.replaceAll("/$", "")
            : baseUrl;
  }

  public T open(String path) {
    driver.get(normaliseBaseUrl() + path);
    return (T) this;
  }

}
