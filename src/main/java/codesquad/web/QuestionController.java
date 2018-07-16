package codesquad.web;

import codesquad.domain.Question;
import codesquad.domain.User;
import codesquad.security.LoginUser;
import codesquad.service.QnaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/questions")
public class QuestionController {
    @Resource(name = "qnaService")
    QnaService qnaService;

    @PostMapping("")
    public String create(Question question, @LoginUser User loginUser) {
        qnaService.create(loginUser, question);
        return "redirect:/";
    }
}
