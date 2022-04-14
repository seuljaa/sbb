package com.mysite.sbb.answer;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.question.QuestionDto;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUserDto;
import com.mysite.sbb.user.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/create/{id}")
	public String answerCreate(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
		QuestionDto questionDto = this.questionService.getQuestion(id);
		SiteUserDto siteUserDto = this.userService.getUser(principal.getName());
		if( bindingResult.hasErrors() ) {
			model.addAttribute("question", questionDto);
			return "question_detail";
		}
		this.answerService.create(questionDto, answerForm.getContent(), siteUserDto);
		return String.format("redirect:/question/detail/%s", id);
	}
}