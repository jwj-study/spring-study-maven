package com.tmax.persistence;

import static org.junit.Assert.fail;
import java.sql.Connection;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)		

//@RunWith에 Runner클래스를 설정하면 JUnit에 내장된 Runner대신 그 클래스를 실행한다. 
//여기서는 스프링 테스트를 위해서 SpringJUnit4ClassRunner라는 Runner 클래스를 설정
//한 클래스내에 여러개의 테스트가 있더라도 어플리케이션 컨텍스트를 초기 한번만 로딩하여 사용하기 때문에, 
//여러개의 테스트가 있더라도 처음 테스트만 조금 느리고 그 뒤의 테스트들은 빠르다.

//자동으로 만들어줄 애플리케이션 컨텍스트의 설정파일위치를 지정한 것		xml형태의 애플리케이션 컨텍스트만 로딩 가능
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@SpringApplicationConfiguration은 Spring Boot에서 class형태의 애플리케이션 컨텍스트를 로딩 할 수 있다.


//기타
//@Autowired, @MockBean에 해당되는 것들에만 application context를 로딩하게 됨
//@SpringBootTest		application context를 전부 로딩하여 무거운 프로젝트가 될 수 있음

//applicationcontext는 스프링 컨테이너로 BeanFactory 인터페이스의 하위 인터페이스이다.
//applicationcontext는 BeanFactory + 부가 기능(국제화(다국어) 기능, 환경 변수 관련 처리, 애플리케이션 이벤트, 리소스 조회)을 가진다.
//BeanFactory는 스프링 컨테이너의 최상위 컨테이너로 스프링 빈을 관리하고 조회한다.
//스프링 컨테이너에는 빈 저장소가 존재하는데 key : value객체로 관리한다.   싱글톤으로 관리한다.

//로그 남기는 어노테이션
@Log4j
public class DataSourceTests {

	// @Autowired  필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
  @Setter(onMethod_ = { @Autowired })
  private DataSource dataSource;

  @Setter(onMethod_ = { @Autowired })
  private SqlSessionFactory sqlSessionFactory;

  @Test
  public void testMyBatis() {

    try (
    		SqlSession session = sqlSessionFactory.openSession();
    		Connection con = session.getConnection();
      ) {

      log.info(session);
      log.info(con);
      

    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void testConnection() { 
    try (Connection con = dataSource.getConnection();){

      log.info(con);      
      
    }catch(Exception e) {
      fail(e.getMessage());
    }
  }
}

