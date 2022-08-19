package org.apache.struts.example.jasperreports.action;

import com.opensymphony.xwork2.ActionSupport;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.example.jasperreports.model.Person;
import org.apache.struts2.ServletActionContext;

public class JasperAction extends ActionSupport {
  private static final Logger LOG = LogManager.getLogger(JasperAction.class);

  /**
   * It is recommended to initialize only once when starting WAS,<br>
   * but since this project is an example, <br>
   * I simply put the Jasper initialization code in the constructor.
   */
  public JasperAction() {
    try {
      LOG.info("=== Start compile ===");
      JasperCompileManager.compileReportToFile(
          Optional.ofNullable(JasperAction.class.getResource("/jasper/our_jasper_template.jrxml"))
              .orElseThrow(
                  () -> {
                    throw new IllegalStateException("our_jasper_template.jrxml File not found.");
                  })
              .getFile(),
          ServletActionContext.getServletContext().getRealPath("/WEB-INF/jasper")
              + "/our_compiled_template.jasper");
      LOG.info("=== End compile ===");
    } catch (Exception e) {
      throw new IllegalStateException("Failed to compile, " + e.getMessage(), e);
    }
  }

  /** List to use as our JasperReports dataSource. */
  private List<Person> myList;

  @Override
  public String execute() {
    LocalDateTime registerDate = LocalDateTime.now();
    // Create some imaginary persons.
    Person p1 = new Person(1L, "Patrick", "Lightbuddie", registerDate.minusSeconds(30));
    Person p2 = new Person(2L, "Jason", "Carrora", registerDate.minusSeconds(20));
    Person p3 = new Person(3L, "Alexandru", "Papesco", registerDate.minusSeconds(10));
    Person p4 = new Person(4L, "Jay", "Boss", registerDate);

    // Normally we would provide a pre-compiled .jrxml file
    // or check to make sure we don't compile on every request.
    myList = new ArrayList<>();
    myList.add(p1);
    myList.add(p2);
    myList.add(p3);
    myList.add(p4);

    return SUCCESS;
  }

  public List<Person> getMyList() {
    return myList;
  }
}
