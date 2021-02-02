package shoppinglist.view;

import java.util.List;
import shoppinglist.model.ShopList;

public interface ShopListCollectionLoader {
    List<ShopList> load();
}
