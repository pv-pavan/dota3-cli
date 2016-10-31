package com.pv.dota3.cli.manager.enums;

import com.pv.dota3.cli.menu.beans.Menu;
import com.pv.dota3.cli.menu.beans.MenuOption;
import com.pv.dota3.cli.menu.enums.MenuOptionType;


/**
 * Created by pv on 22/10/16.
 */
public enum GameState {

    INITIAL,
    PROFILE,
    GAME,
    GAME_FIGHT;

    ;
    private Menu masterMenu;


    public Menu getMasterMenu() {
        return masterMenu;
    }

    public void setMasterMenu(Menu masterMenu) {
        this.masterMenu = masterMenu;
    }

    static{
        INITIAL.setMasterMenu(
                new Menu()
                        .addOption(new MenuOption("Create new character", MenuOptionType.CREATE_PROFILE))
        );

        PROFILE.setMasterMenu(
                new Menu().addOption(new MenuOption("Create new game", MenuOptionType.CREATE_GAME))
        );


        GAME.setMasterMenu(
                new Menu()
                        .addOption(new MenuOption("Explore", MenuOptionType.GAME_EXPLORE))
                        .addOption(new MenuOption("Save and exit", MenuOptionType.SAVE_EXIT))
        );

        GAME_FIGHT.setMasterMenu(
                new Menu()
                        .addOption(new MenuOption("Fight", MenuOptionType.GAME_FIGHT))
                        .addOption(new MenuOption("Explore more", MenuOptionType.GAME_EXPLORE))
                        .addOption(new MenuOption("Save and exit", MenuOptionType.SAVE_EXIT))

        );

    }
}
