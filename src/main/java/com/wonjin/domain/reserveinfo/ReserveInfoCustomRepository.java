package com.wonjin.domain.reserveinfo;

import java.util.List;

import com.wonjin.domain.dto.ReserveInfoRequestDto;

public interface ReserveInfoCustomRepository {
	List<ReserveInfoRequestDto> getReserveRequestList(String date);

}
