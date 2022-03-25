package com.example.smartindiahackathon.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.smartindiahackathon.Dto.ReviewDto;
import com.example.smartindiahackathon.Dto.ReviewRequest;
import com.example.smartindiahackathon.Exception.SpringException;
import com.example.smartindiahackathon.Model.Place;
import com.example.smartindiahackathon.Model.Review;
import com.example.smartindiahackathon.Model.User;
import com.example.smartindiahackathon.Repository.PlaceRepository;
import com.example.smartindiahackathon.Repository.ReviewRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class ReviewService {

	private final PlaceRepository placeRepository;
	private final AuthService authService;
	private final ReviewRepository reviewRepository;
	
	
	public ReviewDto save(ReviewRequest reviewDto) throws SpringException{
		Place place = placeRepository.getById(reviewDto.getPlaceId());
		if(place == null) {
			throw new SpringException("place not found");
		}
		User user = authService.getCurrentUser();
		if(user == null) {
			throw new SpringException("user is not logged in");
		}
		Review review = MapRequestToReview(reviewDto,user,place);
		reviewRepository.save(review);
		
		return MapReviewToDto(review);
	}
	
	private Review MapRequestToReview(ReviewRequest reviewDto,User user,Place place) {
		return Review.builder()
				.createdDate(Instant.now())
				.place(place)
				.user(user)
				.text(reviewDto.getReview())
				.build();
	}
	
	private ReviewDto MapReviewToDto(Review review) {
		return ReviewDto.builder()
				.createdDate(review.getCreatedDate())
				.placeId(review.getPlace().getPlaceId())
				.username(review.getUser().getUsername())
				.review(review.getText())
				.reviewId(review.getReviewId())
				.build();
	}
	
	public List<ReviewDto> getReviewsOfPlace(Integer placeId) throws SpringException{
		Optional<Place> place = placeRepository.findById(placeId);
		place.orElseThrow(() -> new SpringException("No such place found"));
		
		return reviewRepository.findByPlace(place.get())
				.stream()
				.map(this::MapReviewToDto)
				.collect(Collectors.toList());
	}
	
	public ReviewDto getReview(Integer reviewId) {
		return MapReviewToDto(reviewRepository.findById(reviewId).orElseThrow(()-> new SpringException("Review Not Found...")));
	}
	
	public void deleteReview(Integer reviewId) throws SpringException{
		reviewRepository.findById(reviewId).orElseThrow(()-> new SpringException("review not found"));
		reviewRepository.deleteById(reviewId);
	}
}
