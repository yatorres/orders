package com.prog.distribuida;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("")
public class OrderResource {

  @Inject
  OrderRepository ordersRepository;

  @Path("/orders")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Orders> findAll() {
    return ordersRepository.listAll();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/orders/{id}")
  public Orders get(@PathParam("id") Long id) {
    return ordersRepository.findById(id);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/customers/{customerId}/orders")
  public List<Orders> getCustomerOrders(@PathParam("customerId") Long customerId) {
    return ordersRepository.customerOrders(customerId);
  }

  @Path("/orders")
  @Transactional
  @POST
  @Consumes("application/json")
  @Produces("application/json")
  public Response add(Orders entity) {
    ordersRepository.persist(entity);
    return Response.status(Response.Status.CREATED).build();
  }

  @Path("/orders/{id}")
  @Transactional
  @DELETE
  public void delete(@PathParam("id") Long id) {
    ordersRepository.deleteById(id);
  }
}
