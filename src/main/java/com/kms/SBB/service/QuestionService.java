package com.kms.SBB.service;


import com.kms.SBB.entitiy.question.Question;
import com.kms.SBB.exception.DataNotFoundException;
import com.kms.SBB.repository.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    // dto를 사용하지 않아 repository에 바로 접근
    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }
    //질문 데이터 조회
    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question is Not Found..");
        }
    }
}
