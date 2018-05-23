package nz.ac.auckland.unit;

import nz.ac.auckland.Category;
import nz.ac.auckland.Document;
import nz.ac.auckland.Relevance;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class TestBusinessValidation {
    Category c1, c2, c3;
    Set<Category> categories = new HashSet<>();
    double total;

    @Before
    public void setUp(){
        c1 = new Category("C1");
        c2 = new Category("C2");
        c3 = new Category("C3");
        total = 0.0;
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

        //When
        for(Category c : _categories){
            documentCounts.put(c,(double) c.getDocumentCount());
            total += (double) c.getDocumentCount();
        }
        c1.updatePopularity(documentCounts.get(c1)/total);
        c2.updatePopularity(documentCounts.get(c2)/total);
        c3.updatePopularity(documentCounts.get(c3)/total);

        //Then
        assertThat(c1.getPopularity(), equalTo(0.5));
        assertThat(c2.getPopularity(), equalTo(0.3));
        assertThat(c3.getPopularity(), equalTo(0.2));

    }

    @Test
    public void calculate_popularity_of_a_category_of_odd_number_of_documents(){
        //Given
        Map<Category,Double> documentCounts = new HashMap();
        for (int i = 0; i < 45; i++) {
            if (i < 15) {
                Document d1 = new Document("Mock text for a document of category 1", c1);
                c1.addDocument(d1);
            } else if (i < 20) {
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

        //When
        for(Category c : categories){
            documentCounts.put(c,(double) c.getDocumentCount());
            total += (double) c.getDocumentCount();
        }
        c1.updatePopularity(documentCounts.get(c1)/total);
        c2.updatePopularity(documentCounts.get(c2)/total);
        c3.updatePopularity(documentCounts.get(c3)/total);

        //Then
        assertThat(c1.getPopularity(), equalTo(0.45));
        assertThat(c2.getPopularity(), equalTo(0.24));
        assertThat(c3.getPopularity(), equalTo(0.31));
    }

    @Test(expected=NumberFormatException.class)
    public void calculate_popularity_of_a_category_when_no_documents_are_returned(){
        //Given
        Map<Category,Double> documentCounts = new HashMap();
        categories.clear();
        Category c4 = new Category("C4");

        //When
        documentCounts.put(c4,(double) c4.getDocumentCount());
        total += (double) c4.getDocumentCount();

        //Then
        c4.updatePopularity(documentCounts.get(c4)/total);
        fail("Cannot update popularity of a category which has no documents");
    }

    @Test
    public void set_relevance_of_a_category(){
        //Given
        Category category1 = c1;

        //When
        category1.setRelevance(Relevance.WEAK_RELEVANT);

        //Then
        assertThat(category1.getRelevance(), equalTo(0.25));
    }


    @Test
    public void users_obtain_maturity_of_the_business_idea(){
        //Given
        c1.updatePopularity(0.2);
        c1.setRelevance(Relevance.RELEVANT); //0.5
        c2.updatePopularity(0.3);
        c2.setRelevance(Relevance.VERY_RELEVANT); //0.75
        c3.updatePopularity(0.5);
        c3.setRelevance(Relevance.THE_SAME); //1.0

        double maturity = 0.0;

        //When
        for(Category c : categories){
            double popularity = c.getPopularity();
            double relevance = c.getRelevance();
            maturity += (popularity*relevance);
        }

        //Then
        assertThat(maturity, equalTo(0.825));

    }

}
