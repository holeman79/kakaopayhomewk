package com.wonjin.domain.reserveinfo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wonjin.domain.dto.ReserveInfoRequestDto;

public class ReserveInfoRepositoryImpl implements ReserveInfoCustomRepository{
	
	@PersistenceContext
	private EntityManager em;
	@Override
	public List<ReserveInfoRequestDto> getReserveRequestList(String date) {
		Query q = em.createNativeQuery(getReserveRequestListQuery(date), "ReserveInfoRequestDto");
		List<ReserveInfoRequestDto> result = q.getResultList();
		return result;
	}
	public String getReserveRequestListQuery(String date) {
		StringBuilder sb = new StringBuilder(RESERVE_REQUEST_QUERY);
		sb.append(" WHERE A.DATE = '" + date +"'");
		return sb.toString();
	}
	private static String RESERVE_REQUEST_QUERY = " "
			+ " SELECT A.DATE, A.TIME_FROM, A.TIME_TO, A.CONF_ID, B.NAME AS CONF_NAME, A.RESERVER, A.REPEAT_COUNT \n"
			+ " FROM RESERVE_INFO A \n"
			+ " JOIN CONF_ROOM B \n"
			+ " ON A.CONF_ID = B.ID \n" ;
}
