package lat.jack.employee.employee;

import lat.jack.employee.employee.Algorithms.EmployeeSearch;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinearSearchTest {

    @Test
    public void testLinearSearch() {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        int[] keys = {30, 90, 100};
        int[] expected = {2, 8, -1};

        for (int i = 0; i < keys.length; i++) {
            int result = EmployeeSearch.linearSearch(arr, keys[i]);
            assertEquals(expected[i], result);
        }

    }
}
