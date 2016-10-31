package com.pv.dota3.cli.persistence;

/**
 * Created by pv on 22/10/16.
 */
public class ProfileDAOFactory {


   public static ProfilePersistence getProfilePersistencImpl(PersistenceType type){
        switch (type){
            case FILE:
                return new ProfileFilePersistenceImpl();
            default:
                return new ProfileFilePersistenceImpl();
        }
    }
}
