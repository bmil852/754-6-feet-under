package nz.ac.auckland.unit;

import nz.ac.auckland.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    Category c1,c2;

    @Before
    public void setUp(){
        apiCommunicator = mock(APICommunicator.class);
        documents = new ArrayList<>();
        d1 = new Document("doc1");
        c1= new Category();
        c2= new Category();
        d1.setCategory(c1);
        d2 = new Document("doc2");
        documents.add(d1);
        documents.add(d2);
    }

    @Test
    public void check_if_documents_are_clustered_by_categories(){
        //Given
        generate_mock_search_results_after_performing_search();
        perform_search();
        //When
        Set<Category> categories = _searchEngineAlgorithm.getResultCategories();

        //Then
        assertThat(categories.size() == 2 && categories.contains(c1) && categories.contains(c2),equalTo(true));
    }

    @Test
    public void check_if_documents_are_clustered_by_the_same_category(){
        //Given
        generate_mock_search_results_with_same_category_after_performing_search();
        perform_search();

        //When
        Set<Category> categories = _searchEngineAlgorithm.getResultCategories();

        //Then
        assertThat(categories.size() == 1,equalTo(true));
    }

    @Test(expected = RuntimeException.class)
    public void fails_when_a_returned_document_is_not_clustered_by_a_category(){
        //Given
        generate_mock_search_results_with_missing_category_after_performing_search();

        //When
        _searchEngineAlgorithm.getResultCategories();


        //Then
        fail("Should throw a runtime exception");
    }


    @Test
    public void check_if_concise_and_informative_label_for_category_is_formed(){
        //Given

        _searchEngineAlgorithm.searchAndProcess(new ArrayList<Keyword>());
        List<Document> documents = _searchEngineAlgorithm.getSearchResults();

        //When
        for(Document d : documents){
            Category c = d.getCategory();
            for(Keyword k : d.getKeywords()){
                c.label += k.word+" ";
            }
        }

        //Then
        assertThat(c1.label.equals("this is a complete label"),equalTo(true));

    }

    private void generate_mock_search_results_after_performing_search(){
        d2.setCategory(c2);
        when(apiCommunicator.search(anyList())).thenReturn(documents);
    }

    private void generate_mock_search_results_with_missing_category_after_performing_search(){
        when(apiCommunicator.search(anyList())).thenReturn(documents);
    }

    private void generate_mock_search_results_with_same_category_after_performing_search(){
        d2.setCategory(c1);
        when(apiCommunicator.search(anyList())).thenReturn(documents);
    }

    private void perform_search(){
        _searchEngineAlgorithm = new SearchEngine(apiCommunicator);
        _searchEngineAlgorithm.searchAndProcess(new ArrayList<Keyword>());
        _searchEngineAlgorithm.getSearchResults();
    }

}
