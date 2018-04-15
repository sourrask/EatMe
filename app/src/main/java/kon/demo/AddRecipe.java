package kon.demo;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import data.ControlPanel;
import data.Ingredient;

public class AddRecipe extends AppCompatActivity{

    ControlPanel cp;
    String[] ingredientsName;
    MyArrayAdapter adapter2;
    int first=0;
    Boolean pressed;
    EditText recipe,ingredient,amount;
    Button add,save;
    ListView list;
    String str=null;
    Double amountOf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecipe);
        pressed=false;
        createView();
        cp = new ControlPanel(getApplicationContext());

    }

    @Override
    protected void onResume() {
        super.onResume();
        cp = new ControlPanel(getApplicationContext());
    }

    private void createView() {


        recipe= (EditText) findViewById(R.id.addRecipeText);
        ingredient= (EditText) findViewById(R.id.addIngredientText);
        amount = (EditText) findViewById(R.id.ingredientAmount);
        add = (Button) findViewById(R.id.addNextIngredient);
        list = (ListView) findViewById(R.id.newingredients);
        save = (Button) findViewById(R.id.addRecipe);

//        add.setClickable(false);
//        ingredient.setFocusable(false);
//        amount.setFocusable(false);

}

    public void saveIngredient(View view) {
        if (pressed=true) {


                    String ingredientName = ingredient.getText().toString();
                    amountOf =Double.valueOf(amount.getText().toString());
                    cp.addIngredientToRecipe(str, ingredientName, null, amountOf, 0, "");
                    ingredient.setText("");
                    amount.setText("0");
                    int index = 0;
                    List<Ingredient> ingrs = cp.getIngredientsFromRecipe(str);
                    ingredientsName = new String[ingrs.size()];
                    for (Ingredient ings : ingrs) {
                        String ingrName = ings.name;
                        ingredientsName[index] = ingrName;
                        index++;
                    }

                    Arrays.sort(ingredientsName);

                    adapter2 = new MyArrayAdapter(AddRecipe.this, ingredientsName, cp, true);
                    list.setAdapter(adapter2);

        }
    }

    public void saverecipe(View view) {
            str = recipe.getText().toString();
            if (str!=null) {
                pressed = true;
                recipe.setClickable(false);
                recipe.setFocusable(false);
                recipe.setTextColor(getResources().getColor(R.color.TextColor));
                add.setClickable(true);
                ingredient.setFocusable(true);
                amount.setClickable(true);
                saveRecipe(str); //saveRecipe not working error
            }
    }

    private void saveRecipe(String str) {
        cp.addRecipe(str);
        cp.save();
    }
}
