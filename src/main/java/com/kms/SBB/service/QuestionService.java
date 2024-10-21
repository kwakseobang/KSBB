package com.kms.SBB.service;


import com.kms.SBB.entitiy.question.Question;
import com.kms.SBB.repository.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionService {
    // dto를 사용하지 않아 repository에 바로 접근
    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }
}
