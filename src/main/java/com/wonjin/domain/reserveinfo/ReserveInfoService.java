package com.wonjin.domain.reserveinfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonjin.domain.dto.ReserveInfoRequestDto;

@Service
public class ReserveInfoService {
	@Autowired
	private ReserveInfoRepository reserveInfoRepository;
	
	public List<ReserveInfoRequestDto> getReserveInfoRequestList(String date){
		 return reserveInfoRepository.getReserveRequestList(date);
	}
	public ReserveInfo insertReserveInfoRequest(ReserveInfo reserveInfo) {
		ReserveInfo result = null;
		List<ReserveInfo> resultList = new ArrayList<>();
		if(reserveCheckRepeat(reserveInfo, resultList)) {
			for(ReserveInfo item : resultList) {
				result = reserveInfoRepository.save(item);
			}
		}else{result = null;}
		return result;		
	}
	
	public boolean reserveCheck(ReserveInfo reserveInfo) {
		List<ReserveInfoRequestDto> reserveList = getReserveInfoRequestList(reserveInfo.getDate());
		int confId = reserveInfo.getConfId();
		int timeFromConvert = Integer.parseInt(reserveInfo.gettimeFrom().replace(":",""));
		int timeToConvert = Integer.parseInt(reserveInfo.gettimeTo().replace(":",""));
		for (int i = 0; i < reserveList.size(); i++) {
			ReserveInfoRequestDto reserveItem = reserveList.get(i);
			int prevTimeFromConvert = Integer.parseInt(reserveItem.gettimeFrom().replace(":",""));
			int prevTimeToConvert = Integer.parseInt(reserveItem.gettimeTo().replace(":",""));
			if(confId == reserveItem.getConfId() && timeFromConvert < prevTimeToConvert && timeToConvert > prevTimeFromConvert) {
				return false;
			}
		}
		return true;
	}
	public boolean reserveCheckRepeat(ReserveInfo reserveInfo, List<ReserveInfo> resultList) {
		int repeatCount = reserveInfo.getRepeatCount();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < repeatCount; i++) {
			ReserveInfo item = new ReserveInfo();
			String date = reserveInfo.getDate();
			try {
				cal.setTime(transFormat.parse(date));
				cal.add(Calendar.DATE, 7 * i);
				date = transFormat.format(cal.getTime());
				
				item.setDate(date);
				item.setConfId(reserveInfo.getConfId());
				item.setRepeatCount(repeatCount - i);
				item.setReserver(reserveInfo.getReserver());
				item.settimeFrom(reserveInfo.gettimeFrom());
				item.settimeTo(reserveInfo.gettimeTo());
				if(reserveCheck(item)) {
					resultList.add(item);
				}
				else return false;

			}catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
