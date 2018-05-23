package nz.ac.auckland;

import java.text.DecimalFormat;
import java.util.Set;

public class BusinessValidator {

    public void calculateCategoryPopularities(Set<Category> _categories) {
        double corpusSize = 0.0;
        for (Category c : _categories) {
            corpusSize += c.getDocumentCount();
        }
        for (Category c : _categories) {
            c.updatePopularity((double) c.getDocumentCount() / (corpusSize));
        }
    }

    public double getOverallMaturity(Set<Category> _categories) {
        try {
            calculateCategoryPopularities(_categories);
            double maturity = 0.0;
            for (Category c : _categories) {
                if(c.getRelevance() <= 0.0){
                    throw new RuntimeException();
                }else {
                    maturity += (c.getRelevance() * c.getPopularity());
                }
            }
            DecimalFormat df = new DecimalFormat("#0.000");
            maturity = Double.parseDouble(df.format(maturity));
            return maturity;
        } catch (RuntimeException e){
            throw new RuntimeException("Category needs to have relevance set before maturity can be computed");
        }
    }
}
