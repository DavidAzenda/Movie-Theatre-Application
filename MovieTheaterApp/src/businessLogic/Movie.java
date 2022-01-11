package businessLogic;
import java.time.LocalDateTime;
import java.util.HashSet;

public class Movie {
	private String title;
	private int movieId;
	private String news;
	private boolean earlyRelease;

	public Movie(String title, int movieId, String news, boolean earlyRelease) {
		this.title = title;
		this.movieId = movieId;
		this.news = news;
		this.earlyRelease = earlyRelease;
	}

	public Movie() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public boolean isEarlyRelease() {
		return earlyRelease;
	}

	public void setEarlyRelease(boolean earlyRelease) {
		this.earlyRelease = earlyRelease;
	}

	@Override
	public String toString() {
		return "title:" + title +
				"\nmovieId:" + movieId +
				"\nnews:'" + news +
				"\nearlyRelease:" + earlyRelease;
	}
}

