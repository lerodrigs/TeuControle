package com.teucontrole.teucontrole.Models;

public class Item
{
    private String descricao;
    private int image;
    private int id;

    public void setDescricao(String _descricao)
    {
        this.descricao = _descricao;
    }

    public String getDescricao()
    {
        return this.descricao;
    }

    public void setImage(int _image)
    {
        this.image = _image;
    }

    public int getImage()
    {
        return this.image;
    }

    public void setId(int _id)
    {
        this.id = _id;
    }

    public int getId()
    {
        return this.id;
    }
}
