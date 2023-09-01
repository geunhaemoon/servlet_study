package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.JsonParseUtil;
import utils.ResponseUtil;

/**
 설명
 
 회원가입 -> 사용자 정보 데이터의 추가를 의미
 추가 -> Create 데이터 베이스에 정보를 insert > Post 요청 사용 
 
 POST 메소드의 특징
 1. 요청시 서버로 전달되어지는 데이터가 주소창에 표시되지 않는다. 
 	>> GET : http://localhost:8080/category?categoryName=한식 (x) (이런식으로 표시 안됨)
 	>> POST : http://localhost:8080/category (BODY에 데이티를 담아서 서버로 전송)
 2. 전송 데이터의 용량 제한이 없다.
 
 
 * */


@WebServlet("/auth/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Map<String, Object> userMap = JsonParseUtil.toMap(request.getInputStream());
		
		System.out.println(userMap);

//		System.out.println(userMap.get("username"));
//		System.out.println(userMap.get("password"));
//		System.out.println(userMap.get("name"));
//		System.out.println(userMap.get("email"));
		
		System.out.println("회원가입");
		
		ResponseUtil.response(response).of(200).body("회원가입 성공 !!");
		
		
		
		
		
	
	}

}
