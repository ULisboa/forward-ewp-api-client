package pt.ulisboa.forward.ewp.api.client.factory;

import feign.Client;
import feign.Client.Default;
import feign.Feign;
import feign.Logger.Level;
import feign.Target;
import feign.Target.HardCodedTarget;
import feign.form.FormEncoder;
import feign.jaxb.JAXBContextFactory;
import feign.jaxb.JAXBContextFactory.Builder;
import feign.jaxb.JAXBEncoder;
import feign.slf4j.Slf4jLogger;
import javax.net.ssl.HttpsURLConnection;
import pt.ulisboa.forward.ewp.api.client.config.ClientConfiguration;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.decoder.ApiErrorDecoder;
import pt.ulisboa.forward.ewp.api.client.interceptor.JwtRequestInterceptor;
import pt.ulisboa.forward.ewp.api.client.utils.EwpJaxbDecoder;

/**
 * Factory that provides client instances for specific Forward EWP APIs.
 */
public class ApiClientFactory {

  private ApiClientFactory() {
  }

  /**
   * Creates a client to a specific Forward EWP API.
   *
   * @param apiClassType API type.
   * @return Client for a specific Forward EWP API.
   */
  public static <T extends BaseApi> T createClient(Class<T> apiClassType) {
    ClientConfiguration configuration = getConfiguration();
    return createClient(
        new Default(null, HttpsURLConnection.getDefaultHostnameVerifier()),
        new HardCodedTarget<>(apiClassType, configuration.getApiBaseUrl()));
  }

  public static <T extends BaseApi> T createClient(Client httpClient, Target<T> target) {
    return Feign.builder()
        .encoder(new FormEncoder(getJAXBEncoder()))
        .decoder(new EwpJaxbDecoder())
        .requestInterceptor(new JwtRequestInterceptor())
        .client(httpClient)
        .errorDecoder(new ApiErrorDecoder())
        .logger(new Slf4jLogger())
        .logLevel(Level.BASIC)
        .target(target);
  }

  private static JAXBEncoder getJAXBEncoder() {
    JAXBContextFactory jaxbContextFactory = new Builder().build();
    return new JAXBEncoder(jaxbContextFactory);
  }

  private static ClientConfiguration getConfiguration() {
    ClientConfiguration configuration = ClientConfiguration.getInstance();
    if (configuration == null) {
      throw new IllegalStateException("Client configuration must be set before creating a client");
    }
    return configuration;
  }
}
