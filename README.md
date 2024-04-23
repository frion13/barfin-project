# Демо проект по автоматизации Barfin Network

<p align="center">
<a href="https://barfin.network/ru">
    <img src="img/logo/barfin.png"  alt="Barfin"/>
  </a>
</p>

## Содержание

- [Технологический стек](#тех-стек)
- [Покрытый функционал](#покрытый-функционал)
- [Сборка в Jenkins](#сборка-в-jenkins)
- [Запуск из терминала](#запуск-из-терминала)
- [Примеры использования](#примеры-использования)
- [Allure отчет](#allure-отчет)
- [Интеграция с Jira](#интеграция-с-jira)
- [Отчет в Telegram](#отчет-в-telegram)
- [Видео примеры прохождения тестов](#видео-примеры-прохождения-тестов)

## <a id="тех-стек">Технологический стек</a>

<p align="center">
  <a href="https://www.jetbrains.com/idea/">
    <img src="img/logo/idea.svg" width="50" height="50" alt="IntelliJ IDEA"/>
  </a>
  <a href="https://www.oracle.com/java/">
    <img src="img/logo/java.svg" width="50" height="50" alt="Java"/>
  </a>
  <a href="https://selenide.org/">
    <img src="img/logo/selenide.svg" width="50" height="50" alt="Selenide"/>
  </a>
   <a href="https://junit.org/junit5/">
    <img src="img/logo/junit.svg" width="50" height="50" alt="JUnit 5"/>
  </a>
  <a href="https://qameta.io/">
    <img src="img/logo/allure.svg" width="50" height="50" alt="Allure"/>
  </a>
  <a href="https://github.com/">
    <img src="img/logo/github.svg" width="50" height="50" alt="GitHub"/>
  </a>
  <a href="https://gradle.org/">
    <img src="img/logo/gradle.svg" width="50" height="50" alt="Gradle"/>
  </a>
  <a href="https://aerokube.com/selenoid/">
    <img src="img/logo/selenoid.svg" width="50" height="50" alt="Selenoid"/>
  </a>
  <a href="https://www.jenkins.io/">
    <img src="img/logo/jenkins.svg" width="50" height="50" alt="Jenkins"/>
  </a>
  <a href="https://qameta.io/testops">
    <img src="img/logo/allureto.svg" width="50" height="50" alt="Allure TestOps"/>
  </a>
  <a href="https://www.atlassian.com/software/jira">
    <img src="img/logo/jira.svg" width="50" height="50" alt="Jira"/>
  </a>
  <a href="https://rest-assured.io/">
    <img src="img/logo/restassured.png" width="50" height="50" alt="Telegram"/>
  </a>
   <a href="https://web.telegram.org">
    <img src="img/logo/telegram.svg" width="50" height="50" alt="RestAssured"/>
  </a>
</p>

В  проекте используется комплексный подход к автоматизации тестирования веб-приложений. UI автотесты реализованы на ***Java*** с использованием фреймворка ***Selenide***. Применяются паттерны ***PageObject*** и ***Lambda Steps*** для организации код.
Для API автотесты реализованы на ***RestAssured***. Хранение свойств организовано с помощью библиотеки ***Owner*** 
Для запуска тестов в изолированной среде используется ***Selenoid***, который работает в контейнерах Docker. Отчеты о выполнении тестов формируются с помощью ***Allure Report*** и ***Allure TestOps***. Тесты прикреплены к таске в ***jira***.
Сборка проекта происходит через ***Gradle***, а за запуск тестов и управление сборками отвечает ***Jenkins***. По окончании тестирования результаты отправляются через ***Telegram*** бот в чат.



## <a id="покрытый-функционал">Покрытый функционал</a>


## Разработаны автотесты на UI и API.

### UI Тесты

#### Тестирование входа на сайт

- **Вход с валидными данными**
- **Вход с неверным паролем**
- **Вход с неверным именем пользователя**
- **Вход с пустыми полями ввода**
- **Восстановление пароля для зарегистрированного пользователя**
- **Восстановление пароля для незарегистрированного пользователя**

#### Тестирование регистрации

- **Регистрация с валидными данными**
- **Регистрация с некорректным подтверждением пароля**
- **Регистрация с некорректным форматом email**
- **Регистрация с уже зарегистрированным email**

### API Тесты

#### Тестирование входа на сайт

- **Вход с валидными данными**
- **Вход с неверным паролем**
- **Вход с неверным логином**

#### Тестирование регистрации

- **Регистрация с валидными данными**
- **Регистрация с некорректным подтверждением пароля**
- **Регистрация с некорректным форматом email**

### Вспомогательные функции

- **Расширения для входа и удаления пользователя с помощью API**


## <a id="сборка-в-jenkins">Сборка в Jenkins</a>
Для запуска сборки в Jenkins необходимо выполнить следующие шаги:

1. **Авторизоваться**

2. **Перейти на страницу проекта, выбрать `Build with Parameters` из меню.** Для прямого доступа к джобе использовать [эту ссылку](https://jenkins.autotests.cloud/job/24-lomanovskaya_diplomaProject/).

3. **Настройка параметров сборки**:
   - Выбрать браузер, в котором будут выполняться тесты.
   - Указать дополнительные параметры, если это необходимо, такие как размер браузера или версия.

4. **Запуск сборки**: Нажать кнопку `Build` для начала процесса сборки и выполнения тестов.

5. **Мониторинг и отчет**: По завершении прогона втотестов появится Allure Report, также можно посмотреть логи и результаты тестированияю.

<img width="1424" alt="image" src="https://github.com/frion13/barfin-project/assets/112623336/c5f7339f-bbd7-4b8a-8441-a196b9b2507a">

## <a id="запуск-из-терминала">Запуск из терминала</a>

Для запуска тестов из терминала используется команда
```gradle clean test```

Необходимо создать файл auth.properties в папке src/test/resources/config/ и передать туда значения свойств
```registration-email=user@email.ru    //Емаил незарегистрированного пользователя
email=tser@email.ru            //Емаил зарегистрированного пользователя
password=password```       //Пароль

## <a id="примеры-использования">Примеры использования</a>

## <a id="тallure-отче">Allure отчет</a>

## <a id="интеграция-с-jira">Интеграция с Jira</a>

## <a id="отчет-в-telegram">Отчет в Telegram</a>

## <a id="видео-примеры-прохождения-тестов">Видео примеры прохождения тестов</a>






