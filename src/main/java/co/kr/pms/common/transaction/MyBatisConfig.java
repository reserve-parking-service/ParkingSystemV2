package co.kr.pms.common.transaction;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages={"co.kr.pms.biz.repository.mapper"}, sqlSessionFactoryRef="sqlSessionFactory")
public class MyBatisConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactotry(@Qualifier("dataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("co.kr.pms.biz.dto.mapper");
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mappers/config/mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mappers/*-mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

}
