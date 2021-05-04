package pt.ulisboa.forward.ewp.api.client.utils;

import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * A decoder for Feign that uses a Spring OXM's Jaxb2Marshaller. By providing a Jaxb2Marshaller it
 * is possible to make use of all features that Jaxb2Marshaller provides, including setting package
 * names to scan all classes on it. For EWP, this is necessary to automatically import all data
 * classes.
 */
public class EwpJaxbDecoder implements Decoder {

  private final Jaxb2Marshaller marshaller;

  public EwpJaxbDecoder() {
    this.marshaller = ApiUtils.getJaxb2Marshaller();
  }

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
      saxParserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",
          false);
      saxParserFactory.setNamespaceAware(true);

      return this.marshaller.unmarshal(new SAXSource(
          saxParserFactory.newSAXParser().getXMLReader(),
          new InputSource(response.body().asInputStream())));
    } catch (ParserConfigurationException | SAXException e) {
      throw new DecodeException(response.status(), e.toString(), response.request(), e);
    } finally {
      if (response.body() != null) {
        response.body().close();
      }
    }
  }
}
