


import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Creator {

    private final String WORK_ORDER_DIRECTORY = "./work-orders/";

    public static void main(String args[]) {
        Creator creator = new Creator();
        creator.createWorkOrders();
    }

    public void createWorkOrders() {
        // read input, create work orders and write as json files
        Scanner scanner = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        while (true) {
            waitForUser(scanner);
            WorkOrder newWorkOrder = getWorkOrderFromUser(scanner);
            System.out.print(".");
            createJsonFile(newWorkOrder, mapper);
        }
    }

    private void waitForUser(Scanner sc) {
        System.out.println("Press enter to add a new work order...");
        sc.nextLine();
    }

    private void createJsonFile(WorkOrder order, ObjectMapper mapper) {
        File filename = new File(WORK_ORDER_DIRECTORY + String.valueOf(order.getId()) + ".json");
        System.out.print(".");
        try (FileWriter fileWriter = new FileWriter(filename)) {
            String json = mapper.writeValueAsString(order);
            fileWriter.write(json);
            System.out.print(".\n");
            System.out.println("Work order file created.\n");
        } catch (JsonProcessingException ex) {
            System.out.println("Error in converting to JSON");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error in writing file to path");
            ex.printStackTrace();
        }
    }

    private WorkOrder getWorkOrderFromUser(Scanner sc) {
        System.out.println("Ready to create new work order");
        WorkOrder order = new WorkOrder();
        order.setId(Math.abs((int) System.currentTimeMillis()));
        System.out.printf("\tWork Order - Id# %d\n", order.getId());
        System.out.println("\t-------------------");
        System.out.print("\tDescription - ");
        order.setDescription(sc.nextLine());
        System.out.print("\tName of Technician - ");
        order.setSenderName(sc.nextLine());
        order.setStatus(Status.INITIAL);
        System.out.print("\nCreating.");

        return order;
    }
}