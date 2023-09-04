package servlet;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserData;
import entity.User;
import utils.ResponseUtil;

//아이디 중복확인
@WebServlet("/auth/signup/duplicate/username")
public class DuplicateUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//getParameter로 username 받아옴 
		String username = request.getParameter("username");
		Boolean responseData = false;

		// user 에서 하나씩 꺼내는 기능 , 400이면 중복됐다? 
		// 400으로 응답하는게 아니라 200 으로 응답함 (정상적으로 됐으니까/?? )
		for(User user : UserData.userList) {
			if (Objects.equals(user.getUsername(), username)) {
//				ResponseUtil.response(response).of(200).body(true);
//				return;
				responseData = true;
				break;
			}
		}
		
		
		ResponseUtil.response(response).of(200).body(responseData); 
		//응답
		
	}
}
