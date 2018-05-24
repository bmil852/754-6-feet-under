package nz.ac.auckland;

public class Document {
    private String text;
    private Category category;

    public void setCategory(Category category) {
        this.category = category;
    }

    public Document(String text) {
        this.text = text;
    }

    public Category getCategory() {
        return category;
    }
}
