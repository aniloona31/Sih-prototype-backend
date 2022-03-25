package com.example.smartindiahackathon.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.smartindiahackathon.Model.Answer;
import com.example.smartindiahackathon.Model.Question;

//import com.PlaceFinder.CollegeProject.Model.Answer;
//import com.PlaceFinder.CollegeProject.Model.Question;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {

	List<Answer> getByQuestion(Question question);

}
