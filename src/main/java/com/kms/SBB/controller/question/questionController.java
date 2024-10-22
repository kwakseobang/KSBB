package com.kms.SBB.controller.question;

import com.kms.SBB.AnswerForm;
import com.kms.SBB.entitiy.question.Question;
import com.kms.SBB.service.QuestionService;
import com.kms.SBB.QuestionForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class questionController {
    // @RequiredArgsConstructor는 롬복(Lombok)이 제공하는 애너테이션
    // final이 붙은 속성을 포함하는 생성자를 자동으로 만들어 주는 역할을 한다
    // 내부적으로 QuestionController를 생성할 때 롬복으로 만들어진 생성자에 의해 questionRepository 객체가 자동으로 주입된다
    private final QuestionService questionService;

    // 매개변수로 Model 지정하면 객체가 자동으로 생성된다
    @GetMapping("/list")
    public String list(Model model) {
        //model 객체는 자바 클래스(Java class)와 템플릿(template) 간의 연결 고리
        //질문 목록 다 가져오기
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    //요청한 URL인 http://localhost:8080/question/detail/2의 숫자 2처럼 변하는
    // id값을 얻을 때에는 @PathVariable 애너테이션을 사용한다
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }


    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    // @Valid 애너테이션을 적용하면 QuestionForm의 설정한 검증 기능이 동작한다.
    // 그리고 이어지는 BindingResult 매개변수는 @Valid 애너테이션으로 검증이 수행된 결과를 의미하는 객체이다.
    //BindingResult 매개변수는 항상 @Valid 매개변수 바로 뒤에 위치해야 한다.
    @PostMapping("/create")
    public String questionCreate(
            @Valid QuestionForm questionForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        this.questionService.createQuestion(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }
}
