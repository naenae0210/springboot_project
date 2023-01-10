package joinweb.join.controller;

import joinweb.join.domain.Member;
import joinweb.join.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final MemberRepository memberRepository;

    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "home"; // home.html로 찾아감
    }

    @GetMapping("/")
    public String homeLogin(
            @CookieValue(name = "memberId", required = false) Long memberId,
            Model model) {
        if (memberId == null) {
            return "home";
        }
        //로그인
        Member loginMember = memberRepository.findOne(memberId);
        if (loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
