package com.enology.eip.e_nology.catalog.object;

/**
 * Created by Lolo on 27/02/2015.
 */
public class CatalogObject
{
    String recipeName;

    public CatalogObject(String recipeName)
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
