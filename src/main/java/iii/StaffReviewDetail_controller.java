package iii;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/StaffReviewDetail_controller")
public class StaffReviewDetail_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
		if(StaffReviewDetail_model.reviewPass(request, response)) {
		//	out.print(request.getSession().getAttribute("ID"));
			response.sendRedirect(request.getContextPath()+"/Index.jsp");
		}		
		else {
			out.print("no");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
