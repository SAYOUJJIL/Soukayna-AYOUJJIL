package annotation;

import metier.Imetier;

import java.lang.reflect.InvocationTargetException;
public class PreAnnotation {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
    ConfigurationAnnotation context=new ConfigurationAnnotation();
    context.getClasses("dao", "metier");
    Imetier imetier= (Imetier) context.getInstances().get(Imetier.class);
    System.out.println(imetier.calcule());
}}

