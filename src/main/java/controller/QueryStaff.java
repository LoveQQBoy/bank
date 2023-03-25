package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import Beam.MemberBeam;
import Service.MemberService;
import iii.StaffLogin_model;

@WebServlet("/QueryStaff")
public class QueryStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(StaffLogin_model.isStaff(request, response)) {
			
			WebApplicationContext webApplicationContext=WebApplicationContextUtils.
					getWebApplicationContext(getServletContext());
			MemberService memberService=webApplicationContext.getBean(MemberService.class);
			List<MemberBeam> memberBeam=memberService.findAll();
			request.getServletContext().setAttribute("MemberInformation", memberBeam);
			request.getRequestDispatcher("/StaffMemberReview.jsp").forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath()+"/StaffLogin.jsp");
		}
		
	}

}
