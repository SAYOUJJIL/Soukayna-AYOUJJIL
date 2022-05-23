package injectionConstructeur;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationConstructeurs {
   private Map<Class,Object> listClass= new HashMap<Class,Object>();
   private List<Class> listClasse=new ArrayList<>();
    public ConfigurationConstructeurs(List<Class> listClasse) {
        this.listClasse= listClasse;
    }
    public Object getInstance(Class r) throws InstantiationException, IllegalAccessException {
        for (Class ce:listClass.keySet()) {
          if (ce.getInterfaces()[0].toString().equals(r.toString())){
              return listClass.get(ce);
          }
        }
        return null;
    }
    public  void instacierInjection() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        for (Class c:listClasse) {
            listClass.put(c,c.newInstance());
        }
        for (Class c:listClasse) {
            if (c.getDeclaredFields()!=null){
                for (Field f:c.getDeclaredFields()) {
                    if ( f.getType().toString().contains("i")){
                        String methodName="setDao";
                        Method method=c.getMethod(methodName,f.getType());
                        method.invoke(listClass.get(c), getInstance(f.getType()));

                    }
                }
            }
        }
        }
    public Map<Class, Object> getListClass() {
        return listClass;
    }
}

