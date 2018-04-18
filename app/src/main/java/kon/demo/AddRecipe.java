package kon.demo;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import data.ControlPanel;
import data.Ingredient;
import data.Name;
import data.Unit;

import static data.Unit.getUnit;

public class AddRecipe extends CPActivity{

    String[] ingredientsName; //Todo remove if other todo is redundant

    //adapter
    MyArrayAdapter adapter2;

    //UI elements
    EditText recipe,ingredient,amount;
    Button add,save;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecipe);
        createView();

    }


    private void createView() {
        //UI elements
        recipe= (EditText) findViewById(R.id.addRecipeText);
        ingredient= (EditText) findViewById(R.id.addIngredientText);
        amount = (EditText) findViewById(R.id.ingredientAmount);
        add = (Button) findViewById(R.id.addNextIngredient);
        list = (ListView) findViewById(R.id.newingredients);
        save = (Button) findViewById(R.id.addRecipe);

        //adding listeners
        //ToDo remove, is reduntant
        recipe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    amount.setBackground(getDrawable(R.drawable.rounded));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

}

    public void saveIngredient(View view) {
        //get ingredient and recipe name and amount from the edittect
        String recName = recipe.getText().toString();
        String ingName = ingredient.getText().toString();
        String amountString = amount.getText().toString();
        Double am;

        //if ingredient filed is not empty it sets add button to clickable
        if (ingName.length()!=0) {
                //checks if it should be added to a recipe
                if (recName.length() != 0) {
                    saverecipe(view);

                    if (amountString.length() == 0) {
                        amount.setBackground(getDrawable(R.drawable.roundedred));
                    } else {
                        if (Name.isValid(ingName)) {
                            ingredient.setBackground(getDrawable(R.drawable.roundedred));
                        } else {
                            amount.setBackground(getDrawable(R.drawable.rounded));
                            ingredient.setBackground(getDrawable(R.drawable.roundedred));
                            am = Double.valueOf(amountString);

                            //no categories yet, so add ingredient without a unit
                            cp.addIngredient(ingName, "none", "NONE");
                            cp.addIngredientToRecipe(
                                    recName, ingName, "none", am, "NONE");
                            ingredient.setText("");
                            amount.setText("");

                            /*ToDo remove, is reduntant (not sure)
                            int index = 0;
                            List<Ingredient> ingrs = cp.getIngredientsFromRecipe(recName);
                            ingredientsName = new String[ingrs.size()];
                            for (Ingredient ings : ingrs) {
                                String ingrName = ings.name;
                                ingredientsName[index] = ingrName;
                                index++;
                            }
                            Arrays.sort(ingredientsName);
                            adapter2 = new MyArrayAdapter(this, ingredientsName, cp, true);
                            */
                        }
                    }

                } else { //when no recipe is given
                    add.setClickable(true);
                    //if amount is not given just adds the ingredient in the ingredientList
                    if (amountString == null || amountString.length() == 0) {
                        amountString = "0";
                    }
                    am = Double.valueOf(amountString);
                    cp.addIngredient(ingName, "none", "NONE");
                    cp.addIngredientToInventory(ingName, am);
                    ingredient.setText("");
                    amount.setText("");

                }
            }
            updateUI();
    }

    /**
     * updates the listview that represents all recipes
     */
    private void updateUI() {
        list.setAdapter(adapter2);
        cp.save();
    }

    /**
     * onclick listener that will lock the recipe name and
     * move the focus to the ingredient edittext
     */
    public void saverecipe(View view) {
        //get the recipe name
        String recName = recipe.getText().toString();

        //if a recipe name is present and does not contain illegal characters
        //add and lock recipe
        if (Name.isValid(recName)) {
            recipe.setBackground(getDrawable(R.drawable.rounded));
            recipe.setClickable(false);
            recipe.setFocusable(false);
            recipe.setTextColor(getResources().getColor(R.color.TextColor));
            add.setClickable(true);
            ingredient.setFocusable(true);
            amount.setClickable(true);
            saveRecipe(recName);
        } else { //show that the recipeName is incorrect
            recipe.setBackground(getDrawable(R.drawable.roundedred));
        }
    }

    /**
     * adds an recipe with no ingredients
     * @param str name of the new recipe
     */
    private void saveRecipe(String str) {
        cp.addRecipe(str);
        cp.save();
    }

    /**
     * listener for when the back viewbutton is pressed
     * does the same as pressing back
     */
    public void back(View view) {
        onBackPressed();
    }
}
