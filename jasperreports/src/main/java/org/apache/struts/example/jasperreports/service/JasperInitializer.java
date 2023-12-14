package org.apache.struts.example.jasperreports.service;

import java.io.File;
import java.util.Optional;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

public class JasperInitializer implements InitializingBean, DisposableBean, ServletContextAware {
  private ServletContext sc;

  private static final Logger LOG = LogManager.getLogger(JasperInitializer.class);

  private static final String COMPILED_JASPER_FILE = "/WEB-INF/jasper/our_compiled_template.jasper";

  @Override
  public void afterPropertiesSet() throws Exception {
    try {
      LOG.info("=== Start JasperReport compile ===");
      JasperCompileManager.compileReportToFile(
          Optional.ofNullable(
                  JasperInitializer.class.getResource("/jasper/our_jasper_template.jrxml"))
              .orElseThrow(
                  () -> {
                    throw new IllegalStateException("our_jasper_template.jrxml File not found.");
                  })
              .getFile(),
          sc.getRealPath(COMPILED_JASPER_FILE));
      LOG.info("=== End JasperReport compile ===");
    } catch (Exception e) {
      throw new IllegalStateException("Failed to compile, " + e.getMessage(), e);
    }
  }

  @Override
  public void setServletContext(ServletContext servletContext) {
    this.sc = servletContext;
  }

  @Override
  public void destroy() throws Exception {
    File templteFile = new File(sc.getRealPath(COMPILED_JASPER_FILE));
    LOG.info(
        "=== Compiled JasperReport file ({}) delete result: {} ===",
        templteFile.getAbsolutePath(),
        templteFile.delete());
  }
}
