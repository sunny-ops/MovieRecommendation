package movie.model;

public class Recommendations {
    protected Integer RecommendationID;
    protected Users User;
    protected Movies movie;

    public Recommendations(Integer recommendationID, Users user, Movies movie) {
        RecommendationID = recommendationID;
        User = user;
        this.movie = movie;
    }

    public Recommendations(Integer recommendationID) {
        RecommendationID = recommendationID;
    }

    public Recommendations(Users user, Movies movie) {
        User = user;
        this.movie = movie;
    }

    public Integer getRecommendationID() {
        return RecommendationID;
    }

    public Users getUser() {
        return User;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setRecommendationID(Integer recommendationID) {
        RecommendationID = recommendationID;
    }

    public void setUser(Users user) {
        User = user;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }
}
