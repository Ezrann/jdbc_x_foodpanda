package service;

import mapper.Mapper;
import model.dao.CustomerDao;
import model.dao.CustomerDaoImpl;
import model.dto.CustomerDto;
import model.entity.Customer;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    private final CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public List<CustomerDto> getCustomers() {
        return customerDao.queryAllCustomers().stream().filter(c -> c.getIsDeleted().equals(false)).map(Mapper::fromCustomerToCustomerDto).toList();
    }

    @Override
    public int deleteCustomer(Integer id) { return customerDao.deleteCustomerById(id); }

    @Override
    public int updateCustomer(Integer id) { return customerDao.updateCustomerById(id); }

    @Override
    public int addCustomer(Customer customer) { return customerDao.insertCustomer(customer); }
}
