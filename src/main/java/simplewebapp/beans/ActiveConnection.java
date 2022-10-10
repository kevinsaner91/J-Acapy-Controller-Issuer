package simplewebapp.beans;

import java.util.List;

import com.owlike.genson.annotation.JsonProperty;

public class ActiveConnection {

	private List<Result> results = null;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public ActiveConnection() {
	}

	/**
	 *
	 * @param results
	 */
	public ActiveConnection(@JsonProperty("results") List<Result> results) {
		this.results = results;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

}
