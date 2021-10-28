<h1 align="center">
  <br>
  <a href="https://www.erasmuswithoutpaper.eu/"><img src="https://developers.erasmuswithoutpaper.eu/logo.png" alt="EWP" width="350"></a>
  <a href="https://www.ulisboa.pt/"><img src="https://rem.rc.iseg.ulisboa.pt/img/logo_ulisboa.png" alt="ULisboa" width="380"></a>
    <br>
  Forward EWP APIs Client for EWP Node
  <br>
</h1>

<p align="center">
  <a href="https://maven-badges.herokuapp.com/maven-central/pt.ulisboa/forward-ewp-api-client">
    <img src="https://maven-badges.herokuapp.com/maven-central/pt.ulisboa/forward-ewp-api-client/badge.svg"
        alt="Maven Central">
  </a>
  <a href="https://github.com/ULisboa/forward-ewp-api-client/blob/master/LICENSE">
    <img alt="GitHub" src="https://img.shields.io/github/license/ulisboa/forward-ewp-api-client">
  </a>
  <img src="https://img.shields.io/badge/JDK-11+-green.svg" alt="JDK 11+">
</p>

<h4 align="center">This Java library provides a simple way to use the Forward EWP APIs of an EWP
Node (https://github.com/ULisboa/ewp-node) by abstracting protocol communications.</h4>

## Work in Progress warning

This project is still early in development. Therefore, until the first major version is released,
non-backward changes may be introduced.

## Requirements

To clone and use this project, you'll need [Git](https://git-scm.com), if you intend to build it
yourself, and [Maven](https://maven.apache.org/).

## Using the library

To use the library, click the Maven Central badge above and use the correct snippet for your
dependency manager.

## Cloning the Project

To clone the project run:

```
git clone --recursive https://github.com/ULisboa/forward-ewp-api-client
```

Note the ```--recursive``` flag that is needed so the external dependencies configured as submodules
are also cloned.

## Building with Maven

Run the command line:

```
mvn clean package
```

The JAR will be located at forward-ewp-api-client/target. The JAR may then be added as library to
any Java project.

## Usage Example

A typical usage example, for obtaining the information of a given institution by its HEI ID, is as
follows:

```
import eu.erasmuswithoutpaper.api.institutions.InstitutionsResponse;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.config.ClientConfiguration;
import pt.ulisboa.forward.ewp.api.client.contract.institutions.InstitutionsApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseDto.Message;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.factory.ApiClientFactory;

public class MyApp {

  public static void main(String[] args) {
    ClientConfiguration.configure("https://example.com", "host-code", "secret");
    InstitutionsApi client = ApiClientFactory.createClient(InstitutionsApi.class);
    ResponseWithDataDto<InstitutionsResponse> response = client.findByHeiId("xyz");
    List<Message> nodeMessages = response.getMessages();
    InstitutionsResponse institutionsResponse = response.getDataObject();
    // Handle the response obtained...
  }
}
```

The following sections detail the main steps to use the library. Other aspects/details are also
included.

## Configuration

The library must be configured before being able to use it. For that, it is necessary to run the
following fragment:

```
ClientConfiguration.configure("https://example.com", "host-code", "secret");
```

Once this fragment is ran, every client created will use it as reference. Therefore, as long as the
configuration parameters do not change, it is only necessary to run this fragment once. The
configuration expects three parameters, by order:

1. The EWP Node base URL;
2. The host code registered on the EWP Node;
3. The secret associated with the host (identified by the host code) registered on the EWP Node.

## Client Creation

To create a client for a specific Forward EWP API, first, it is necessary to know what is the
corresponding contract interface. The contract interface is a Java interface providing methods that
abstract the communication details. The supported Forward EWP APIs and corresponding contract
interfaces can be checked at [APIs supported](#apis-supported).

A client is created by running:

```
<CONTRACT_CLASS> client = ApiClientFactory.createClient(<CONTRACT_CLASS>.class);
```

Replace the fragments ```<CONTRACT_CLASS>``` with the contract interface corresponding to the
intended Forward EWP API.

## Client Responses

After a client is created, you can use it by invoking the methods specified on the contract
interface.

When a method of the client is called, generally, as long as the request does not fail, an instance
of ResponseWithDataDto\<T\>, where T may be any EWP data class (except for the Authentication API,
where it is of a specific EWP Node data class), is returned. That instance will contain a list of
messages that the EWP Node may have produced
(that may contain relevant information), and the EWP data object itself, as returned by the target
EWP server.

## Exception handling

When a client request fails (for example, when running, in the usage example,
```client.findByHeiId("xyz")```, it fails because the given HEI ID does not implement the
corresponding EWP API or for some other reason), there are two possible runtime exceptions thrown:

- RequestException: Thrown for all failed requests. It contains the status code of the call, a list
  of messages that the EWP Node produced (specially when incorrect values are passed to the Forward
  EWP API) and, possibly (may be undefined), an ErrorResponse instance that contains the original
  error response emitted by the target EWP server;
- ErrorDecoderException: Thrown if error decoder fails to throw a RequestException. If this is
  thrown it may indicate some problem with the EWP Node or the client itself.

## APIs supported

All the contract interfaces are located on package pt.ulisboa.forward.ewp.api.client.contract.

Currently, the supported Forward EWP APIs and corresponding contract interfaces are as follows:

| Name of the API  | Contract interface  |
|---|---|
| Authentication  | AuthenticationApi  |
| Institutions V2  | InstitutionsV2Api  |
| Organizational Units V2  | OrganizationalUnitsV2Api  |
| Simple Course Replication V1  | SimpleCourseReplicationV1Api  |
| Courses V0  | CoursesV0Api  |
| InterInstitutional Agreements V3  | InterInstitutionalAgreementsV3Api  |
| InterInstitutional Agreements V4 | InterInstitutionalAgreementsV4Api  |
| InterInstitutional Agreement CNR V2 | InterInstitutionalAgreementCnrV2Api  |
| InterInstitutional Agreement Approvals V1  | InterInstitutionalAgreementApprovalsV1Api  |
| Fact Sheets V1  | FactsheetsV1Api  |
| Courses V0  | CoursesV0Api  |
| Outgoing Mobilities V1 | OutgoingMobilitiesV1Api  |
| Outgoing Mobility CNR V1 | OutgoingMobilityCnrV1Api  |
| Outgoing Mobility Learning Agreements V1 | OutgoingMobilityLearningAgreementsV1Api  |
| Outgoing Mobility Learning Agreement CNR V1 | OutgoingMobilityLearningAgreementCnrV1Api  |
| Incoming Mobilities V1 | IncomingMobilitiesV1Api  |
| Incoming Mobilities CNR V1 | IncomingMobilityCnrApi  |
| Incoming Mobilities ToR CNR V1 | IncomingMobilityToRCnrApi  |

The contract interfaces contain Javadoc comments that are relevant to read before using them.

## License

This project is licensed under the terms of the [MIT license](LICENSE).