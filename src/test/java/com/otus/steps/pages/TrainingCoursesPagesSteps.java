package com.otus.steps.pages;

import com.google.inject.Inject;
import com.otus.pages.TrainingCoursesPages;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;

public class TrainingCoursesPagesSteps {
  @Inject
  public TrainingCoursesPages trainingCoursesPages;

  @Если("Открыть страницу Подготовительные курсы")
  public void openTrainingCoursesPages(){
    trainingCoursesPages.open("/online").trainingCoursesPageWait();
  }

  @Тогда("Ищем курсы с минимальной и максимальной ценой и выводим в консоль информацию о найденных курсах")
  public void findMinMaxTaxTrainingCourse(){
    trainingCoursesPages.trainingCoursesPagesGetMinMaxCoursesTax();
  }
}
