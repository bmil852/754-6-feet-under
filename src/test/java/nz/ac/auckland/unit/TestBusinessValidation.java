package nz.ac.auckland.unit;

import nz.ac.auckland.BusinessValidator;
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
    Set<Category> categories = new HashSet<Category>();
    BusinessValidator bvs;

    @Before
    public void setUp(){
        bvs = new BusinessValidator(categories);
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
        bvs.addCategory(c1);
        bvs.addCategory(c2);
        bvs.addCategory(c3);
    }

    @Test
    public void calculate_popularity_of_a_category(){
        //Given
        Set<Category> _categories = categories;

        //When
        bvs.calculateCategoryPopularities();

        //Then
        assertThat(c1.getPopularity(), equalTo(0.5));
        assertThat(c2.getPopularity(), equalTo(0.3));
        assertThat(c3.getPopularity(), equalTo(0.2));
    }

    @Test
    public void calculate_popularity_of_a_category_of_odd_number_of_documents(){
        //Given
        Set<Category> _categories = categories;
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
        bvs.addCategory(c1);
        bvs.addCategory(c2);
        bvs.addCategory(c3);

        //When
        bvs.calculateCategoryPopularities();

        //Then
        assertThat(c1.getPopularity(), equalTo(0.448));
        assertThat(c2.getPopularity(), equalTo(0.241));
        assertThat(c3.getPopularity(), equalTo(0.310));
    }

    @Test(expected=NumberFormatException.class)
    public void calculate_popularity_of_a_category_when_no_documents_are_returned(){
        //Given
        categories.clear();
        BusinessValidator _bvs = new BusinessValidator(categories);
        Category c4 = new Category("C4");
        _bvs.addCategory(c4);

        //When
        _bvs.calculateCategoryPopularities();

        //Then
        fail("Should throw an exception as you cannot have a category without documents");
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
        c1.setRelevance(Relevance.RELEVANT);
        c2.setRelevance(Relevance.VERY_RELEVANT);
        c3.setRelevance(Relevance.THE_SAME);

        //When
        double maturity = bvs.getOverallMaturity();

        //Then
        assertThat(maturity, equalTo(0.675));
    }

    @Test(expected=RuntimeException.class)
    public void users_obtain_maturity_of_the_business_idea_with_category_without_relevance(){
        //Given
        Category c4 = new Category("C4");
        c2.setRelevance(Relevance.NOT_RELEVANT);
        c3.setRelevance(Relevance.THE_SAME);
        categories.add(c4);

        //When
        bvs.getOverallMaturity();


        //Then
        fail("Category needs to have relevance set before maturity can be computed");
    }

}
