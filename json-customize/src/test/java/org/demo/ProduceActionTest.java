package org.demo;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import jakarta.servlet.ServletException;
import java.io.UnsupportedEncodingException;
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

    assertThatJson(output).node("nickname").isAbsent();

    assertThatJson(output).node("name").isEqualTo("William Shakespeare");
    assertThatJson(output).node("username").isEqualTo("WillShak");
    assertThatJson(output).node("password").isEqualTo("******");
    assertThatJson(output).node("birthday").isEqualTo("04/26/1564");

    assertThatJson(output).node("addresses[0].city").isEqualTo("Stratford-upon-Avon");
    assertThatJson(output).node("addresses[0].name").isEqualTo("home");
    assertThatJson(output).node("addresses[0].street").isEqualTo("Henley");

    assertThatJson(output).node("addresses").isArray().hasSize(1);

    assertThatJson(output).node("lastLogin").isString().matches("\\d{2}/\\d{2}/\\d{4}");
  }
}
