package lat.jack.employee.employee;

import lat.jack.employee.employee.Controllers.LoginView;
import lat.jack.employee.employee.Controllers.Toaster;
import lat.jack.employee.employee.DataStructures.TaskQueue;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ToastQueueTest {

    String message = "Test String ";

    @Test
    public void doesToastQueueIncrease() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        Toaster testToaster = message -> {
            System.out.println(message);
            latch.countDown();
        };
        TaskQueue taskQueue = new TaskQueue(testToaster);

        int loopAmount = 3;

        for (int i = 0; i < loopAmount; i++) {
            taskQueue.addToQueue(message + i);
        }

        assertEquals(loopAmount, taskQueue.getQueue().size());
        assertTrue(latch.await(10, TimeUnit.SECONDS), "Processing did not complete within a given time");

    }
}
