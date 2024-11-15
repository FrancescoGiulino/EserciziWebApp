package org.example.es2webapp.model.dao.ristorante;

import org.example.es2webapp.model.entities.Ristorante;
import org.springframework.data.repository.CrudRepository;

//quest'interfaccia estende l'interfaccia CrudRepository di SpringBoot
//prende due tipi: <oggetto_da_manipolare, chiave>
public interface RistorantiRepository extends CrudRepository<Ristorante, Long> {
    //VUOTA --> hybernate si preoccuperà di creare tutti i metodi che serviranno al controller
}
