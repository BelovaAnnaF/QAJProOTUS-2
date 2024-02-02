package com.otus.factory;

import com.otus.exeptions.DriverNotSupportedException;
import org.openqa.selenium.WebDriver;

public interface IFactory<T> {
  WebDriver newDriver(String brouserName) throws DriverNotSupportedException;
}
