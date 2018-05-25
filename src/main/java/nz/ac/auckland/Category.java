package nz.ac.auckland;

import java.util.ArrayList;
import java.util.List;

public class Category {
    public Label categoryLabel;
    private List<Document> _documents;
    private String _summary;

    public Category(){
        categoryLabel = new Label();
        _documents = new ArrayList<Document>();
    }

    public List<Document> getDocuments() {
        return _documents;
    }

    public void addDocument(Document d) {
        _documents.add(d);
    }

    public String getSummary() {
        return _summary;
    }

    public void setSummary(String summary) {
        _summary = summary;
    }
}
