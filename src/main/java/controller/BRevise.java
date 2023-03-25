package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/BRevise")
public class BRevise extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String su = (String)request.getSession().getAttribute("su");		
		try {			
			DataSource ds = (DataSource) request.getServletContext().getAttribute("ds-sqlsrv-servlethw");
			Connection conn = ds.getConnection();
			String sql = " select [username], [account], [password], [bornDate], [identityCard], "
						+ " [phoneNumber], [email] from [CustomerInformation] where [bankAccount]=? ";

			PreparedStatement pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, su);		
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String account = rs.getString(2);
				request.getSession().setAttribute("account", account);
				String password = rs.getString(3);
				request.getSession().setAttribute("password", password);
				String bornDate = rs.getString(4);
				request.getSession().setAttribute("bornDate", bornDate);
				String identityCard = rs.getString(5);
				request.getSession().setAttribute("identityCard", identityCard);
				String phoneNumber = rs.getString(6);
				request.getSession().setAttribute("phoneNumber", phoneNumber);
				String email = rs.getString(7);
				request.getSession().setAttribute("email", email);									
			}			
			conn.close();
			request.getRequestDispatcher("/Upuserdata.jsp") //
			.forward(request, response);
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
