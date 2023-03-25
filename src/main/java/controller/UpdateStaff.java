package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import Beam.MemberBeam;
import Service.MemberService;


@WebServlet("/UpdateStaff")
public class UpdateStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Map<String,String> errorMessage = new HashMap<>();
		Map<String,String> rememberMessage = new HashMap<>();
		
		String username=request.getParameter("username");
		String account=request.getParameter("account");
		String password=request.getParameter("password");
		String bornDate=request.getParameter("bornDate");
		String identityCard = request.getParameter("identityCard");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String p_id =request.getParameter("id");
		Integer id=Integer.valueOf(p_id);
		rememberMessage.put("username",username);
		rememberMessage.put("account",account);
		rememberMessage.put("password",password);
		rememberMessage.put("phoneNumber",phoneNumber);
		rememberMessage.put("email",email);
		
		
		if (username.trim().length()==0 ) {
			errorMessage.put("username", "請輸入20個以內的字元");
		}else if(username.length() >20) {
			errorMessage.put("username", "您輸入字元大於20");
			rememberMessage.put("username","");
		}
		
		if (account.trim().length()==0 ) {
			errorMessage.put("account", "請輸入20個以內的字元");
		}else if(account.length() >20) {
			errorMessage.put("account", "您輸入字元大於20");
			rememberMessage.put("account","");
		}
		
		if (password.trim().length()==0 ) {
			errorMessage.put("password", "請輸入20個以內的字元");
		}else if(password.length() >20) {
			errorMessage.put("password", "您輸入字元大於20");
			rememberMessage.put("password","");
		}
		
		if (bornDate.trim().length()==0 ) {
			errorMessage.put("bornDate", "請更改日期");
		}
		
		if (identityCard.trim().length()==0 ) {
			errorMessage.put("identityCard", "請輸入剛好10個字元");
		}else if(identityCard.length() >10) {
			errorMessage.put("identityCard", "請輸入剛好10個字元");
		}else if(identityCard.length() <10) {
			errorMessage.put("identityCard", "請輸入剛好10個字元");
		}
		
		if (phoneNumber.trim().length()==0 ) {
			errorMessage.put("phoneNumber", "請輸入20個以內的數字");
		}else if(phoneNumber.length() >20) {
			errorMessage.put("phoneNumber", "您輸入數字大於20");
			rememberMessage.put("phoneNumber","");
		}
		
		if (email.trim().length()==0 ) {
			errorMessage.put("email", "請輸入30個以內的字元");
		}else if(email.length() >30) {
			errorMessage.put("email", "您輸入的字元大於30");
			rememberMessage.put("email","");
		}
		
		if(!errorMessage.isEmpty()) {
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("rememberMessage", rememberMessage);
			
			request.getRequestDispatcher("/UpdateStaff.jsp").forward(request, response);
			return;
		}
		
		MemberBeam memberBeam=new MemberBeam(username,account,password,
											bornDate,identityCard,phoneNumber,email);
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.
									getWebApplicationContext(getServletContext());
		MemberService memberService = webApplicationContext.getBean(MemberService.
										class);
		memberBeam.setP_ID(id);
		memberService.update(memberBeam);
		
		response.sendRedirect(request.getContextPath()+"/QueryStaff");
		
	}

}
