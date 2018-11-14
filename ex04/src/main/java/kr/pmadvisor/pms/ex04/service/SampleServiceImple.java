package kr.pmadvisor.pms.ex04.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImple implements SampleService{

	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		
		// p453 : 반복적으로 사용하던 log 기록을 빼고, AOP 개념을 적용함!!! 
		return Integer.parseInt(str1) + Integer.parseInt(str2);
	}

}
