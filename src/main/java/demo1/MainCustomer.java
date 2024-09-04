package demo1;

import demo1.config.SpringConfig;
import demo1.entity.CustomerEntity;
import demo1.repository.CustomerRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MainCustomer {
    static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    static CustomerRepository customerRepository = (CustomerRepository) context.getBean(CustomerRepository.class);
    public static void main(String[] args) {
        addCustomer("Nguyen Hoang", LocalDate.of(2001,07,23), "Male", "abc@gmail.com", "1234567890", "DN");
        listAllCustomers();
        findCustomerById(1);
        findCustomersByName("Nguyen Hoang");
        findCustomersByPhoneOrEmail("1234567890", "abc@gmail.com");
        listMenAgedBetween20And30();
    }
    private static void addCustomer(String name, LocalDate birthdate, String sex, String email, String phone, String address) {
        CustomerEntity customer = new CustomerEntity();
        customer.setName(name);
        customer.setBirthdate(birthdate);
        customer.setSex(sex);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customerRepository.save(customer);
        System.out.println("Add customer: " + customer);
    }
    private static void listAllCustomers() {
        List<CustomerEntity> customers = (List<CustomerEntity>) customerRepository.findAll();
        for (CustomerEntity customer : customers) {
            System.out.println(customer);
        }
    }
    private static void findCustomerById(int id) {
        Optional<CustomerEntity> customers = customerRepository.findById(id);
        if (customers.isPresent()) {
            System.out.println(customers);

        } else {
            System.out.println("Customer not found with ID: " + id);
        }
    }
    private static void findCustomersByName(String name) {
        List<CustomerEntity> customers = customerRepository.findByName(name);
        for (CustomerEntity customer : customers) {
            System.out.println(customer);
        }
    }
    private static void findCustomersByPhoneOrEmail(String phone, String email) {
        List<CustomerEntity> customers = customerRepository.findByPhoneOrEmail(phone, email);
        for (CustomerEntity customer : customers) {
            System.out.println(customer);
        }
    }
    private static void listMenAgedBetween20And30() {
        List<CustomerEntity> customers = customerRepository.findMenAgedBetween20And30();
        for (CustomerEntity customer : customers) {
            System.out.println(customer);
        }
    }
}
