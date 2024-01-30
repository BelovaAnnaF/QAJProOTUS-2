package com.otus.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.*;

import static org.jsoup.Jsoup.connect;

public class TrainingCoursesPages extends AbsBasePage<TrainingCoursesPages> {

  /*Перейти в раздел Курсы > Подготовительные курсы (https://otus.ru/online/),
  выбрать самый дорогой и самый дешевый курс при помощи filter и вывод информации о нем в консоль.
   */
  public TrainingCoursesPages(WebDriver driver) {
    super(driver);
  }
  public void trainingCoursesPageWait(){
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(), 'Онлайн-курсы для подготовки к поступлению на основные курсы')]")));
  }
  public void trainingCoursesPagesGetMinMaxCoursesTax() {
    List<WebElement> listCourses;
    List<String> urlCourses = new ArrayList<>();
    Map<String, Integer> coursesMap = new HashMap<>();

    listCourses = driver.findElements(By.xpath("//a[contains(@class, 'lessons__new-item_hovered')]"));
    listCourses.stream().forEach(s -> {
      urlCourses.add(s.getAttribute("href"));
    });
    urlCourses.stream().forEach((c) -> {
      try {
        Document doc = connect(c).get();
        Elements taxCourses = doc.selectXpath("//div[contains(text(), 'Стоимость')]/following::div[1]");
        Elements nameCourses = doc.selectXpath("//div[contains(text(), 'Подготовительный')]/following::h3[1]");;

        String tax = taxCourses.text().replaceAll("\\D+", "");

        coursesMap.put(nameCourses.text(),Integer.parseInt(tax));
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    coursesMap.entrySet().stream()
            .filter((p) -> p.getValue() != null)
            .max(Map.Entry.comparingByValue()).ifPresent(System.out::println);

    coursesMap.entrySet().stream()
            .filter((p) -> p.getValue() != null)
            .min(Map.Entry.comparingByValue()).ifPresent(System.out::println);
  }
}
