package com.order.rush_order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
// docker 실행 -> contextLoads() 에러 발생
// @SpringBootTest가 MySQL DB에 연결을 시도하는 시점에 MySQL 컨테이너가 준비되지 않아서 발생
// 이유: mysql 생성 이전 -> contextLoads() 메서드 실행 되면서 JPA는 빈 생성을 위해 DB driver를 필요로 하게 되므로 TEST는 로컬 H2로 빈 생성해준다
@ActiveProfiles("test")
class RushOrderApplicationTests {

    @Test
    void contextLoads() {
    }

}
