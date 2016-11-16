package lab.aikibo.controller;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import lab.aikibo.config.AppConfig;
import org.junit.runner.RunWith;
import lab.aikibo.constant.StatusRespond;
import lab.aikibo.model.StatusInq;

/**
 * Created by tamami on 29/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class SpptRestControllerTest {

  @Autowired
  SpptServices spptServices;

  @Test
  public void testDBException() {
    StatusInq result = spptServices.getSpptByNopThn("332901000100100010", "2016", "127.0.0.1");

    assertEquals(StatusRespond.DATABASE_ERROR, result.getCode());
  }

}
