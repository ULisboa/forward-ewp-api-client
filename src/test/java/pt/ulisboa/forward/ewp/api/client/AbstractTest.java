package pt.ulisboa.forward.ewp.api.client;

import java.io.StringWriter;
import javax.xml.bind.JAXBException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public abstract class AbstractTest {

  protected <T> String marshallToXml(T object) throws JAXBException {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller
        .setPackagesToScan("eu.erasmuswithoutpaper.api", "pt.ulisboa.forward.ewp.api.client.dto");
    StringWriter stringWriter = new StringWriter();
    marshaller.createMarshaller().marshal(object, stringWriter);
    return stringWriter.toString();
  }
}
