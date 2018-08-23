package com.wonjin.domain.reserveinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveInfoRepository extends JpaRepository<ReserveInfo, Long>, ReserveInfoCustomRepository {
	
}
