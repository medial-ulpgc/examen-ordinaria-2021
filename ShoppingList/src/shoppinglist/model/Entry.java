package shoppinglist.model;

public class Entry {

    private final Product product;
    private boolean isMarked;

    public Entry(Product product, boolean isMarked) {
        this.product = product;
        this.isMarked = isMarked;
    }

    public boolean isIsMarked() {
        return isMarked;
    }

    public void setIsMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    public Product getProduct() {
        return product;
    }

}
