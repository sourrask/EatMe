package kon.demo;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import data.ControlPanel;
import data.Ingredient;
import data.Recipe;

public class RecipeDialog {
    RecipeDialog(Context con, final ControlPanel cp, String recName) {
        System.out.println(recName);
        AlertDialog.Builder dialog = new AlertDialog.Builder(con);
        Recipe rec = (Recipe) cp.recs.get(recName);
        final List<Ingredient> ingredientList = cp.getIngredientsFromRecipe(rec.name);
        final Double[] haveAmount = new Double [ingredientList.size()];
        final Double[] needAmount = new Double[ingredientList.size()];
        final Double[] calculatedAmount= new Double[ingredientList.size()];
        final String[] ingredientName = new String [ingredientList.size()];
        final String[] ingredientRemove= new String [ingredientList.size()];
        int i=0;

        for(Ingredient ings: ingredientList){
            String ingredient=ings.name;
            needAmount[i]= ings.amountNeed;
            haveAmount[i]=((Ingredient)cp.ings.get(ings.name)).amountHave;
            ingredientRemove[i]=ingredient;

            ingredientName[i]=ingredient+ "\n"  + needAmount[i] +"\n(Have: "+haveAmount[i]+" )";
            i++;
        }
        Arrays.sort(ingredientName);
        dialog.setTitle(rec.name + " :");






        dialog.setItems(ingredientName,null);//todo add maybe another onclikc on each ingredient?
        //what todo when using ings you have
        dialog.setNegativeButton(R.string.useWhatIHave, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                        int index2=0;
                        for (Ingredient ing : ingredientList) {
                            calculatedAmount[index2]=haveAmount[index2]-needAmount[index2];
                            cp.removeIngredientFromInventory(ingredientRemove[index2]);
                            if (calculatedAmount[index2]>0){
                                cp.addIngredientToInventory(ingredientRemove[index2],calculatedAmount[index2]);
                                cp.save();
                            }
                            index2++;
                        }
            }
        });

        //what todo when you want to add missing to SL
        dialog.setNeutralButton(R.string.addMissingToList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int index2=0;
                for (Ingredient ing : ingredientList) {
                    calculatedAmount[index2]=haveAmount[index2]-needAmount[index2];
                    cp.removeIngredientFromInventory(ingredientRemove[index2]);
                    if (calculatedAmount[index2]>0){
                        cp.addIngredientToInventory(ingredientRemove[index2],calculatedAmount[index2]);
                        cp.save();
                    }else{
                        double inverted=calculatedAmount[index2]*(-1);
                        cp.addIngredientToShoppingList(ingredientRemove[index2],inverted);
                        cp.save();
                    }
                    index2++;
                }
            }
        });
        dialog.setPositiveButton(R.string.cancel, null);
        dialog.create();
        dialog.show();
    }
}
