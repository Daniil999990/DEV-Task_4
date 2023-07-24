import DTO.Client;

import java.util.List;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(Database clientRepository) {
        this.clientRepository = (ClientRepository) clientRepository;
    }

    public long create(String name) {
        validateName(name);
        Client client = new Client(0, name);
        return clientRepository.create(client);
    }

    public String getById(long id) {
        Client client = clientRepository.getById(id);
        if (client == null) {
            throw new IllegalArgumentException("Client not found with id: " + id);
        }
        return client.getName();
    }

    public void setName(long id, String name) {
        validateName(name);
        Client client = clientRepository.getById(id);
        if (client == null) {
            throw new IllegalArgumentException("Client not found with id: " + id);
        }
        client.setName(name);
        clientRepository.update(client);
    }

    public void deleteById(long id) {
        if (clientRepository.getById(id) == null) {
            throw new IllegalArgumentException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }

    public List<Client> listAll() {
        return clientRepository.listAll();
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
