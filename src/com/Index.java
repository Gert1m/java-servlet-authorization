package com;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// определяем по какому адрессу будет доступ к сервлету, в данном случае cinema/0
@WebServlet(urlPatterns = "/0")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// переопределяем метод doGet который вызывается в случае перехода на страницу по адрессу
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// получаем значение атрибута session из класса HttpSession (false означает что не надо создавать сессию если она не существовала ранее)
		HttpSession session = request.getSession(false);
		// инициализируем переменую username в которой будет хранится имя пользователя
		// и isLogin в которой будет хранится паттерн в зависимости от вида сессии
		String username, isLogin;
		// проверяем что наша сессия существует
		if (session != null) {
			// присваиваем значение которое хранится в сессии под именем username переменной username
			username = (String) session.getAttribute("username");
			// если значение равно null (тоесть его нет) то
	        if (username == null) {
	        	// переменой username присваиваем значение "Аноним"
	            username = "Аноним";
	            // а паттерну для входа значение "Войти"
	            isLogin = "Войти";
	        // если же username хранит имя пользователя то
	        } else {
	        	// значение username не переопределяем (оно хранит имя пользователя)
	        	// паттерн входа будет равен "Смена пользователя"
	        	isLogin = "Смена пользователя";
	        }
	    // если сессия не существует то
		} else {
			// переменой username присваиваем значение "Аноним"
			username = "Аноним";
			// а паттерну для входа значение "Войти"
			isLogin = "Войти";
		}
		// и передаём в request аттрибуты username и isLogin
        request.setAttribute("username", username);
        request.setAttribute("isLogin", isLogin);
		// в конечном итоге устанавливаем в качесте отображения на странице файл index.jsp и передаём в него объекты request и response
		request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
}