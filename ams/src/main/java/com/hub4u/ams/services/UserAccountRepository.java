package com.hub4u.ams.services;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hub4u.ams.frwk.UserNotFoundException;
import com.hub4u.ams.model.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

	Optional<UserAccount> findByUserName(String userName) throws UserNotFoundException;
}
