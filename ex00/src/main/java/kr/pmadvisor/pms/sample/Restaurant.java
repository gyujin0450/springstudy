package kr.pmadvisor.pms.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

/**
 *	Restaurant 객체가  Chef 타입의 객체를 필요로 한다는 상황임
 */

@Component  // 스프링에게 해당클래스가 스프링에서 관리해야 하는 대상임을 표시
@Data		// Lombok의 setter를 생성하는 기능과 생성자, toString() 등을 자동으로 생성하도록함
public class Restaurant {


@Setter(onMethod_ = @Autowired) // 자동으로 setChef()를 컴파일시 생성함
private Chef chef;

}

