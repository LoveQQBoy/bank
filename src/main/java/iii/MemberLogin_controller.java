package iii;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/MemberLogin_controller", loadOnStartup = 1 )
public class MemberLogin_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//檢查使用者是否已通過身分驗證或進行身分驗證時是通過的
		if(UserService.userAuthentication(request) || UserService.isAuthenticated(request)) {
						
			response.sendRedirect(request.getContextPath()+"/MemberOperation.jsp"); 
			
		}else if(UserService.reuserAuthentication(request)) {
					
			request.setAttribute("waitlogin", "<h5>客戶您好，您的帳戶待審核中</h5>");			
			request.getRequestDispatcher("/CreateMember.jsp").forward(request, response);
			
		}else {					
			request.setAttribute("loginerror", "查無此號，請先建立帳戶");
			request.getRequestDispatcher("/CreateMember.jsp").forward(request, response);
		}
						
		
	}
}
