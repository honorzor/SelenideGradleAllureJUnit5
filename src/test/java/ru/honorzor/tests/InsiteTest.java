package ru.honorzor.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Test;
import ru.honorzor.driver.SettingsDriver;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static ru.honorzor.helpers.LocatorHelper.byClass;
import static ru.honorzor.helpers.LocatorHelper.byTestId;

public class InsiteTest extends SettingsDriver {


    @Test
    @Epic("c87")
    @Description(value = "1.11.1 Создание инсайта")
    public void createInsite() {
        step("Step 1. Перейти на сайт https://preprod.uxcrowd.ru ",
                () -> Selenide.open("https://preprod.uxcrowd.ru"));

        step("Step 2. Нажать кнопку 'Войти'",
                () -> $(byTestId("Login menu button")).shouldBe(visible).click());

        step("Step 3. Ввести данные для авторизации", () -> {
            step("Step 3.1 Ввести логин", () -> $(byTestId("Email input")).setValue("kot4@yopmail.com"));
            step("Step 3.2 Ввести пароль", () -> $(byTestId("Password input")).setValue("Dv9aA8"));
            step("Step 3.3 Нажать кнопку 'Войти'", () -> $(byTestId("Login button"))).click();
            step("Step 3.4 Нажать кнопку 'Next page'",
                    () -> $(byClass("MuiButtonBase-root MuiIconButton-root MuiIconButton-colorInherit"))
                            .shouldBe(visible).click());
        });

        step("Step 4. Найти тест с названием 'Тест' и нажать на название",
                () -> $x("//a[contains(@href, 'tests/59629203')]").click());

        step("Step 5. В вопросе \"1. Опишите свое впечатление от сайта\" нажать на кнопку \"Плей\" рядом с тестировщиком №2",
                () -> {
                    step("Step 5. Нажать кнопку 'Плей'", () -> {
                        $$x("//a[contains(@href,'new-video')]").first().shouldHave(visible).click();
                    });

                    step("Step 5.1.2 Кнопка 'Настройка профиля'",
                            () -> $("[ng-tr='PRV.PRV74']").shouldHave(Condition.text("Настройки профиля")));

                    step("Step 5.1.3 Кнопка 'Выйти'",
                            () -> $("[ng-tr='PRV.PRV73']").shouldHave(Condition.text("Выйти")));

                });

        step("Step 5.2 Видеопроигрыватель с элементами", () -> {
            step("Step 5.2.1 Полоса прокрутки и отображения таймлайна",
                    () -> $("[class='sc-fzoiQi ilyUIH']").shouldHave(Condition.hidden));
        });

        step("Step 5.3 Блок информации о видео с элементами: ", () -> {
            step("Step 5.3.1 Раскрывающийся список \"Профиль пользователя tttt@yopmail.com\" (по умолчанию скрыт)",
                    () -> $("[class='sc-fzoMdx iKwhSE']").shouldBe(visible).click());

            step("Step 5.3.1.1 ОС: Windows NT 10.0 Win64",
                    () -> {
                        $$x("//p[contains(@class,'MuiTypography-root MuiTypography-body1')]")
                                .get(3).shouldHave(Condition.text("ОС: Windows NT 10.0  Win64"));

                        step("Step 5.3.1.2 Разрешение экрана: 1600x900",
                                () -> $$x("//p[contains(@class,'MuiTypography-root MuiTypography-body1')]"))
                                .get(4).shouldHave(Condition.text("Разрешение экрана: 1600x900"));
                    });

            step("Step 5.3.2 Номер видео",
                    () -> $("[class='MuiGrid-root MuiGrid-container MuiGrid-align-items-xs-center']")
                            .shouldHave(Condition.text("Видео №1")));
            step("Step 5.3.3 Дата и время выполнение теста",
                    () -> $(byTestId("Video creation date")).shouldHave(Condition.text("20.03.2020")));
        });

        step("Step 5.4 Блок \"Задания\" (справа от видеопроигрывателя) с элементами:", () -> {
            step("Step 5.4.1 Заголовок \"Задания\"",
                    () -> $("[class='MuiTypography-root MuiTypography-h5']").shouldHave(Condition.text("Задания")));
            step("Step 5.4.2 Поле \"Поиск по транскрипту\"",
                    () -> $("[class='MuiGrid-root sc-fznLPX kfIeLV MuiGrid-container MuiGrid-align-items-xs-center']")
                            .shouldBe(visible));
        });


        step("Step 5.4.3 Список заданий:", () -> {
            step("Step 5.4.3.1 Голосовой:",
                    () -> $$x("//div[contains(@data-testid,'Video question')]")
                            .get(1).shouldHave(Condition.text("1. Опишите свое первое впечатление от сайта.")));

            step("Step 5.4.3.2 Рейтинг:",
                    () -> $$x("//div[contains(@data-testid,'Video question')]")
                            .get(2).shouldHave(Condition.text("2. Я буду часто использовать этот сайт.")));

            step("Step 5.4.3.3 Выбор одного ответа:",
                    () -> $$x("//div[contains(@data-testid,'Video question')]")
                            .get(3).shouldHave(Condition.text("3. Удалось ли вам выполнить задание?")));

            step("Step 5.4.3.4 Выбор нескольких ответов:",
                    () -> $$x("//div[contains(@data-testid,'Video question')]")
                            .get(4).shouldHave(Condition.text("4. Какие типы мобильных приложений вы используете чаще всего?")));
            step("Step 5.4.3.5 Письменный ответ:",
                    () -> $$x("//div[contains(@data-testid,'Video question')]")
                            .get(5).shouldHave(Condition.text("5. Какими тремя словами можно лучше всего описать этот сайт?")));
        });

        step("Step 6 Нажать кнопку \"Создать инсайт\" на панели видеопроигрывателя", () -> {
            step("Step 6.1.1 Нажать на видео для того что бы появилась кнопка 'Создать инсайт'",
                    () -> $("[class='sc-fznxKY kfRyXm']").click());

            step("Step 6.1.3 Нажать кнопку 'Создать инсайт'",
                    () -> $("[class='sc-fzoXzr kcfuJb']").shouldBe(visible).click());

        });

        step("Step 7 Ввести в поле внутри окна инсайта текст \"Инсайт\"",
                () -> $("[class='MuiInputBase-input MuiInputBase-inputMultiline']").setValue("INSITE"));

        step("Step 8 Нажать кнопку \"Добавить\"",
                () -> $("[class='MuiButton-label']").shouldBe(enabled).click());

        step("Step 9 Нажать в списке инсайтов на название \"Инсайт\"", () -> {
            step("9.1 Нажать на нужный 'Инсайт'",
                    () -> $$x("//div[contains(@class,'sc-fznYue ksbWrM')]").get(3).click());

            step("Step 9.3.1 Раскрывающийся список",
                    () -> $$x("//p[contains(@class,'MuiTypography-root MuiTypography-body1')]")
                            .first().shouldHave(text("Профиль пользователя tttt@yopmail.com")));
        });

        step("Step 10 Нажать ссылку \"Перейти к оригиналу видео\"",
                () -> $$x("//p[contains(@class,'MuiTypography-root MuiTypography-body1')]").get(1).click());

        step("Step 11 Выйти из профиля", () -> $("[ng-tr='PRV.PRV73']").click());
        step("Step 12 Ожидания появления главной страницы",() -> $("[ng-tr='NLHOM.NLHOM3']")).shouldBe(visible);

    }


    @Test
    @Epic("C305")
    @Description(value = "1.11.3 Редактирование инсайта")
    public void editInsite() {
        step("Step 1 Перейти на сайт https://preprod.uxcrowd.ru",
                () -> Selenide.open("https://preprod.uxcrowd.ru"));

        step("Step 2. Нажать кнопку 'Войти'",
                () -> $(byTestId("Login menu button")).shouldBe(visible).click());

        step("Step 3. Ввести данные для авторизации", () -> {
            step("Step 3.1 Ввести логин", () -> $(byTestId("Email input")).setValue("kot4@yopmail.com"));
            step("Step 3.2 Ввести пароль", () -> $(byTestId("Password input")).setValue("Dv9aA8"));
            step("Step 3.3 Нажать кнопку 'Войти'", () -> $(byTestId("Login button"))).click();
            step("Step 3.4 Нажать кнопку 'Next page'",
                    () -> $(byClass("MuiButtonBase-root MuiIconButton-root MuiIconButton-colorInherit"))
                            .shouldBe(visible).click());
        });

        step("Step 4. Найти тест с названием 'Тест' и нажать на название",
                () -> $x("//a[contains(@href, 'tests/59629203')]").click());

        step("Step 5. В вопросе \"1. Опишите свое впечатление от сайта\" нажать на кнопку \"Плей\" рядом с тестировщиком №2",
                () -> {
                    step("Step 5. Нажать кнопку 'Плей'", () -> {
                        $$x("//a[contains(@href,'new-video')]").first().shouldHave(visible).click();
                    });

                    step("Step 5.1.2 Кнопка 'Настройка профиля'",
                            () -> $("[ng-tr='PRV.PRV74']").shouldHave(Condition.text("Настройки профиля")));

                    step("Step 5.1.3 Кнопка 'Выйти'",
                            () -> $("[ng-tr='PRV.PRV73']").shouldHave(Condition.text("Выйти")));

                });

        step("Step 5.2 Видеопроигрыватель с элементами", () -> {
            step("Step 5.2.1 Полоса прокрутки и отображения таймлайна",
                    () -> $("[class='sc-fzoiQi ilyUIH']").shouldHave(Condition.hidden));
        });

        step("Step 5.3 Блок информации о видео с элементами: ", () -> {
            step("Step 5.3.1 Раскрывающийся список \"Профиль пользователя tttt@yopmail.com\" (по умолчанию скрыт)",
                    () -> $("[class='sc-fzoMdx iKwhSE']").shouldBe(visible).click());

            step("Step 5.3.1.1 ОС: Windows NT 10.0 Win64",
                    () -> {
                        $$x("//p[contains(@class,'MuiTypography-root MuiTypography-body1')]")
                                .get(3).shouldHave(Condition.text("ОС: Windows NT 10.0  Win64"));

                        step("Step 5.3.1.2 Разрешение экрана: 1600x900",
                                () -> $$x("//p[contains(@class,'MuiTypography-root MuiTypography-body1')]"))
                                .get(4).shouldHave(Condition.text("Разрешение экрана: 1600x900"));
                    });

            step("Step 5.3.2 Номер видео",
                    () -> $("[class='MuiGrid-root MuiGrid-container MuiGrid-align-items-xs-center']")
                            .shouldHave(Condition.text("Видео №1")));
            step("Step 5.3.3 Дата и время выполнение теста",
                    () -> $(byTestId("Video creation date")).shouldHave(Condition.text("20.03.2020")));
        });

        step("Step 5.4 Блок \"Задания\" (справа от видеопроигрывателя) с элементами:", () -> {
            step("Step 5.4.1 Заголовок \"Задания\"",
                    () -> $("[class='MuiTypography-root MuiTypography-h5']").shouldHave(Condition.text("Задания")));
            step("Step 5.4.2 Поле \"Поиск по транскрипту\"",
                    () -> $("[class='MuiGrid-root sc-fznLPX kfIeLV MuiGrid-container MuiGrid-align-items-xs-center']")
                            .shouldBe(visible));
        });


        step("Step 5.4.3 Список заданий:", () -> {
            step("Step 5.4.3.1 Голосовой:",
                    () -> $$x("//div[contains(@data-testid,'Video question')]")
                            .get(1).shouldHave(Condition.text("1. Опишите свое первое впечатление от сайта.")));

            step("Step 5.4.3.2 Рейтинг:",
                    () -> $$x("//div[contains(@data-testid,'Video question')]")
                            .get(2).shouldHave(Condition.text("2. Я буду часто использовать этот сайт.")));

            step("Step 5.4.3.3 Выбор одного ответа:",
                    () -> $$x("//div[contains(@data-testid,'Video question')]")
                            .get(3).shouldHave(Condition.text("3. Удалось ли вам выполнить задание?")));

            step("Step 5.4.3.4 Выбор нескольких ответов:",
                    () -> $$x("//div[contains(@data-testid,'Video question')]")
                            .get(4).shouldHave(Condition.text("4. Какие типы мобильных приложений вы используете чаще всего?")));
            step("Step 5.4.3.5 Письменный ответ:",
                    () -> $$x("//div[contains(@data-testid,'Video question')]")
                            .get(5).shouldHave(Condition.text("5. Какими тремя словами можно лучше всего описать этот сайт?")));
        });


        step("Step 6. В списке инсайтов в строке с названием \"Инсайт\" нажать кнопку \"Редактировать\"",
                () -> $$x("//button[contains(@title,'Редактировать')]").first().click());


        step("Step 7 Изменить текст в поле на \"Инсайт для удаления\"\n" +
                        "Нажать кнопку \"Сохранить\"",
                () -> {
                    step("Step 7.1 Ввести в поле \"Инсайт для удаления\"",
                            () -> $("[class='MuiInputBase-input MuiOutlinedInput-input']").setValue("Insite for delete"));

                    step("Step 7.2 Нажать кнопку 'Сохранить'",
                            () -> $$x("//button[contains(@class,'MuiButtonBase-root MuiButton-root MuiButton-contained sc-fznzqM jMQSSk MuiButton-containedPrimary')]")
                                    .get(1).click());

                });
        step("Step 11 Выйти из профиля", () -> $("[ng-tr='PRV.PRV73']").click());
        step("Step 12 Ожидания появления главной страницы",() -> $("[ng-tr='NLHOM.NLHOM3']")).shouldBe(visible);
    }

}
