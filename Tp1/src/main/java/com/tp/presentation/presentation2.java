package com.tp.presentation;

import com.tp.dao.IDao;
import com.tp.metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class presentation2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("C:\\Users\\marou\\OneDrive\\Desktop\\Architecture-des-composants-d-entreprises\\Tp1\\src\\main\\java\\com\\tp\\presentation\\config.txt"));
        String daoClassName = scanner.nextLine();

        Class<?> cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

        String metierClassName = scanner.nextLine();
        Class<?> cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();

        Method setDaoMethod = cMetier.getMethod("setDao", IDao.class);
        setDaoMethod.invoke(metier, dao);

        System.out.println("Résultats = " + metier.calcul());

        scanner.close();
    }
}
