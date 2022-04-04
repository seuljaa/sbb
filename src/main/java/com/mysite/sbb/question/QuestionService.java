package com.mysite.sbb.question;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 필드값만 파라미터로 받는 생성자를 자동으로 만들어줌
@Service
public class QuestionService {
	private final QuestionRepository questionRepository;
	private final ModelMapper modelMapper;
	
	private QuestionDto of(Question question) {
		return modelMapper.map(question, QuestionDto.class);
	}
	
	public List<QuestionDto> getList(){
		List<Question> questionList = this.questionRepository.findAll();
		List<QuestionDto> questionDtoList = questionList.stream().map(q -> of(q)).toList();
		return questionDtoList;
	}
}
