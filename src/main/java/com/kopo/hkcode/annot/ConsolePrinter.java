package com.kopo.hkcode.annot; // 이 클래스가 속한 패키지(폴더) 경로

import org.springframework.stereotype.Component; // Spring이 이 클래스를 Bean으로 등록할 수 있게 해주는 어노테이션 import

// @Component: Spring이 자동으로 이 클래스를 Bean(객체)으로 만들어서 관리해준다.
// ("consolePrinter"): 이 Bean의 이름(ID)을 "consolePrinter"로 직접 지정한다.
//                    지정하지 않으면 클래스명 첫 글자를 소문자로 바꾼 "consolePrinter"가 기본값이 된다.
@Component("consolePrinter")
public class ConsolePrinter implements Printer { // Printer 인터페이스를 구현하는 클래스 선언

    @Override // 부모 인터페이스(Printer)에 정의된 print() 메서드를 여기서 실제로 구현한다는 표시
    public void print(String message) { // 문자열 message를 받아서 콘솔에 출력하는 메서드
        // TODO Auto-generated method stub
        System.out.println("console call " + message); // "console call " 뒤에 전달받은 message를 붙여서 콘솔에 출력
    }
}