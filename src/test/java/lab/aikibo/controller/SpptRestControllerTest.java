package lab.aikibo.controller;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

import lab.aikibo.config.AppConfig;
import lab.aikibo.constant.StatusRespond;
import lab.aikibo.model.StatusInq;
import lab.aikibo.services.SpptServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by tamami on 29/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class SpptRestControllerTest {

  @Autowired
  SpptServices spptServices;



  @Test(expected=Exception.class)
  public void testDBException() {
    StatusInq result = spptServices.getSpptByNopThn("332901000100100010", "2016", "127.0.0.1");

    assertEquals(StatusRespond.DATABASE_ERROR, result.getCode());
  }

}
