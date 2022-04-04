package com.mysite.sbb.answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.question.QuestionDto;
import com.mysite.sbb.question.QuestionService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;
	
	@RequestMapping("/create/{id}")
	public String answerCreate(Model model, @PathVariable("id") Integer id, @RequestParam String content) {
		QuestionDto questionDto = this.questionService.getQuestion(id);
		this.answerService.create(questionDto, content);
		return String.format("redirect:/question/detail/%s", id);
	}
}