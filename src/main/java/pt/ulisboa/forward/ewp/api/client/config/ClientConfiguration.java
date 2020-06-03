package pt.ulisboa.forward.ewp.api.client.config;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.Objects;

/** Class that contains configuration necessary to build the client to the Forward EWP APIs. */
public class ClientConfiguration {

  private static ClientConfiguration instance;

  private final String apiBaseUrl;
  private final String hostCode;
  private final String secret;

  private ClientConfiguration(String apiBaseUrl, String hostCode, String secret) {
    Objects.requireNonNull(apiBaseUrl, "API Base URL must not be null");
    Objects.requireNonNull(hostCode, "Host code must not be null");
    Preconditions.checkArgument(!Strings.isNullOrEmpty(secret), "Secret must not be empty");

    this.apiBaseUrl = apiBaseUrl;
    this.hostCode = hostCode;
    this.secret = secret;
  }

  public String getApiBaseUrl() {
    return apiBaseUrl;
  }

  public String getHostCode() {
    return hostCode;
  }

  public String getSecret() {
    return secret;
  }

  /**
   * Configures globally the client.
   *
   * @param apiBaseUrl Base API URL for the Forward Ewp APIs. For example, if the Forward
   *     Institutions EWP API is available at https://www.example.com/api/forward/ewp/institutions,
   *     then the base API URL is https://www.example.com/.
   * @param hostCode Unique identifier of the host.
   * @param secret Secret associated with the host, used for encoding during the authentication
   *     phase.
   */
  public static void configure(String apiBaseUrl, String hostCode, String secret) {
    instance = new ClientConfiguration(apiBaseUrl, hostCode, secret);
  }

  /** Resets the client configuration, cleaning it. */
  public static void reset() {
    instance = null;
  }

  /**
   * Obtains the current instance of the client configuration.
   *
   * @return Current instance of the client configuration
   */
  public static ClientConfiguration getInstance() {
    return instance;
  }
}
