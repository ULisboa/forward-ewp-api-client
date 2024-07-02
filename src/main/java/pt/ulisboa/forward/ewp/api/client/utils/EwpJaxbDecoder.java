package pt.ulisboa.forward.ewp.api.client.utils;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import eu.erasmuswithoutpaper.api.architecture.v1.EmptyV1;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto;

/** A decoder for Feign that uses a lazy loaded Unmarshaller. */
public class EwpJaxbDecoder implements Decoder {

  private static final Supplier<Unmarshaller> UNMARSHALLER_SUPPLIER =
      Suppliers.memoize(
          () -> {
            try {
              JAXBContext jaxbContext =
                  XmlUtils.createJAXBContext(
                      Map.of(
                          "eu.erasmuswithoutpaper.api", EmptyV1.class.getClassLoader(),
                          "pt.ulisboa.forward.ewp.api.client.dto",
                              ResponseDto.class.getClassLoader()));
              return jaxbContext.createUnmarshaller();
            } catch (JAXBException e) {
              throw new IllegalStateException(
                  "Failed to create unmarshaller for EWP Node communications", e);
            }
          });

  public EwpJaxbDecoder() {}

  @Override
  public Object decode(Response response, Type type) throws IOException, FeignException {
    if (response.status() == 204) {
      return Util.emptyValueOf(type);
    }
    if (response.body() == null) {
      return null;
    }
    while (type instanceof ParameterizedType) {
      ParameterizedType ptype = (ParameterizedType) type;
      type = ptype.getRawType();
    }
    if (!(type instanceof Class)) {
      throw new UnsupportedOperationException(
          "JAXB only supports decoding raw types. Found " + type);
    }

    try {
      SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
      /* Explicitly control sax configuration to prevent XXE attacks */
      saxParserFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
      saxParserFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
      saxParserFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false);
      saxParserFactory.setFeature(
          "http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
      saxParserFactory.setNamespaceAware(true);

      return UNMARSHALLER_SUPPLIER
          .get()
          .unmarshal(
              new SAXSource(
                  saxParserFactory.newSAXParser().getXMLReader(),
                  new InputSource(response.body().asInputStream())));
    } catch (ParserConfigurationException | SAXException | JAXBException e) {
      throw new DecodeException(response.status(), e.toString(), response.request(), e);
    } finally {
      if (response.body() != null) {
        response.body().close();
      }
    }
  }
}
