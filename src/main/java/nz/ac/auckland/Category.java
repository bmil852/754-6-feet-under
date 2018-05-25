package nz.ac.auckland;

import java.util.ArrayList;
import java.util.List;

public class Category {
    public Label categoryLabel;
    public List<Document> _documents;
    public String _summary;

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
}
