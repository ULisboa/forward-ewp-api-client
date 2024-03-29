package pt.ulisboa.forward.ewp.api.client.dto.iias.hash;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"hashExtracted", "hashExpected", "valid"})
@XmlRootElement(name = "iia-with-hash-validation",
    namespace =
        "https://github.com/ULisboa/ewp-node/tree/master/api/forward/ewp/iias/hash/validation")
public class InterInstitutionalAgreementHashValidationDto {

  @XmlElement(name = "hash-extracted", required = true,
      namespace =
          "https://github.com/ULisboa/ewp-node/tree/master/api/forward/ewp/iias/hash/validation")
  private String hashExtracted;

  @XmlElement(name = "hash-expected", required = true,
      namespace =
          "https://github.com/ULisboa/ewp-node/tree/master/api/forward/ewp/iias/hash/validation")
  private String hashExpected;

  @XmlElement(name = "valid", required = true,
      namespace =
          "https://github.com/ULisboa/ewp-node/tree/master/api/forward/ewp/iias/hash/validation")
  private boolean valid;

  public InterInstitutionalAgreementHashValidationDto() {

  }

  public InterInstitutionalAgreementHashValidationDto(String hashExtracted,
      String hashExpected, boolean valid) {
    this.hashExtracted = hashExtracted;
    this.hashExpected = hashExpected;
    this.valid = valid;
  }

  public String getHashExtracted() {
    return hashExtracted;
  }

  public void setHashExtracted(String hashExtracted) {
    this.hashExtracted = hashExtracted;
  }

  public String getHashExpected() {
    return hashExpected;
  }

  public void setHashExpected(String hashExpected) {
    this.hashExpected = hashExpected;
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }
}