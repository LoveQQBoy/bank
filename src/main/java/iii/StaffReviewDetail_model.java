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



public class StaffReviewDetail_model{
	
	public static boolean reviewPass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =response.getWriter();
		boolean reviewPass=false;
		String account = request.getParameter("account");
		String password= request.getParameter("password");
		String username= request.getParameter("username");
		String bornDate= request.getParameter("bornDate");
		String identityCard= request.getParameter("identityCard");
		String phoneNumber= request.getParameter("phoneNumber");
		String email= request.getParameter("email");
		String applicationStatus= request.getParameter("applicationStatus");
		String bankAccount= "1234567890123456"; //rember delete
		
		String memberProfilePath="insert into CustomerInformation (username,applicationStatus,account,password,bornDate,identityCard,phoneNumber,bankAccount,email) values (?,?,?,?,?,?,?,?,?)";
		Connection conn=null;
		
		try {
			Context initContext=new InitialContext();
			DataSource ds=(DataSource)initContext.lookup("java:/comp/env/jdbc/bank");
			conn=ds.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(memberProfilePath);
			pstmt.setString(1, username);
			pstmt.setString(2, applicationStatus);
			pstmt.setString(3, account);
			pstmt.setString(4, password);
			pstmt.setString(5, bornDate);
			pstmt.setString(6, identityCard);
			pstmt.setString(7, phoneNumber);
			pstmt.setString(8, bankAccount);
			pstmt.setString(9, email);
			reviewPass=true;
			pstmt.executeUpdate();
//			if(pstmt.executeUpdate() == 1) {
//				reviewPass=false;
//			}
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
		return reviewPass;
		
	}


}
