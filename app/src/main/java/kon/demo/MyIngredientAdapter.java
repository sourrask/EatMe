package kon.demo;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import data.ControlPanel;
import data.Ingredient;


//used in InventoryActivity to get all ingredients and allow to add them to the inventory
public class MyIngredientAdapter extends ArrayAdapter<String> {
    //lists
    private final String[] ingredients;
    private final double[] ingredientsAmount;

    //whether for inventory
    boolean isInventory;

    //context
    private Activity context;

    //control panel
    ControlPanel cp;

    public MyIngredientAdapter(Activity context, String[] resource, boolean isInv) {
        super(context,R.layout.add_remove_rowlayout, resource);
        this.ingredients = resource;
        this.isInventory = isInv;
        this.context = context;
        cp = new ControlPanel(context);

        //get all ingredient amounts
        ingredientsAmount = new double[resource.length];
        for (int i = 0 ; i < resource.length; i++) {
            if (isInv) {
                ingredientsAmount[i] = ((Ingredient)cp.ings.get(resource[i])).amountHave;
            } else {
                ingredientsAmount[i] = ((Ingredient)cp.ings.get(resource[i])).amountNeed;
            }
        }
    }

    /**
     * view holder
     */
    static class ViewHolder {
        public TextView text, amount;
        public ImageView add;
        public ImageView minus;
        ViewHolder(View v){
            text = (TextView)v.findViewById(R.id.ingredientName);
            add = (ImageView) v.findViewById(R.id.add);
            minus = (ImageView) v.findViewById(R.id.minus);
            amount = (TextView) v.findViewById(R.id.numberAdding);
        }
    }


    //create row view with buttons and text, set limit to button minus
    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent){
        View row = convertView;

        final MyIngredientAdapter.ViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.add_remove_rowlayout, null,true);
            viewHolder = new ViewHolder(row);
            viewHolder.text = (TextView)row.findViewById(R.id.ingredientName);
            viewHolder.amount = (TextView) row.findViewById(R.id.numberAdding);
            viewHolder.add = (ImageView)row.findViewById(R.id.add);
            viewHolder.minus = (ImageView)row.findViewById(R.id.minus);
            row.setTag(viewHolder);
            row.setTag(viewHolder);
            row.setOnClickListener(null);
        } else {
            viewHolder=(MyIngredientAdapter.ViewHolder) row.getTag();
        }

        String amountString = Double.toString(ingredientsAmount[position]);
        viewHolder.amount.setText(amountString);

        //add listeners
        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add amount
                ingredientsAmount[position]++;
                String amountString = Double.toString(ingredientsAmount[position]);
                viewHolder.amount.setText(amountString);
                viewHolder.minus.setClickable(true);

            }


        });

        viewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //decrease amount
                if (ingredientsAmount[position] > 0) {
                    ingredientsAmount[position]--;
                    String amountString = Double.toString(ingredientsAmount[position]);
                    viewHolder.amount.setText(amountString);
                } else {
                    viewHolder.minus.setClickable(false);
                }
            }


        });
        viewHolder.text.setText(ingredients[position]);

        return row;
    }

    /**
     *
     * @return the array with all amounts
     */
    public double[] getAmounts() {
        return ingredientsAmount;
    }
}