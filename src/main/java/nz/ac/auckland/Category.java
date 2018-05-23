package nz.ac.auckland;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private String _categoryName;
    private List<Document> _documents;
    private double _popularity;
    private double _relevance;

    public Category(String categoryName) {
        _categoryName = categoryName;
        _documents = new ArrayList<Document>();
    }

    public int getDocumentCount() {
        return _documents.size();
    }

    public List<Document> getDocuments() {
        return _documents;
    }

    public void addDocument(Document d) {
        _documents.add(d);
    }

    public void updatePopularity(double newPopularity) {
        try {
            DecimalFormat df = new DecimalFormat("#0.00");
            _popularity = Double.parseDouble(df.format(newPopularity));
        }catch(NumberFormatException e){
            throw new NumberFormatException("Cannot update popularity of a category which has no documents");
        }
    }

    public double getPopularity() {
        return _popularity;
    }

    public double getRelevance() {
        return _relevance;
    }

    public void setRelevance(String relevance) {
        switch (relevance){
            case "NOT_RELEVANT":
                _relevance = 0.0;
                break;
            case "WEAK_RELEVANT":
                _relevance = 0.25;
                break;
            case "RELEVANT":
                _relevance = 0.5;
                break;
            case "VERY_RELEVANT":
                _relevance = 0.75;
                break;
            case "THE_SAME":
                _relevance = 1.0;
                break;
            default:
                break;
        }
    }
}
