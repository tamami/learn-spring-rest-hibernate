package lab.aikibo.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lab.aikibo.config.AppConfig;
import lab.aikibo.model.Sppt;

public class AppForTest {
  public static void main(String args[]) {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    Sppt sppt = (Sppt) context.getBean("dataSppt");
    System.out.println("NOP: " + sppt.getNop());
  }
}
