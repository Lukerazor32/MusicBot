FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test.Good_music_taste_bot
ENV BOT_TOKEN=5782512465:AAEoRUKU94W1XxzvK18r71lI6EvSfwYiUBo
ENV BOT_DB_USERNAME=music_db_user
ENV BOT_DB_PASSWORD=music_db_password
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "app.jar"]