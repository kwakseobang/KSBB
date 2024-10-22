package com.kms.SBB.entitiy.question;

import com.kms.SBB.entitiy.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {

    @Id // id 속성을 기본키로 지정한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //   자동으로 1씩 증가하여 저장
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT") // columnDefinition은 열 데이터의 유형이나 성격을 정의할 때 사용한다. TEXT 글자 수 제한 X
    private String content;

    private LocalDateTime createDate;

    // 1: N 관계 답변이 여러개일수도있으므로 List , mappedBy = "question" 참조 엔티티의 속성명 정의
    // cascade = CascadeType.REMOVE 질문 삭제하면 해당 질문의 답변들도 다같이 삭제
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}