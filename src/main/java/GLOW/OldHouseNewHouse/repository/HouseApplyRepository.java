package GLOW.OldHouseNewHouse.repository;

import GLOW.OldHouseNewHouse.data.entity.HouseApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseApplyRepository extends JpaRepository<HouseApply, Long> {
    List<HouseApply> findByHouseId(Long id);
}

