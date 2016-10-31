package com.pv.dota3.cli.profile;

import com.pv.dota3.cli.game.ExplorableGame;
import com.pv.dota3.cli.game.Game;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pv on 14/10/16.
 */
public class Profile implements Serializable {

    private String name;
    private Set<Game> games;

    public Profile(String name) {
        this.name = name;
        this.games = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
