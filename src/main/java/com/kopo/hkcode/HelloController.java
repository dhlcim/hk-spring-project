package com.kopo.hkcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.kopo.hkcode.pojo.Hello;

@Controller // 이 어노테이션이 반드시 있어야 스프링이 알아봅니다!
public class HelloController {

	@Autowired
	private Hello hello; // 스프링이 beans.xml에 있는 hello 객체를 자동으로 꽂아줌

	@RequestMapping(value = "/helloTest", method = RequestMethod.GET)
//  ↑ 브라우저에서 /helloTest 로 접속하면 이 메소드 실행

public String helloTest(Model model) {

    String result = hello.sayHello();
    //              ↑ beans.xml 에서 가져온 Hello 객체의 메소드 호출
    //              ↑ beans.xml 에 name=spring 으로 설정되어 있어서
    //              ↑ result = "Hello spring" 이 됨

    model.addAttribute("message", result);
    //    ↑ JSP에 "message" 라는 이름으로 "Hello spring" 을 전달

    return "helloPojo";
    //  ↑ helloPojo.jsp 로 이동
}

	@RequestMapping("/hello")
//  ↑ 브라우저에서 /hello 로 접속하면 이 메소드 실행

public String hello(Model model) {

    model.addAttribute("message", "Hello World from Spring MVC!");
    //    ↑ JSP에 "message" 라는 이름으로
    //    ↑ 코드에 직접 쓴 문자열을 전달
    //    ↑ hello 객체 전혀 사용 안 함!

    return "helloPojo";
    //  ↑ 똑같이 helloPojo.jsp 로 이동
	}
}
