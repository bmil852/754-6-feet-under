package nz.ac.auckland.unit;

import org.junit.Before;
import org.junit.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestJUnitWorks {
    Category c1, c2, c3;

    @Before
    public void setUp(){
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
        Set<Category> categories;
        List<Integer> documentCounts;
        int total = 0;

        //When
        for(Category c : categories){
            documentCounts.add(c.getDocumentCount());
            total += c.getDocumentCount();
        }

        //replace list value at index
        documentCounts.indexAt(0,documentCounts.get(0)/total);
        documentCounts.indexAt(1,documentCounts.get(1)/total);
        documentCounts.indexAt(2,documentCounts.get(2)/total);

        //Then
        assertThat(documentCounts.get(0), equalTo(0.5));
        assertThat(documentCounts.get(1), equalTo(0.3));
        assertThat(documentCounts.get(2), equalTo(0.2));

    }
}
