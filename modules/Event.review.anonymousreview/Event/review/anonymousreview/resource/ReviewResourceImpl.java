package Event.review.anonymousreview.resource;
import java.util.*;
import java.lang.*;

import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;
import id.ac.ui.cs.prices.winvmj.core.exceptions.*;

import Event.review.core.resource.ReviewResourceDecorator;
import Event.review.core.resource.ReviewResourceComponent;
import Event.review.core.model.Review;
import Event.review.core.model.ReviewImpl;
import Event.review.core.service.ReviewServiceComponent;
import Event.review.anonymousreview.service.ReviewServiceImpl;

public class ReviewResourceImpl extends ReviewResourceDecorator {
	private ReviewServiceComponent reviewanonymousreviewServiceImpl;

    public ReviewResourceImpl (ReviewResourceComponent record, ReviewServiceComponent recordService) {
        super(record);
		this.reviewanonymousreviewServiceImpl = new ReviewServiceImpl(recordService);
    }

    
    @Route(url="call/anonymousreview/save")
    public List<HashMap<String,Object>> saveReview(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		Review reviewanonymousreview = createReview(vmjExchange);
		return getAllReview(vmjExchange);
	}

    public Review createReview(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			Review result = reviewanonymousreviewServiceImpl.createReview(requestBody);
			return result;
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

    public Review createReview(VMJExchange vmjExchange, int id){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			Review result = reviewanonymousreviewServiceImpl.createReview(requestBody, id);
			return result;
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

	
    @Route(url="call/anonymousreview/update")
    public HashMap<String, Object> updateReview(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")){
			return null;
		}

		Map<String, Object> requestBody = vmjExchange.getPayload();
		return reviewanonymousreviewServiceImpl.updateReview(requestBody);
	}

	
    @Route(url="call/anonymousreview/detail")
    public HashMap<String, Object> getReview(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}

		String idStr = vmjExchange.getGETParam("reviewId");
		return reviewanonymousreviewServiceImpl.getReview(idStr);
	}

	
    @Route(url="call/anonymousreview/list")
    public List<HashMap<String,Object>> getAllReview(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}

		return reviewanonymousreviewServiceImpl.getAllReview();
	}

    public List<HashMap<String,Object>> transformReviewListToHashMap(List<Review> ReviewAnonymousReviewList){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < ReviewAnonymousReviewList.size(); i++) {
            resultList.add(ReviewAnonymousReviewList.get(i).toHashMap());
        }

        return resultList;
	}

	
    @Route(url="call/anonymousreview/delete")
    public List<HashMap<String,Object>> deleteReview(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}

		Map<String, Object> requestBody = vmjExchange.getPayload();
		
		return reviewanonymousreviewServiceImpl.deleteReview(requestBody);
	}

	
}
