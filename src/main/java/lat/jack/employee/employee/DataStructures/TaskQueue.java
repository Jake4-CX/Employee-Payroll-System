package lat.jack.employee.employee.DataStructures;

import lat.jack.employee.employee.Controllers.LoginView;
import lat.jack.employee.employee.Controllers.Toaster;

import java.util.ArrayList;
import java.util.List;

public class TaskQueue {

    private final List<String> queue;
    private final Toaster toaster;

    public TaskQueue(Toaster toaster) {
        this.queue = new ArrayList<>();
        this.toaster = toaster;
        startTaskProcessor();
    }

    public synchronized void addToQueue(String message) {
        queue.add(message);
    }

    private String getNextMessage() {
        synchronized (queue) {
            if (queue.isEmpty()) {
                return null;
            }
            return queue.remove(0);
        }
    }

    private void startTaskProcessor() {
        new Thread(() -> {
            while (true) {
                try {
                    String message;
                    while ((message = getNextMessage()) == null) {
                        Thread.sleep(50); // Wait for 50ms if queue is empty
                    }
                    toaster.toaster(message);
                    Thread.sleep(3000); // Wait 3 seconds before executing next task
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public List<String> getQueue() {
        return this.queue;
    }

}
