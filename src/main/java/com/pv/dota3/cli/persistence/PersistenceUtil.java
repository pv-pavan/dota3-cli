package com.pv.dota3.cli.persistence;

import com.pv.dota3.cli.profile.services.ProfileServiceFactory;

/**
 * Created by pv on 31/10/16.
 */
public class PersistenceUtil {

    private static String profilesLoc = "./profiles/";

    public static String getProfilesLoc() {
        return profilesLoc;
    }

    public static void setProfilesLoc(String profilesLoc) {
        PersistenceUtil.profilesLoc = profilesLoc;
    }
}
