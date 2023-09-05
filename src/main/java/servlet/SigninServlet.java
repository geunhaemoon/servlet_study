package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserData;
import entity.User;
import security.Authentication;
import security.SecurityContextHolder;
import utils.JsonParseUtil;
import utils.ResponseUtil;

@WebServlet("/auth/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//위에서 제이슨에서 받아와서 여기서 바꾸는 역할을 함...? 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> signinUser = JsonParseUtil.toMap(request.getInputStream());
//		Boolean responseData = false;
		
		Map<String, String> responseData = new HashMap<>();
		
		System.out.println(signinUser);
		
		for(User user : UserData.userList) {
			if(Objects.equals(user.getUsername(), signinUser.get("username"))
					&& Objects.equals(user.getPassword(), signinUser.get("password"))){
//				responseData = true;
				String token = UUID.randomUUID().toString(); // 로그인 될때마다 새로운 토큰을 만듬
				SecurityContextHolder.addAuth(new Authentication(user, token));
				responseData.put("token", token);
				break;
			}
		}
		
		//401 > 인증되지않았다
		ResponseUtil.response(response).of(200).body(JsonParseUtil.toJson(responseData));
	}

}
