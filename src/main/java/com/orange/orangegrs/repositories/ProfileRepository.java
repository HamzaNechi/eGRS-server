package com.orange.orangegrs.repositories;

import com.orange.orangegrs.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Short> {
}
