package pt.ulisboa.forward.ewp.api.client.dto.iias.hash;

import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"hashes"})
@XmlRootElement(name = "hashes",
    namespace =
        "https://github.com/ULisboa/ewp-node/tree/master/api/forward/ewp/iias/hash/calculation")
public class InterInstitutionalAgreementHashesCalculationResultDTO {

  @XmlElement(name = "hashes", required = true,
      namespace =
          "https://github.com/ULisboa/ewp-node/tree/master/api/forward/ewp/iias/hash/calculation")
  private Collection<String> hashes;

  public InterInstitutionalAgreementHashesCalculationResultDTO() {
  }

  public InterInstitutionalAgreementHashesCalculationResultDTO(Collection<String> hashes) {
    this.hashes = hashes;
  }

  public Collection<String> getHashes() {
    return hashes;
  }

  public void setHashes(Collection<String> hashes) {
    this.hashes = hashes;
  }

}
