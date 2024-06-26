package com.otus.pages;

import com.google.inject.Inject;
import com.otus.listeners.WebDriverListener;
import com.otus.support.GuiceScooped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class MainPage extends AbsBasePage<MainPage> {
  @Inject
  public MainPage(GuiceScooped guiceScooped){super(guiceScooped);}

  private String courseNameLocator = "//div[h5[contains(text(), '%s')]]";
  private String inPutButtonLocator = "//button[contains(text(), 'Войти')]";
  private String findNameBlockLocator = "//h2[contains(text(), '%s')]";
  private String coursePageCheck = "//h1[contains(text(), '%s')]";
  @FindBy(xpath = "//a[contains(@href, 'https://otus.ru/lessons/')]//span[contains(., 'С')]")
  private List<WebElement> dateItem;
  private WebDriverListener eventListener = new WebDriverListener();
  private Logger log = LogManager.getLogger();

  //ожидаем окончания загрузки главной страницы и проверяем наличие блока Популярные курсы
  public void mainPageWaitDownload(String blockName) {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(inPutButtonLocator)));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(findNameBlockLocator, blockName))));
  }

  //ищем на главной странице курс по имени
  public void mainPageCourseFind(String courseName) {
    WebElement courseComponentName = driver.findElement(By.xpath(String.format(courseNameLocator, courseName)));
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView();", courseComponentName);
    eventListener.beforeClickOn(courseComponentName, driver);
  }

  //клик по карточке курса
  public void mainPageCourseClick(String courseName) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    new Actions(driver).click(driver.findElement(By.xpath(String.format(courseNameLocator, courseName)))).perform();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(coursePageCheck, courseName))));
  }

  //ищем курс и кликаем по нему
  public void mainPageCourseFindAndClicK(String courseName) {
    mainPageCourseFind(courseName);
    mainPageCourseClick(courseName);
  }

  //получаем список курсов на главной странице и собираем даты их начала
  public void mainPageGetCoursesDate(LocalDate dateCourses) {
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    Map<WebElement, LocalDate> coursesStartMap = new HashMap<>();

    dateItem.stream().map((WebElement element) -> {
      List<String> dateStringList  = Arrays.stream(element.getText().split(" ")).skip(1)
              .limit(2).collect(Collectors.toList());
      if (dateStringList.size() < 3) {
        if (dateStringList.get(1).equals("декабря")) {
          dateStringList.add(2, String.valueOf(LocalDate.now().getYear() - 1));
        } else {
          dateStringList.add(2, String.valueOf(LocalDate.now().getYear()));
        }
      }

      String dateInString = dateStringList.get(0) + " " + dateStringList.get(1) + " " + dateStringList.get(2);

      if (Pattern.compile("\\d+ [а-я]+ \\d{4}").matcher(dateInString).find()) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        coursesStartMap.put(element, LocalDate.parse(dateInString, formatter));
      }

      return coursesStartMap;
    }
      ).collect(Collectors.toList());

    for (Map.Entry<WebElement, LocalDate> e : coursesStartMap.entrySet()) {
      if (e.getValue().isAfter(dateCourses) || e.getValue().equals(dateCourses)) {

        WebElement cartCoursesElement = e.getKey().findElement(By.xpath(".//ancestor::a"));
        String titleCourses = cartCoursesElement
                .findElement(By.cssSelector("h5"))
                .getText();
        System.out.printf("Название курса - %s, курс стартует с - %s \n", titleCourses, e.getValue());

      }
    }
  }
}



