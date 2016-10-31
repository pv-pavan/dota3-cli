package com.pv.dota3.cli.persistence;

import com.pv.dota3.cli.profile.Profile;

import java.util.List;

/**
 * Created by pv on 15/10/16.
 */
public interface ProfilePersistence {
    Profile save(Profile character);
    List<Profile> getAll();
    Profile findByName(String name);
    void delete(String name);

}
