package com.pv.dota3.cli.profile.services;

import com.pv.dota3.cli.persistence.PersistenceType;
import com.pv.dota3.cli.persistence.ProfileDAOFactory;

/**
 * Created by pv on 23/10/16.
 */
public class ProfileServiceFactory {

    public static ProfileService getProfileService(PersistenceType type){
        return new ProfileService(ProfileDAOFactory.getProfilePersistencImpl(PersistenceType.FILE));
    }

}


