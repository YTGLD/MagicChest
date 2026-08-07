package com.ytgld.magic_chest.item;

public class BaseSoul extends BaseItem{
    private final int color;

    public BaseSoul(Properties properties, int color) {
        super(properties);
        this.color = color;
    }


    @Override
    public int color() {
        return color;
    }
}
