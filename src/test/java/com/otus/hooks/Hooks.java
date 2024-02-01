package com.otus.hooks;

import com.google.inject.Inject;
import com.otus.support.GuiceScooped;
import io.cucumber.java.After;

public class Hooks {
  @Inject
  private GuiceScooped guiceScooped;

  @After
  public void close(){

    if(guiceScooped.getDriver() != null){
      guiceScooped.getDriver().close();
      guiceScooped.getDriver().quit();
    }

  }
}
