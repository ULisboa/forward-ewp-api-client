package pt.ulisboa.forward.ewp.api.client;

import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public abstract class AbstractTest {

  protected <T> String marshallToXml(T object) throws JAXBException {
    JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
    StringWriter stringWriter = new StringWriter();
    jaxbContext.createMarshaller().marshal(object, stringWriter);
    return stringWriter.toString();
  }
}
