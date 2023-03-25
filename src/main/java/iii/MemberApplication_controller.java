package iii;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MemberApplication_controller")
public class MemberApplication_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if(UserService.newAccount(request)) {					
			request.setAttribute("createmessage", "創建成功，請於1~2工作日後，再行登入");
			request.getRequestDispatcher("/CreateMember.jsp")
			.forward(request, response);
			
		}else {
			if(request.getAttribute("mutiaccount")!=null) {
				request.setAttribute("createmessage", "此帳號已被使用");
				request.getRequestDispatcher("/CreateMember.jsp")
				.forward(request, response);
			}else {
				request.setAttribute("createmessage", "創建失敗");
				request.getRequestDispatcher("/CreateMember.jsp")
				.forward(request, response);
			}
		}
	}

}
