package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

	private final QuestionService questionService;

	@RequestMapping("/list")
	public String list(Model model) {
		List<QuestionDto> questionList = this.questionService.getList();
		model.addAttribute("questionList", questionList);
		return "question_list";
	}

	@RequestMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		QuestionDto question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	@GetMapping("/create")
	public String questionCreate() {
		return "question_form";
	}
	
	@PostMapping("/create")
	public String questionCreate(@RequestParam String subject, @RequestParam String content) {
		this.questionService.create(subject, content);
		return "redirect:/question/list";
	}
	
}