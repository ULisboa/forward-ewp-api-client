package pt.ulisboa.forward.ewp.api.client;

import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import pt.ulisboa.forward.ewp.api.client.utils.XmlUtils;

public abstract class AbstractTest {

  protected <T> String marshallToXml(T object) throws JAXBException {
    JAXBContext jaxbContext =
        XmlUtils.createJAXBContext(
            "eu.erasmuswithoutpaper.api", "pt.ulisboa.forward.ewp.api.client.dto");
    Marshaller marshaller = jaxbContext.createMarshaller();
    StringWriter stringWriter = new StringWriter();
    marshaller.marshal(object, stringWriter);
    return stringWriter.toString();
  }
}
