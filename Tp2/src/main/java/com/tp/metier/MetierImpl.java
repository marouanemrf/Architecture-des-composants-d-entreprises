package com.tp.metier;

import com.tp.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("metier") // Déclare ce bean comme un composant Spring
public class MetierImpl implements IMetier {

    @Autowired
    private IDao dao;
    public  void  setDao(IDao dao) {
        this.dao = dao;
    }


    @Override
    public double calcul() {
        return dao.getValue() * 2;
    }
}
