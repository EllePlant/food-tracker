package example.application;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import example.data.DatabaseHelper;
import example.data.PantryItem;
import example.data.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class PantryFragment extends Fragment {
    private Button add_button;
    private ListView list;
    private ArrayAdapter<PantryItem> pantryAdapter;
    private DatabaseHelper db;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        db = new DatabaseHelper(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pantry, container, false);
        getActivity().setTitle("Pantry");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = view.findViewById(R.id.pantry_listview);
        // Create the pantry list view
        createPantryAdapter();

        add_button = view.findViewById(R.id.add_button);
        add_button.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_add);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new PantryAddItemFragment()).commit();
                return true;
            }
        });
    }

    private void createPantryAdapter() {
        // Create static display of data until database is ready
        PantryItem item1 = new PantryItem("Apples", "Red Gala", 10, 2, PantryItem.MassUnit.KILOGRAM,
                    LocalDate.now(), PantryItem.ItemType.FRUIT, true);
        PantryItem item2 = new PantryItem("Bananas", "Cavendish", 15,3, PantryItem.MassUnit.KILOGRAM,
                    LocalDate.now(), PantryItem.ItemType.FRUIT, true);

        User user1 = new User("name", "pass", "email");

        user1.getPantryCollection().addItem(item1);
        user1.getPantryCollection().addItem(item2);

        // Get Pantry
        List<PantryItem> pantry = new ArrayList<>(user1.getPantryCollection().getPantry());

        // Initialize the ListView and the ArrayAdapter
        pantryAdapter = new PantryAdapter(this.getContext(), pantry);

        // Set the ArrayAdapter on the ListView
        list.setAdapter(pantryAdapter);
    }



    public class PantryAdapter extends ArrayAdapter<PantryItem> {
        /**
         * Creates a new UserListAdapter.
         * @param context the context
         * @param pantry the list of food items to display
         */
        public PantryAdapter(Context context, List<PantryItem> pantry) {
            super(context, 0, pantry);
        }

        /**
         * Gets the view for the user at the specified position.
         * @param position the position
         * @param convertView the view to convert
         * @param parent the parent view
         * @return the view
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            PantryItem item = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            // This view use the item_user layout as the template
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
            }

            // Lookup views within item layout
            TextView nameTextView = convertView.findViewById(R.id.name_textview);
            TextView descTextView = convertView.findViewById(R.id.desc_textview);

            // Populate the data into the template view using the data object
            nameTextView.setText(item.getItemName());
            descTextView.setText(item.getDescription());



            // Return the completed view to render on screen
            return convertView;
        }
    }
    }



