package nz.ac.auckland.unit;

import nz.ac.auckland.Category;
import nz.ac.auckland.Document;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TestBusinessValidation {
    Category c1, c2, c3;
    Set<Category> categories = new HashSet<>();

    @Before
    public void setUp(){
        c1 = new Category("C1");
        c2 = new Category("C2");
        c3 = new Category("C3");
        for (int i = 0; i < 100; i++) {
            if (i < 50) {
                Document d1 = new Document("Mock text for a document of category 1", c1);
                c1.addDocument(d1);
            } else if (i < 80) {
                Document d2 = new Document("Mock text for a document object of category 2", c2);
                c2.addDocument(d2);
            } else {
                Document d3 = new Document("Mock text for a document object of category 3", c3);
                c3.addDocument(d3);
            }
        }
        categories.add(c1);
        categories.add(c2);
        categories.add(c3);
    }

    @Test
    public void calculate_popularity_of_a_category(){
        //Given
        Set<Category> _categories = categories;
        Map<Category,Double> documentCounts = new HashMap();
        double total = 0.0;

        //When
        for(Category c : _categories){
            documentCounts.put(c,(double) c.getDocumentCount());
            total += (double) c.getDocumentCount();
        }

        //replace list value at index
        c1.updatePopularity(documentCounts.get(c1)/total);
        c2.updatePopularity(documentCounts.get(c2)/total);
        c3.updatePopularity(documentCounts.get(c3)/total);

        //Then
        assertThat(c1.getPopularity(), equalTo(0.5));
        assertThat(c2.getPopularity(), equalTo(0.3));
        assertThat(c3.getPopularity(), equalTo(0.2));

    }
}
