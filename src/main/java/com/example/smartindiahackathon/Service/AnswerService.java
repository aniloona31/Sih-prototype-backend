package com.example.smartindiahackathon.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.smartindiahackathon.Dto.AnswerDto;
import com.example.smartindiahackathon.Dto.AnswerRes;
import com.example.smartindiahackathon.Exception.SpringException;
import com.example.smartindiahackathon.Model.Answer;
import com.example.smartindiahackathon.Model.Question;
import com.example.smartindiahackathon.Model.User;
import com.example.smartindiahackathon.Repository.AnswerRepository;
import com.example.smartindiahackathon.Repository.QuestionRepository;
import com.example.smartindiahackathon.Repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AnswerService {

	private final AnswerRepository answerRepository;
	private final QuestionRepository questionRepository;
	private final UserRepository userRepository;
	
	public void postAnswer(AnswerDto answerDto) {
		Question question = questionRepository.findById(answerDto.getQuestionId()).orElseThrow(()-> new SpringException("question not found"));
		User user = userRepository.findByUsername(answerDto.getUsername()).orElseThrow(()-> new SpringException("user not found..."));
		answerRepository.save(MapAnswerDtoToAnswer(answerDto,question,user));
	}
	
	private Answer MapAnswerDtoToAnswer(AnswerDto answerDto,Question question,User user) {
		return Answer.builder()
		.answer(answerDto.getAnswer())
		.user(user)
		.question(question)
		.build();
	}

	private AnswerRes MapAnswerToAnswerDto(Answer answer) {
		return AnswerRes.builder()
				.answer(answer.getAnswer())
				.answerId(answer.getAnswerId())
				.username(answer.getUser().getUsername())
				.build();
	}
	public List<AnswerRes> getAnswers(Integer questionId) {
		Question question = questionRepository.findById(questionId).orElseThrow(()-> new SpringException("Question doesn't exist"));
		return answerRepository.getByQuestion(question)
				.stream()
				.map(this :: MapAnswerToAnswerDto)
				.collect(Collectors.toList());
	}
}
