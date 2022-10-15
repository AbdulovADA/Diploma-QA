# Процедура запуска авто-тестов:

1) Открыть проект в IntelliJ IDEA
2) Запустить Docker
3) В терминале IntelliJ IDEA выполнить команду "docker-compose up"
4) Запустить приложение командой: "java -jar ./artifacts/aqa-shop.jar"
5) Для получения отчета в Allure выполнить команду: "./gradlew allureServe"