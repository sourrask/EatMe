package kon.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import data.ControlPanel;
import data.Ingredient;
import data.Unit;

import static data.Unit.getUnit;

public class AddRecipe extends AppCompatActivity{

    ControlPanel cp;
    String[] ingredientsName;
    MyArrayAdapter adapter2;
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
        recipe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pressed=true;

            }

            @Override
            public void afterTextChanged(Editable s) {
                pressed=true;
            }
        });

}

    public void saveIngredient(View view) {
        if (pressed=true) {

                    String unit="NONE";
                    Unit units=getUnit(unit);
                    String ingredientName = ingredient.getText().toString();
                    amountOf =Double.valueOf(amount.getText().toString());
                    cp.addIngredient(ingredientName,"none",unit);
                    cp.addIngredientToRecipe(str, ingredientName, "none", amountOf, 0,units);
                    ingredient.setText("");
                    amount.setText("");
                    int index = 0;
                    List<Ingredient> ingrs = cp.getIngredientsFromRecipe(str);

                    ingredientsName = new String[ingrs.size()+1];
                    for (Ingredient ings : ingrs) {
                        String ingrName = ings.name;
                        ingredientsName[index] = ingrName;
                        index++;
                    }
                    Arrays.sort(ingredientsName);
                    adapter2 = new MyArrayAdapter(this, ingredientsName, cp, true);

                    update();

        }
    }

    private void update() {
        list.setAdapter(adapter2);
        cp.save();
    }

    public void saverecipe(View view) {
            str = recipe.getText().toString();
            if (str!=null) {
                recipe.setClickable(false);
                recipe.setFocusable(false);
                recipe.setTextColor(getResources().getColor(R.color.TextColor));
                add.setClickable(true);
                ingredient.setFocusable(true);
                amount.setClickable(true);
                saveRecipe(str);

            }
    }

    private void saveRecipe(String str) {
        cp.addRecipe(str);
        cp.save();
    }
}
