package com.example.smartindiahackathon.Repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.smartindiahackathon.Model.Place;
import com.example.smartindiahackathon.Model.User;
import com.example.smartindiahackathon.Model.Vote;



@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer>{

	List<Vote> getByUser(User user);

	Vote getByPlaceAndUser(Place place, User user);

}
