package test;

import com.dkatalis.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class CustomerTest {

    private CustomerService customerService;
    private final String name;
    private Boolean expected;
    @Before
    public void setup(){
        customerService = new CustomerService();
    }

    // Inject via constructor


    public CustomerTest( String name, Boolean expected) {

        this.name = name;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {"alice", true},
                {"bob", true}
        });
    }


    @Test
    public void testLogin() {

        customerService.login(name);
    }
    @Test
    public void testDeposit(){
        customerService.deposit("1000");
    }
}