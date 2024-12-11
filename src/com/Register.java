package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//определяем по какому адрессу будет доступ к сервлету, в данном случае cinema/register
@WebServlet(urlPatterns = "/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// переопределяем метод doGet который вызывается в случае перехода на страницу по адрессу
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// устанавливаем в качестве отображения register.jsp
		request.getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
	}
	
	// переопределяем метод doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// извлекаем значения из register.jsp
		// username берёт значение из <input ... name="username">
		String username = request.getParameter("username");
		// password берёт значение из <input ... name="password">
		String password = request.getParameter("password");
		// confirm_password берёт значение из <input ... name="confirm_password">
		String confirm_password = request.getParameter("confirm_password");
		
		// проверяем что пароли совпадают
		if (password.equals(confirm_password)) {
			// подключаемся к базе данных
			try (Connection connect = DriverManager.getConnection("jdbc:sqlite:users.db");
				Statement cursor = connect.createStatement()) {
				// создаём запросс на создание новой таблице в базе данных если она не существовала ранее
				String sqlCreate = "CREATE TABLE IF NOT EXISTS users (\n"
			                + " username text PRIMARY KEY,\n"
			                + " password text NOT NULL\n"
			                + ");";
				// создаём новую таблицу в базе данных
				cursor.execute(sqlCreate);
				// добавляем в таблицу базы данных имя пользователя и пароль
				cursor.execute("INSERT INTO users VALUES ('"+ username +"', '"+ password +"')");
				
				// создаём сессию, в данном случае передаём в неё значение True чтобы она создалась если не существовала ранее
				HttpSession session = request.getSession();
				// устанавливаем в сессии аттрибуду под именем username значие которое ввёл пользователь
		        session.setAttribute("username", username);
		        // переадрисовываем пользователя на cinema/0 (тоесть Index.java сервлет)
				response.sendRedirect("0");
			// обрабатываем ошибки баззы даных
	        } catch (SQLException e) {
	        	// обрабатываем ошибку UNIQUE constraint failed в случае если пользователь вводит имя которое уже зарегистрировано в базе данных
	        	if (e.getErrorCode() == 19) {
	        		// передаём в passwordMatch объекта request значение "Такой пользователь уже существует!"
	        		request.setAttribute("passwordMatch", "Такой пользователь уже существует!");
	        		// устанавливаем в качестве отображения опять register.jsp в который передаём request и response
	    			request.getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
	        	} else {
	        		System.out.println(e);
				}
	        } 
		// если пароли не совпадают то 
		} else {
			// передаём в passwordMatch объекта request значение "Пароли должны совпадать!"
			request.setAttribute("passwordMatch", "Пароли должны совпадать!");
			// устанавливаем в качестве отображения опять register.jsp в который передаём request и response
			request.getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
		}
	}
}
