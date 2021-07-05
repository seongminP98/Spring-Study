package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration //스프링이 뜰 때 Configuration을 읽고 이거는 스프링 빈에 등록하란 뜻이네 라고 인식함. 저 로직을 호출해서 스프링 빈에 등록함
public class SpringConfig { //얘가 뜰 때 memberService랑 memberRepository를 둘 다 스프링에 등록을 하고 그러면서 스프링 빈에 등록되어 있는 memberRepository를 memberService에 넣어준다.

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {    //스프링 데이터 JPA가 빈에 등록해놓음
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    } //컴포넌트 스캔써서 주석처리 함.

//    @Bean
//    public MemberRepository memberRepository(){
//
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(em);
//    }
}
