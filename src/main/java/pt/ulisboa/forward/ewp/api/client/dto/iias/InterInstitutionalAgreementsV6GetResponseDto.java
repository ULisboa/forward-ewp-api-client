package pt.ulisboa.forward.ewp.api.client.dto.iias;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"iias"})
@XmlRootElement(name = "iias")
public class InterInstitutionalAgreementsV6GetResponseDto {
  @XmlElement(name = "iia", required = true)
  private Collection<InterInstitutionalAgreementV6WithHashValidationResponseDto> iias = new ArrayList<>();

  public InterInstitutionalAgreementsV6GetResponseDto() {
  }

  public InterInstitutionalAgreementsV6GetResponseDto(
      Collection<InterInstitutionalAgreementV6WithHashValidationResponseDto> iias) {
    this.iias = iias;
  }

  public Collection<InterInstitutionalAgreementV6WithHashValidationResponseDto> getIias() {
    return iias;
  }

  public void setIias(
      Collection<InterInstitutionalAgreementV6WithHashValidationResponseDto> iias) {
    this.iias = iias;
  }
}
