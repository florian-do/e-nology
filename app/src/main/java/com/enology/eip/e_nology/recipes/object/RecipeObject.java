package com.enology.eip.e_nology.recipes.object;

/**
 * Created by Lolo on 27/02/2015.
 */
public class RecipeObject
{
    String recipeName;

    public RecipeObject(String recipeName)
    {
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
