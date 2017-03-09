package br.com.addr;

import br.com.addr.conf.PersistenceTestConf;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceTestConf.class)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public abstract class AbstractDBTestCase {


}
