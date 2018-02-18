package ru.gitstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gitstats.model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {


}
