<%@ page language="java" contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Кинотеатр</title>
    <style>

body {
    position: relative;
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    line-height: 1.6;
    color: #f4f4f4;
    background: #333;
}

header {
    padding: 10px 0;
    text-align: center;
}

nav{
    position: absolute;
    
    width: 20%;
    height: 10%;
    left: 79%;
    text-align: left;
    list-style: none;
    border: 1px solid #ccc;
}

.avatar {
    right: 0;
    width: 20%;
    height: 82%;
    border: 1px solid #ccc;
}

#about {
    margin-top: 7%;
}

section {
    padding: 20px;
    margin: 10px;
}

nav ul {
    list-style: none;
    padding: 0;
}

nav ul li {
    display: inline;
    margin: 0 2%;
}

.movie {
    border: 1px solid #ccc;
    padding: 10px;
    margin-bottom: 10px;
}

a {
    color: rgb(90, 147, 239);
}

footer {
    text-align: center;
    padding: 10px 0;
}
</style>
</head>
<body>
    <header>
        <h1>Кинотеатр "Свет", где начинается ваше приключение!</h1>
            <nav>
                <ul style=" margin-block-start: 1%; margin-block-end: 1%;">
                    <li style="position: absolute; font-size: xx-large; width: 70%;">
                        <p style="margin-block-start: 0; margin-block-end: 0;">${username}</p>
                    </li>
                    <li style="position: absolute; bottom: 1%; text-align: right; width: 70%; font-size: x-large;">
                        <a href="/cinema/login">${isLogin}</a>
                    </li>
                    <li style="position: absolute; left: 71.5%; width: 25%; height: 90%; border: 1px solid #ccc;">
                        <img src="" alt="фото">
                    </li>
                </ul>
            </nav>
    </header>
    <section id="about" class="movie">
        <h2>О нас</h2>
        <p>Наш кинотеатр предлагает широкий выбор фильмов для всей семьи. Мы гарантируем незабываемые впечатления.</p>
    </section>

    <section id="movies">
        <h2>Текущие фильмы</h2>
        <div class="movie">
            <h3>Фильм 1</h3>
            <p>Описание фильма 1. Дата выхода: скоро</p>
        </div>
        <div class="movie">
            <h3>Фильм 2</h3>
            <p>Описание фильма 2. Дата выхода: скоро</p>
        </div>
    </section>

    <footer>
        <h2 id="contact">Контакты</h2>
        <p>Email: info@cinema.ru</p>
        <p>Cделано с помощью <a href="https://trychatgpt.ru">trychatgpt</a></p>
    </footer>
</body>
</html>