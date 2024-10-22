package com.kms.SBB.controller.question;

import com.kms.SBB.entitiy.question.Question;
import com.kms.SBB.repository.question.QuestionRepository;
import com.kms.SBB.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class questionController {
    // @RequiredArgsConstructor는 롬복(Lombok)이 제공하는 애너테이션
    // final이 붙은 속성을 포함하는 생성자를 자동으로 만들어 주는 역할을 한다
    // 내부적으로 QuestionController를 생성할 때 롬복으로 만들어진 생성자에 의해 questionRepository 객체가 자동으로 주입된다
    private final QuestionService questionService;

    // 매개변수로 Model 지정하면 객체가 자동으로 생성된다
    @GetMapping("/question/list")
    public String list(Model model) {
        //model 객체는 자바 클래스(Java class)와 템플릿(template) 간의 연결 고리
        //질문 목록 다 가져오기
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList",questionList);
        return "question_list";
    }
    //요청한 URL인 http://localhost:8080/question/detail/2의 숫자 2처럼 변하는
    // id값을 얻을 때에는 @PathVariable 애너테이션을 사용한다
    @GetMapping(value = "/question/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question",question);
        return "question_detail";
    }
}
