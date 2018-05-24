package nz.ac.auckland.unit;

import nz.ac.auckland.Document;
import nz.ac.auckland.Keyword;
import nz.ac.auckland.SearchEngine;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class TestMarketComprehension {
    SearchEngine _searchEngineAlgorithm;

    @Test
    public void check_if_documents_are_clustered_by_categories(){
        boolean hasCategory = true;
        //Given
        _searchEngineAlgorithm.searchAndProcess(new ArrayList<Keyword>());
        List<Document> documents = _searchEngineAlgorithm.getSearchResults();

        //When
        for(Document d : documents){
            if(d.getCategory() == null){

                //Then
                assertTrue(false);

            }
        }
    }
}
