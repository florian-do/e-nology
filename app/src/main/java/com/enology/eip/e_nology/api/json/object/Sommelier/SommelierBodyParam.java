package com.enology.eip.e_nology.api.json.object.Sommelier;

import com.google.gson.annotations.Expose;

/**
 * Created by do_f on 19/11/15.
 */
public class SommelierBodyParam {
    @Expose
    private String  recipe;

    public SommelierBodyParam(String recipe) {
        this.recipe = recipe;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
}
