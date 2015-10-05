package org.apache.examples.struts.actions.data;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.examples.struts.models.Order;
import org.apache.examples.struts.services.OrdersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.apache.struts2.rest.RestActionSupport;

import java.util.Collection;

public class OrderController extends RestActionSupport implements ModelDriven<Object> {

    private static final Logger log = LogManager.getLogger(OrderController.class);

    private Order model = new Order();
    private String id;
    private Collection<Order> list = null;
    private OrdersService ordersService = new OrdersService();

    // GET /data/order/1
    public HttpHeaders show() {
        return new DefaultHttpHeaders("show");
    }

    // GET /data/order
    public HttpHeaders index() {
        list = ordersService.getAll();
        return new DefaultHttpHeaders("index")
            .disableCaching();
    }

    // DELETE /data/order/1
    public String destroy() {
        log.debug("Delete order with id: {}", id);

        // To demonstrate exception handling we throw an exception when someone tries to delete an order
        if(id != null) {
            throw new RuntimeException(getText("exception.operation.not.supported"));
        }

        ordersService.remove(id);
        return "success";
    }

    // POST /data/order
    public HttpHeaders create() {
        log.debug("Create new order: {}", model);
        ordersService.save(model);
        return new DefaultHttpHeaders("success")
            .setLocationId(model.getId());
    }

    // PUT /data/order/1
    public String update() {
        log.debug("Update order: {}", model);
        ordersService.save(model);
        return "success";
    }

    public void setId(String id) {
        if (id != null) {
            this.model = ordersService.get(id);
        }
        this.id = id;
    }
    
    public Object getModel() {
        if(list != null) {
            return list;
        } else {
            if(model == null) {
                model = new Order();
            }
            return model;
        }
    }
}
