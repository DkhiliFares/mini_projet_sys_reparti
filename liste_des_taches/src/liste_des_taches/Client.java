package liste_des_taches;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
public class Client {
	public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Remotelist stub = (Remotelist) registry.lookup("Rem");

            // Test the RMI methods
            stub.addTask("Task 1");
            stub.addTask("Task 2");
            List<String> tasks = stub.getTasks();
            for (String task : tasks) {
                System.out.println(task);
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

