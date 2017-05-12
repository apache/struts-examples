package org.apache.struts.example;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class NumberAction extends ActionSupport {

    private static final Logger LOG = LogManager.getLogger(NumberAction.class);

    private BigDecimal bigDecimal = BigDecimal.valueOf(1.01);
    private Double bigDouble = 1.01;
    private double primitiveDouble = 1.01;

    @Override
    public String execute() throws Exception {
        LOG.info("BigDecimal value: {}", bigDecimal);
        LOG.info("Double value: {}", bigDouble);
        LOG.info("double value: {}", primitiveDouble);

        return INPUT;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public Double getBigDouble() {
        return bigDouble;
    }

    public void setBigDouble(Double bigDouble) {
        this.bigDouble = bigDouble;
    }

    public double getPrimitiveDouble() {
        return primitiveDouble;
    }

    public void setPrimitiveDouble(double primitiveDouble) {
        this.primitiveDouble = primitiveDouble;
    }
}
