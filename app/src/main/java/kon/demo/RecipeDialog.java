package kon.demo;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.Arrays;
import java.util.List;

import data.ControlPanel;
import data.Ingredient;
import data.Recipe;

public class RecipeDialog {
    RecipeDialog(Context con, ControlPanel cp, String recName) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(con);
        Recipe rec = (Recipe) cp.recs.get(recName);
        List<Ingredient> ingredientList = cp.getIngredientsFromRecipe(rec.name);
        Double[] haveAmount = new Double [ingredientList.size()];
        Double[] needAmount = new Double[ingredientList.size()];
        String[] ingredientName = new String [ingredientList.size()];
        int i=0;

        for(Ingredient ings: ingredientList){
            String ingredient=ings.name;
            needAmount[i]= ings.amountNeed;
            haveAmount[i]=((Ingredient)cp.ings.get(ings.name)).amountHave;

            int size=50-ingredient.length()- needAmount[i].toString().length();
            String gap= "";
            int index=0;
            while (index!=size) {
                gap=gap + " ";
                index++;
            }
            ingredient=ingredient+ gap  + needAmount[i] +"\n(Have: "+haveAmount[i]+" )";
            ingredientName[i]=ingredient;
            i++;
        }
        Arrays.sort(ingredientName);
        dialog.setTitle(rec.name + " :");






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
}
