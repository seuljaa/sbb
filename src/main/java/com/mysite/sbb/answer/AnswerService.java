package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mysite.sbb.question.QuestionDto;
import com.mysite.sbb.user.SiteUserDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	private final AnswerRepository answerRepository;
	private final ModelMapper modelMapper;
	
	private Answer of(AnswerDto answerDto) {
		return modelMapper.map(answerDto, Answer.class);
	}
	
	public AnswerDto create(QuestionDto questionDto, String content, SiteUserDto author) {
		AnswerDto answerDto = new AnswerDto();
		answerDto.setContent(content);
		answerDto.setCreateDate(LocalDateTime.now());
		answerDto.setQuestion(questionDto);
		answerDto.setAuthor(author);
		Answer answer = of(answerDto);
		this.answerRepository.save(answer);
		return answerDto;
		
	}
}
