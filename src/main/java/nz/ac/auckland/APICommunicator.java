package nz.ac.auckland;

import java.util.List;

public interface APICommunicator {
    List<Document> search(List<Keyword> weightedKeywords);

    String summarizeCategory(List<Document> documentsInCategory);
}
