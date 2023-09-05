package security;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class SecurityContextHolder {

	public static List<Authentication> authentications = new ArrayList<>();

	public static void addAuth(Authentication authentication) {
		authentications.add(authentication);
	}
	
	// 토큰을 받아서 인증되었는지 확인하는 작업
	public static Boolean isAuthenticted(String token) {
		for(Authentication authentication : authentications) {
			if (Objects.equals(authentication.getToken(),token)) {
				return true;
			}
		}
		return false;
	}
	
	public static Authentication findAuthenticationByToken(String token) {
		for(Authentication authentication : authentications) {
			if (Objects.equals(authentication.getToken(),token)) {
				return authentication;
			}
		}
		return null;
	}
	
	public static void removeAuth(String token) {
		for(Authentication authentication : authentications) {
			if (Objects.equals(authentication.getToken(),token)) {
				authentications.remove(authentication);
				break;
			}
		}
	}
	
}
