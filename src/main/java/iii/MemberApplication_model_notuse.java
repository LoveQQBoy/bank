package iii;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class MemberApplication_model_notuse {

	public static boolean createMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out =response.getWriter();
		boolean create = false;
		String username=request.getParameter("username");
		String account =request.getParameter("account");
		String password=request.getParameter("password");
		String bornDate=request.getParameter("bornDate");
		String identityCard=request.getParameter("identityCard");
		String phoneNumber=request.getParameter("phoneNumber");
		String email=request.getParameter("email");
		
		if(username =="" || account =="" || password =="" || identityCard==""
				 || phoneNumber =="") {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return create;
		}
		
		String createDataPath="insert into CustomerApplication(username,account,password,bornDate,identityCard,phoneNumber,email) values(?,?,?,?,?,?,?)";
		
		Connection conn=null;
		try {
			InitialContext initContext = new InitialContext();
			Context envContext=(Context)initContext.lookup("java:/comp/env");
			DataSource ds=(DataSource)envContext.lookup("jdbc/bank");
			conn=ds.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(createDataPath);
			pstmt.setString(1, username);
			pstmt.setString(2, account);
			pstmt.setString(3, password);
			pstmt.setString(4, bornDate);
			pstmt.setString(5, identityCard);
			pstmt.setString(6, phoneNumber);
			pstmt.setString(7, email);
			create = true;
			pstmt.executeUpdate();
			conn.close();
			
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//	out.println();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
		}

		return create;
	}

}
