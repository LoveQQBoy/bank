package iii;

import java.io.IOException;
import java.io.PrintWriter;
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


@WebServlet("/MemberLoginXXXXX")
public class MemberLoginXXXXX extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberLoginXXXXX() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =response.getWriter();
		
		String account = request.getParameter("account");
		String password= request.getParameter("password");
		String username= request.getParameter("username");
		String memberProfilePath="select account,password from ApplicationList where account =? and password = ?";
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
				request.getSession().setAttribute("member", username);
				conn.close();
			}
			else {
				conn.close();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"查無資料");
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
		
		response.sendRedirect(request.getContextPath()+"/MemberLoginResponse.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
