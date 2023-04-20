package idusw.springboot.boardjhm.controller;

import idusw.springboot.boardjhm.domain.Member;
import idusw.springboot.boardjhm.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {
    // 생성자 주입
    MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String getLoginForm() {
        // memberService.toString();
        return "/members/login";
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
