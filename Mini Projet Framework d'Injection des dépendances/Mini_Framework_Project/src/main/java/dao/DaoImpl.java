package dao;

import annotation.AnComponent;
@AnComponent
public class DaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("version base de données");
        double temp=Math.random()*40;
        return temp;
    }
}


