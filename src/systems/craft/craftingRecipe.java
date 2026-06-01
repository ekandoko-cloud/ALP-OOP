package systems.craft;

import models.item.Ingredients;
import models.item.Item;

import java.util.ArrayList;

public class craftingRecipe {
    private String recipeName;
    private Item resultItem;

    public static class IngredientReq {
        private Ingredients ingredient;
        private int amount;

        public IngredientReq(Ingredients ingredient, int amount) {
            this.ingredient = ingredient;
            this.amount = amount;
        }

        public Ingredients getIngredient() { return ingredient; }
        public int getAmount() { return amount; }
    }

    private ArrayList<IngredientReq> requiredIngredients;

    public craftingRecipe(String recipeName, Item resultItem, ArrayList<IngredientReq> requiredIngredients) {
        this.recipeName = recipeName;
        this.resultItem = resultItem;
        this.requiredIngredients = requiredIngredients;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Item getResultItem() {
        return resultItem;
    }

    public void setResultItem(Item resultItem) {
        this.resultItem = resultItem;
    }

    public ArrayList<IngredientReq> getRequiredIngredients() {
        return requiredIngredients;
    }

    public void setRequiredIngredients(ArrayList<IngredientReq> requiredIngredients) {
        this.requiredIngredients = requiredIngredients;
    }
}