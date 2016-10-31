package com.pv.dota3.cli.menu.beans;

import com.pv.dota3.cli.menu.enums.MenuOptionType;

/**
 * Created by pv on 15/10/16.
 */
public class MenuOption {
    private String label;
    private MenuOptionType type;

    public MenuOption(String label, MenuOptionType type) {
        this.label = label;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public MenuOptionType getType() {
        return type;
    }

    public void setType(MenuOptionType type) {
        this.type = type;
    }
}
