package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet(name = "/saludame")
public class Saludar extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Saludar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String nombre = request.getParameter("nombre");
//		response.getWriter().append("Jelou");
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/saludos","root","1234");
			PreparedStatement ps = con.prepareStatement("SELECT saludo FROM saludos WHERE nombre = ?");
			ps.setString(1, nombre);
			String mensaje = "";
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				mensaje= rs.getString("saludo");
			}
			response.getWriter().append("<html><body><h1>");
			response.getWriter().append("Saludo,"+nombre+" "+mensaje);
			response.getWriter().append("</h1></body></html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
