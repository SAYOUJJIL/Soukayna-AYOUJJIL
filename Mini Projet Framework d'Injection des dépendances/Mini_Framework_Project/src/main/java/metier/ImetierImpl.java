package metier;
import annotation.AnAutowired;
import annotation.AnComponent;
import dao.IDao;
@AnComponent
public class ImetierImpl implements Imetier {
    @AnAutowired
    private IDao dao;
    @Override
    public double calcule() {
        double tmp= dao.getData();
        double res=tmp*540/Math.cos(tmp*Math.PI);
        return res;
    }
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
