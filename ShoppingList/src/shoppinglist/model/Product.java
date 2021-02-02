package shoppinglist.model;

public class Product {

    private final String description;
    private final String category;

    public Product(String description, String category) {
        this.description = description;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" + "description=" + description + ", category=" + category + '}';
    }

}
