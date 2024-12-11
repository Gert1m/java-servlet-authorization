<%@ page language="java" contentType="text/html; charset=UTF-8"
 		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <p class="h">Регистрация в системе</p>
        <form action="/cinema/register" method="POST">
	        <div class="form-group">
	            <label for="text">Имя пользователя:</label>
	            <input type="text" id="text" name="username" required>
	        </div>
	        <p style="color: red;">${passwordMatch}</p>
	        <div class="form-group">
	            <label for="password">Пароль:</label>
	            <input type="password" id="password" name="password" required>
	        </div>
	        <div class="form-group">
	            <label for="confirm_password">Подтверждение пароля:</label>
	            <input type="password" id="confirm_password" name="confirm_password" required>
	        </div>
	        <center>
        		<button type="submit">Зарегистрироваться</button>
        	</center>
        </form>
        <div class="link">
            <p>Уже есть аккаунт? <a href="/cinema/login">Войти</a></p>
            <a href="/cinema/0">Вернуться на главную</a>
        </div>
    </div>
</body>
</html>