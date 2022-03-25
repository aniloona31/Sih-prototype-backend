package com.example.smartindiahackathon.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.smartindiahackathon.Model.Photos;
import com.example.smartindiahackathon.Model.Place;

//import com.PlaceFinder.CollegeProject.Model.Photos;
//import com.PlaceFinder.CollegeProject.Model.Place;
@Repository
public interface PhotosRepository extends JpaRepository<Photos, Integer>{

	List<Photos> getByPlace(Place place);

}
