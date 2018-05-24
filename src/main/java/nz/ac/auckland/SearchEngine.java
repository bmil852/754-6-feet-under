package nz.ac.auckland;

import java.util.List;

public class SearchEngine {
    private APICommunicator apiCommunicator;
    private List<Document> searchResults;

    public SearchEngine(APICommunicator apiCommunicator){
        this.apiCommunicator = apiCommunicator;
    }

    public void searchAndProcess(List<Keyword> keywords){
        searchResults = apiCommunicator.search(keywords);
    }

    public List<Document> getSearchResults() {
        return searchResults;
    }
}
