package com.gecko.model.dao.customer;

import com.gecko.model.dao.IGenericDAO;
import com.gecko.model.entity.Customer;

import java.util.List;

public interface CustomerDAO extends IGenericDAO<Customer,Integer> {
    int getTotalPage();
    void changeRole(Integer id);
    Customer findByEmail(String email);
    List<Customer> getAllCustomer();
}
