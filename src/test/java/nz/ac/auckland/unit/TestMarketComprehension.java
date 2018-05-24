package nz.ac.auckland.unit;

import nz.ac.auckland.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestMarketComprehension {
    SearchEngine _searchEngineAlgorithm;

    @Test
    public void check_if_documents_are_clustered_by_categories(){
        boolean hasCategory = true;
        //Given
        generate_mock_search_results_after_performing_search();
        _searchEngineAlgorithm.searchAndProcess(new ArrayList<Keyword>());
        List<Document> documents = _searchEngineAlgorithm.getSearchResults();

        //When
        for(Document d : documents){
            if(d.getCategory() == null){

                //Then
                assertFalse(hasCategory);

            }
        }
    }

    private void generate_mock_search_results_after_performing_search(){
        APICommunicator apiCommunicator = mock(APICommunicator.class);
        List<Document> documents = new ArrayList<>();
        Document d1 = new Document("doc1");
        d1.setCategory(new Category());
        Document d2 = new Document("doc2");
        d2.setCategory(new Category());
        documents.add(d1);
        documents.add(d2);
        when(apiCommunicator.search(anyList())).thenReturn(documents);
        _searchEngineAlgorithm = new SearchEngine(apiCommunicator);
    }

}
