package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String LOAD_DRIVER="com.mysql.cj.jdbc.Driver";
	public static String URL="jdbc:mysql://localhost:3306/userinfo";
	public static String PASSWORD="root";
	public static String USERNAME="root";
	Connection connection;

    public RegisterServlet() {
        super();

    }


	public void init(ServletConfig config) throws ServletException {
		try {
			connection =DriverManager.getConnection(URL,USERNAME,PASSWORD);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String uname=request.getParameter("uname");
		String password=request.getParameter("password");
		try {
			PreparedStatement ps =  connection.prepareStatement("INSERT INTO uinfo values(?,?,?,?)");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, uname);
			ps.setString(4, password);
			ps.executeUpdate();
			
			PrintWriter pw= response.getWriter();
			pw.println("<html><body bgcolor=black text=white><center>");
			pw.println("<h1>User Register Successfully</h1>");
			pw.println("<a href=login.html>Login</a>");
			pw.println("</center></body></html>");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
