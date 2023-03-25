package iii;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.http.HttpServletRequest;

import javax.sql.DataSource;

public class UserService {
	// 檢查使用者是否已通過身分驗證?
	public static boolean isAuthenticated(HttpServletRequest request) {
		boolean authenticated = false;

		String authenticatedUsername = (String) request.getSession().getAttribute("authenticated");

		if (authenticatedUsername != null) {
			authenticated = true;
		}

		return authenticated;
	}

	
	
	// 進行使用者身分驗證
	public static boolean userAuthentication(HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("UTF-8");		
		String account = request.getParameter("account").toLowerCase();
		String password = request.getParameter("pwd");
		boolean authenticated = false;

		try {		
			DataSource ds = (DataSource) request.getServletContext().getAttribute("ds-sqlsrv-servlethw");
			Connection conn = ds.getConnection();		
			String sql1 = "select username from CustomerInformation where account=? and password=?";
			String sql2 = "select top(1) bankAccount, total from record where bankAccount =( " 
					+ "  select bankAccount from [CustomerInformation] "
					+ "  where account=? and password=? )" 
					+ "  order by operationTime desc";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt1.setString(1, account);
			pstmt1.setString(2, password);
			pstmt2.setString(1, account);
			pstmt2.setString(2, password);
			ResultSet rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				authenticated = true;
				request.getSession().invalidate();
				String username = rs1.getString(1);
				request.getSession().setAttribute("authenticated", username);
			}

			ResultSet rs2 = pstmt2.executeQuery();
			if (rs2.next()) {
				String su = rs2.getString(1);
				int totalmoney = rs2.getInt(2);
					
				request.getSession().setAttribute("su", su);
				request.getSession().setAttribute("totalmoney", totalmoney);
			}
			conn.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return authenticated;
	}

	
	// 檢查帳戶是否待審核
	public static boolean reuserAuthentication(HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String account = request.getParameter("account").toLowerCase();
		String password = request.getParameter("pwd");
		boolean flag = false;

		try {			
			DataSource ds = (DataSource) request.getServletContext().getAttribute("ds-sqlsrv-servlethw");
			Connection conn = ds.getConnection();
			
			String sql1 = "select username from CustomerApplication where account=? and password=?";		
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);			
			pstmt1.setString(1, account);
			pstmt1.setString(2, password);
			
			ResultSet rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				flag = true;
			}			
			conn.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}
	
	
	
	public static boolean newAccount(HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("username");
		String account = request.getParameter("account").toLowerCase();
		String password = request.getParameter("password");
		String bornDate=request.getParameter("bornDate");
		String identityCard=request.getParameter("identityCard");
		String phoneNumber=request.getParameter("phoneNumber");
		String email=request.getParameter("email");
		boolean newAccount = false;

		try {
			DataSource ds = (DataSource) request.getServletContext().getAttribute("ds-sqlsrv-servlethw");
			Connection conn = ds.getConnection();
			
			String sql1 = " SELECT account  FROM CustomerInformation where account = ?"
					+ " union "
					+ " SELECT account FROM CustomerApplication where account = ?";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, account);
			pstmt1.setString(2, account);
			ResultSet rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				
				request.setAttribute("mutiaccount", "");
			}else {				
				String sql = "INSERT INTO [dbo].[CustomerApplication]"  
							+"(username,account,password,bornDate,identityCard,phoneNumber,email)"
							+ "values(?,?,?,?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, account);
				pstmt.setString(3, password);
				pstmt.setString(4, bornDate);
				pstmt.setString(5, identityCard);
				pstmt.setString(6, phoneNumber);
				pstmt.setString(7, email);
	
				int count = pstmt.executeUpdate();
				if (count > 0) {
					newAccount = true;
				}
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newAccount;
	}

	
	
	public static boolean dewi(HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("UTF-8");
		int inputmoney;
		PreparedStatement pstmt1;
		PreparedStatement pstmt2;
		String su = (String) request.getSession().getAttribute("su");
		boolean deposit = false;
		try {
			DataSource ds = (DataSource) request.getServletContext().getAttribute("ds-sqlsrv-servlethw");
			Connection conn = ds.getConnection();
			if (request.getParameter("inputmoney") != null) {
				inputmoney = Integer.valueOf(request.getParameter("inputmoney"));				
					
				String sql1 = " INSERT INTO [dbo].[record]([bankAccount],[transactionAmount] " 
						+ " ,[method],[total]) "
						+ " VALUES(?, ?, '存款' " 
						+ " , (select top(1) total from [record] where bankAccount=? "
						+ " order by operationTime desc)+?) ";												
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setString(1, su);
				pstmt1.setInt(2, inputmoney);
				pstmt1.setString(3, su);
				pstmt1.setInt(4, inputmoney);

			} else {
				inputmoney = Integer.valueOf(request.getParameter("outputmoney"));
								
				String sql1 = " INSERT INTO [dbo].[record]([bankAccount],[transactionAmount] " 
						+ " ,[method],[total]) "
						+ " VALUES(?, ?, '提款' " 
						+ " , (select top(1) total from [record] where bankAccount=? "
						+ " order by operationTime desc)-?) ";
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setString(1, su);
				pstmt1.setInt(2, inputmoney);
				pstmt1.setString(3, su);
				pstmt1.setInt(4, inputmoney);
			}

			int count = pstmt1.executeUpdate();
			if (count > 0) {
				deposit = true;
			}

			String sql2 = "select top(1) [total] from [dbo].[record] where bankAccount=? order by operationTime desc";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, su);
			
			ResultSet rs = pstmt2.executeQuery();
			if (rs.next()) {
				int totalmoney = rs.getInt(1);
				request.getSession().setAttribute("totalmoney", totalmoney);
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deposit;
	}		

}
