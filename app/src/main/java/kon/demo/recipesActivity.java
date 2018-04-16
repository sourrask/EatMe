package kon.demo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import data.ControlPanel;
import data.Ingredient;
import data.Name;
import data.RecipeList;

import java.text.ParsePosition;
import java.util.Arrays;
import java.util.List;

/*
 * Activity class for the recipes activity. In this activity we display the recipes in the database.
 * The back button of the phone will be available to click and will make the user return to the
 * home screen.
 */
public class recipesActivity extends AppCompatActivity {
    private Context context;
    ControlPanel cp;
    int i=0;
    String[] recipesName;
    MyArrayAdapter adapter;
    List<Ingredient> ingredientList;
    Double [] amount,haveAmount,needAmount;



    //search editText
    EditText searchText;
    String[] ingredientName;


    // Gets the template for the recipes activity by setting the content view to the desired layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_activity);
        context = getApplicationContext();


    }

    @Override
    protected void onResume() {
        super.onResume();
        cp = new ControlPanel(getApplicationContext());
        update();
    }

    //update listview with recipes
    public void update(){
        final ListView recipesView= (ListView) findViewById(R.id.listRecipe);
        RecipeList recipeList;
        recipeList = cp.recs;
        recipesName = new String[recipeList.size()];


        i = 0;
        for (Name recipe: recipeList){
            String name = recipe.getName();
            recipesName[i]=name;
            i++;
        }
        Arrays.sort(recipesName);
        adapter = new MyArrayAdapter(this, recipesName, cp,false);
        recipesView.setAdapter(adapter);

        //set a pop up view wih the ingredients for every recipe
        recipesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(recipesActivity.this );
                ingredientList=cp.getIngredientsFromRecipe(recipesName[position]);
                amount= new Double[ingredientList.size()];
                haveAmount =new Double [ingredientList.size()];
                needAmount = new Double[ingredientList.size()];
                i=0;
                ingredientName=new String [ingredientList.size()];

                for(Ingredient ings: ingredientList){
                    String ingredient=ings.name;
                    amount[i]= ings.amountNeed;
                    haveAmount[i]=ings.amountHave;

                    int size=60-ingredient.length()-amount[i].toString().length();
                    String gap= "";
                    int index=0;
                    while (index!=size) {
                        gap=gap + " ";
                        index++;
                    }
                    ingredient=ingredient+ gap  + amount[i] +"  (Have: "+haveAmount[i]+" )";
                    ingredientName[i]=ingredient;
                    i++;
                }
                Arrays.sort(ingredientName);
                dialog.setTitle(recipesName[position] + " :");






                dialog.setItems(ingredientName,null);//todo add maybe another onclikc on each ingredient?
                //what todo when using ings you have
                dialog.setNegativeButton(R.string.useWhatIHave, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        int index2=0;
//                        for (Ingredient ing : ingredientList) {
//                            cp.removeIngredientFromInventory(ingredientName[index2]);
//                            index2++;
//                        }
                    }
                });

                //what todo when you want to add missing to SL
                dialog.setNeutralButton(R.string.addMissingToList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                dialog.setPositiveButton(R.string.cancel, null);
                dialog.create();
                dialog.show();

            }
        });
        searchText= (EditText) findViewById(R.id.searchText);

        cp.save();





                //enable search
                searchText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    //when text is entered
                    @Override
                    public void onTextChanged(CharSequence cs, int start, int before, int count) {
                        recipesActivity.this.adapter.getFilter().filter(cs);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


    }


    // Back button that returns to the homescreen
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

    public void addrecipe(View view) {
        Intent add = new Intent(this, AddRecipe.class);
        startActivity(add);
    }


    //pop up dialog to delete a recipe
    public void deleteRecipe(View view) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle(R.string.removeRecipe);
        builder.setItems(recipesName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateDeleteRecommended(which);
            }
        });

        builder.setPositiveButton(R.string.finish,null);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    //deleting selected recipe from the dialog
    private void updateDeleteRecommended(int which) {
        cp.deleteRecipe(recipesName[which]);
        update();
    }
}
