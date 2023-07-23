import DTO.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientService {
    private Map<Long, Client> clients;
    private long nextId;

    public ClientService() {
        this.clients = new HashMap<>();
        this.nextId = 1;
    }

    public long create(String name) {
        validateName(name);
        long id = nextId++;
        Client client = new Client(id, name);
        clients.put(id, client);
        return id;
    }

    public String getById(long id) {
        Client client = clients.get(id);
        if (client == null) {
            throw new IllegalArgumentException("Client not found with id: " + id);
        }
        return client.getName();
    }

    public void setName(long id, String name) {
        validateName(name);
        Client client = clients.get(id);
        if (client == null) {
            throw new IllegalArgumentException("Client not found with id: " + id);
        }
        client.setName(name);
    }

    public void deleteById(long id) {
        if (!clients.containsKey(id)) {
            throw new IllegalArgumentException("Client not found with id: " + id);
        }
        clients.remove(id);
    }

    public List<Client> listAll() {
        return new ArrayList<>(clients.values());
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (name.length() < 3 || name.length() > 50) {
            throw new IllegalArgumentException("Name length must be between 3 and 50 characters");
        }
    }
}
