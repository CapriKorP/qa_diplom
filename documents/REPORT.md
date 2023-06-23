## Отчет о тестировании

### Описание
Произведено автоматизированное тестирование сервиса приобретения туров с банковской карты и оформление тура в кредит с использованием банковской карты с подключением MySQL и PostgreSQL.
Написаны UI тесты для Selenium, а так же тесты для проверки записи данных в БД.
Интегрирован Allure для вывода детализированных отчетов.  

Написаны 72 теста:
- 18 тестов провалены (25%)
- 54 теста успешно пройдены (75%)

### Обнаруженные баги
По результатам автотестов составлено 9 баг-репортов.

1. В поле "Месяц" возможно указать значение "00", оплата проходит успешно.
2. Отсутствуют ограничения на ввод данных пользователя.
3. Ошибки записи в БД.

### Отчет Allure c подключенным MySQL    
Overview Report 

![Overview report mySQL](https://github.com/CapriKorP/qa_diplom/assets/122346047/7ac8b767-5083-410a-9663-cb0170cf1ce3)  

Suite CreditTest Report  
![Suites Credit report MySQL](https://github.com/CapriKorP/qa_diplom/assets/122346047/8413fdff-8a96-4af1-8e70-12b3d4a9f3c4)

Suite PaymentTest Report
![Suites Payment report mySQL](https://github.com/CapriKorP/qa_diplom/assets/122346047/9b1b1a32-efdc-4c67-bb8c-0b79b6a4be8c)

### Отчет Allure c подключенным PostgreSQL 
Overview Report 
![Overview report PostgreSQL](https://github.com/CapriKorP/qa_diplom/assets/122346047/7dc4bb64-09cc-4d48-9d14-c7bb085293cb)

Suite CreditTest Report 
![Suites Credit report PostgreSQL](https://github.com/CapriKorP/qa_diplom/assets/122346047/a3e649d0-5848-4915-9abd-e61adc288605)

Suite PaymentTest Report
![Suites Payment report PostgreSQL](https://github.com/CapriKorP/qa_diplom/assets/122346047/d31dbc24-4e49-482b-8a41-1edabbc45da4)
