package example.application;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class TipsFragment extends Fragment {

    private RecyclerView recyclerView;
    private TipsAdapter adapter;
    Tip tip1_poultry = new Tip("Type: Poultry\nUse within 1-2 days of purchase or freeze it for longer storage.", R.drawable.tips_poultry);
    Tip tip2_poultry = new Tip("Type: Poultry\nCooked poultry can be stored in the refrigerator for 3-4 days.", R.drawable.tips_poultry);
    Tip tip3_poultry = new Tip("Type: Poultry\nCook thoroughly to an internal temperature of 165°F (74°C) to ensure it is safe to eat.", R.drawable.tips_poultry);
    Tip tip1_red_meat = new Tip("Type: Red Meat\nStore in the refrigerator at or below 40°F (4°C).", R.drawable.tips_red_meat);
    Tip tip2_red_meat = new Tip("Type: Red Meat\nCook to the desired level of doneness based on the type of meat and personal preference.", R.drawable.tips_red_meat);
    Tip tip3_red_meat = new Tip("Type: Red Meat\nRaw red meat can be frozen for 4-12 months, depending on the type.", R.drawable.tips_red_meat);
    Tip tip1_fish = new Tip("Type: Fish\nUse fresh fish within 1-2 days of purchase.", R.drawable.tips_fish);
    Tip tip2_fish = new Tip("Type: Fish\nIf freezing fish, wrap it tightly in plastic wrap and place it in a freezer bag or container.", R.drawable.tips_fish);
    Tip tip3_fish = new Tip("Type: Fish\nThaw frozen fish in the refrigerator overnight before cooking.", R.drawable.tips_fish);
    Tip tip1_pork = new Tip("Type: Pork\nCook pork to an internal temperature of 145°F (63°C) with a 3-minute rest time.", R.drawable.tips_pork);
    Tip tip2_pork = new Tip("Type: Pork\nUse pork within 3-5 days of purchase or freeze it for longer storage.", R.drawable.tips_pork);
    Tip tip3_pork = new Tip("Type: Pork\nStore pork in the refrigerator at a temperature of 40°F (4°C) or below.", R.drawable.tips_pork);
    Tip tip1_seafood = new Tip("Type: Seafood\nThaw frozen seafood in the refrigerator overnight before cooking.", R.drawable.tips_seafood);
    Tip tip2_seafood = new Tip("Type: Seafood\nStore fresh seafood in the refrigerator at a temperature of 32-39°F (0-4°C) on ice or in a sealed container.", R.drawable.tips_seafood);
    Tip tip3_seafood = new Tip("Type: Seafood\nUse fresh seafood within 1-2 days of purchase.", R.drawable.tips_seafood);
    Tip tip1_eggs = new Tip("Type: Eggs\nStore eggs in the refrigerator at a temperature of 40°F (4°C) or below.", R.drawable.tips_eggs);
    Tip tip2_eggs = new Tip("Type: Eggs\nKeep eggs in their original carton to protect them and prevent absorption of strong odors.", R.drawable.tips_eggs);
    Tip tip3_eggs = new Tip("Type: Eggs\nCheck eggs for freshness by placing them in a bowl of water – fresh eggs sink, while old eggs float.", R.drawable.tips_eggs);
    Tip tip1_dairy = new Tip("Type: Dairy\nKeep dairy products sealed tightly to prevent odors and bacterial contamination.", R.drawable.tips_dairy);
    Tip tip2_dairy = new Tip("Type: Dairy\nCheck expiration date and use them before they expire.", R.drawable.tips_dairy);
    Tip tip3_dairy = new Tip("Type: Dairy\nAvoid storing dairy products in the refrigerator door, as temperatures can fluctuate.", R.drawable.tips_dairy);
    Tip tip1_fruit = new Tip("Type: Fruit\nStore ripe fruits in the refrigerator to prolong their freshness.", R.drawable.tips_fruit);
    Tip tip2_fruit = new Tip("Type: Fruit\nStore fruits separately from vegetables to prevent them from ripening too quickly.", R.drawable.tips_fruit);
    Tip tip3_fruit = new Tip("Type: Fruit\nWash fruits before consuming them to remove any dirt or residues.", R.drawable.tips_fruit);
    Tip tip1_vegetable = new Tip("Type: Vegetable\nWrap leafy greens in a damp paper towel and place them in a plastic bag to maintain freshness.", R.drawable.tips_vegetable);
    Tip tip2_vegetable = new Tip("Type: Vegetable\nStore root vegetables, such as potatoes and onions, in a cool, dark, and dry place.", R.drawable.tips_vegetable);
    Tip tip3_vegetable = new Tip("Type: Vegetable\nWash vegetables before consuming them, except for mushrooms, which should be wiped clean with a damp cloth.", R.drawable.tips_vegetable);


    private static class Tip {
        private String text;
        private int imageResId;

        public Tip(String text, int imageResId) {
            this.text = text;
            this.imageResId = imageResId;
        }

        public String getText() {
            return text;
        }

        public int getImageResId() {
            return imageResId;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tips, container, false);
        getActivity().setTitle("Tips");

        recyclerView = rootView.findViewById(R.id.my_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new TipsAdapter();
        recyclerView.setAdapter(adapter);

        // Create a list of tips
        List<Tip> tipsList = new ArrayList<>();
        tipsList.add(tip1_vegetable);
        tipsList.add(tip1_eggs);
        tipsList.add(tip3_pork);

        tipsList.add(tip1_fish);
        tipsList.add(tip1_red_meat);
        tipsList.add(tip1_pork);

        tipsList.add(tip1_fruit);
        tipsList.add(tip2_fish);
        tipsList.add(tip3_seafood);

        tipsList.add(tip3_dairy);
        tipsList.add(tip2_red_meat);
        tipsList.add(tip2_fruit);

        tipsList.add(tip2_poultry);
        tipsList.add(tip2_seafood);
        tipsList.add(tip3_fruit);

        tipsList.add(tip2_eggs);
        tipsList.add(tip3_vegetable);
        tipsList.add(tip3_poultry);

        tipsList.add(tip2_dairy);
        tipsList.add(tip3_fish);
        tipsList.add(tip3_eggs);

        tipsList.add(tip3_red_meat);
        tipsList.add(tip1_poultry);
        tipsList.add(tip2_pork);

        tipsList.add(tip1_seafood);
        tipsList.add(tip2_vegetable);
        tipsList.add(tip1_dairy);

        // Add the tips to the adapter
        adapter.setTipsList(tipsList);

        return rootView;
    }


    private static class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipViewHolder> {

        private List<Tip> tipsList;

        public TipsAdapter() {
        }

        public void setTipsList(List<Tip> tipsList) {
            this.tipsList = tipsList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public TipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_tips, parent, false);
            return new TipViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull TipViewHolder holder, int position) {
            Tip tip = tipsList.get(position);

            // Update the image
            holder.tipImageView.setImageResource(tip.getImageResId());

            // Update the text
            holder.tipTextView.setText(tip.getText());
        }

        @Override
        public int getItemCount() {
            return tipsList.size();
        }

        public static class TipViewHolder extends RecyclerView.ViewHolder {
            ImageView tipImageView;
            TextView tipTextView;

            public TipViewHolder(View itemView) {
                super(itemView);
                tipImageView = itemView.findViewById(R.id.tipImage);
                tipTextView = itemView.findViewById(R.id.tipText);
            }
        }
    }
}