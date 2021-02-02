package shoppinglist.view;

import java.util.List;
import shoppinglist.model.ShopList;

public interface ShopListCollectionDisplay {
    void display(List<ShopList> shopListCollection);
    void on(ListSelected listSelected);
    public interface ListSelected{
        void execute(ShopList list);
    }
}
