package kon.demo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class AddRecipe extends CPActivity{

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

                    if (!cp.isValidName(ingName)) {
                        ingredient.setBackground(getDrawable(R.drawable.roundedred));
                    } else {
                        if (amountString.length() == 0) {
                            amount.setBackground(getDrawable(R.drawable.roundedred));
                        } else {
                            amount.setBackground(getDrawable(R.drawable.rounded));
                            ingredient.setBackground(getDrawable(R.drawable.rounded));
                            am = Double.valueOf(amountString);

                            //no categories/units yet, so add ingredient without a category/unit
                            cp.addIngredient(ingName, "none", "NONE");
                            cp.addIngredientToRecipe(
                                    recName, ingName, "none", am, "NONE");
                            ingredient.setText("");
                            amount.setText("");
                        }
                    }

                } else { //when no recipe is given
                    add.setClickable(true);
                    //if amount is not given just adds the ingredient in the ingredientList
                    if (!cp.isValidName(ingName)) {
                        ingredient.setBackground(getDrawable(R.drawable.roundedred));
                    } else {
                        ingredient.setBackground(getDrawable(R.drawable.rounded));
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
            }
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
        if (cp.isValidName(recName)) {
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
