package pt.ulisboa.forward.ewp.api.client;

import eu.erasmuswithoutpaper.api.architecture.v1.EmptyV1;
import java.io.StringWriter;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto;
import pt.ulisboa.forward.ewp.api.client.utils.XmlUtils;

public abstract class AbstractTest {

  protected <T> String marshallToXml(T object) throws JAXBException {
    JAXBContext jaxbContext =
        XmlUtils.createJAXBContext(
            Map.of(
                "eu.erasmuswithoutpaper.api",
                EmptyV1.class.getClassLoader(),
                "pt.ulisboa.forward.ewp.api.client.dto",
                ResponseDto.class.getClassLoader()));
    Marshaller marshaller = jaxbContext.createMarshaller();
    StringWriter stringWriter = new StringWriter();
    marshaller.marshal(object, stringWriter);
    return stringWriter.toString();
  }
}
