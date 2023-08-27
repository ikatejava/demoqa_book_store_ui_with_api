# Демо-проект по автоматизации тестирования UI с API для [<img src="https://demoqa.com/images/Toolsqa.jpg">](https://demoqa.com/books) Book Store


> [demoqa.com](https://demoqa.com/) - тренировочная платформа для QA-студентов и инженеров.
____

> ## :ballot_box_with_check: <a name="Содержание">**Содержание:**</a>

* <a href="#tools">Технологии и инструменты</a>

* <a href="#cases">Примеры автоматизированных тест-кейсов</a>

* <a href="#jenkins">Сборка в Jenkins</a>

* <a href="#console">Запуск тестов из терминала</a>

* <a href="#allure">Allure отчет</a>

* <a href="#allure-testops">Интеграция с Allure TestOps</a>

* <a href="#jira">Интеграция с Jira</a>

* <a href="#telegram">Уведомление в Telegram с помощью бота</a>

* <a href="#video">Примеры видео выполнения тестов на Selenoid</a>

____

<a id="tools"></a>
> ## :ballot_box_with_check: <a name="Технологии и инструменты">**Технологии и инструменты:**</a>

<p  align="center"

<code><a href="https://www.java.com/"><img width="5%" title="Java" src="media/logos/Java.svg"></a></code>
<code><a href="https://www.jetbrains.com/idea/"><img width="5%" title="IntelliJ IDEA" src="media/logos/Intellij_IDEA.svg"></a></code>
<code><a href="https://gradle.org/"><img width="5%" title="Gradle" src="media/logos/Gradle.svg"></code></a>
<code><a href="https://junit.org/junit5/"><img width="5%" title="JUnit5" src="media/logos/JUnit5.svg"></a></code>
<code><a href="https://selenide.org/"><img width="5%" title="Selenide" src="media/logos/Selenide.svg"></a></code>
<code><a href="https://aerokube.com/selenoid/"><img width="5%" title="Selenoid" src="media/logos/Selenoid.svg"></a></code>
<code><a href="https://rest-assured.io/"><img width="5%" title="REST Assured" src="media/logos/RestAssured.svg"></a></code>
<code><a href="https://github.com/"><img width="5%" title="GitHub" src="media/logos/GitHub.svg"></a></code>
<code><a href="https://github.com/allure-framework"><img width="5%" title="Allure Report" src="media/logos/Allure.svg"></a></code>
<code><a href="https://qameta.io/"><img width="5%" title="Allure TestOps" src="media/logos/Allure_TO.svg"></a></code>
<code><a href="https://www.jenkins.io"><img width="5%" title="Jenkins" src="media/logos/Jenkins.svg"></a></code>
<code><a href="https://www.atlassian.com/software/jira"><img width="5%" title="Jira" src="media/logos/Jira.svg"></a></code>
<code><a href="https://web.telegram.org"><img width="5%" title="Telegram" src="media/logos/Telegram.svg"></a></code>

</p>

- В данном проекте автотесты написаны на <code>Java</code> с использованием фреймворков [Selenide](https://selenide.org/), [REST Assured](https://rest-assured.io/) и [JUnit 5](https://junit.org/junit5/).
- Для сборки проекта был использован [Gradle](https://gradle.org/).
- При удаленном запуске тестов браузер запускается в [Selenoid](https://selenoid.autotests.cloud/).
- Удаленный запуск реализован в [Jenkins](https://jenkins.autotests.cloud/) с формированием <code>Allure</code>-отчета.
- Подключена отправка уведомлений о результатах запуска сборки в <code>Telegram</code> при помощи бота.
- Осуществлена интеграция с [Allure TestOps](https://allure.autotests.cloud/) и [Jira](https://jira.autotests.cloud/).
  
Содержание Allure-отчета:

* Шаги теста;
* Скриншот страницы на последнем шаге;
* Page Source;
* Логи браузерной консоли;
* Видео выполнения автотеста.

____

<a id="cases"></a>
> ## :ballot_box_with_check: <a name="Примеры автоматизированных тест-кейсов">Примеры автоматизированных тест-кейсов:</a>

- Проверка возможности авторизации заранее созданного пользователя с корректными логином и паролем;
- Проверка возможности регистрации с корректными данными;
- Проверка возможности удаления аккаунта;
- Проверка доступности информации в профиле пользователя: userId, username, добавленные в профиль книги;
- Проверка возможности добавления книг в профиль: как по одной книге, так и списком;
- Попытка добавить в профиль книгу с несуществующим идентификатором;
- Проверка возможности удаления книг из профиля: как по одной книге, так и всех книг вместе;
- Попытка удалить из профиля книгу с несуществующим идентификатором.

____
