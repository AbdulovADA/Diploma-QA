# Процедура запуска авто-тестов:

1) Открыть проект в IntelliJ IDEA
2) Запустить Docker
3) В терминале IntelliJ IDEA выполнить команду "docker-compose up"
4) В терминале IntelliJ IDEA выполнить команду для запуска приложения: 
- MySQL: java -jar ./artifacts/aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/app
- Postgres: java -jar ./artifacts/aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app
5) В терминале IntelliJ IDEA выполнить команду для прогона автотестов: 
- MySQL: ./gradlew clean test -D dbUrl=jdbc:mysql://localhost:3306/app -D dbUser=app -D dbPass=pass
- Postgres: ./gradlew clean test -D dbUrl=jdbc:postgresql://localhost:5432/app -D dbUser=app -D dbPass=pass
6) Для получения отчета в Allure выполнить команду: "./gradlew allureServe"

## Ссылки
1) [План автоматизации](https://github.com/AbdulovADA/Diploma-QA/blob/master/docs/Plan.md)
2) [Отчет о тестировании](https://github.com/AbdulovADA/Diploma-QA/blob/master/docs/FinalReport.md)
3) [Отчет о проведенной автоматизации](https://github.com/AbdulovADA/Diploma-QA/blob/master/docs/Summary.md)
