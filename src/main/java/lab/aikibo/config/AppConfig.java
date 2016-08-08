package lab.aikibo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lab.aikibo.model.Sppt;
import lab.aikibo.dao.SpptDao;
import lab.aikibo.dao.SpptDaoImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"lab.aikibo"})
@Import({HibernateConfiguration.class})
public class AppConfig {

  /* ini ga pernah ada
  @Bean(name="dataSppt")
  public Sppt getDataSppt() {
    SpptDao spptDao = new SpptDaoImpl();
    Sppt sppt = spptDao.getSpptByNopThn("332901000100100010", "2013");
    return sppt;
  }
  */

}
