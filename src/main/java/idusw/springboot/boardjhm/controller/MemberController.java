package idusw.springboot.boardjhm.controller;

import idusw.springboot.boardjhm.domain.Member;
import idusw.springboot.boardjhm.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {
    // 생성자 주입
    MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    private HttpSession session;
    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("member", Member.builder().build()); // 폼에서의 요청을 전달할 DTO 객체를 생성
        // memberService.toString();
        return "/members/login";
    }

    @PostMapping("/login")
    public String loginMember(@ModelAttribute("member") Member m, Model model, HttpServletRequest request) {
        // @ModelAttribute : 요청으로 전달된 객체 (폼에서 입력한 정보를 갖는), email, pw
        Member result = null;
        if((result = memberService.login(m)) != null) {
            session = request.getSession();
            session.setAttribute("mb", result);
            return "redirect:/"; // 재지정(redirection)
        }
        else
            return "redirect:/members/register";
    }

    @GetMapping("/logout")
    public String logoutMember() {
        session.invalidate(); // session 객체 무효화, 저장된 속성도 사라짐
        return "redirect:/";
    }

    @GetMapping("/")
    public String getMemberList(Model model) {
        // 1. 매개변수를 받아 기본 작업을 하고, 2. 서비스에게 요청을 전달 - readList() 가 처리 후 반환, 3. 결과를 view에 전달
        List<Member> memberList = new ArrayList<>(); // 결과를 받을 객체
        if((memberList = memberService.readList()) != null) {
            model.addAttribute("list", memberList);
            return "/members/list";
        } else {
            model.addAttribute("error message", "목록 조회에 실패. 권환 확인");
            return "/error/message";
        }
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        // Member 형의 객체를 생성하고,
        model.addAttribute("member", Member.builder().build());
        return "/members/register"; // register.html, view resolving
    }

    @PostMapping("/register")
    public String registerMember(@ModelAttribute("member") Member m, Model model) {
        if(memberService.create(m) > 0)
            return "redirect:/members/login"; // 홈으로 재지정함 /admin/index.html : 컨트롤러에게 재지정
        else
            return "redirect:/members/register";
    }

    @GetMapping("/forgot")
    public String getForgotForm() {
        // memberService.toString();
        return "/members/forgot-password";
    }


//    @GetMapping
//    public String goHome() { // /admin/
//        System.out.println("root");
//        return "redirect:/admin/";
//    }

//    @GetMapping("404")
//    public String get404() {
//        return "/404";
//    }
//
//    @GetMapping("blank")
//    public String getBlank() {
//        return "/blank";
//    }
//
//    @GetMapping("charts")
//    public String getCharts() {
//        return "/charts";
//    }
//
//    @GetMapping("forgot-password")
//    public String getForgotPassword() {
//        return "/forgot-password";
//    }
//
//    @GetMapping("login")
//    public String getLogin() {
//        return "/login";
//    }
//
//    @GetMapping("register")
//    public String getRegister() {
//        return "/register";
//    }
//
//    @GetMapping("utilities-animation")
//    public String getUtilitiesAnimation() {
//        return "/utilities-animation";
//    }
//
//    @GetMapping("utilities-border")
//    public String getUtilitiesBorder() {
//        return "/utilities-border";
//    }
//
//    @GetMapping("utilities-color")
//    public String getUtilitiesColor() {
//        return "/utilities-color";
//    }
//
//    @GetMapping("utilities-other")
//    public String getUtilitiesOther() {
//        return "/utilities-other";
//    }
}
