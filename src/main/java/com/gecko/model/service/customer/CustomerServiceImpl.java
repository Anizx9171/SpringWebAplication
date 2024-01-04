package com.gecko.model.service.customer;

import com.gecko.model.dao.customer.CustomerDAO;
import com.gecko.model.entity.Customer;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerDAO customerDAO;
    @Override
    public List<Customer> findAll(Integer integer) {
        return customerDAO.findAll(integer);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerDAO.findByEmail(email);
    }

    @Override
    public boolean create(Customer customer) {
        String hashPass = BCrypt.hashpw(customer.getPassword(),BCrypt.gensalt(12));
        customer.setPassword(hashPass);
        return customerDAO.create(customer);
    }

    @Override
    public boolean update(Customer customer) {
        return customerDAO.update(customer);
    }

    @Override
    public boolean delete(Integer integer) {
        return customerDAO.delete(integer);
    }

    @Override
    public Customer findById(Integer integer) {
        return customerDAO.findById(integer);
    }

    @Override
    public List<Customer> findByName(String str) {
        return customerDAO.findByName(str);
    }

    @Override
    public int getTotalPage() {
        return customerDAO.getTotalPage();
    }

    @Override
    public void changeRole(Integer id) {
        customerDAO.changeRole(id);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerDAO.getAllCustomer();
    }
}
