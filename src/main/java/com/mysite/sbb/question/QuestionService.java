package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 필드값만 파라미터로 받는 생성자를 자동으로 만들어줌
@Service
public class QuestionService {
	private final QuestionRepository questionRepository;
	private final ModelMapper modelMapper;
	
	private QuestionDto of(Question question) {
		return modelMapper.map(question, QuestionDto.class);
	}
	
	public Page<QuestionDto> getList(int page){
		List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		// page값을 숫자로 받아와서 pageable의 조건을 정해준다.
		Page<Question> questionList = this.questionRepository.findAll(pageable);
		// questions 리포지터리에서 모든 값을 꺼내오는데 아까 정해둔 조건(limit)대로 꺼내 Page를 Question의 list형태로 만든다.
		Page<QuestionDto> questionDtoList = questionList.map(q -> of(q));
		// 컨트롤러에서는 Question을 쓰지않고 QuestionDto를 사용하기때문에 변환한후 return한다.
		return questionDtoList;
	}
	
	public QuestionDto getQuestion(Integer id) {
		Optional<Question> question  = this.questionRepository.findById(id);
		if (question.isPresent()) {
			return of(question.get());
		}
		else {
			throw new DataNotFoundException("question not found");
		} 
	}
	
	public QuestionDto create(String subject, String content) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q = this.questionRepository.save(q);
		return of(q);
	}
}
