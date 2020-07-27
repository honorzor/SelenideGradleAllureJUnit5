package ru.honorzor.helpers;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.by;

public class LocatorHelper {


    public static By byTestId(String testId) {
        return by("data-testId", testId);

    }

    public static By byClass(String Class) {
        return by("Class", Class);
    }

}
