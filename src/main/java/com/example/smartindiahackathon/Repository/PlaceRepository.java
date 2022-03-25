package com.example.smartindiahackathon.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.smartindiahackathon.Model.Place;
import com.example.smartindiahackathon.Model.State;

//import com.PlaceFinder.CollegeProject.Model.Place;
//import com.PlaceFinder.CollegeProject.Model.State;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer>{

	Optional<Place> findByPlaceName(String placeName);


	Place getByPlaceName(String placeName);


	List<Place> getByState(State state);



}
