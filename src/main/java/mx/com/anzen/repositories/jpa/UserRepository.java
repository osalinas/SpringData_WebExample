package mx.com.anzen.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.anzen.model.jpa.impl.UserJPAImpl;

public interface UserRepository extends JpaRepository<UserJPAImpl, String> {

}
