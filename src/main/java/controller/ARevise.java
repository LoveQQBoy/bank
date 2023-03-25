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

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

@WebServlet("/ARevise")
public class ARevise extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username").toLowerCase();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String bornDate = request.getParameter("bornDate");
		String identityCard = request.getParameter("identityCard");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String su = (String)request.getSession().getAttribute("su");
		
		try {			
			DataSource ds = (DataSource) request.getServletContext().getAttribute("ds-sqlsrv-servlethw");
			Connection conn = ds.getConnection();
			
			String sql1 = " SELECT [account]  FROM [CustomerInformation] where [account] = ? union "
					+ " SELECT [account] FROM [CustomerApplication] where [account] = ?";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, account);
			pstmt1.setString(2, account);
			ResultSet rs1 = pstmt1.executeQuery();
			
			if(rs1.next() && !rs1.getString(1).equals(request.getSession().getAttribute("account"))) {				
				request.setAttribute("reviseMessage", "帳號已被使用");
				request.getRequestDispatcher("/Upuserdata.jsp") 
				.forward(request, response);
								
			} else {				
			
				String sql = " UPDATE [dbo].[CustomerInformation] SET"
						+ " [username]=?,[account]=?,[password]=?,[bornDate]=? "
						+ " ,[identityCard]=?,[phoneNumber]=?,[email]=? "
						+ " where [bankAccount]=? ";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);		
				pstmt.setString(1, username);		
				pstmt.setString(2, account);		
				pstmt.setString(3, password);		
				pstmt.setString(4, bornDate);		
				pstmt.setString(5, identityCard);		
				pstmt.setString(6, phoneNumber);		
				pstmt.setString(7, email);		
				pstmt.setString(8, su);		
				int count = pstmt.executeUpdate();
				if (count>0) {

					request.getSession().setAttribute("authenticated", username);	
					request.getSession().setAttribute("account", account);	
					request.getSession().setAttribute("password", password);							
					request.getSession().setAttribute("bornDate", bornDate);							
					request.getSession().setAttribute("identityCard", identityCard);							
					request.getSession().setAttribute("phoneNumber", phoneNumber);							
					request.getSession().setAttribute("email", email);												 
					
					request.setAttribute("reviseMessage", "修改成功");									
				}else {
					request.setAttribute("reviseMessage", "修改失敗");
				}			
				conn.close();
				request.getRequestDispatcher("/Upuserdata.jsp") 
				.forward(request, response);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}

}
