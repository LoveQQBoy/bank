package iii;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Dewi_controller")
public class Dewi_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;      
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("inputmoney") != null) {					
			if(UserService.dewi(request)) {
				request.getSession().setAttribute("dewimessage","存款成功");				
			}else {				
				request.getSession().setAttribute("dewimessage","存款失敗");
			}
		}else{
			if(UserService.dewi(request)) {
				request.getSession().setAttribute("dewimessage","提款成功");				
			}else {				
				request.getSession().setAttribute("dewimessage","提款失敗");
			}
		}
		
		response.sendRedirect(request.getContextPath()+"/MemberOperation.jsp");
	}

}
