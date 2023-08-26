package example.application;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import example.data.PantryItem;
import example.data.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class PantryAddItemFragment extends Fragment {


    EditText item_name;
    EditText item_expiry;
    EditText item_quantity;
    EditText item_amount;
    Spinner item_unitType;
    PantryItem.MassUnit user_unitType;
    Spinner item_type;
    PantryItem.ItemType user_itemType;

    EditText item_description;
    Spinner notification_preference;
    boolean user_expiryNotifyPref;
    Button save_button;
    private User currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pantry_add_item, container, false);
        getActivity().setTitle("Pantry");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        item_name = view.findViewById(R.id.item_name);
        item_description = view.findViewById(R.id.item_description);
        item_quantity = view.findViewById(R.id.item_quantity);
        item_amount = view.findViewById(R.id.item_amount);
        item_expiry = view.findViewById(R.id.item_expiry);

        item_unitType = view.findViewById(R.id.spinner_unitType);
        item_type = view.findViewById(R.id.spinner_type);

        notification_preference = view.findViewById(R.id.spinner_notificationPreference);
        save_button = view.findViewById(R.id.save_button);
        save_button.setBackgroundColor(Color.BLACK);

        ArrayAdapter<CharSequence> unitTypeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.Item_Weight, android.R.layout.simple_spinner_item);
        unitTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        item_unitType.setAdapter(unitTypeAdapter);

        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.Item_Type, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        item_type.setAdapter(typeAdapter);

        ArrayAdapter<CharSequence> notificationsAdapter = ArrayAdapter.createFromResource(getContext(), R.array.Notification_Preference, android.R.layout.simple_spinner_item);
        notificationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notification_preference.setAdapter(notificationsAdapter);

        notification_preference.setSelection(notificationsAdapter.getPosition("Enabled"));

        item_unitType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //Set unit mass type of item
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (item_unitType.getSelectedItem().toString() == "litre(s)"){
                    user_unitType = PantryItem.MassUnit.LITRE;
                }
                if (item_unitType.getSelectedItem().toString() == "millilitre(s)"){
                    user_unitType = PantryItem.MassUnit.MILLILITRE;
                }
                if (item_unitType.getSelectedItem().toString() == "gram(s)"){
                    user_unitType = PantryItem.MassUnit.GRAM;
                }
                if (item_unitType.getSelectedItem().toString() == "kilogram(s)"){
                    user_unitType = PantryItem.MassUnit.KILOGRAM;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle no selection
                user_unitType = null;
            }
        });

        item_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            // Set food type of item
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (item_type.getSelectedItem().toString() == "Poultry"){
                    user_itemType = PantryItem.ItemType.POULTRY;
                }
                if (item_type.getSelectedItem().toString() == "Red Meat"){
                    user_itemType = PantryItem.ItemType.RED_MEAT;
                }
                if (item_type.getSelectedItem().toString() == "Fish"){
                    user_itemType = PantryItem.ItemType.FISH;
                }
                if (item_type.getSelectedItem().toString() == "Pork"){
                    user_itemType = PantryItem.ItemType.PORK;
                }
                if (item_type.getSelectedItem().toString() == "Seafood"){
                    user_itemType = PantryItem.ItemType.SEAFOOD;
                }
                if (item_type.getSelectedItem().toString() == "Eggs"){
                    user_itemType = PantryItem.ItemType.EGGS;
                }
                if (item_type.getSelectedItem().toString() == "Dairy"){
                    user_itemType = PantryItem.ItemType.DAIRY;
                }
                if (item_type.getSelectedItem().toString() == "Fruit"){
                    user_itemType = PantryItem.ItemType.FRUIT;
                }
                if (item_type.getSelectedItem().toString() == "Vegetable"){
                    user_itemType = PantryItem.ItemType.VEGETABLE;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle no selection
                user_itemType = null;
            }
        });

        notification_preference.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //Set boolean for notification preference
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (notification_preference.getSelectedItem().toString() == "Enabled"){
                    user_expiryNotifyPref = true;
                }
                if (notification_preference.getSelectedItem().toString() == "Enabled"){
                    user_expiryNotifyPref = false;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Default to true
                user_expiryNotifyPref = true;
            }
        });

        save_button.setOnClickListener(new View.OnClickListener() {

            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            public void onClick(View v) {
                //Get the values from user for PantryItem
                String user_name = item_name.getText().toString();
                String user_desc = item_description.getText().toString();
                int user_quantity = Integer.parseInt(item_quantity.getText().toString());
                int user_amount = Integer.parseInt(item_amount.getText().toString());
                LocalDate user_expiry = LocalDate.parse(item_expiry.getText().toString(),format);

                // Construct new Pantry Item
                PantryItem newItem = new PantryItem(user_name, user_desc, user_quantity, user_amount, user_unitType, user_expiry, user_itemType, user_expiryNotifyPref);

                //TODO figure out how to access the currentUser from here
                // Add item to currentUsers pantry
                //currentUser.getPantryCollection().addItem(newItem);

                // Switch back to Pantry View
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new PantryFragment());
                fragmentTransaction.commit();

            }
        });


    }
}

