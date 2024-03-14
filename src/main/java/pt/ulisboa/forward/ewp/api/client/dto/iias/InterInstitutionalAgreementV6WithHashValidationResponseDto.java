package pt.ulisboa.forward.ewp.api.client.dto.iias;

import eu.erasmuswithoutpaper.api.iias.v6.endpoints.IiasGetResponseV6.Iia;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import pt.ulisboa.forward.ewp.api.client.dto.iias.hash.InterInstitutionalAgreementHashValidationDto;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"iia", "hashValidation"})
@XmlRootElement(name = "iia-with-hash-validation")
public class InterInstitutionalAgreementV6WithHashValidationResponseDto {

  @XmlElement(name = "iia", required = true, namespace = "https://github.com/erasmus-without-paper/ewp-specs-api-iias/blob/stable-v6/endpoints/get-response.xsd")
  private Iia iia;

  @XmlElement(name = "hash-validation", required = true)
  private InterInstitutionalAgreementHashValidationDto hashValidation;

  public InterInstitutionalAgreementV6WithHashValidationResponseDto() {
  }

  public InterInstitutionalAgreementV6WithHashValidationResponseDto(
      Iia iia, InterInstitutionalAgreementHashValidationDto hashValidation) {
    this.iia = iia;
    this.hashValidation = hashValidation;
  }

  public Iia getIia() {
    return iia;
  }

  public void setIia(Iia iia) {
    this.iia = iia;
  }

  public InterInstitutionalAgreementHashValidationDto getHashValidation() {
    return hashValidation;
  }

  public void setHashValidation(
      InterInstitutionalAgreementHashValidationDto hashValidation) {
    this.hashValidation = hashValidation;
  }
}
