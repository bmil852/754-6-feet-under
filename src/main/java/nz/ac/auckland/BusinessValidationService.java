package nz.ac.auckland;

import java.util.Set;

public class BusinessValidationService {

    public void calculateCategoryPopularities(Set<Category> _categories) {
        int corpusSize = 0;
        for (Category c : _categories) {
            corpusSize += c.getDocumentCount();
        }
        for (Category c : _categories) {
            c.updatePopularity(c.getDocumentCount() / (corpusSize));
        }
    }

    public double getOverallMaturity(Set<Category> _categories) {
        try {
            calculateCategoryPopularities(_categories);
            double d = 0.0;
            for (Category c : _categories) {
                if(c.getRelevance() <= 0.0){
                    throw new RuntimeException();
                }else {
                    d += (c.getRelevance() * c.getPopularity());
                }
            }
            return d;
        } catch (Exception e){
            throw new RuntimeException("Category needs to have relevance set before maturity can be computed");
        }
    }
}
