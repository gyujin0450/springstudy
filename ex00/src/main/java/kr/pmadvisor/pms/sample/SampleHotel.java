package kr.pmadvisor.pms.sample;

import org.springframework.stereotype.Component;

//import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@ToString
@Getter
//@AllArgsConstructor
@RequiredArgsConstructor
public class SampleHotel {

	@NonNull
	private Chef chef;
//	private String name;

//	아래 생성자를 주입을  @RequiredArgsConstructor 로 대체.. 
//	public SampleHotel(Chef chef) {
//		this.chef = chef;
//	}


	
	
}
