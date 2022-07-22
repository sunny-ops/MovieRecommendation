package movie.model;

//import java.sql.Date;
import java.util.Date;

public class Rate {
    protected Integer RateID;
    protected Users User;
    protected Movies movie;
    protected Double Rate;
    protected Date date;

    public Rate(Integer rateID, Users user, Movies movie, Double rate, Date date) {
        RateID = rateID;
        User = user;
        this.movie = movie;
        Rate = rate;
        this.date = date;
    }

    public Rate(Integer rateID) {
        RateID = rateID;
    }

    public Rate(Users user, Movies movie, Double rate, Date date) {
        User = user;
        this.movie = movie;
        Rate = rate;
        this.date = date;
    }

    public Integer getRateID() {
        return RateID;
    }

    public Users getUser() {
        return User;
    }

    public Movies getMovie() {
        return movie;
    }

    public Double getRate() {
        return Rate;
    }

    public Date getDate() {
        return date;
    }

    public void setRateID(Integer rateID) {
        RateID = rateID;
    }

    public void setUser(Users user) {
        User = user;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public void setRate(Double rate) {
        Rate = rate;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "RateID=" + RateID +
                ", User=" + User.getUserId() +
                ", movie=" + movie.getMovieId() +
                ", Rate=" + Rate +
                ", date=" + date +
                '}';
    }
}
