package shoppinglist.control;

import java.awt.DisplayMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import shoppinglist.model.Entry;
import shoppinglist.model.Product;
import shoppinglist.model.ShopList;
import shoppinglist.view.ShopListCollectionDisplay;
import shoppinglist.view.ShopListCollectionLoader;
import shoppinglist.view.ShopListDisplay;

public class ShopListPresenter {

    private final ShopListCollectionLoader shopListCollectionLoader;
    private final ShopListCollectionDisplay shopListCollectionDisplay;
    private final ShopListDisplay shopListDisplay;
    private List<ShopList> shopLists;

    public ShopListPresenter(ShopListCollectionLoader shopListCollectionLoader, ShopListCollectionDisplay shopListCollectionDisplay, ShopListDisplay shopListDisplay) {
        this.shopListCollectionLoader = shopListCollectionLoader;
        this.shopListCollectionDisplay = shopListCollectionDisplay;
        this.shopListDisplay = shopListDisplay;
    }

    public void load() {
        this.shopLists = shopListCollectionLoader.load();
        shopListCollectionDisplay.display(shopListCollectionLoader.load());
        shopListCollectionDisplay.on(listSelected());

    }

    private ShopListCollectionDisplay.ListSelected listSelected() {
        return new ShopListCollectionDisplay.ListSelected() {
            @Override
            public void execute(ShopList list) {
                shopListDisplay.display(list);
                shopListDisplay.on(mesageMarking(list));
                //shopListDisplay.on(newProduct(list));
                shopListDisplay.on(deleteProduct(list));
                shopListDisplay.on(reorder(list));
            }
        };
    }

    private ShopListDisplay.Reorder reorder(ShopList list) {
        return new ShopListDisplay.Reorder() {
            @Override
            public void reorder(ShopListDisplay.Field field, ShopListDisplay.Direction direction) {
                final Function<Entry, String> order;
                switch (field) {
                    case CATEGORY:
                        order = (Entry entry) -> entry.getProduct().getCategory();
                        break;
                    case DESCRIPTION:
                        order = (Entry entry) -> entry.getProduct().getDescription();
                        break;
                    default:
                        order = (Entry entry) -> entry.getProduct().getDescription();
                }

                list.getEntries().sort(comparator(order, direction));

            }
        };
    }

    Comparator<Entry> comparator(Function<Entry, String> order, ShopListDisplay.Direction direction) {
        return (Entry arg0, Entry arg1) -> {
            if (direction == ShopListDisplay.Direction.ASC) {
                return order.apply(arg0).compareTo(order.apply(arg1));
            } else {
                return order.apply(arg1).compareTo(order.apply(arg0));
            }
        };
    }

    ShopListDisplay.ProductDeletion deleteProduct(ShopList list){
        return new ShopListDisplay.ProductDeletion() {
            @Override
            public void delete(Product product) {
                
                Iterator<Entry> iterator = list.getEntries().iterator();
                while (iterator.hasNext()) {
                    Entry entry = iterator.next();
                    if(entry.getProduct().equals(product)){
                        iterator.remove();
                    }
                }
               shopListDisplay.display(list);
            }
        };
    }
    
    ShopListDisplay.EntryMarking  mesageMarking(ShopList list){
        return new ShopListDisplay.EntryMarking() {
            @Override
            public void mark(Entry entry) {
                entry.setIsMarked(!entry.isIsMarked());
                shopListDisplay.display(list);
            }
        };
    }
    ShopListDisplay.ProductAddition  newProduct(ShopList list){
        return new ShopListDisplay.ProductAddition() {
            @Override
            public void add(Product product) {
                Entry entry = new Entry(product, true);
                list.getEntries().add(entry);
                shopListDisplay.display(list);
            }
        };
    }
}
