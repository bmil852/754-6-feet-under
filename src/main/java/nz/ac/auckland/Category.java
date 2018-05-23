package nz.ac.auckland;


import java.util.ArrayList;
import java.util.List;

public class Category {
    private String _categoryName;
    private List<Document> _documents;
    private double _popularity;

    public Category(String categoryName) {
        _categoryName = categoryName;
        _documents = new ArrayList<>();
    }

    public int getDocumentCount() {
        return _documents.size();
    }

    public void updatePopularity(double newPopularity) {
        _popularity = newPopularity;
    }

    public double getPopularity() {
        return _popularity;
    }

    public void addDocument(Document d){
        _documents.add(d);
    }

}
