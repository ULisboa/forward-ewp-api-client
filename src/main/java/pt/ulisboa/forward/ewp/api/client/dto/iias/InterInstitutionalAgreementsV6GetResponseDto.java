package pt.ulisboa.forward.ewp.api.client.dto.iias;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.springframework.util.Base64Utils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"iias", "rawXmlInBase64"})
@XmlRootElement(name = "iias")
public class InterInstitutionalAgreementsV6GetResponseDto {
  @XmlElement(name = "iia", required = true)
  private Collection<InterInstitutionalAgreementV6WithHashValidationResponseDto> iias = new ArrayList<>();

  @XmlElement(name = "raw-xml-base64")
  private byte[] rawXmlInBase64;

  public InterInstitutionalAgreementsV6GetResponseDto() {
  }

  public InterInstitutionalAgreementsV6GetResponseDto(
      Collection<InterInstitutionalAgreementV6WithHashValidationResponseDto> iias,
      byte[] rawXmlInBase64) {
    this.iias = iias;
    this.rawXmlInBase64 = rawXmlInBase64;
  }

  public Collection<InterInstitutionalAgreementV6WithHashValidationResponseDto> getIias() {
    return iias;
  }

  public void setIias(
      Collection<InterInstitutionalAgreementV6WithHashValidationResponseDto> iias) {
    this.iias = iias;
  }

  /**
   * Returns the raw XML as a string.
   * This raw XML is the actual response of the subsequent IIA EWP API that the EWP Node makes.
   *
   * @return The raw XML as a string
   */
  public String getRawXmlAsString() {
    return new String(getRawXml());
  }

  /**
   * Returns the raw XML as a byte array.
   * This raw XML is the actual response of the subsequent IIA EWP API that the EWP Node makes.
   *
   * @return The raw XML as a byte array
   */
  public byte[] getRawXml() {
    return Base64Utils.decode(this.rawXmlInBase64);
  }

  /**
   * Returns the raw XML as a byte array, encoded in base 64.
   * This raw XML is the actual response of the subsequent IIA EWP API that the EWP Node makes.
   *
   * @return The raw XML as a byte array, encoded in base64
   */
  public byte[] getRawXmlInBase64() {
    return rawXmlInBase64;
  }

  public void setRawXmlInBase64(byte[] rawXmlInBase64) {
    this.rawXmlInBase64 = rawXmlInBase64;
  }
}
