package commonserver.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class SqlReadTemplate {

    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "read.druid")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "readSqlSessionFactory")
    public SqlSessionFactory readSqlSessionFactory(@Qualifier("readDataSource") DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();       // 创建Mybatis的连接会话工厂实例
        bean.setDataSource(dataSource);     // 设置数据源bean
        try {
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "sqlSessionReadTemplate")
    public SqlSessionTemplate sqlSessionReadTemplate(@Qualifier("readSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);   //使用上面配置的Factory
    }
}
