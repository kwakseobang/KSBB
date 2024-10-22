package com.kms.SBB.repository.user;

import com.kms.SBB.entitiy.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser,Long> {
}
