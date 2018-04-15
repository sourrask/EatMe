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


//used in inventoryActivity to get all ingredients and allow to add them to the inventory
public class MyIngredientAdapter extends ArrayAdapter<String> {
    private final String[] ingredients;
    boolean isInventory;
    private Activity context;
    String str;
    double counter;

    ControlPanel cp;

    public MyIngredientAdapter(Activity context, String[] resource, boolean isInv) {
        super(context,R.layout.add_remove_rowlayout, resource);
        this.ingredients=resource;
        this.isInventory = isInv;
        this.context=context;
        cp = new ControlPanel(context);
    }
    static class ViewHolder {
        public TextView text, amount;
        public ImageView add;
        public ImageView minus;
        ViewHolder(View v){
            text=(TextView)v.findViewById(R.id.ingredientName);
            add=(ImageView) v.findViewById(R.id.add);
            minus=(ImageView) v.findViewById(R.id.minus);
            amount= (TextView) v.findViewById(R.id.numberAdding);


        }
    }


    //create row view with buttons and text, set limit to button minus
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        View row=convertView;

        MyIngredientAdapter.ViewHolder viewHolder=null;
        if (row==null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.add_remove_rowlayout, null,true);
            viewHolder=new ViewHolder(row);
            viewHolder.text=(TextView)row.findViewById(R.id.ingredientName);
            viewHolder.amount=(TextView) row.findViewById(R.id.numberAdding);
            viewHolder.add=(ImageView)row.findViewById(R.id.add);
            viewHolder.minus=(ImageView)row.findViewById(R.id.minus);
            row.setTag(viewHolder);
            row.setTag(viewHolder);
            row.setOnClickListener(null);
        }else{
            viewHolder=(MyIngredientAdapter.ViewHolder) row.getTag();
        }
        //create the counter
        int i = 0;
        Ingredient ing = (Ingredient) cp.ings.get(ingredients[position]);
        if (isInventory) {
            counter = ing.amountHave;
        } else {
            counter = ing.amountHave;
        }
        i++;

        str= Double.toString(counter);
        viewHolder.amount.setText(str);

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter++;
                str=Double.toString(counter);
                finalViewHolder.amount.setText(str);
                finalViewHolder.minus.setClickable(true);

            }


        });

        final ViewHolder finalViewHolder1 = viewHolder;
        viewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (counter>0) {
                    counter--;
                    str=Double.toString(counter);
                    finalViewHolder1.amount.setText(str);
                }else finalViewHolder.minus.setClickable(false);
            }


        });
        viewHolder.text.setText(ingredients[position]);

        return row;
    }


}