package com.test.metier;

import com.test.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;



@Component("metier")
public class MetierImpl implements IMetier {
    @Autowired
    private IDao dao;

    @PostConstruct
    private void init() {
        System.out.println("[TRACE] DAO injecté = " + dao.getClass().getSimpleName());
    }

    @Override
    public double calcul() { return dao.getValue() * 2; }
}