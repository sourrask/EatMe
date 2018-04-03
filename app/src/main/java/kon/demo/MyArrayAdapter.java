package kon.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import data.ControlPanel;
import data.Ingredient;
import data.Name;
import data.Recipe;


public class MyArrayAdapter extends ArrayAdapter<String> {

    private final String[] recipes;
    private Activity context;
    private ListView listView;
    ControlPanel cp;
    String string,str;
    Boolean bool;



    public MyArrayAdapter(Activity context, String[] recipes, ControlPanel cp, boolean bool) {
        super(context,R.layout.rowlayout, recipes);
        this.recipes=recipes;
        this.context=context;
        this.cp=cp;
        this.bool=bool;



    }
    static class ViewHolder {
        public TextView text;
        public ImageView send;
        public ImageView delete;
        public ImageView edit;
        ViewHolder(View v){
            text=(TextView)v.findViewById(R.id.recipeText);
            send=(ImageView) v.findViewById(R.id.send);
            edit=(ImageView) v.findViewById(R.id.edit);
            delete=(ImageView) v.findViewById(R.id.delete);


        }
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent){
        View row=convertView;
        ViewHolder viewHolder=null;
        if (row==null) {
            LayoutInflater inflater = (LayoutInflater) context.getLayoutInflater();
            row = inflater.inflate(R.layout.rowlayout, null,true);
            viewHolder=new ViewHolder(row);
            viewHolder.text=(TextView)row.findViewById(R.id.recipeName);
            viewHolder.send=(ImageView)row.findViewById(R.id.send);
            viewHolder.delete=(ImageView)row.findViewById(R.id.delete);
            viewHolder.edit=(ImageView)row.findViewById(R.id.edit);
            row.setTag(viewHolder);
            row.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) row.getTag();
        }
        viewHolder.text.setText(recipes[position]);
        if (bool=true){
            viewHolder.send.setVisibility(View.INVISIBLE);
            viewHolder.edit.setVisibility(View.INVISIBLE);
            viewHolder.delete.setVisibility(View.INVISIBLE);
        }
        //todo check
//        if (bool=true) {
//            row.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String string=recipes[position];
//                    List<Ingredient> ingredients;
//                    ingredients=cp.getIngredientsFromRecipe(string);
//                    String[] ings = new String [ingredients.size()];
//                    int index=0;
//
//                    for (Ingredient ing: ingredients){
//                        String str=ing.getName();
//                        ings[index]=str;
//                        index++;
//                    }
//                    Arrays.sort(ings);
//                    MyIngredientAdapter myIngredientAdapter= new MyIngredientAdapter(context,ings);
//                    listView.setAdapter(myIngredientAdapter);
//                    showDialogListView(v);
//
//                }
//            });
//        }

        return row;
    }

//    private void showDialogListView(View v) {
//        AlertDialog.Builder builder=new AlertDialog.Builder(context);
//        builder.setCancelable(true);
//        builder.setNegativeButton(R.string.cancel,null);
//        builder.setNegativeButton(R.string.missingToShopping,null);
//        builder.setNeutralButton(R.string.removeAvailable,null);
//        builder.setView(listView);
//        AlertDialog dialog=builder.create();
//        dialog.show();
//    }
//

}
