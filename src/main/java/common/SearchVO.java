package common;

public class SearchVO {
	private String searchType;
	private String searchWord;
	public SearchVO() {
	}
	public SearchVO(String searchType, String searchWord) {
		this.searchType = searchType;
		this.searchWord = searchWord;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	@Override
	public String toString() {
		return "SearchVO [searchType=" + searchType + ", searchWord=" + searchWord + "]";
	}
	
}
