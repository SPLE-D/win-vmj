package Event.review.anonymousreview.service;

import java.util.*;
import java.lang.*;

import Event.review.core.service.ReviewServiceDecorator;
import Event.review.core.service.ReviewServiceComponent;
import Event.review.core.model.Review;
import Event.review.ReviewFactory;

public class ReviewServiceImpl extends ReviewServiceDecorator {
    public ReviewServiceImpl (ReviewServiceComponent record) {
        super(record);
    }

 	public Review createReview(Map<String, Object> requestBody){
		boolean anonymous = parseBooleanValue(requestBody.get("anonymous"));
		Review reviewanonymousreview = record.createReview(requestBody);
		Review reviewanonymousreviewdeco = ReviewFactory.createReview("Event.review.anonymousreview.model.ReviewImpl", reviewanonymousreview, anonymous);
		Repository.saveObject(reviewanonymousreviewdeco);
		return reviewanonymousreviewdeco;
	}

	public Review createReview(Map<String, Object> requestBody, int id){
		boolean anonymous = parseBooleanValue(requestBody.get("anonymous"));
		Review review = record.createReview(requestBody, id);
		Review reviewanonymousreview = ReviewFactory.createReview("Event.review.anonymousreview.model.ReviewImpl", review, anonymous);
		Repository.saveObject(reviewanonymousreview);
		return reviewanonymousreview;
	}

    public HashMap<String, Object> updateReview(Map<String, Object> requestBody){
		int id = parseIntValue(requestBody.get("reviewId"), "reviewId");
		Event.review.anonymousreview.model.ReviewImpl reviewanonymousreview = getAnonymousReviewObjectById(id);

		reviewanonymousreview.setEventId(parseIntValue(requestBody.get("eventId"), "eventId"));
		reviewanonymousreview.setAttendeeId(parseIntValue(requestBody.get("attendeeId"), "attendeeId"));
		reviewanonymousreview.setRating(parseIntValue(requestBody.get("rating"), "rating"));
		reviewanonymousreview.setComment((String) requestBody.get("comment"));
		reviewanonymousreview.setAnonymous(parseBooleanValue(requestBody.get("anonymous")));

		Repository.updateObject(reviewanonymousreview);
		
		return reviewanonymousreview.toHashMap();
	}

	public HashMap<String, Object> getReview(String idStr){
		int id = parseIntValue(idStr, "reviewId");
		Review reviewanonymousreview = getAnonymousReviewObjectById(id);
		return reviewanonymousreview.toHashMap();
	}

	public HashMap<String, Object> getReviewById(int id){
		List<HashMap<String, Object>> reviewList = getAllReview();
		for (HashMap<String, Object> review : reviewList){
			int review_id = ((Number) review.get("reviewId")).intValue();
			if (review_id == id){
				return review;
			}
		}
		return null;
	}

    public List<HashMap<String,Object>> getAllReview(){
		List<Review> List = Repository.getAllObject("review_anonymousreview");
		return transformListToHashMap(List);
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<Review> List){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < List.size(); i++) {
            resultList.add(List.get(i).toHashMap());
        }

        return resultList;
	}

    public List<HashMap<String,Object>> deleteReview(Map<String, Object> requestBody){
		record.deleteReview(requestBody);
		return getAllReview();
	}

	private Event.review.anonymousreview.model.ReviewImpl getAnonymousReviewObjectById(int id) {
		List<Review> list = Repository.getAllObject("review_anonymousreview");

		for (Review review : list) {
			if (review.getReviewId() == id) {
				return (Event.review.anonymousreview.model.ReviewImpl) review;
			}
		}

		throw new IllegalArgumentException("AnonymousReview not found for reviewId: " + id);
	}

	private int parseIntValue(Object value, String fieldName) {
		if (value == null) {
			throw new IllegalArgumentException(fieldName + " is required");
		}

		if (value instanceof Number) {
			return ((Number) value).intValue();
		}

		if (value instanceof String) {
			return Integer.parseInt((String) value);
		}

		throw new IllegalArgumentException("Invalid integer value for " + fieldName + ": " + value);
	}

	private boolean parseBooleanValue(Object value) {
		if (value == null) {
			throw new IllegalArgumentException("anonymous is required");
		}

		if (value instanceof Boolean) {
			return (Boolean) value;
		}

		if (value instanceof String) {
			return Boolean.parseBoolean((String) value);
		}

		throw new IllegalArgumentException("Invalid boolean value for anonymous: " + value);
	}
	
}
