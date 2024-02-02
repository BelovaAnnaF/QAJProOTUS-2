package com.otus;

import com.otus.annotations.Driver;
import com.otus.pages.MainPage;
import com.otus.pages.TrainingCoursesPages;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

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
  @Test
  public void openTrainingCoursesPages(){
    TrainingCoursesPages trainingCoursesPages = new TrainingCoursesPages(driver);
    trainingCoursesPages.open("/online").trainingCoursesPageWait();
    trainingCoursesPages.trainingCoursesPagesGetMinMaxCoursesTax();
  }
}
