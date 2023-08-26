package example.data;

import org.jetbrains.annotations.NotNull;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class PantryItem {
    private String name;
    private Integer itemID;
    private static Integer itemCount = 0;           // Track Item IDs to create new one for each item
    private String description;
    private Integer quantity;
    private Integer amount;                         // Quantity of Object


    public enum MassUnit{
        LITRE,
        MILLILITRE,
        GRAM,
        KILOGRAM
    }                      // Mass Units Enumerable
    private MassUnit unitType;                      // Unit type of the quantity of Item
    private LocalDate expiry;                       // Expiry date of the item
    private LocalDate lastExpirycheck;              //
    private Integer shelfLife;                      // Typical shelf life for items with no expiry. Calculated by itemType, Integer represents number of days
    public enum ItemType{                           // Item Type Enumerable

        POULTRY,
        RED_MEAT,
        FISH,
        PORK,
        SEAFOOD,
        EGGS,
        DAIRY,
        FRUIT,
        VEGETABLE

    }
    private ItemType itemType;                      // Store an enumerable ItemType
    private boolean expiryNotification_preference;


    /**
     *  Creates a new Food Item
     * @param name              Name of the item
     * @param description       Description of the item
     * @param quantity          Quantity of the item stored
     * @param amount            Amount of the item stored
     * @param unitType          Unit type of the amount stored
     * @param expiry            Expiry date of the item
     * @param itemType          Food type of the item
     */
    public PantryItem(String name, String description, Integer quantity, Integer amount, MassUnit unitType, LocalDate expiry, ItemType itemType, Boolean expiryPreference) {
        this.itemID = itemCount++;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.amount = amount;
        this.unitType = unitType;
        this.lastExpirycheck = LocalDate.now();
        if (expiry == null){                        // NULL OR WHATEVER THE LACK OF A SELECTED DATE RETURNS (CAN GUARANTEE WE'LL BE ABLE TO RETURN NULL IF THE USER DOESN'T SELECT A DATE)
            this.expiry = calculateExpiry();
        }
        else{
            this.expiry = expiry;
        }
        this.itemType = itemType;
        this.expiryNotification_preference = expiryPreference;
    }

    /**
     * Gets the name of the item.
     */
    public String getItemName() {
        return this.name;
    }

    /**
     * Gets the description of the item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the weight of the item.
     */
    public Integer getWeight() {
        return amount;
    }

    /**
     * Gets the expiry of the item.
     */
    public LocalDate getExpiry() {
        return expiry;
    }

    /**
     * Gets the expiry notification preference of the item.
     */ //TODO write tests
    public boolean getExpiryPreference() {
        return expiryNotification_preference;
    }

    /**
     * Gets the quantity of the item.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Gets the type of the item.
     */
    public ItemType getItemType() {
        return itemType;
    }


    /**
     * Gets the name of the item.
     */
    public void modifyItemName(String newName){this.name = newName;}

    /**
     * Modifies the description of the item.
     */
    public void modifyDescription(String newDescription){this.description = newDescription;}

    /**
     * Modifies the weight of the item.
     */
    public void modifyWeight(Integer newAmount){this.amount = newAmount;}

    /**
     * Modifies the expiry of the item.
     */
    public void modifyExpiry(LocalDate newExpiry){this.expiry = newExpiry;}
    /**
     * Modifies the expiry notification preference of the item.
     */ //TODO write tests
    public void modifyExpiryNotificationPref(boolean newExpiryPreference){this.expiryNotification_preference = newExpiryPreference;}

    /**
     * Modifies the quantity of the item.
     */
    public void modifyQuantity(Integer newQuantity) {this.quantity = newQuantity;}


    /**
     * Modifies the ItemType of an Item, if the User selects they may also change the shelfLife based on this
     *
     * @param newItemType   The ItemType to be changed to
     * @param alterExpiry   Boolean check to alter (TRUE) shelf life or not (FALSE) //TODO PRESENT AS CHECKBOX?
     */
    //TODO Write tests
    //TODO change this to calculate a new expiry while taking days since creation into consideration? (i.e. adjust the calculated expiry to account for days already passed)
    public void modifyItemType(ItemType newItemType, @NotNull Boolean alterExpiry){
        this.itemType = newItemType;
        if (alterExpiry){
            this.expiry = calculateExpiry();
        }
    }

    /**
     * Helper method to calculate Shelf Life based on itemType
     *
     * Partially based on this aus gov infographic: https://media.healthdirect.org.au/images/inline/original/food_safety_table_infographic-e181c3.jpg
     */
    //TODO write tests
    public void calculateShelfLife() {

        if (this.itemType == ItemType.SEAFOOD){
            this.shelfLife = 2;
        }

        else if (this.itemType == ItemType.POULTRY ||
                this.itemType == ItemType.FISH ||
                this.itemType == ItemType.PORK)
        {
            this.shelfLife = 3;
        }

        else if (this.itemType == ItemType.RED_MEAT){
            this.shelfLife = 4;
        }

        else if (this.itemType == ItemType.EGGS){
            this.shelfLife = 21;
        }

        else if (this.itemType == ItemType.DAIRY){
            this.shelfLife = 12;
        }

        else if (this.itemType == ItemType.FRUIT){
            this.shelfLife = 7;
        }

        else if (this.itemType == ItemType.VEGETABLE){
            this.shelfLife = 10;
        }

        else {
            this.shelfLife = 4;
        }

    }

    //TODO write tests
    public LocalDate calculateExpiry(){
        calculateShelfLife();
        LocalDate currentDate = LocalDate.now();
        return currentDate.plusDays(this.shelfLife);
    }

    //TODO write tests
    public String checkExpiry(){
        if(this.expiryNotification_preference){
            // Check if this item's expiry has been checked in the last 12 hours
            if (this.lastExpirycheck.isAfter(this.lastExpirycheck.plus(12, ChronoUnit.HOURS))){

                // Update lastExpiryCheck
                this.lastExpirycheck = LocalDate.now();

                // If expired
                if (this.expiry.isAfter(LocalDate.now())){
                    return String.format("%s has expired", this.getItemName());
                }
                else{
                    return null;
                }
            }
        }

        return null;
    }










}
