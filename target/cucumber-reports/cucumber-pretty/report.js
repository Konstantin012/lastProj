$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/main/resources/features/rozetka-sort.feature");
formatter.feature({
  "name": "Тестируем страницу авторизации",
  "description": "  сайта automationpractice.com с водом\n  заведомо неверных данных и проверяем\n  отображаемые сообщения об ошибках",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@LoginProfile"
    }
  ]
});
formatter.scenario({
  "name": "Неудачная авторизация",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@LoginProfile"
    }
  ]
});
formatter.step({
  "name": "Я нахожусь на домашней странице",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});