package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import com.mysite.sbb.answer.AnswerDto;
import com.mysite.sbb.user.SiteUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDto {
	private Integer id;
	private String subject;
	private String content;
	private List<AnswerDto> answerList;
	private LocalDateTime createDate;
	private SiteUser author;
}
