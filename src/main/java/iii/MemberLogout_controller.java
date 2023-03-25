package iii;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MemberLogout_controller")
public class MemberLogout_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
          
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	if(UserService.isAuthenticated(request)) {//當使用者已通過身分驗證
    		//銷毀目前的Session物件
			request.getSession().invalidate();
			
			//重新導向「登入表單」
			response.sendRedirect(request.getContextPath()+"/Index.jsp");
		}
    	else
    		response.sendError(HttpServletResponse.SC_FORBIDDEN);//狀態碼：403    	
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
