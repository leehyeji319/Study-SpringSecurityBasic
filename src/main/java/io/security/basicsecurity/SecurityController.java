package io.security.basicsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *packageName    : io.security.basicsecurity
 * fileName       : SecurityController
 * author         : modsiw
 * date           : 2023/12/31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/12/31        modsiw       최초 생성
 */
@RestController
public class SecurityController {

	@GetMapping("/")
	public String index() {
		return "home";
	}

	@GetMapping("loginPage")
	public String loginPage() {
		return "loginPage";
	}
}
