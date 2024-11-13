package com.dipartimento.demowebapplications.persistence.dao.impljdbc;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.model.Ristorante;
import com.dipartimento.demowebapplications.persistence.DBManager;
import com.dipartimento.demowebapplications.persistence.dao.PiattoDao;

import java.util.ArrayList;
import java.util.List;

public class RistoranteProxy extends Ristorante {
    @Override
    public List<Piatto> getPiatti() {
        if (this.piatti == null) {
            this.piatti = new ArrayList<>();
            List<String> piattiNomi = DBManager.getInstance().getRistorantePiattoDao().findPiattiByRistorante(this.nome);
            PiattoDao piattoDao = DBManager.getInstance().getPiattoDao();

            for (String piattoNome : piattiNomi) {
                Piatto piatto = piattoDao.findByPrimaryKey(piattoNome);
                if (piatto != null) {
                    this.piatti.add(piatto);
                }
            }
        }
        return this.piatti;
    }
}
