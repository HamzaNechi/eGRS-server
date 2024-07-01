package com.orange.orangegrs.services;


import com.orange.orangegrs.entities.Profile;
import com.orange.orangegrs.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService{


    @Autowired
    private ProfileRepository profileRepository;



    @Override
    public List<Profile> getAllProfiles() {
        return this.profileRepository.findAll();
    }



    @Override
    public Profile updateProfile(Profile profile) {
        return this.profileRepository.save(profile);
    }
}
