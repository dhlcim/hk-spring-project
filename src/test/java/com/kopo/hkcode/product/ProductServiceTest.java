package com.kopo.hkcode.product;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.sql.DataSource;
import java.sql.Connection;
// 1. 스프링이 테스트를 관리하도록 설정
@RunWith(SpringJUnit4ClassRunner.class)
// 2. 조립 지시서(XML) 위치 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/beans_product.xml"})
public class ProductServiceTest {
    // 3. 테스트할 부품을 자동으로 가져옴
    @Autowired
    private ProductService productService;
    // 추가: DataSource도 주입받아서 접속 정보 확인에 사용
    @Autowired
    private DataSource dataSource;
    @Test
    public void 가비아_DB_조회_테스트() {
        // 4. 실행
        String result = productService.getMaxQtyByRegion("A77");
        // 5. 검증 및 출력
        System.out.println("================================");
        System.out.println("테스트 결과값: " + result);
        System.out.println("================================");
        // 결과가 null이 아니면 테스트 성공!
        assertNotNull(result);
    }
    // 추가: 주입된 DB 접속 정보를 콘솔에서 확인
    @Test
    public void 주입된_DB접속정보_확인() throws Exception {
        Connection conn = dataSource.getConnection();
        System.out.println("================================");
        System.out.println("DB 연결 성공: " + !conn.isClosed());
        System.out.println("DB URL: " + conn.getMetaData().getURL());
        System.out.println("DB 계정: " + conn.getMetaData().getUserName());
        System.out.println("DB 드라이버: " + conn.getMetaData().getDriverName());
        System.out.println("================================");
        conn.close();
        assertNotNull(conn);
    }

    // 추가: 평균 QTY 조회 결과를 콘솔에서 확인
    @Test
    public void 가비아_DB_평균_조회_테스트() {
        // getAvgQtyByRegion() 호출 → A77 지역 평균 판매량 조회
        String result = productService.getAvgQtyByRegion("A77");
        // 결과 콘솔 출력
        System.out.println("================================");
        System.out.println("평균 테스트 결과값: " + result);
        System.out.println("================================");
        // 결과가 null이 아니면 테스트 성공!
        assertNotNull(result);
    }
}