package com.kopo.hkcode.annot;

import org.springframework.context.ApplicationContext; // Spring 컨테이너(공장)를 다루는 인터페이스 import
import org.springframework.context.support.GenericXmlApplicationContext; // XML 파일로 Spring 컨테이너를 생성하는 클래스 import

public class MainApp {
    public static void main(String[] args) {

        // 1. 스프링 컨테이너(공장) 구동
        // XML 설정 파일을 읽어서 Spring 컨테이너를 시작한다.
        // 이 시점에 beans_annt.xml에 설정된 Bean들이 모두 자동으로 생성된다.
        ApplicationContext context = new GenericXmlApplicationContext(
                "file:src/main/webapp/WEB-INF/spring/beans_annt.xml");

        // 2. 공장에서 조립된 'hello' 부품 꺼내기
        // getBean(Hello.class): 타입으로 Bean을 꺼내는 방법
        // 이전에 getBean("hello")로 이름으로 꺼내는 방법과 동일한 결과이다.
        Hello hello = context.getBean(Hello.class);

        // 3. 실행 및 확인
        // Hello 클래스의 print()를 호출 → 내부적으로 printer.print(sayHello())가 실행된다.
        // @Qualifier("stringPrinter")로 지정되어 있으므로 "string call Hello hkcode" 출력
        hello.print();

        // "stringPrinter"라는 이름의 Bean을 꺼내서 Printer 타입으로 형변환한다.
        // 위의 hello.print()에서 사용된 printer와 동일한 객체이다.
        Printer printer = (Printer) context.getBean("stringPrinter");

        // printer.print()를 직접 호출하여 출력한다.
        // hello.sayHello()는 "Hello hkcode"를 반환하므로 "string call Hello hkcode"가 출력된다.
        // 결과적으로 hello.print()와 동일한 내용이 한 번 더 출력된다.
        printer.print("test"); // printer로 직접 출력
    }
}