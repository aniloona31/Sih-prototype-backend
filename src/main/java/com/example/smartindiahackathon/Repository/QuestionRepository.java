package com.example.smartindiahackathon.Repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.smartindiahackathon.Model.Question;
import com.example.smartindiahackathon.Model.User;



@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	List<Question> findByUser(User user);

	List<Question> findByResolved(boolean b);

	Question getByQuestion(String question);

}
