package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/Deleteuser")
public class Deleteuser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String su = (String)request.getSession().getAttribute("su");
		try {			
			DataSource ds = (DataSource) request.getServletContext().getAttribute("ds-sqlsrv-servlethw");
			Connection conn = ds.getConnection();
			String sql = " DELETE FROM [dbo].[CustomerInformation] where [bankAccount]=? "
					   + " DELETE FROM [dbo].[record] where [bankAccount]=? ";

			PreparedStatement pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, su);		
			pstmt.setString(2, su);		
				
			int count = pstmt.executeUpdate();
			conn.close();
			
			if (count>0) {
				request.getSession().invalidate();
				request.setAttribute("deleteMessage", "刪除成功");
				request.getRequestDispatcher("/CreateMember.jsp") //
				.forward(request, response);
			}else {
				request.setAttribute("deleteMessage", "刪除失敗");
				request.getRequestDispatcher("/Upuserdata.jsp") //
				.forward(request, response);
			}			
								
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
