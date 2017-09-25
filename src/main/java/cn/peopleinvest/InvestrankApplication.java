package cn.peopleinvest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
@MapperScan("cn.peopleinvest.mapper")  //扫描的是mapper.xml中namespace指向值的包位置
public class InvestrankApplication {
	public static void main(String[] args) {
		SpringApplication.run(InvestrankApplication.class, args);
	}
}
