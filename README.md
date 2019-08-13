## Задание

Форма регистрации с отправкой имейла после одобрения из внешней системы.
Дана форма регистрации в нашем приложении, в которой необходимо заполнить:
- логин,
- пароль,
- адрес электронной почты,
- ФИО. 

После отправки формы, мы регистрируем данные из нее в нашей БД, а также отправляем
ее для одобрения во внешней системе.
Пусть обмен с этой внешней системой будет через некое messaging решение. После
одобрения или отклонения заявки, наше приложение должно отправить сообщение на
электронную почту нашему пользователю с результатом проверки.

Стэк: JavaSE 8+, Spring boot 2.0, dbms - h2. Для тестов предпочтение Junit/Mockito/Assertj,
т.к. на проекте будут именно они. Остальное по вкусу.
В качестве абстракции над шиной предлагаем взять такой набросок:
https://pastebin.com/qWjRPuyp
Возвращать из них можно заглушки, дабы сэкономить время на реализацию тестового
задания.
Неплохо при этом помнить, что в реальной эксплуатации любая часть нашей системы может
отказать.
Будем очень рады обоснованиям принятых архитектурных решений. Комментарии в коде к
ним крайне приветствуются.

## Комментарии к реализации

Немного о нейминге. В задании указано что это форма регистрации, но я решил назвать это все не user/registration и.т.п, а лидами(leads)
Мотивация моя конечно не предельно прозрачная, я воспринял систему как сбор заявок на регистрацию в определнное событие, а не в качестве регистрации пользователя как участника системы с дальнейшей аутентификацией и авториизацией.    
Поэтому реализация и напоминает больше концепцию регистрации на мероприятие, нежели signUp. 

### Схема архитектуры

![scheme](https://github.com/scrobot/skblab-verifier/blob/develop/img/arc.png?raw=true)

### Краткое описание компонентов системы

#### модуль api-idl

Данный модуль содержит в себе proto-схемы, генерирует gRPC сервисы.
Имеет адаптацию под spring grpc и webflux.

#### модуль leads-api

Данный микросервис представляет собой точку входа с Rest API. Юзер делает запрос
к эндпоинту системы, отправляя json c данными в контроллер, который в свою очередь
передает эти данные в `lead-handler-service` и получает от него.

Модель запроса:
```json
{
  "login": "user",
  "password": "test1234",
  "email": "skblab.verifier@gmail.com",
  "fullname": "Ivanov Ivan Petrovich"
}
```

Модель ответа:
```json
{
  "requestId": 1,
  "email": "skblab.verifier@gmail.com"
}
``` 

#### модуль lead-handler-service

Данный сервис является основным связующим звеном с остальными сервисамии. Его задачами являются

- Получать инбокс на обработку запросов
- Валидация инбокса 
- Persistence инбокса
- Запрос на проверку лида в системе одобрения
- Асихронный слушатель ответов из системы одобрения
- Запрос на отправку сообщения на e-mail адрес указанный в лиде.

Схему работы сервиса разбита на 3 части.
1. Сервис получает из Rest API модуля запрос, валидирует данные, если он не проходят ва 
2. Затем следует persistence, в качестве базы выбран H2. После сохранения лида, сервис отправляет в `approver-service` асинхронно запрос на получение одобрения, в ответ получает протобаф `Empty` и сразу же возвращает ответ в Rest. Т.е. пользователь не ждет, пока вся цепочка будет выполнена, он практически моментально получает ответ.
3. сервис ждет асинхронно ответа из системы одобрения по налаженному grpc stream-коннекшену, и получив резльтаты, в зависимости от пришедшей модели ответа, он отправляет письмо на е-мейл указанный в лиде с информацией об одобрении/неодобрении его заявки.     

#### модуль approver-service

Данный модуль имеет 2 основные функции 

- Получение заявки на одобрении и сохранение их в БД
- Периодический опрос(в текущей реализации 2 раза в секунду) хранилища на наличие новых заявок, обработка их и отсылка результата через стрим подписчикам 

#### модуль email-sender

Задача данного модуля простейшая - он просто формирует сообщения из входящих моделей и отправляет их на указанный е-мейл адрес.

![mail](https://github.com/scrobot/skblab-verifier/blob/develop/img/email.jpg?raw=true)

#### Модуль Metrics

Задача модуля предельно проста: агрегировать кастомные метрики со всех микросервисов и писать их в influxdb. 

Я не стал заморачиваться, подрубать DropWizard и прочее, посчитал что для текущих задач этого достаточно, а базовые метрики всегда нужно расширять для превентивных мер. 

### Особенности технологического стека, Benefits

Я выбрал для реализации Project Reactor(WebFlux). Вследствие чего, данная система вышла полностью реактивная. 
Какие это дает преимущества: 

- Консистентный код. Вся система покрыта сущностями Mono/Flux. 
- Упрощенное тестирование - реактивные системы, наследующие стандарты Reactive Streams, проще тестировать
- Spring boot имеет стартер grpc с webflux, что сразу генерирует реактивные стабы
- Код выглядит менее императивно и более декларативно. Его легче читать(Субъективно)
- Поддержка BackPressure из-под коробки(отдельно об этом ниже) 
- Есть возможность реализовать реактивный персистенс(монга из-под коробки, а для jdbc есть https://github.com/chang-chao/spring-webflux-reactive-jdbc-sample, но я это не использовал в проекте)
- Project Reactor поддерживает адаптеры под RxJava, так что если поддерживать в дальнейшем эту систему, и интегрировать микросервисы с Rx'ом - это будет не так больно
- Проще обрабатывать ошибки, имеются интерфейсы которые отлавливают исключения и возвращают описанный результат
- Отказоустойчивость - есть где-то на stacktrace выпадет exception внутри Mono/Flux, сразу же будет вызван маппинг `onErrorReturn`/`onErrorResume`. Плюс заданы таймауты на самом стеке вызова.

### Недостатки, или что можно улучшить

- монорепа дает свое - постоянно нужно заводить вручную модули в classpath. Это крайне неудобно, однако в данной ситуации деражть 5-6 проектов было бы еще неудобнее. 
- В данный момент система не покрыта docker'ом. Есть интеграция плагина, но код пока не проходил стадию деплоя. Неплохо бы собрать все в контейнеры, добавить в `docker-compose` образы и запускать по кнопке
- Покрытие тестами: провал. Должно быть намного выше. Сейчас даже 10% нет. Банально не успел написать. TDD крутая штука, но нужно быть либо хорошо натренированным чтобы ее использовать, либо иметь хороший запас по времени. В данный момент есть только unit-тесты, интеграционные не написаны. Практически первостепенная важность - внедрить TDD/BDD(Spec)
- Косячнул с гитом - надо было внедрять git-flow с первого коммита. Сейчас есть только master/develop бранчи
- Не внедрил Spring cloud + Eureka(Consul). Неплохо было бы это сделать, чтобы не биндить сервисы руками. И далее можно, или даже нужно, пойти в сторону Spring Cloud k8s - Service Discovery, Healthcheck, e.t.c.
- Система довольно отказоустойчива, но я не успел везде внедрить вероятность Timeout'а. Circuit Breaker'ом является в данном случае связка grpc+webflux. Если будут еще REST-сервисы - то можно уже Hystrix вводить
- Микросервисы получились довольно независимы, но в этом случае, как и всегда, произошел trade off. Пришлось выберать между высокой грануляцией и слабой связанностью
- В `approver-service` реализованна "колхозная" версия персистентной очереди. Она не thread safe, и явно будет проблематичной если на нее хоть немного дать нагрузку. Хотел заменить какой-нибудь пересистентной очередью, но подумал что это будет оверхед по времени в данном случае
- Нет честного BackPressure. Если представить сейчас что на Lead API сейчас пойдет нагрузка 100|1000|10000 запросов в секунду - потребители(consumer) не будут успевать справляться с данными от производителя(producer). Даже если БЛ отрабатывать будет как надо, однозначно будут падать сервисы с OutOfMemory в DynamicBuffer. Чтобы этого избежать нужно внедрить прослойку между Lead API и Lead Handler, которая будет собирать в себе заявки, складывать в очередь и выдавать порционно лида для обработки. Чтобы повысить перфоманс нужно паралелить выполенения, а чтобы не словить OutOfMemory и реализовать "честный" BackPressure - необходимо интергрировать http://rsocket.io/. Тогда система будет довольно жизнеспособной.
- Неплохо бы внедрить Zipkin'а для трассировки
- Очень с собой боролся, хотел добавить с самого начала 'QuilityWall' - статические анализаторы Checkstyle, PMD, findbugs. Но это бы значительно увеличило время разработки. Надо обязательно добавлять, если код претендует на звание "production ready" 
 
В целом, можно много еще чего поменять и модифицировать. Нет предела совершенству (c)   
