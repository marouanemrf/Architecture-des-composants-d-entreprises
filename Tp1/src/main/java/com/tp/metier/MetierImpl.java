package com.tp.metier;

import com.tp.dao.IDao;

public class MetierImpl implements IMetier {
    private IDao dao;

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        return dao.getValue() * 2;
    }
}
