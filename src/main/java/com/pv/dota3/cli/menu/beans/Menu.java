package com.pv.dota3.cli.menu.beans;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pv on 15/10/16.
 */
public class Menu {
    private Map<Integer, MenuOption> options;
    private int currentIndex = 0;

    public Map<Integer, MenuOption> getOptions() {
        if(options == null){
            options = new LinkedHashMap<>();
        }
        return options;
    }

    public Menu addOption(MenuOption option){
        this.getOptions().put(++currentIndex, option);
        return this;
    }

    public Menu addOptions(Menu masterMenu){
        for(MenuOption o: masterMenu.getOptions().values()){
            this.getOptions().put(++currentIndex, o);
        }
        return this;
    }
}
