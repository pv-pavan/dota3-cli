package com.pv.dota3.cli.profile.services;

import com.pv.dota3.cli.profile.Profile;
import com.pv.dota3.cli.persistence.ProfilePersistence;

import java.util.List;

/**
 * Created by pv on 15/10/16.
 */
public class ProfileService {

    private ProfilePersistence profileDao;

    protected ProfileService(ProfilePersistence profileDao) {
        this.profileDao = profileDao;
    }

    public List<Profile> listProfiles(){
        return profileDao.getAll();
    }


    public Profile save(Profile character){
        return profileDao.save(character);
    }

    public Profile getByName(String n){
        return profileDao.findByName(n);
    }

    public Profile create(String name){
        Profile c =  new Profile(name);
        return save(c);

    }
}
