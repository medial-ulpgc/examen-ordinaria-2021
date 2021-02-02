package shoppinglist.view;

import java.util.function.Function;
import shoppinglist.model.Entry;
import shoppinglist.model.Product;
import shoppinglist.model.ShopList;

public interface ShopListDisplay {

    void display(ShopList shopList);

    void on(Reorder reorder);

    void on(EntryMarking mmesageMarking);

    void on(ProductAddition newProduct);

    void on(ProductDeletion deleteProduct);

    public enum Direction {
        ASC,
        DESC;
    }

    public enum Field {
        DESCRIPTION,
        CATEGORY;
    }

    public interface Reorder {

        void reorder(Field field, Direction direction);
    }

    public interface EntryMarking {

        void mark(Entry entry);
    }

    public interface ProductAddition {

        void add(Product product);
    }

    public interface ProductDeletion {

        void delete(Product product);
    }

}
