package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import Beam.MemberBeam;
import Service.MemberService;


@WebServlet("/FindStaff")
public class FindStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String PID=request.getParameter("id");
		int id = Integer.parseInt(PID);
		
		WebApplicationContext  webApplicationContext = 
				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		MemberService memberService = webApplicationContext.getBean(MemberService.class);
		MemberBeam mb = memberService.findById(id);
		request.setAttribute("mb", mb);
		RequestDispatcher rd = request.getRequestDispatcher("/UpdateStaff.jsp");
		rd.forward(request, response);
		return;
	}

}
