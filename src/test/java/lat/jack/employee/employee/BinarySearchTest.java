package lat.jack.employee.employee;

import org.junit.Test;

import lat.jack.employee.employee.Algorithms.EmployeeSearch;

import static org.junit.jupiter.api.Assertions.*;
public class BinarySearchTest {

    @Test
    public void testBinarySearch() {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        int[] keys = {30, 90, 100};
        int[] expected = {2, 8, -10};

        for (int i = 0; i < keys.length; i++) {
            int result = EmployeeSearch.binarySearch(arr, keys[i]);
            System.out.println("Key: " + keys[i] + ", Result: " + result);
            assertEquals(expected[i], result);
        }

    }
}
