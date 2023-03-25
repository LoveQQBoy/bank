package iii;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/StaffLogin_controller")
public class StaffLogin_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			
			//登入會員
		if(StaffLogin_model.isStaff(request, response)) {
		//	out.print(request.getSession().getAttribute("ID"));
			response.sendRedirect(request.getContextPath()+"/MemberLoginResponse.jsp");
		}
			//確認登入會員
		else if(StaffLogin_model.staffPermissionsLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/MemberLoginResponse.jsp");
		}
		else {
			response.sendRedirect(request.getContextPath()+"/StaffLogin.jsp");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
