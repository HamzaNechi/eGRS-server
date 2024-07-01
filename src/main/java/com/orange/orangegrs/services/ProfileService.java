package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.Profile;

import java.util.List;

public interface ProfileService {


    List<Profile> getAllProfiles();




    Profile updateProfile(Profile profile);
}
