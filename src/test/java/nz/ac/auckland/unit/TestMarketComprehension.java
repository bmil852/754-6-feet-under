package nz.ac.auckland.unit;

import nz.ac.auckland.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestMarketComprehension {
    SearchEngine _searchEngineAlgorithm;
    List<Document> documents;
    APICommunicator apiCommunicator;
    Document d1, d2;
    Category c1;

    @Before
    public void setUp(){
        apiCommunicator = mock(APICommunicator.class);
        documents = new ArrayList<>();
        d1 = new Document("doc1");
        c1= new Category();
        d1.setCategory(c1);
        d2 = new Document("doc2");
    }

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

    @Test
    public void check_if_documents_are_clustered_by_the_same_category(){
        boolean hasCategory = true;
        //Given
        generate_mock_search_results_with_same_category_after_performing_search();
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

    @Test(expected = RuntimeException.class)
    public void fails_when_a_returned_document_is_not_clustered_by_a_category(){
        //Given
        generate_mock_search_results_with_missing_category_after_performing_search();
        _searchEngineAlgorithm.searchAndProcess(new ArrayList<Keyword>());
         _searchEngineAlgorithm.getSearchResults();

        //When
        _searchEngineAlgorithm.getResultCategories();


        //Then
        fail("Should throw a runtime exception");
    }

    private void generate_mock_search_results_after_performing_search(){
        d2.setCategory(new Category());
        documents.add(d1);
        documents.add(d2);
        when(apiCommunicator.search(anyList())).thenReturn(documents);
        _searchEngineAlgorithm = new SearchEngine(apiCommunicator);
    }

    private void generate_mock_search_results_with_missing_category_after_performing_search(){
        documents.add(d1);
        documents.add(d2);
        when(apiCommunicator.search(anyList())).thenReturn(documents);
        _searchEngineAlgorithm = new SearchEngine(apiCommunicator);
    }

    private void generate_mock_search_results_with_same_category_after_performing_search(){
        d2.setCategory(c1);
        documents.add(d1);
        documents.add(d2);
        when(apiCommunicator.search(anyList())).thenReturn(documents);
        _searchEngineAlgorithm = new SearchEngine(apiCommunicator);
    }

}
