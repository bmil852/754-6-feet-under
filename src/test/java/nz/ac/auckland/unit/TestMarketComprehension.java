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
    Keyword keyword1, keyword2, keyword3, keyword4;
    List<Keyword> keywords1, keywords2;

    @Before
    public void setUp(){
        apiCommunicator = mock(APICommunicator.class);
        documents = new ArrayList<>();
        keywords1= new ArrayList<>();
        keywords2= new ArrayList<>();
        d1 = new Document("doc1");
        d2 = new Document("doc2");
        c1= new Category("category1");
        c2= new Category("category2");
        d1.setCategory(c1);
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
        List<Keyword> keywords= new ArrayList<>();
        keyword1 = new Keyword("this");
        keyword2 = new Keyword("is a");
        keyword3 = new Keyword("complete");
        keyword4 = new Keyword("label");

        keyword1.setWeight(1);
        keyword2.setWeight(2);
        keyword3.setWeight(3);
        keyword4.setWeight(4);

        keywords.add(keyword1);
        keywords.add(keyword2);
        keywords.add(keyword3);
        keywords.add(keyword4);

        d1.setKeywords(keywords1);
        d2.setKeywords(keywords2);
        keywords1.add(keyword1);
        keywords1.add(keyword2);
        keywords2.add(keyword3);
        keywords2.add(keyword4);

        generate_mock_search_results_with_same_category_after_performing_search();
        perform_search();

        //When
        _searchEngineAlgorithm.labelCategories();


        //Then
        assertEquals(c1.categoryLabel.getLabel(), "this is a complete label");

    }

    @Test
    public void check_if_label_for_category_is_formed_removing_duplicates(){
        //Given
        List<Keyword> keywords= new ArrayList<>();
        keyword1 = new Keyword("a");
        keyword2 = new Keyword("complete");
        keyword3 = new Keyword("complete");
        keyword4 = new Keyword("label");

        keyword1.setWeight(1);
        keyword2.setWeight(2);
        keyword3.setWeight(3);
        keyword4.setWeight(4);

        keywords.add(keyword1);
        keywords.add(keyword2);
        keywords.add(keyword3);
        keywords.add(keyword4);

        d1.setKeywords(keywords1);
        d2.setKeywords(keywords2);
        keywords1.add(keyword1);
        keywords1.add(keyword2);
        keywords2.add(keyword3);
        keywords2.add(keyword4);

        generate_mock_search_results_with_same_category_after_performing_search();
        perform_search();

        //When
        _searchEngineAlgorithm.labelCategories();


        //Then
        assertEquals(c1.categoryLabel.getLabel(), "a complete label");

    }

    @Test
    public void check_if_labels_for_multiple_categories_are_formed(){
        //Given
        List<Keyword> keywords= new ArrayList<>();
        keyword1 = new Keyword("a");
        keyword2 = new Keyword("concise");
        keyword3 = new Keyword("complete");
        keyword4 = new Keyword("label");

        keyword1.setWeight(1);
        keyword2.setWeight(2);
        keyword3.setWeight(3);
        keyword4.setWeight(4);

        keywords.add(keyword1);
        keywords.add(keyword2);
        keywords.add(keyword3);
        keywords.add(keyword4);

        d1.setKeywords(keywords1);
        d2.setKeywords(keywords2);
        keywords1.add(keyword1);
        keywords1.add(keyword2);
        keywords2.add(keyword3);
        keywords2.add(keyword4);

        generate_mock_search_results_after_performing_search();
        perform_search();

        //When
        _searchEngineAlgorithm.labelCategories();


        //Then
        assertEquals(c1.categoryLabel.getLabel(), "a concise");
        assertEquals(c2.categoryLabel.getLabel(), "complete label");

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
