package com.example.demo.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Calc;
import com.example.demo.repository.CalcRepository;


@Service
public class CalcService {
	@Autowired
    private CalcRepository calcRepository;

    public List<Calc> findAll() {
    	return calcRepository.findAll();
    }
    
    public Optional<Calc> findOne(Long id) {
    	return calcRepository.findById(id);
    }

    public Calc save(Calc calc) throws ParseException {
		return calcRepository.save(calc);
    }

    public void delete(Calc id) {
    	calcRepository.delete(id);
    }
    
    /**
     * calcDate baseDateを基準日として、calcTarget(年月日)に対してcalcValue分の加減を行う
     *
     * @param String baseDate 基準年月日
     * @param String calcTarget 加減対象
     * @param String calcValue 加減数
     * @return 計算結果の年月日
     */
    public String calcDate(String baseDate
						, String calcTarget
						, String calcValue) {

		if (calcValue == null || "".equals(calcValue)) {
			return null;
		}
		if (calcTarget == null || "".equals(calcTarget)) {
			return null;
		}
		
		ZonedDateTime baseZonedDt = convertToDate(baseDate);
		if(baseZonedDt == null) {
			return null;
		}
		
		ZonedDateTime zonedCalcDay = null;
		if ("0".equals(calcTarget)) {
			//day
			zonedCalcDay = baseZonedDt.plusDays(Integer.parseInt(calcValue));
		} else if ("1".equals(calcTarget)) {
			//month
			zonedCalcDay = baseZonedDt.plusMonths(Integer.parseInt(calcValue));
		} else if ("2".equals(calcTarget)) {
			//year
			zonedCalcDay = baseZonedDt.plusYears(Integer.parseInt(calcValue));
		} else if ("3".equals(calcTarget)) {
			//月末
			zonedCalcDay = baseZonedDt.plusMonths(Integer.parseInt(calcValue)).with(TemporalAdjusters.lastDayOfMonth());
		} else if ("4".equals(calcTarget)) {
			//月初
			zonedCalcDay = baseZonedDt.plusMonths(Integer.parseInt(calcValue)).with(TemporalAdjusters.firstDayOfMonth());
		} else {
			return null;
		}
		return convertToString(zonedCalcDay);
	}
    
    /**
     * calcTarget(年月日)に対応する表示名称を返す
     *
     * @param String calcTarget(年月日)のcode
     * @return calcTarget(年月日)に対応する表示名称を返す
     */
	public String convertCodeToName(String code) {
		String displayname = "";
		switch (code) {
		case "0":
			displayname = "日 後or前";
			break;
		case "1":
			displayname = "月 後or前";
			break;
		case "2":
			displayname = "年 後or前";
			break;
		case "3":
			displayname = "ヶ月 後or前の月末";
			break;
		case "4":
			displayname = "ヶ月 後or前の月初";
			break;
		default:
			displayname = "";
			break;
		}
		return displayname;
	}
	
    /**
     * String→ZonedDateTime型に変換して返す
     *
     * @param String strDate
     * @return ZonedDateTime型に変換した値
     */
	private ZonedDateTime convertToDate(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date formatDate;
		try {
			formatDate = sdf.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return ZonedDateTime.ofInstant(formatDate.toInstant(), ZoneId.of("Asia/Tokyo"));
	}

    /**
     * ZonedDateTime→String型に変換して返す
     *
     * @param ZonedDateTime　zonedDateTime
     * @return String型に変換した値
     */
	private String convertToString(ZonedDateTime zonedDateTime) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return zonedDateTime.format(dateTimeFormatter);
	}

}
