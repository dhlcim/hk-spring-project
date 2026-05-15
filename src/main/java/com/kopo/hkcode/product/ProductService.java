package com.kopo.hkcode.product;
// =====================================================
// ProductService.java
// DB 조회 로직을 담당하는 서비스 클래스 (Bean으로 등록됨)
// Spring이 beans_product.xml을 읽어 자동으로 생성·관리함
// =====================================================
import java.sql.Connection;          // DB 연결 객체
import java.sql.PreparedStatement;   // SQL 실행 객체 (? 파라미터 바인딩 지원)
import java.sql.ResultSet;           // SQL 실행 결과를 담는 객체
import java.util.List;               // 관리자 목록을 받기 위한 List 타입
import javax.sql.DataSource;         // DB 연결을 제공하는 인터페이스 (Spring이 구현체를 주입해줌)
public class ProductService {
    // final: 한 번 주입되면 절대 변경 불가 → 안정성 보장
    // Spring이 생성자를 통해 아래 두 값을 주입해 줌
    private final DataSource dataSource;    // DB 연결 담당 (beans_product.xml의 dataSource Bean)
    private final List<String> managerList; // 관리자 이름 목록 (beans_product.xml의 <list>에서 주입)
    // -------------------------------------------------------
    // 생성자 주입
    // Spring이 beans_product.xml의 <constructor-arg>를 보고
    // 이 생성자를 호출하여 dataSource와 managerList를 꽂아줌
    // -------------------------------------------------------
    public ProductService(DataSource dataSource, List<String> managerList) {
        this.dataSource = dataSource;       // 주입받은 DataSource 저장
        this.managerList = managerList;     // 주입받은 관리자 목록 저장
    }
    // -------------------------------------------------------
    // 지역 최대 QTY 조회 메서드
    // regionId: 조회할 지역 코드 (예: "A44")
    // return: 조회 결과 문자열
    // -------------------------------------------------------
    public String getMaxQtyByRegion(String regionId) {
        String result = "";  // 최종 반환할 결과 문자열 초기화
        // ? 자리에 regionId가 들어감 → SQL Injection 방지
        String sql = "SELECT MAX(QTY) FROM kopo_channel_seasonality_new WHERE regionid = ?";
        // try-with-resources: try 블록이 끝나면 conn, pstmt 자동으로 close() 호출됨
        try (Connection conn = dataSource.getConnection();              // DataSource에서 DB 연결 획득
             PreparedStatement pstmt = conn.prepareStatement(sql)) {   // SQL 미리 컴파일
            // 첫 번째 ? 자리에 regionId 값 바인딩 (1번째 파라미터)
            pstmt.setString(1, regionId);
            // SQL 실행 → 결과를 ResultSet에 담음
            try (ResultSet rs = pstmt.executeQuery()) {
                // rs.next(): 결과가 한 행이라도 있으면 true
                if (rs.next()) {
                    int maxQty = rs.getInt(1); // 첫 번째 컬럼(MAX(QTY)) 값을 int로 읽음
                    result = "지역 [" + regionId + "]의 최대 판매량은 " + maxQty + "개 입니다.";
                }
            }
        } catch (Exception e) {
            // DB 연결 실패, SQL 오류 등 예외 발생 시 오류 메시지를 result에 담음
            result = "DB 조회 에러 발생: " + e.getMessage();
            e.printStackTrace(); // 콘솔에 상세 오류 출력 (개발 중 디버깅용)
        }
        // 주입된 관리자 목록이 비어있지 않으면 첫 번째 관리자 이름을 결과에 추가
        // managerList.get(0) → "김대현 관리자"
        if (managerList != null && !managerList.isEmpty()) {
            result += " (조회 담당자: " + managerList.get(0) + ")";
        }
        return result; // 최종 결과 문자열 반환 → Controller로 전달됨
    }

    // -------------------------------------------------------
    // 지역 평균 QTY 조회 메서드 (추가)
    // regionId: 조회할 지역 코드 (예: "A44")
    // return: 평균 조회 결과 문자열
    // -------------------------------------------------------
    public String getAvgQtyByRegion(String regionId) {
        String result = "";  // 최종 반환할 결과 문자열 초기화

        // AVG: 해당 지역의 QTY 전체 평균값 계산
        String sql = "SELECT AVG(QTY) FROM kopo_channel_seasonality_new WHERE regionid = ?";

        // try-with-resources: try 블록이 끝나면 conn, pstmt 자동으로 close() 호출됨
        try (Connection conn = dataSource.getConnection();              // DataSource에서 DB 연결 획득
             PreparedStatement pstmt = conn.prepareStatement(sql)) {   // SQL 미리 컴파일

            // 첫 번째 ? 자리에 regionId 값 바인딩
            pstmt.setString(1, regionId);

            // SQL 실행 → 결과를 ResultSet에 담음
            try (ResultSet rs = pstmt.executeQuery()) {
                // rs.next(): 결과가 한 행이라도 있으면 true
                if (rs.next()) {
                    double avgQty = rs.getDouble(1); // AVG는 소수점이 있으므로 double로 읽음
                    // String.format("%.1f"): 소수점 첫째 자리까지만 표시
                    result = "지역 [" + regionId + "]의 평균 판매량은 " + String.format("%.1f", avgQty) + "개 입니다.";
                }
            }
        } catch (Exception e) {
            // DB 연결 실패, SQL 오류 등 예외 발생 시 오류 메시지를 result에 담음
            result = "DB 조회 에러 발생: " + e.getMessage();
            e.printStackTrace(); // 콘솔에 상세 오류 출력 (개발 중 디버깅용)
        }

        // 주입된 관리자 목록이 비어있지 않으면 첫 번째 관리자 이름을 결과에 추가
        if (managerList != null && !managerList.isEmpty()) {
            result += " (조회 담당자: " + managerList.get(0) + ")";
        }
        return result; // 최종 결과 문자열 반환 → Controller로 전달됨
    }
}