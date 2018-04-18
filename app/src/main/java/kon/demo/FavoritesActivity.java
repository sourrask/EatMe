package kon.demo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import data.Recipe;


/*
 * Activity class for the favorites activity. In this activity we display the favorite recipes.
 * We also use the back button of the phone to return to the homescreen.
 */
public class FavoritesActivity extends CPActivity {
    //lists
    List<Recipe> favoritesList;
    List<Recipe> nonFavoritesList;
    String[] favoritesName;
    String[] nonFavoritesName;

    //adapter
    MyArrayAdapter adapter;

    //view elements
    EditText searchText;
    ListView favoritesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    /**
     * update UI with recipes
     */
    private void updateUI() {
        //UI elements
        favoritesView = (ListView) findViewById(R.id.favoritesList);
        searchText = (EditText) findViewById(R.id.searchFavorites);

        //lists
        favoritesList = cp.getRecommendedRecipes();
        favoritesName = EatMeTools.listToRecipeNameArray(favoritesList);

        nonFavoritesList = cp.getAllRecipe();
        nonFavoritesList.removeAll(favoritesList);
        nonFavoritesName = EatMeTools.listToRecipeNameArray(nonFavoritesList);

        Arrays.sort(favoritesName);
        Arrays.sort(nonFavoritesName);

        //get adapter
        adapter = new MyArrayAdapter(this, favoritesName,cp,false);
        favoritesView.setAdapter(adapter);

        //add listeners
        favoritesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                (new RecipeDialog(FavoritesActivity.this, cp, favoritesName[position])).build();
            }
        });

        //enable search
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                FavoritesActivity.this.adapter.getFilter();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        cp.save();
    }

    /**
     * listener for the addrecipe button
     * opens the dialog for adding recipes to favorite
     */
    public void addrecipe(View view) {
        buildAlertDialog(R.string.addToFavorites, nonFavoritesName);
    }

    /**
     *
     * @param recipeName recipe name that changes away/to recommended
     */
    private void updateRecommended(String recipeName) {
        cp.changeRecommended(recipeName);
        updateUI();
    }

    /**
     * listener for the removerecipe button
     * opens the dialog for removing recipes from favorite
     */
    public void removeRecipe(View view) {
        buildAlertDialog(R.string.removeFromFavorites, favoritesName);
    }

    /**
     * builds an alert dialog for adding and removing from favorites
     * @param title title for the alertDialog (R.string. expected)
     * @param items list of string to be shown in the dialog
     */
    private void buildAlertDialog(int title, final String[] items) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateRecommended(items[which]);
            }
        });

        builder.setPositiveButton(R.string.finish,null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
