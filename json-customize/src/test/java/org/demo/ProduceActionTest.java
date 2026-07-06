package org.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.jayway.jsonpath.JsonPath;
import jakarta.servlet.ServletException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.junit.StrutsJUnit4TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProduceActionTest extends StrutsJUnit4TestCase<ProduceAction> {
  private static final Logger log = LoggerFactory.getLogger(ProduceActionTest.class);

  @Test
  public void getActionMapping() {
    ActionMapping mapping = getActionMapping("/produce.action");
    assertNotNull(mapping);
    assertEquals("/", mapping.getNamespace());
    assertEquals("produce", mapping.getName());
  }

  @Test
  public void executeAction() throws ServletException, UnsupportedEncodingException {
    String output = executeAction("/produce.action");
    log.info("output: {}", output);

    assertNotNull(output);
    assertEquals("William Shakespeare", JsonPath.read(output, "$.name"));
    assertEquals("WillShak", JsonPath.read(output, "$.username"));
    assertEquals("******", JsonPath.read(output, "$.password"));
    assertEquals("04/26/1564", JsonPath.read(output, "$.birthday"));

    assertEquals("Stratford-upon-Avon", JsonPath.read(output, "$.addresses[0].city"));
    assertEquals("home", JsonPath.read(output, "$.addresses[0].name"));
    assertEquals("Henley", JsonPath.read(output, "$.addresses[0].street"));

    List<Object> addresses = JsonPath.read(output, "$.addresses");
    assertEquals(1, addresses.size());

    String lastLogin = JsonPath.read(output, "$.lastLogin");
    assertNotNull(lastLogin);
    assertTrue(lastLogin.matches("\\d{2}/\\d{2}/\\d{4}"));
  }
}
