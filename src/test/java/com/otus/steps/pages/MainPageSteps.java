package com.otus.steps.pages;

import com.google.inject.Inject;
import com.otus.pages.MainPage;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;

import java.time.LocalDate;

public class MainPageSteps {
  @Inject
  public MainPage mainPage;

  @Если("Открыть главную страницу")
  public void openMainPage() {
    mainPage.open("/").mainPageWaitDownload("Популярные курсы");
  }

  @Тогда("Находим курс по названию - {string} и кликаем на плитку курса")
  public void nameCourseFindAndClick(String courseName){
    mainPage.mainPageCourseFindAndClicK(courseName);
  }

  @Тогда("Ищем курсы на дату {string} или позже и выводим в консоль информацию о найденных курсах")
  public void dateCourseFindAndClick(String dateCourses){
    mainPage.mainPageGetCoursesDate(LocalDate.parse(dateCourses));
  }
}

