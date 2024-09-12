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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static String LOAD_DRIVER="com.mysql.cj.jdbc.Driver";
	public static String URL="jdbc:mysql://localhost:3306/userinfo";
	public static String PASSWORD="root";
	public static String USERNAME="root";
	Connection connection;
       

    public LoginServlet() {
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
		String uname=request.getParameter("uname");
		String password=request.getParameter("password");
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM uinfo WHERE uname=?");
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			PrintWriter pw= response.getWriter();
			pw.println("<html><body bgcolor=black text=white><center>");
			if(rs.next()) {
				pw.println("Welcome "+uname);
			}else {
				pw.print("INVALID USER");
			}
			pw.println("</center></body></html>");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
