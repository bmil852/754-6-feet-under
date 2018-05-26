package nz.ac.auckland;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Category {
    public Label categoryLabel;
    private String _categoryName;
    private List<Document> _documents;
    private double _popularity;
    private double _relevance = -1.0;
    private String summary ="";

    public Category(String categoryName) {
        categoryLabel = new Label();
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
        DecimalFormat df = new DecimalFormat("#0.000");
        _popularity = Double.parseDouble(df.format(newPopularity));
    }

    public double getPopularity() {
        return _popularity;
    }

    public double getRelevance() {
        return _relevance;
    }

    public void setRelevance(Relevance relevance) {
        _relevance = relevance.nominal_value;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
