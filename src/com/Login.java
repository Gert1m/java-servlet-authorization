package com;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//определяем по какому адрессу будет доступ к сервлету, в данном случае cinema/login
@WebServlet(urlPatterns = "/login") 
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// переопределяем метод doGet который вызывается в случае перехода на страницу по адрессу
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// устанавливаем в качестве отображение файл login.jsp
		request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	// переопределяем метод doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// извлекаем значения из login.jsp
		// username берёт значение из <input ... name="username">
		String username = request.getParameter("username");
		// password берёт значение из <input ... name="password">
		String password = request.getParameter("password");
		
		// подключаемся к базе данных
		try (Connection connect = DriverManager.getConnection("jdbc:sqlite:users.db");
			Statement cursor = connect.createStatement()) {
				// получаем пароль из базы данных для введёного при логине пользователя
				ResultSet password_from_db = cursor.executeQuery("SELECT password FROM users WHERE username = '" + username + "'");
				// переводим пароль из базы данных в привычный строчный формат
				String user_password = password_from_db.getString("password");
				
				// делаем проверку что введённый пользователем пароль совпадает с паролем в базе данных
	            if (password.equals(user_password) && user_password!=null) {
	            	// создаём сессию, в данном случае передаём в неё значение True чтобы она создалась если не существовала ранее
					HttpSession session = request.getSession();
					// устанавливаем в сессии аттрибуду под именем username значие которое ввёл пользователь
			        session.setAttribute("username", username);
			        // переадрисовываем пользователя на cinema/0 (тоесть Index.java сервлет)
					response.sendRedirect("0");
				// если пароли не совпадают то
	            } else {
	            	// передаем в loginMatch объекта request значение "Неверное имя пользователя или пароль!"
					request.setAttribute("loginMatch", "Неверное имя пользователя или пароль!");
					// устанавливаем в качестве отображения опять login.jsp в который передаём request и response
					request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				}
	    // выводим ошибки при работе с базами данных
		} catch (SQLException e) {
				System.out.print(e);
		}
    }
}
