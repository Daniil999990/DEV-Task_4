import DTO.Client;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database database = Database.getInstance();

        ClientService clientService = new ClientService(database);
        try {
            long clientId = clientService.create("John");
            System.out.println("New client ID: " + clientId);

            String clientName = clientService.getById(clientId);
            System.out.println("Client name by ID: " + clientName);

            clientService.setName(clientId, "Daniil");
            System.out.println("Updated client name: " + clientService.getById(clientId));

            clientService.deleteById(clientId);

            List<Client> allClients = clientService.listAll();
            System.out.println("All clients: ");
            for (Client client : allClients) {
                System.out.println(client.getId() + " - " + client.getName());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
