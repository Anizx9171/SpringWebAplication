package com.gecko.model.service.customer;

import com.gecko.model.entity.Customer;
import com.gecko.model.service.IGenericService;

import java.util.List;

public interface CustomerService extends IGenericService<Customer,Integer> {
   int getTotalPage();
   void changeRole(Integer id);
   Customer findByEmail(String email);
   List<Customer> getAllCustomer();
}
