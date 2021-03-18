package com.wallet.service;

import com.wallet.dao.OrderDao;
import com.wallet.model.Category;
import com.wallet.model.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("/orders")
public class OrderService {
    private OrderDao orderDao = new OrderDao();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Order createOrder(Order order) {
        return orderDao.createOrder(order);
    }

    @GET
    @Path("/{categoryId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Order getAllOrdersByCategoryId(){
        return (Order) orderDao.getAllOrdersByCategoryId(Category.builder()
                .id(1L)
                .build());
    }

    @DELETE
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteOrderById(@PathParam("orderId") Long orderId){
        orderDao.deleteOrderById(orderId);
    }
}
