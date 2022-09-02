package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TryCatchFinally;

import model.MyUtil;

/**
 * Servlet implementation class menu
 */
@WebServlet("/menu")
public class menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public menu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession(true);
		response.setLocale(new Locale(new String("zh"), new String("TW")));
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		MyUtil.printHead(pw);
		String user = (String)session.getAttribute("user");
		if (user == null) response.sendRedirect("Login");
		else {
			pw.println("hello " + user + "<br>");
			pw.println("<a href=Query><input type=button value=Query name=B1><br><br>");
			pw.println("<a href=Shopping><input type=button value=Shopping name=B2><br><br>");
		}
	}

}
