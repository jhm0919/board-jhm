package idusw.springboot.boardjhm.controller;

import idusw.springboot.boardjhm.domain.Memo;
import idusw.springboot.boardjhm.service.MemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/memo")
public class MemoController {
    // 생성자 주입 (Constructor DI) vs IoC(Inversion of Control) 기법 중 하나가 DI, DL ...
    MemoService memoService;
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }
    @GetMapping("/tables")
    public String getTables() {
        return "/memo/tables";
    }

    @GetMapping("/init")
    public String initialize(Model model) {
        List<Memo> result = new ArrayList<>();
        result = memoService.initialize();
        model.addAttribute("attr", result);
        return "/memo/list";
    }

    @GetMapping("/")
    public String getList(Model model) {
        List<Memo> result = new ArrayList<>();
        result = memoService.readList(); // 여기를 수정함
        model.addAttribute("attr", result);
        return "/memo/list";
    }

    @GetMapping("//{mno}")
    public String getList(@PathVariable("mno") Long mno, Model model) {
        Memo result = new Memo();
        Memo m = new Memo();
        m.setMno(mno);
        result = memoService.read(m); // 여기를 수정함
        model.addAttribute("attr", result);
        return "/memo/one";
    }

}
