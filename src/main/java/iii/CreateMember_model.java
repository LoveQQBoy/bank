package iii;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class CreateMember_model {
	
	

	public static boolean createMember_model(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		boolean createMember = false;
		String username = request.getParameter("username");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String bornDate = request.getParameter("bornDate");
		String identityCard = request.getParameter("identityCard");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String applicationStatus = request.getParameter("applicationStatus");
		String reason = request.getParameter("reason");

		String changeMemberProfilePath = "INSERT INTO [dbo].[CustomerInformation]"
				+ "([username],[account],[password],[bornDate],[identityCard],[phoneNumber]"
				+ ",[email],[applicationStatus],[reason])" + "VALUES(?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		
		//若審查未通過或是待審查直接return,不insert進table
		if(applicationStatus.equals("待審查") || applicationStatus.equals("審查未通過")) {
			createMember = true;
			return createMember;
		}
		
			try {
				Context initContext = new InitialContext();
				DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/bank");
				conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(changeMemberProfilePath);

				pstmt.setString(1, username);
				pstmt.setString(2, account);
				pstmt.setString(3, password);
				pstmt.setString(4, bornDate);
				pstmt.setString(5, identityCard);
				pstmt.setString(6, phoneNumber);
				pstmt.setString(7, email);
				pstmt.setString(8, applicationStatus);
				pstmt.setString(9, reason);

				if (pstmt.executeUpdate() == 1) {
					createMember = true;

				}
				conn.close();

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
			}
			return createMember;

		
	}

}
