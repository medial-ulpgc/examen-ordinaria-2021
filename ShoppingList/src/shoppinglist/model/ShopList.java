package shoppinglist.model;

import java.util.List;

public class ShopList {

    private final String name;

    private final List<Entry> entries;

    public ShopList(String name, List<Entry> entries) {
        this.name = name;
        this.entries = entries;
    }

    public String getName() {
        return name;
    }

    public List<Entry> getEntries() {
        return entries;
    }

}
