package nz.ac.auckland;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchEngine {
    private APICommunicator apiCommunicator;
    private List<Document> searchResults;

    public SearchEngine(APICommunicator apiCommunicator){
        this.apiCommunicator = apiCommunicator;
    }

    public void labelCategories(){
        Set<Category> categories = getResultCategories();
        for(Category c : categories){
            for(Document d : c.getDocuments()) {
                for (Keyword k : d.getKeywords()) {
                    if(!c.categoryLabel.getKeywords().contains(k.word)) {
                        c.categoryLabel.addKeyword(k);
                    }
                }
            }
            c.categoryLabel.formLabel();
        }
    }

    public void searchAndProcess(List<Keyword> keywords){
        searchResults = apiCommunicator.search(keywords);
        if(searchResults.size() < 2){
            // specifications say 'a set of documents are expected to be returned' which I assume to be at least 2
            throw new RuntimeException("Search must return a set of documents");
        }
    }

    public List<Document> getSearchResults() {
        return searchResults;
    }

    public Set<Category> getResultCategories() {
            Set<Category> categories = new HashSet<Category>();
            for (Document d : searchResults) {
                if (d.getCategory() == null) {
                    throw new RuntimeException("Every document must be clustered into a category");
                }
                categories.add(d.getCategory());
                d.getCategory().addDocument(d);
            }
            return categories;
    }
}
