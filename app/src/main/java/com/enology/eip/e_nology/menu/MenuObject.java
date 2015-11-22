package com.enology.eip.e_nology.menu;

/**
 * Created by Lolo on 09/11/2014.
 */
public class MenuObject
{
    private int image = 0;

    private String text = null;

    private String number = null;

    public MenuObject(int image, String text, String number)
    {
        this.image = image;
        this.text = text;
        this.number = number;
    }

    public void setImage(int image)
    {
        this.image = image;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public int getImage()
    {
        return image;
    }

    public String getText()
    {
        return text;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }
}
