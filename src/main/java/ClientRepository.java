import DTO.Client;

import java.util.List;

public interface ClientRepository {
    long create(Client client);
    Client getById(long id);
    void update(Client client);
    void deleteById(long id);
    List<Client> listAll();
}
