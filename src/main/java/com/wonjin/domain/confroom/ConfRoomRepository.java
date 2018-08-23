package com.wonjin.domain.confroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfRoomRepository extends JpaRepository<ConfRoom, Long> {

}
