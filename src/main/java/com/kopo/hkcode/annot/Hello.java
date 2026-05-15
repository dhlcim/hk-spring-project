package com.kopo.hkcode.annot;

import org.springframework.beans.factory.annotation.Autowired;  // 의존성 자동 주입을 위한 어노테이션 import
import org.springframework.beans.factory.annotation.Qualifier;  // 여러 Bean 중 특정 Bean을 지정하기 위한 어노테이션 import
import org.springframework.beans.factory.annotation.Value;      // 값을 직접 주입하기 위한 어노테이션 import
import org.springframework.stereotype.Component;                // Spring Bean 등록을 위한 어노테이션 import

// @Component: Spring이 자동으로 이 클래스를 Bean으로 만들어서 관리해준다.
// ("hello"): 이 Bean의 이름(ID)을 "hello"로 지정한다.
@Component("hello")
public class Hello {

    // @Value: Spring이 이 변수에 "hkcode"라는 값을 직접 넣어준다.
    // XML 설정 없이 어노테이션만으로 값을 주입할 수 있다.
    @Value("hkcode")
    private String name;

    // @Autowired: Spring이 Printer 타입의 Bean을 자동으로 찾아서 주입해준다.
    // @Qualifier("stringPrinter"): Printer 타입의 Bean이 여러 개(consolePrinter, stringPrinter)이므로
    //                              그 중에서 "stringPrinter"라는 이름의 Bean을 사용하겠다고 지정한다.
    @Autowired
    @Qualifier("stringPrinter")
    private Printer printer;

    // name 값을 외부에서 변경할 수 있도록 해주는 setter 메서드
    public void setName(String name) {
        this.name = name;
    }

    // printer 객체를 외부에서 변경할 수 있도록 해주는 setter 메서드
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    // "Hello " + name 형태의 인사말 문자열을 만들어서 반환하는 메서드
    // 예: name이 "hkcode"이면 "Hello hkcode"를 반환
    public String sayHello() {
        return "Hello " + name;
    }

    // printer의 print() 메서드를 호출하여 인사말을 출력하는 메서드
    // sayHello()로 만든 문자열을 printer에게 전달해서 출력한다.
    // 현재 @Qualifier("stringPrinter")로 지정되어 있으므로 "string call Hello hkcode"가 출력된다.
    public void print() {
        this.printer.print(sayHello());
    }
}