package review.model;

import java.math.BigDecimal;
import java.util.Date;


public class Reviews {
	protected int reviewId;
	protected Date created;
	protected String content;
	protected BigDecimal rating;
	protected String userName;
	protected int restaurantId;
	
	
	
	public Reviews(int reviewId, Date created, String content, BigDecimal rating, String userName, int restaurantId) {
		this.reviewId = reviewId;
		this.created = created;
		this.content = content;
		this.rating = rating;
		this.userName = userName;
		this.restaurantId = restaurantId;
	}
	
	public Reviews(String content, BigDecimal rating, String userName, int restaurantId) {
		this.content = content;
		this.rating = rating;
		this.userName = userName;
		this.restaurantId = restaurantId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	
}