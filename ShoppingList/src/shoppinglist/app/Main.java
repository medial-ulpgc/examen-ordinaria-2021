package shoppinglist.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import shoppinglist.model.ShopList;
import shoppinglist.view.ShopListCollectionDisplay;
import shoppinglist.view.ShopListCollectionLoader;

public class Main {
    public static void main(String[] args) {
        List<ShopList> shoplists = new ArrayList<>();
        shoplists.add(new ShopList("BBQ", new ArrayList<>()));
        shoplists.add(new ShopList("Weekend", new ArrayList<>()));
        shoplists.add(new ShopList("WorkDays", new ArrayList<>()));
        shoplists.add(new ShopList("Birthday Party", new ArrayList<>()));
    
    
        ShopListCollectionLoader loader = loader(shoplists);
        ShopListCollectionDisplay  collection = collectionDisplay();
        //Hasta aqui llegue :(
    }

    private static ShopListCollectionDisplay collectionDisplay() {
        return new ShopListCollectionDisplay() {
            Scanner scaner = new Scanner(System.in);
            private List<ShopList> shopListCollection;
            private ShopListCollectionDisplay.ListSelected listSelected;
            @Override
            public void display(List<ShopList> shopListCollection) {
                this.shopListCollection = shopListCollection;
                System.out.println(shopListCollection);
                String nextLine = scaner.nextLine();
                shopListCollection.stream()
                        .filter((x)->x.getName().equals(nextLine))
                        .limit(1)
                        .forEach(listSelected::execute);
                
            }

            @Override
            public void on(ShopListCollectionDisplay.ListSelected listSelected) {
                this.listSelected =listSelected;
            }
        };
    }

    private static ShopListCollectionLoader loader(List<ShopList> shoplists) {
        return new ShopListCollectionLoader(){
            @Override
            public List<ShopList> load() {
                return shoplists;
            }
        };
    }
    
    
}
