# Дипломный проект. Профессии "Тестировщик ПО". QA-56.
##
## Документация
* [Задание](documents/README.md)
* [План автоматизации](documents/PLAN.md)
* [Отчет об автоматизированном тестировании](documents/REPORT.md)
* [Отчет об автоматизации](documents/FINAL_REPORT.md)


## Запуск проекта
1. Клонировать [проект](https://github.com/CapriKorP/qa_diplom) в свой репозиторий.
2. Открыть в IntelliJ IDEA.
3. В терминале IDEA выполнить ```docker-compose up```*.  

  (* - если запуск на Windows не происходит, то предварительно запустить приложение Docker Desktop)  

## Для подключения mySQL к SUT:

4. Открыть новый терминал.
5. Выполнить команду ```java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/mysql" -jar artifacts/aqa-shop.jar```.
6. Дождаться успешного запуска SUT. В терминале будет выведено сообщение ```Started ShopApplication in "timetostart" seconds (JVM running for "timetostart")```.
7. Открыть новый терминал.
8. Выполнить команду ```./gradlew clean test -DdbUrl=jdbc:mysql://localhost:3306/mysql```.
9. После завершения тестов выполнить команду ```.\gradlew allureServe```.
10. После выгрузки данных отчета Allure, завершить его работу комбинацией клавиш ```CTRL + C```.
11. Подтвердить завершение выполнения пакетного файла командой ```y```, затем ```ENTER```.
12. Завершить работу Docker командой ```docker-compose down```

## Для подключения PostgreSQL к SUT:

4. Открыть новый терминал.
5. Выполнить команду ```java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgres" -jar artifacts/aqa-shop.jar```.
6. Дождаться успешного запуска SUT. В терминале будет выведено сообщение ```Started ShopApplication in "timetostart" seconds (JVM running for "timetostart")```.
7. Открыть новый терминал.
8. Выполнить команду ```./gradlew clean test -DdbUrl=jdbc:postgresql://localhost:5432/postgres```.
9. После завершения тестов выполнить команду ```.\gradlew allureServe```.
10. После выгрузки данных отчета Allure, завершить его работу комбинацией клавиш ```CTRL + C```.
11. Подтвердить завершение выполнения пакетного файла командой ```y```, затем ```ENTER```.
12. Завершить работу Docker командой ```docker-compose down```


