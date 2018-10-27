package kr.pmadvisor.pms.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

//	@Select("SELECT sysdate FROM dual")		: 오라클
	@Select("SELECT sysdate() FROM dual") //: MySQL
	public String getTime();

	public String getTime2();

}
