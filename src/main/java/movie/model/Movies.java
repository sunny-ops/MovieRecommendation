package movie.model;

//import java.util.Date;
import java.sql.Date;

public class Movies {
	protected int movieId;
	protected String posterPath;
	protected String backdropPath;
	protected String backupPath;
	protected String homepage;
	protected int tmdbId;
	protected String language;
	protected String originalTitle;
	protected String overview;
	protected double popularity;
	protected Date releaseDate;
	protected double runtime;
	protected String status;
	protected String tagline;
	protected double voteAverage;
	protected int voteCount;
	
	
	
	public Movies(int movieId, String posterPath, String backdropPath, String backupPath, String homepage, int tmdbId,
			String language, String originalTitle, String overview, double popularity, Date releaseDate, double runtime,
			String status, String tagline, double voteAverage, int voteCount) {
		this.movieId = movieId;
		this.posterPath = posterPath;
		this.backdropPath = backdropPath;
		this.backupPath = backupPath;
		this.homepage = homepage;
		this.tmdbId = tmdbId;
		this.language = language;
		this.originalTitle = originalTitle;
		this.overview = overview;
		this.popularity = popularity;
		this.releaseDate = releaseDate;
		this.runtime = runtime;
		this.status = status;
		this.tagline = tagline;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
	}
	
	

	
	public Movies(int tmdbId, String language, String originalTitle, String overview, Date date,
			double runtime, String status) {
		this.tmdbId = tmdbId;
		this.language = language;
		this.originalTitle = originalTitle;
		this.overview = overview;
		this.releaseDate = date;
		this.runtime = runtime;
		this.status = status;
	}


	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getPosterPath() {
		return posterPath;
	}
	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
	public String getBackdropPath() {
		return backdropPath;
	}
	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}
	public String getBackupPath() {
		return backupPath;
	}
	public void setBackupPath(String backupPath) {
		this.backupPath = backupPath;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public int getTmdbId() {
		return tmdbId;
	}
	public void setTmdbId(int tmdbId) {
		this.tmdbId = tmdbId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getOriginalTitle() {
		return originalTitle;
	}
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public double getPopularity() {
		return popularity;
	}
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public double getRuntime() {
		return runtime;
	}
	public void setRuntime(double runtime) {
		this.runtime = runtime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public double getVoteAverage() {
		return voteAverage;
	}
	public void setVoteAverage(double voteAverage) {
		this.voteAverage = voteAverage;
	}
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	
	
	
}