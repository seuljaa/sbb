package com.mysite.sbb;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;

@SpringBootTest
class SbbApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("����1�Դϴ�.");
		q1.setContent("����1�Դϴ�.");
		q1.setCreateDate(LocalDateTime.now());
		questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("����2�Դϴ�.");
		q2.setContent("����2�Դϴ�.");
		q2.setCreateDate(LocalDateTime.now());
		questionRepository.save(q2);
	}
}
