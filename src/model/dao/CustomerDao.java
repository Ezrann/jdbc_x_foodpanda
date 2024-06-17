package model.dao;

import model.entity.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> queryAllCustomers();
    int updateCustomerByID(Integer id);
    int deleteCustomerByID(Integer id);
    int addNewCustomer(Customer customer);
    Customer searchCustomerByID(Integer id);
}
