package example.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.LocalDate;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "pantry_db";

    // Pantry table name
    private static final String TABLE_PANTRY = "pantryItem";

    // Pantry Table Columns names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_AMOUNT = "amount";
    //private static final String COLUMN_UNIT = "unit";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_ITEMTYPE = "item_type";

    // create table sql query
    private String CREATE_PANTRY_TABLE = "CREATE TABLE " + TABLE_PANTRY + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_AMOUNT + " INTEGER,"
            //+ COLUMN_UNIT + " TEXT,"
            + COLUMN_DATE + " DATE,"
            + COLUMN_ITEMTYPE + " TEXT" +
            ")";

    // drop table sql query
    private String DROP_PANTRY_TABLE = "DROP TABLE IF EXISTS " + TABLE_PANTRY;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PANTRY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_PANTRY_TABLE);

        // Create tables again
        onCreate(db);
    }

    // Method to update name
    public int updateName(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);

        // updating row
        return db.update(TABLE_PANTRY, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    // Method to update description
    public int updateDescription(int id, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, description);

        // updating row
        return db.update(TABLE_PANTRY, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    // Method to update amount
    public int updateAmount(int id, int amount) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_AMOUNT, amount);

        // updating row
        return db.update(TABLE_PANTRY, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    // Method to update unit
    /*public int updateUnit(int id, String unit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_UNIT, unit);

        // updating row
        return db.update(TABLE_PANTRY, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }*/

    // Method to update date
    public int updateDate(int id, LocalDate date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date.toString());

        // updating row
        return db.update(TABLE_PANTRY, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    // Method to update item type
    public int updateItemType(int id, String itemType) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEMTYPE, itemType);

        // updating row
        return db.update(TABLE_PANTRY, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    // Method to add pantry item
   /* public boolean addPantryItem(PantryItem pantryItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, pantryItem.getItemName());
        values.put(COLUMN_DESCRIPTION, pantryItem.getDescription());
        values.put(COLUMN_AMOUNT, pantryItem.getWeight());
        //values.put(COLUMN_UNIT, pantryItem.get().toString()); ~ need to add unit types to pantryItem
        values.put(COLUMN_DATE, pantryItem.getShelfLife().toString());
        values.put(COLUMN_ITEMTYPE, pantryItem.getItemType().toString());

        // Inserting Row
        long result = db.insert(TABLE_PANTRY, null, values);
        db.close(); // Closing database connection

        if (result == -1) {
            return false;  // insertion failed
        } else {
            return true;  // insertion successful
        }
    }*/

}
