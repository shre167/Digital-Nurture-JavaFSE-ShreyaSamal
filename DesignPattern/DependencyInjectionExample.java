public class DependencyInjectionExample {
    interface CustomerRepository {
        String findCustomerById(int id);
    }

    static class CustomerRepositoryImpl implements CustomerRepository {
        @Override
        public String findCustomerById(int id) {
            return "Customer{" + id + ", name=Sample Customer}";
        }
    }

    static class CustomerService {
        private final CustomerRepository repository;

        public CustomerService(CustomerRepository repository) {
            this.repository = repository;
        }

        public String getCustomer(int id) {
            return repository.findCustomerById(id);
        }
    }

    public static void main(String[] args) {
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        System.out.println(service.getCustomer(1001));
    }
}
