# UI Autotests (Selenium + Appium + TestNG) — отчет по проекту

## 1. Описание проекта
В рамках проекта реализован тестовый UI-фреймворк на **Java** для:
- **Web**: автотестирование публичного сайта **Wikipedia (web)** с использованием **Selenium WebDriver + TestNG**;
- **Mobile**: автотестирование Android-приложения **Wikipedia** с использованием **Appium + TestNG**.

---

## 2. Цели и задачи
### Цель
Проверить корректность работы ключевых пользовательских сценариев в web-интерфейсе и Android-приложении Wikipedia средствами автоматизации UI-тестирования.

### Задачи
- Настроить Maven-проект на Java 11+ с зависимостями Selenium/TestNG/Appium.
- Реализовать web-тесты, покрывающие **4+ стабильных сценария**.
- Реализовать mobile-тесты для Wikipedia, покрывающие **3 стабильных сценария**.
- Обеспечить стабильность (WebDriverWait / ожидания, минимизация флаков).
- Подготовить понятную документацию по запуску.

---

## 3. Стек и инструменты
- **Java**: 11+
- **Maven**: сборка и запуск
- **TestNG**: тестовый фреймворк
- **Selenium WebDriver**: web UI тестирование
- **WebDriverManager**: автоматическая установка драйвера браузера
- **Appium Java Client**: mobile UI тестирование
- **Android Emulator / Android Studio**: запуск мобильных тестов

---

## 4. Структура проекта
```
src/test/java
  config
    Config.java                      - чтение config.properties
  utils
    Waiters.java                     - web ожидания (WebDriverWait)
  utils/mobile
    MobileWaiters.java               - mobile ожидания (WebDriverWait)
  web/pages
    WikiHomePage.java                - главная wikipedia.org
    WikiSearchResultsPage.java       - результаты поиска
    WikiArticlePage.java             - страница статьи
    WikiEnglishMainPage.java         - проверка Main Page (en)
  web/tests
    BaseWebTest.java                 - настройка WebDriver
    WikipediaWebTests.java           - web сценарии (TestNG)
  mobile/screens
    WikipediaOnboarding.java         - пропуск onboarding (если появляется)
    WikipediaHomeScreen.java         - главный экран приложения
    WikipediaSearchScreen.java       - экран поиска
    WikipediaArticleScreen.java      - экран статьи
  mobile/tests
    BaseMobileTest.java              - настройка AndroidDriver
    WikipediaAndroidTests.java       - mobile сценарии (TestNG)

src/test/resources
  config.properties                   - конфигурация (URL/таймауты/capabilities)
  testng-web.xml                      - suite для web
  testng-mobile.xml                   - suite для mobile
```

---

## 5. Реализованные сценарии

## 5.1 Web UI (Wikipedia)
Сайт: https://www.wikipedia.org/

### Сценарии (4+)
1) **Поиск статьи**  
   - Ввод текста в поиск (с выбором языка)
   - Переход на результаты
   - Открытие первой статьи
   - Проверки:
     - результаты поиска есть
     - URL содержит `/wiki/`
     - заголовок статьи не пустой
     - контент статьи отображается

2) **Навигация на Main Page через меню (sidebar)** *(EN wiki)*  
   - Открыть статью
   - Нажать `Main page` в левом меню
   - Проверка: заголовок страницы = `Main Page`

3) **Смена языка на EN (через поиск с выбором English)**  
   - Выбрать язык `en` на главной wikipedia.org
   - Выполнить поиск
   - Проверка: текущий URL содержит `en.wikipedia.org`

4) **Проверка базовых элементов статьи**  
   - Открыть статью через поиск
   - Проверка: заголовок присутствует, контент присутствует

Техническая реализация:
- Page Object Model (`web.pages`)
- Явные ожидания (`WebDriverWait` → `utils.Waiters`)
- Параметризация поиска через `@DataProvider`

---

## 5.2 Mobile UI (Wikipedia Android)
Приложение: Wikipedia (Android), пакет `org.wikipedia`

### Сценарии (3)
1) **Проверка главного экрана**
   - Проверка, что на главном экране доступен компоненты поиска

2) **Поиск статьи → открытие результата → проверки статьи**
   - Открыть поиск
   - Ввести `Selenium`
   - Открыть первый результат
   - Проверки:
     - контент отображается
     - заголовок не пустой

3) **Проверка, что заголовок содержит ключевое слово**
   - Открыть поиск
   - Ввести `Java`
   - Открыть первый результат
   - Проверка: `title` содержит `Java` (case-insensitive)

Техническая реализация:
- Page/Screens (`mobile.screens`)
- Явные ожидания (`utils.mobile.MobileWaiters`)
- Обработка onboarding: `WikipediaOnboarding.skipIfPresent()` вызывается перед тестами

---

## 6. Конфигурация
Файл: `src/test/resources/config.properties`

Пример ключевых параметров:
- `web.baseUrl` — URL сайта
- `timeouts.seconds` — таймауты ожиданий
- `appium.serverUrl` — адрес Appium сервера
- `android.appPackage`, `android.appActivity` — Wikipedia Android

---

## 7. Запуск

### 7.1 Требования
#### Для web
- JDK 11+
- Maven
- Google Chrome

#### Для mobile
- Android Studio (эмулятор)
- Node.js LTS
- Appium 2.x (через npm)
- Драйвер `uiautomator2`
- Установленное приложение Wikipedia на эмуляторе/устройстве

---

## 7.2 Проверка окружения (PowerShell)
```powershell
java -version
mvn -version
adb version
adb devices
node -v
npm -v
appium -v
appium driver list --installed
```

---

## 7.3 Запуск WEB тестов
Из корня проекта (PowerShell):
```powershell
mvn clean test "-DtestngXml=src/test/resources/testng-web.xml"
```

---

## 7.4 Запуск MOBILE тестов
1) Запусти Android Emulator (любой Pixel/API).  
2) Проверь что устройство видно:
```powershell
adb devices
```

3) Запусти Appium server (в отдельном терминале):
```powershell
appium --address 127.0.0.1 --port 4723
```

4) Запусти mobile suite:
```powershell
mvn clean test "-DtestngXml=src/test/resources/testng-mobile.xml"
```

---

## 8. Проблемы и решения

### 8.1 Maven ругается `Unknown lifecycle phase ".xml"`
В PowerShell нужно передавать `-D...` в кавычках:
```powershell
mvn "-DtestngXml=src/test/resources/testng-web.xml" test
```

### 8.2 Mobile: устройство не найдено
- Эмулятор не запущен / adb не видит девайс:
```powershell
adb kill-server
adb start-server
adb devices
```

### 8.3 Mobile: onboarding/Welcome мешает тестам
В проект добавлен безопасный шаг:
- `WikipediaOnboarding.skipIfPresent()`
Если onboarding в вашей версии другой — требуется обновить локаторы (можно определить через Appium Inspector).

---

## 9. Что можно улучшить (опционально)
- Добавить Allure-отчеты (для красивого репорта)
- Добавить GitHub Actions для web-suite
- Добавить скриншоты при падении тестов (Selenium + Appium)
- Расширить покрытие: больше сценариев (язык, скролл до элемента, проверка настроек)
