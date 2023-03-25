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



public class ChangeMemberApplication_model{
	
	public static boolean changeMemberApplication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =response.getWriter();
		boolean changeMemberApplication=false;
		String identityCard= request.getParameter("identityCard");
		String applicationStatus=request.getParameter("applicationStatus");
		String reason=request.getParameter("reason");
		
		String changeMemberProfilePath="UPDATE [dbo].[CustomerApplication]\r\n"
				+ "   SET [applicationStatus] = ? \r\n"
				+ "      ,[reason] =  ? \r\n"
				+ " WHERE identityCard = ? ";
		Connection conn=null;
		
		try {
			Context initContext=new InitialContext();
			DataSource ds=(DataSource)initContext.lookup("java:/comp/env/jdbc/bank");
			conn=ds.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(changeMemberProfilePath);
		
			pstmt.setString(1, applicationStatus);
			pstmt.setString(2, reason);
			pstmt.setString(3, identityCard);
			
			pstmt.executeUpdate();
			if(pstmt.executeUpdate() == 1) {
			changeMemberApplication=true;

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
		return changeMemberApplication;
		
	}


}
