package com.test.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("dao2")
public class DaoImpl2 implements IDao {
    @Override public double getValue(){ return 150.0; }
}