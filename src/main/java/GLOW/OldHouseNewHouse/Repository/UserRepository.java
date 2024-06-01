package GLOW.OldHouseNewHouse.repository;

import GLOW.OldHouseNewHouse.Data.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
