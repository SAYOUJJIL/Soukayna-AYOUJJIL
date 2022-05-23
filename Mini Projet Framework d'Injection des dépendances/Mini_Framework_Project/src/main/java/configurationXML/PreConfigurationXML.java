package configurationXML;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class PreConfigurationXML {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        ConfigurationXML classInst=new ConfigurationXML("configurationXML.xml");
        System.out.println(classInst.getClasse().calcule());
    }
}
