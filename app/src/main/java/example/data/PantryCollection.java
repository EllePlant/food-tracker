package example.data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List.*;

public class PantryCollection
{
    private ArrayList<PantryItem> pantryCollection;
    
    /**
     * Creates a new User.
     *
     */
    protected PantryCollection() {
        this.pantryCollection = new ArrayList<>();
    }

    /**
     *
     * Adds an Item to the pantry list. (conditions based on expiry to decide if merge duplicate items or not)
     *
     * @param name          name of item to add
     * @param description   description of item
     * @param weight        weight of item
     * @param expiry        expiry date
     * @param itemType      type of Item
     */

    /**
     * Read from the Pantry Collection
     *
     * @return User's pantry collection
     */
    public ArrayList<PantryItem> getPantry(){return pantryCollection;}

    /** Add a Pantry Item to the Pantry Collection
     *
     * @param item  item to add to the pantry
     */
    public void addItem(PantryItem item){
        pantryCollection.add(item);
    }

    /** Retrieves the item being searched for
     *
     * @param name name of the item wanting to search
     * @return Pantry Item Object
     */
    public PantryItem getItem(String name){
        for (PantryItem item : pantryCollection){
            if(item.getItemName() == name){
                return item;
            }
        }
        return null;
    }

    /** Remove a Pantry Item from the Pantry Collection.
     *
     * @param item Item to remove from the pantry
     */
    public void removeItem(PantryItem item) throws Exception {
        if (!pantryCollection.contains(item)){
            throw new Exception("The item does not exist");
        }
        pantryCollection.remove(item);
    }

    /**
     * List all the names of Items currently in the User's Pantry Collection
     */
    public String getItemList() throws Exception {
        if (pantryCollection.isEmpty()) {
            throw new Exception("The pantry is empty");
        }

        StringBuilder itemList = new StringBuilder();
        for (PantryItem item : pantryCollection) {
            itemList.append(item.getItemName()).append("\n");
        }
        return itemList.toString();
    }

    /**
     * List all the names of Items currently EXPIRED in the User's Pantry Collection
     */
    public String getExpiredList() throws Exception {
        if (pantryCollection.isEmpty()) {
            throw new Exception("The pantry is empty");
        }

        StringBuilder itemList = new StringBuilder();
        for (PantryItem item : pantryCollection) {
            if (item.checkExpiry() != null){
                itemList.append(item.checkExpiry());
            }
        }
        return itemList.toString();
    }
}

