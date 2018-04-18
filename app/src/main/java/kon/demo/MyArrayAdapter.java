package kon.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

    //control panel
    ControlPanel cp;

    //recipe names
    private final String[] recipes;

    //context
    private Activity context;

    //add extra stuff
    Boolean noSendDeleteEdit;

    public MyArrayAdapter(
            Activity context, String[] recipes, ControlPanel cp, boolean noSendDeleteEdit) {
        super(context,R.layout.rowlayout, recipes);
        this.recipes = recipes;
        this.context = context;
        this.cp = cp;
        this.noSendDeleteEdit = noSendDeleteEdit;

    }

    /**
     * view holder
     */
    static class ViewHolder {
        public TextView text;
        public ImageView send;
        public ImageView delete;
        public ImageView edit;
        ViewHolder(View v){
            text = (TextView)v.findViewById(R.id.recipeText);
            send = (ImageView) v.findViewById(R.id.send);
            edit = (ImageView) v.findViewById(R.id.edit);
            delete = (ImageView) v.findViewById(R.id.delete);

        }
    }

    //get listView row and set buttons
    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent){
        View row = convertView;
        ViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.rowlayout, null,true);
            viewHolder = new ViewHolder(row);
            viewHolder.text = (TextView)row.findViewById(R.id.recipeName);
            viewHolder.send = (ImageView)row.findViewById(R.id.send);
            viewHolder.delete = (ImageView)row.findViewById(R.id.delete);
            viewHolder.edit = (ImageView)row.findViewById(R.id.edit);
            row.setTag(viewHolder);
            row.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) row.getTag();
        }
        viewHolder.text.setText(recipes[position]);

        //whether to add extra buttons
        if (noSendDeleteEdit=true){
            viewHolder.send.setVisibility(View.INVISIBLE);
            viewHolder.edit.setVisibility(View.INVISIBLE);
            viewHolder.delete.setVisibility(View.INVISIBLE);
        }
        //ToDo remove
//      row.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//
//              AlertDialog.Builder recipeDialog = new AlertDialog.Builder(getContext());
//              recipeDialog.setTitle(recipes[position]);
//              recipeDialog.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
//                  @Override
//                  public void onClick(DialogInterface dialog, int which) {
//                      cp.deleteRecipe(recipes[position]);
//                  }
//              });
//              recipeDialog.setNegativeButton(R.string.cancel,null);
//          }
//      });
        return row;
    }
}
