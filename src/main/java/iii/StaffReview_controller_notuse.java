package iii;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class StaffReview_controller_notuse{
	
	public static boolean staffReview(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		List<CreateApplication_Beam> createApplicationMemberList=new ArrayList<>();
		String StaffReviewPath= "select username,applicationStatus\r\n"
				+ "from CustomerApplication \r\n"
				+ "where applicationStatus ='待審查'";
		boolean staffReviewStatus=false;
		
		try {
			Connection conn=null;
			
			Context context=new InitialContext();
			DataSource ds=(DataSource)context.lookup("java:/comp/env/jdbc/bank");
			conn=ds.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(StaffReviewPath);
			ResultSet rs=pstmt.executeQuery();
			
			if(rs == null) {
				return staffReviewStatus;
			}else {
				staffReviewStatus = true;
			}
			
			//將資料數據庫資料讀取出來，並先加入在createApplicationMemberList當中
			while(rs.next()) {
				CreateApplication_Beam createApplication=new CreateApplication_Beam();
				createApplication.setUsername(rs.getString("username"));
				createApplication.setApplicationStatus(rs.getString("applicationStatus"));
				createApplicationMemberList.add(createApplication);
			}
			
			//
			ArrayList<String> applicationInformationList = new ArrayList<String>();
			for(CreateApplication_Beam createApplication_Beam : createApplicationMemberList) {
				String username=createApplication_Beam.getUsername();
				String applicationStatus=createApplication_Beam.getApplicationStatus();
				String applicationInformation =String.format(
						"<tr>"
						+"<td>%s</td>"
						+"<td>%s</td>"
						+"<td></td>"
						+ "</tr>"
					
						, username,applicationStatus);
			//	request.setAttribute("username", username);
			//	request.setAttribute("applicationStatus", applicationStatus);
//			request.setAttribute("applicationInformation", applicationInformation);
				
				applicationInformationList.add(applicationInformation);
			}
			request.setAttribute("applicationInformationList", applicationInformationList);
			

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return staffReviewStatus;
		
		
	}

}
