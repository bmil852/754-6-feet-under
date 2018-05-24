package nz.ac.auckland;

import java.util.List;

public class Search {
    APICommunicator _apiCommunicator;

    public Search(APICommunicator apiCommunicator){
        _apiCommunicator = apiCommunicator;
    }

    public List<Document> searchAndProcess(List<Keyword> keywords){
        return _apiCommunicator.search(keywords);
    }
}
