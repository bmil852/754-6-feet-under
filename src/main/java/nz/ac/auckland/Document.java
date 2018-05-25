package nz.ac.auckland;

import java.util.List;

public class Document {
    private String text;
    private Category category;
    private List<Keyword> keywords;

    public Document(String text, Category category){
        this.text = text;
        this.category = category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Document(String text) {
        this.text = text;
    }

    public Category getCategory() {
        return category;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }
}
