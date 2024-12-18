package com.dam2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexion.ConexionBD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/insertarEmp")
@MultipartConfig
public class EjercicioAppGestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public EjercicioAppGestion() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String nombreEmp = request.getParameter("nombreEmp");
		String dniEmp = request.getParameter("dniEmp");
		try {
			Connection con = ConexionBD.getConex(getServletContext());
			String query = "INSERT INTO empleado (dni, nom_emp) VALUES (?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, dniEmp);
			ps.setString(2, nombreEmp);
			ps.executeUpdate();
			response.getWriter().append("<html><body><h1>");
			response.getWriter().append("Saludo,"+nombreEmp);
			response.getWriter().append("</h1></body></html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}