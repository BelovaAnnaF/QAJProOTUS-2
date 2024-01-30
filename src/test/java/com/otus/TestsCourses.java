package com.otus;

import com.otus.annotations.Driver;
import com.otus.extensions.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import com.otus.pages.MainPage;

import java.time.LocalDate;

@ExtendWith(UIExtensions.class)
public class TestsCourses {

  @Driver
  private WebDriver driver;
  //проверяем фильтр по имени курса, найти курс, подсветить и кликнуть, после открытия в карточке курса проверяем, что имя соответствует заданому фильтру
  @Test
  public void openMainPage() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open("/").mainPageWaitDownload("Популярные курсы");
    mainPage.mainPageCourseFindAndClicK("DevRel");
  }

  //находим и открываем карточку курса с самой ранней/поздней датой и открылся нужный курс
  @Test
  public void openMinDateCourseMainPage() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open("/").mainPageWaitDownload("Популярные курсы");
    mainPage.mainPageGetCoursesDate(LocalDate.parse("2024-03-11"));
  }
}
