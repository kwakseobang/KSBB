package com.kms.SBB.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne //N:1 관계  부모 자식 관계를 갖는 구조에서 사용한다. 여기서 부모는 Question, 자식은 Answer
    private Question question; // 질문 entity를 참조하기 위해서.
    //question 속성만 추가하면 안 되고 질문 엔티티와 연결된 속성이라는 것을 답변 엔티티에 표시해야 한다.
    //Answer 엔티티의 question 속성에 @ManyToOne 애너테이션을 추가해 질문 엔티티와 연결한다.
    // 실제 데이터베이스에서는 외래키(foreign key) 관계가 생성된다.
}