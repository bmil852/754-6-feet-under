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
        if(searchResults.size() < 2){
            // specifications say 'a set of documents are expected to be returned' which I assume to be at least 2
            throw new RuntimeException("search must return a set of documents");
        }
    }

    public List<Document> getSearchResults() {
        return searchResults;
    }
}
