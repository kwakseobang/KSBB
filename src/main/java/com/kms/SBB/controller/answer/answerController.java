package com.kms.SBB.controller.answer;

import com.kms.SBB.AnswerForm;
import com.kms.SBB.entitiy.question.Question;
import com.kms.SBB.service.AnswerService;
import com.kms.SBB.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class answerController {

    private final QuestionService questionService;
    private final AnswerService answerService;


    //  @RequestParam(value = "content")는 (question_detail.html)에서 답변으로 입력한 내용(content)을 얻으려고 추가
    // 템플릿의 답변 내용에 해당하는 <textarea>의 name 속성명이 content이므로 여기서도 변수명을 content로 사용한다
    @PostMapping("/create/{id}")
    public String createAnswer(
            Model model,
            @PathVariable("id") Integer id,
            @Valid AnswerForm answerForm, BindingResult bindingResult
            ) {
        Question question = this.questionService.getQuestion(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        this.answerService.create(question, answerForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }

}
