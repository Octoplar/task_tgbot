# task_tgbot


1. telegram bot works as requested (additionally: default response for unknown cities 'посетите центр ${city_name}')

2. built-in H2 db used, added 2 labels by default ('Минск', 'Москва')

3. rest service added:




/manager/cities:............... get all (REST; GET)

/manager/cities/${id}:......... get by name (REST; GET)

/manager/cities:............... add OR update (REST; POST)

/manager/cities/${id}:......... delete label (REST; DELETE)





extras:
added basic test for city service

added JSP page instead unit tests for REST services (I am not interested in REST unit testing in test task because I have learn nothing new in this case)

(http://localhost:8080/manager)




Note: bot token disposed into application.properties - it is not secret =)

how to start:

git clone https://github.com/Octoplar/task_tgbot

cd task_tgbot

mvn spring-boot:run


Then visit http://localhost:8080/manager

