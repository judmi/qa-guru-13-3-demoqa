# Тестовый проект

## Используемые технологии

<a href="https://www.java.com/"><img src="/images/icons/java.svg" height="40"></a>
<a href="https://gradle.org/"><img src="/images/icons/gradle.svg" height="40"></a>
<a href="https://www.jetbrains.com/idea/"><img src="/images/icons/intellij-idea.svg" height="40"></a>
<a href="https://selenide.org/"><img src="/images/icons/selenide.svg" height="40"></a>
<a href="https://junit.org/"><img src="/images/icons/junit5.svg" height="40"></a>
<a href="https://aerokube.com/selenoid/latest/"><img src="/images/icons/selenoid.svg" height="40"></a>
<a href="https://www.docker.com/"><img src="/images/icons/docker-icon.svg" height="40"></a>
<a href="https://www.jenkins.io/"><img src="/images/icons/jenkins.svg" height="40"></a>
<a href="https://docs.qameta.io/allure/"><img src="/images/icons/allure.svg" height="40"></a>
<a href="https://telegram.org/"><img src="/images/icons/telegram.svg" height="40"></a>


## Развертывание Jenkins
Чтобы развернуть Jenkins локально в Docker необходимо:
1. Скачать и установить [Docker Desktop](https://www.docker.com/products/docker-desktop/)
2. Собрать кастомный образ Jenkins из директории, где лежит Dockerfile, при помощи команды
 ```docker build . -t myjenkins```
1. Запустить образ: ```docker run -d --name jenkins -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home myjenkins```
1. Убедиться, что контейнер с именем "jenkins" запущен, при помощи команды ```docker ps```
1. Получить информацию о контейнере можно при помощи команды ```docker inspect jenkins```
1. Jenkins доступен по адресу ```http://localhost:8080/```
1. Использовать для первого входа admin/пароль из файла initialAdminPassword, который можно посмотреть при помощи команды ```docker exec cat var/jenkins_home/secrets/initialAdminPassword```

## Настройка Jenkins
Необходимо установить следующие плагины:
1. Allure Jenkins Plugin
1. Gradle
1. Post build task
1. Text File Operations

Для установки плагинов необходимо:
1. На главной странице в левом меню выбрать Manage Jenkins
1. На открывшейся странице в блоке System Configuration нажать Manage Plugins
1. На странице Plugin Manager выбрать Available, ввести в поисковую строку название плагина и становить около него чек-бокс
1. После выбора всех необоходимых плагинов нажать на Install without restart


## Настройка сборки
1. На главной странице, в левом меню, нажмите New Item
1. Укажите название проекта, выберите Freestyle project и нажмите ОК
1. На странице Configuration настройте параметры проекта. В проекте используется следующая конфигурация:
   1. **Dicard old builds** -> Days to keep builds = 30 -> Max # of builds to keep = 3 (сохранять последние 3 сборки, удалять сборки старше 30 дней)
   1. **This project is parametrized** (для сборки используются параметры):
      1. Choice Parameter: Name = BROWSER_NAME; Choices = chrome, opera, firefox, safari
      1. Choice Parameter: Name = Task; Choices = demoqa_test, properties_test, hello_test
      1. String Parameter: Name = TEXT
   1. Source Code Management -> **Git** (настройка репозитория с исходным кодом):
      1. Repository URL = https://github.com/judmi/qa-guru-13-3-demoqa
      1. Branches to build = */master
   1. Build Triggers -> **Build periodically** (для настройки периодичности запуска сборки):
      1. Schedule = H/5 * * * * (по умолчанию, сборка каждые 5 минут, для другой периодичности укажите ее в формате cron)
   1. **Build Steps**:
      1. *Create/Update Text File* (создаем файл с данными для отправки уведомлений в ТГ, в целях безопасности создаем и храним этот файл в Jenkins, а не в рпозитории):
         1. File Path = notifications/telegram.json
         1. Create at Workspace
         1. Text File Content = 
             ```
                 {
                   "base": {
                     "project": "${JOB_BASE_NAME}",
                     "environment": "qa.guru",
                     "comment": "",
                     "reportLink": "${BUILD_URL}",
                     "language": "en",
                    "allureFolder": "allure-report/",
                     "enableChart": true
                   },
                   "telegram": {
                     "token": "[укажите свой токен]",
                     "chat": "[укажите id чата]",
                     "replyTo": ""
                   }
                 }
             ```
         1. Overwrite file
      1. *Invoke Gradle script* (указываем скрипт для сборки проекта):
         1. Invoke Gradle = выберите установленную версию Gradle
         1. Tasks = 
             ```
             clean
             ${TASK}
             -Dbrowser_name=${BROWSER_NAME}
             "-Dsome_text=${TEXT}"
             ```
   1. **Post-build Actions**:
      1. *Allure Report*:
         1. Path = build/allure-results
      1. *Post build task*:
         1. Script = 
            ```
            cd ..
            FILE=./allure-notifications-4.2.1.jar
            if [ ! -f "$FILE" ]; then
               wget https://github.com/qa-guru/allure-notifications/releases/download/4.2.1/allure-notifications-4.2.1.jar
            fi
            ```
      1. *Post build task*:
         1. Script = 
            ```
            java "-DconfigFile=notifications/telegram.json" -jar ../allure-notifications-4.2.1.jar
            ```

## Параметры сборки

* BROWSER_NAME - определяет браузер, который используется в тесте simplePropertyTest при выборе TASK = properties_test
* TASK - определяет группу тестов для запуска
* TEXT - определяет текст, который будет передан в тест simplePropertyTest1 при выборе TASK = hello_test

## Для запуска автотестов в Jenkins:
1. Открыть проект
1. Выбрать пункт **Build with Parameters**
1. В случае необходимости изменить параметры
1. Нажать **Build**
1. Результат запуска сборки можно посмотреть в отчёте Allure

![This is an image](/images/screenshots/build.PNG)

## Локальный запуск автотестов
Пример команды для запуска тестов с тегом "properties":
```bash
 gradle clean properties_test -Dbrowser_name=safari
```
Пример команды для запуска тестов с тегом "demoqa":
```bash
 gradle clean demoqa_test
```

Получение отчёта:
```bash
allure serve build/allure-results
```


## Пример отчёта
После окончания выполнения автотестов по каждому из них в отчёте доступны скриншоты, исходный код страницы, лог консоли браузера и видеозапись выполнения теста:

![This is an image](/images/screenshots/allure-report-1.PNG)

![This is an image](/images/screenshots/allure-report.PNG)


Пример видеозаписи прохождения теста

![This is an image](/images/screenshots/demoqa-test.gif)


## Оповещения в Telegram
По окончании сборки бот отправляет сообщение с результатами сборки в Telegram-чат.

Для оповещений используется библиотека [allure-notifications](https://github.com/qa-guru/allure-notifications).

[Инструкция по созданию Telegram-бота](https://github.com/qa-guru/knowledge-base/wiki/11.-%D0%A2%D0%B5%D0%BB%D0%B5%D0%B3%D1%80%D0%B0%D0%BC-%D0%B1%D0%BE%D1%82.-%D0%9E%D1%82%D0%BF%D1%80%D0%B0%D0%B2%D0%BB%D1%8F%D0%B5%D0%BC-%D1%83%D0%B2%D0%B5%D0%B4%D0%BE%D0%BC%D0%BB%D0%B5%D0%BD%D0%B8%D1%8F-%D0%BE-%D1%80%D0%B5%D0%B7%D1%83%D0%BB%D1%8C%D1%82%D0%B0%D1%82%D0%B0%D1%85-%D0%BF%D1%80%D0%BE%D1%85%D0%BE%D0%B6%D0%B4%D0%B5%D0%BD%D0%B8%D1%8F-%D1%82%D0%B5%D1%81%D1%82%D0%BE%D0%B2)

![This is an image](/images/screenshots/telegram-notification.PNG)
