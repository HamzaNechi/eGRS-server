package com.orange.orangegrs.services;


import com.orange.orangegrs.entities.Profile;
import com.orange.orangegrs.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService{


    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile addProfile(Profile profile) {
        return profileRepository.save(profile);
    }
}
