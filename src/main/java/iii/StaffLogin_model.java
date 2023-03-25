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



public class StaffLogin_model{
	
	public static boolean isStaff(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		boolean staff=false;
		//防呆避免是null時直接進入此方程式而報錯
		if(request.getSession().getAttribute("ID") == null) {
			return staff;
		}
		
		int IDInteger=(int)request.getSession().getAttribute("ID");
		String ID=Integer.toString(IDInteger);
		if("911".equals(ID)) {
			staff=true;
		}
		return staff;
		
	}

	public static boolean staffPermissionsLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =response.getWriter();
		boolean AdvancedPermission=false;
		String account = request.getParameter("account");
		String password= request.getParameter("password");
		String username= request.getParameter("username");
		String memberProfilePath="select account,password,ID,username from AdvancedPermissions where account =? and password = ?";
		Connection conn=null;
		
		try {
			Context initContext=new InitialContext();
			DataSource ds=(DataSource)initContext.lookup("java:/comp/env/jdbc/bank");
			conn=ds.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(memberProfilePath);
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			
			
			if(rs.next()) {
				AdvancedPermission = true;
				int ID=rs.getInt("ID");
				username = rs.getString("username");
				request.getSession().setAttribute("ID", ID);
				request.getSession().setAttribute("username",username );
				conn.close();
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
		}
		return AdvancedPermission;
		
	}


}
