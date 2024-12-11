<%@ page language="java" contentType="text/html; charset=UTF-8"
 		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <p class="h">Вход в кабинет</p>
        <form action="/cinema/login" method="post">
	        <div class="form-group">
	            <label for="text">Имя пользователя:</label>
	            <input type="text" id="text" name="username">
	        </div>
	        <p style="color: red;">${loginMatch}</p>
	        <div class="form-group">
	            <label for="password">Пароль:</label>
	            <input type="password" id="password" name="password" required>
	        </div>
	        <center>
        		<button type="submit">Войти</button>
        	</center>
        </form>
        <div class="link">
            <p>Нет аккаунта? <a href="/cinema/register">Зарегистрироваться</a></p>
            <a href="/cinema/0">Вернуться на главную</a>
        </div>
    </div>
</body>
</html>