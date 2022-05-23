package extentions;
import dao.IDao;

public class DaoImpl2 implements IDao {
    @Override
    public double getData() {
        System.out.println("DaoImpl Version 3");
        double temp=6000;
        return temp;
    }
}
