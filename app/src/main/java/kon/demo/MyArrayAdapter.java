package kon.demo;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MyArrayAdapter extends ArrayAdapter<String> {

    private final String[] recipes;
    private Activity context;

    public MyArrayAdapter(Activity context, String[] recipes) {
        super(context,R.layout.rowlayout, recipes);
        this.recipes=recipes;
        this.context=context;


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
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
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

        return row;
    }
//    @Override
//    public int getCount(){
//        return recipes.length;
//    }
//    @Override
//    public String getItem(int i){
//        return null;
//    }
//    @Override
//    public long getItemId(int i){
//        return 0;
//    }


}
