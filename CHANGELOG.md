# Changelog

## [0.30.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.30.0) (2024-07-02)





## [0.29.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.29.0) (2024-07-02)




### Refactor
-  adapt to use JAXBContext instead of spring-oxm ([b80382467879063](https://github.com/ULisboa/forward-ewp-api-client/commit/b803824678790633bc50f9cf617664a20cb1534e))

## [0.28.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.28.0) (2024-05-28)

### Features
-  CnrApi now allows to obtain the statuses of several change notifications ([e7a6bb460651989](https://github.com/ULisboa/forward-ewp-api-client/commit/e7a6bb4606519892dbd176581b6dcafc450ab2f2))




## [0.27.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.27.0) (2024-05-28)

### Features
-  add support to obtain a CNR status ([7adb89b2da519dc](https://github.com/ULisboa/forward-ewp-api-client/commit/7adb89b2da519dca969ec1893609407c72c1389f))
-  return change notification IDs as response to CNR Forward EWP APIs ([dbc363c4a21d01c](https://github.com/ULisboa/forward-ewp-api-client/commit/dbc363c4a21d01c522baf566511ecfa4d930a3d1))




## [0.26.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.26.0) (2024-05-02)




### Refactor
-  remove support for discontinued IIAs v6 API, IIA Approval v1 API ([8ecad8b5544e3c1](https://github.com/ULisboa/forward-ewp-api-client/commit/8ecad8b5544e3c1df96b744d92e2e82b4c8b5ed7))

## [0.25.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.25.0) (2024-03-18)


### Improvements
-  adapt Files V1 API to EWP Node 0.29.0 ([7c360949a64fbe9](https://github.com/ULisboa/forward-ewp-api-client/commit/7c360949a64fbe9ee2b279b3527b5e50fa778459))



## [0.24.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.24.0) (2024-03-15)

### Features
-  add namespaces to several response DTOs ([fbf4b38731c59a4](https://github.com/ULisboa/forward-ewp-api-client/commit/fbf4b38731c59a4ae72c014943104fa7e7665f40))




## [0.23.1](https://github.com/ULisboa/forward-ewp-api-client/tree/0.23.1) (2024-03-14)



### Bug Fixes
-  IIA deserialization on InterInstitutionalAgreementV7WithHashValidationResponseDto and InterInstitutionalAgreementV6WithHashValidationResponseDto could fail due to missing namespace ([d15580de46d9fa3](https://github.com/ULisboa/forward-ewp-api-client/commit/d15580de46d9fa3f1344c815793c9924466f1bcf))


## [0.23.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.23.0) (2023-11-20)

### Features
-  add InterInstitutionalAgreementApprovalsV2Api ([869d4d2399447df](https://github.com/ULisboa/forward-ewp-api-client/commit/869d4d2399447df86c2856fbce72fb4b8972e45b))




## [0.22.1](https://github.com/ULisboa/forward-ewp-api-client/tree/0.22.1) (2023-11-03)


### Improvements
-  update InterInstitutionalAgreementsV7Api to latest specification ([cef3e6041356f53](https://github.com/ULisboa/forward-ewp-api-client/commit/cef3e6041356f539a4ab1e5d178ad8b009b260e4))



## [0.22.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.22.0) (2023-11-03)

### Features
-  add support for IIAs Forward EWP API v7 ([5498e02a01dbd28](https://github.com/ULisboa/forward-ewp-api-client/commit/5498e02a01dbd288a34eb1c6d995983ac25ce893))




## [0.21.2](https://github.com/ULisboa/forward-ewp-api-client/tree/0.21.2) (2023-10-19)





## [0.21.1](https://github.com/ULisboa/forward-ewp-api-client/tree/0.21.1) (2023-10-19)





## [0.21.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.21.0) (2023-10-19)

### Features
-  add calculateCooperationConditionsHashes(byte[] xml) to InterInstitutionalAgreementsV6Api ([d93f3c69f6138fd](https://github.com/ULisboa/forward-ewp-api-client/commit/d93f3c69f6138fd5e448f0d67739964dd1abf88a))
-  add raw XML (actual response of the subsequent IIA EWP API that the EWP Node makes) to InterInstitutionalAgreementsV6GetResponseDto ([dc858e104a2d6c4](https://github.com/ULisboa/forward-ewp-api-client/commit/dc858e104a2d6c4794833ca3bbbab3a4dcdaef32))




## [0.20.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.20.0) (2023-10-03)


### Improvements
-  update ewp-connector-api to v3.11.1 ([40a9705fb7dd286](https://github.com/ULisboa/forward-ewp-api-client/commit/40a9705fb7dd28661cad36b1dd6a19c406939b01))
-  add client claim to JWT tokens ([072455ae19d286b](https://github.com/ULisboa/forward-ewp-api-client/commit/072455ae19d286bc127c9141e6eb635d36147ec5))
-  add issued at information to JWT tokens ([28787246b5fee98](https://github.com/ULisboa/forward-ewp-api-client/commit/28787246b5fee98486360bbcb1530a8f371c834e))
-  add expiration time to JWT tokens (defaults to 30s expiration time in future) ([d0ea53ec8bd99aa](https://github.com/ULisboa/forward-ewp-api-client/commit/d0ea53ec8bd99aa31de0663e9e14fa10e14f2785))



## [0.19.2](https://github.com/ULisboa/forward-ewp-api-client/tree/0.19.2) (2023-07-07)





## [0.19.1](https://github.com/ULisboa/forward-ewp-api-client/tree/0.19.1) (2023-07-07)

### Features
-  implement default getMessage() to RequestException ([8604cc15fe3a1c9](https://github.com/ULisboa/forward-ewp-api-client/commit/8604cc15fe3a1c9d776a559b4de5c39ae97be319))




## [0.19.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.19.0) (2023-02-20)

### Features
-  IIAs V6 Forward EWP API get responses now include the obtained IIAs instance as well hash validation information ([fe0fb48c8ca7af5](https://github.com/ULisboa/forward-ewp-api-client/commit/fe0fb48c8ca7af5dbf8506c55b7177adaef82c6f))



### Refactor
-  remove V3/V4 of IIAs Forward EWP APIs ([54bab1e0ace5eef](https://github.com/ULisboa/forward-ewp-api-client/commit/54bab1e0ace5eef9bb563f0a95d2f37c4bd7a059))

## [0.18.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.18.0) (2023-02-16)

### Features
-  add calculateCooperationConditionsHashes methods to IIAs APIs ([507c2461eb47899](https://github.com/ULisboa/forward-ewp-api-client/commit/507c2461eb47899f20b8d6fb1b4d985e34375767))




## [0.17.1](https://github.com/ULisboa/forward-ewp-api-client/tree/0.17.1) (2023-01-04)





## [0.17.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.17.0) (2023-01-04)

### Features
-  add communication ID to ResponseDto ([5003460e6b443d5](https://github.com/ULisboa/forward-ewp-api-client/commit/5003460e6b443d5b2eb52bff0df99d1aefc97994))



### Refactor
-  reuse error response to exception conversion ([5785e939a202165](https://github.com/ULisboa/forward-ewp-api-client/commit/5785e939a202165d5e3169d316d762a5d6ba6109))

## [0.16.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.16.0) (2022-12-19)

### Features
-  add automatic changelog generator ([87d34fc1d27d797](https://github.com/ULisboa/forward-ewp-api-client/commit/87d34fc1d27d797dfef6b8e8e50d6e112d255429))



### Refactor
-  rename host code to client ID on ClientConfiguration ([6b391f1b62854f4](https://github.com/ULisboa/forward-ewp-api-client/commit/6b391f1b62854f49bc333971d82cce0e8ac8f66e))

## [0.15.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.15.0) (2022-11-09)

### Features
-  add support for EWP Incoming Mobility ToRs V2 Forward API ([bb48b550a4b4afc](https://github.com/ULisboa/forward-ewp-api-client/commit/bb48b550a4b4afc7bd0c322dd7f0bfbaba5a2a91))
-  add support for EWP Outgoing Mobilities V2 Forward API ([af0efbce35b7f57](https://github.com/ULisboa/forward-ewp-api-client/commit/af0efbce35b7f578ea740ae81de13493d2e0acf1))
-  add support for EWP Files V1 Forward API ([91ee26a31521421](https://github.com/ULisboa/forward-ewp-api-client/commit/91ee26a31521421b485270b0d6f09b81556d9d21))




## [0.14.2](https://github.com/ULisboa/forward-ewp-api-client/tree/0.14.2) (2022-03-24)



### Bug Fixes
-  there was a typo in the URL of an IncomingMobilitiesV1Api endpoint ([0fc6f7d79ac0654](https://github.com/ULisboa/forward-ewp-api-client/commit/0fc6f7d79ac06543783b2c41e3b7480ffc8a655f))


## [0.14.1](https://github.com/ULisboa/forward-ewp-api-client/tree/0.14.1) (2021-12-29)



### Bug Fixes
-  methods of InterInstitutionalAgreementsV6Api were using wrong versioned return data types ([652cad8021584f6](https://github.com/ULisboa/forward-ewp-api-client/commit/652cad8021584f65d565ce6ff4b5bb6997dec9d5))


## [0.14.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.14.0) (2021-11-09)

### Features
-  update IIA CNR / Outgoing Mobility CNR / Outgoing Mobility Learning Agreement CNR APIs to conform latest changes on EWP Node ([d699af63c2be93a](https://github.com/ULisboa/forward-ewp-api-client/commit/d699af63c2be93ae411ab538bdaabf06d9c3df7d))




## [0.13.1](https://github.com/ULisboa/forward-ewp-api-client/tree/0.13.1) (2021-10-29)



### Bug Fixes
-  update request of Outgoing Mobility Learning Agreements API was not sending correctly XML ([eece6af7488df03](https://github.com/ULisboa/forward-ewp-api-client/commit/eece6af7488df034afdb7e650f9069331fc0ebfa))


## [0.13.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.13.0) (2021-10-29)

### Features
-  add support for EWP InterInstitutional Agreements V6 Forward API ([1b8d79f91172835](https://github.com/ULisboa/forward-ewp-api-client/commit/1b8d79f911728358a0a96dac9025ceb3761b0e2d))




## [0.12.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.12.0) (2021-10-28)

### Features
-  add support for EWP Incoming Mobilities ToRs Forward API ([a4420151d33f4b8](https://github.com/ULisboa/forward-ewp-api-client/commit/a4420151d33f4b8413d526d3017b48b9d4632f60))




## [0.11.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.11.0) (2021-10-28)

### Features
-  add support for EWP Incoming Mobilities ToR CNR Forward API ([0b2c2e87112c66e](https://github.com/ULisboa/forward-ewp-api-client/commit/0b2c2e87112c66e1c5d35d3be24279961c7047c2))
-  add support for EWP Incoming Mobilities CNR Forward API ([1d9fc76e7e06a61](https://github.com/ULisboa/forward-ewp-api-client/commit/1d9fc76e7e06a61e4fc35d1af1f0bd28a4550279))

### Improvements
-  adapt to version-less CNR APIs ([7ada2b9e193f9e0](https://github.com/ULisboa/forward-ewp-api-client/commit/7ada2b9e193f9e0c93790d7c42a31c0d9e4ce996))



## [0.10.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.10.0) (2021-10-27)

### Features
-  add support for EWP InterInstitutional Agreement Approval CNR Forward API ([adc0da1bf5a4f76](https://github.com/ULisboa/forward-ewp-api-client/commit/adc0da1bf5a4f769c7dea5c33183fc72ebdeba62))
-  add support for EWP InterInstitutional Agreements CNR Forward API ([ae37932ccbdd709](https://github.com/ULisboa/forward-ewp-api-client/commit/ae37932ccbdd709e8704d6ea1a41f62c71088af7))




## [0.9.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.9.0) (2021-10-26)

### Features
-  add support for EWP Outgoing Mobilities CNR Forward API and EWP Outgoing Mobility Learning Agreements CNR Forward API ([5c3248020b9ff6d](https://github.com/ULisboa/forward-ewp-api-client/commit/5c3248020b9ff6db8d8d584fab3b01eefceb94cc))




## [0.8.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.8.0) (2021-10-20)

### Features
-  add support for EWP Outgoing Mobility Learning Agreements Forward API ([2a4b952421d2d51](https://github.com/ULisboa/forward-ewp-api-client/commit/2a4b952421d2d51d67abcbe3ab6a3465ca441480))


### Bug Fixes
-  set correctly the return type of one method of OutgoingMobilitiesV1Api ([c3024d889d1abcc](https://github.com/ULisboa/forward-ewp-api-client/commit/c3024d889d1abccac0bd97739caf0ea53140aace))


## [0.7.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.7.0) (2021-05-20)




### Refactor
-  rename factsheet package to factsheets ([5ba91622d8f1daa](https://github.com/ULisboa/forward-ewp-api-client/commit/5ba91622d8f1daa49d24bb831f73fd239b26271a))

## [0.6.1](https://github.com/ULisboa/forward-ewp-api-client/tree/0.6.1) (2021-05-04)



### Bug Fixes
-  IncomingMobilitiesV1Api had wrong return types ([76d2912810fc401](https://github.com/ULisboa/forward-ewp-api-client/commit/76d2912810fc401b0829b0387d2800a6a5cb118e))


## [0.6.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.6.0) (2021-05-04)

### Features
-  create and use a new Feign decoder ([5aca2893df9c175](https://github.com/ULisboa/forward-ewp-api-client/commit/5aca2893df9c175bb3b323e478e39aad8ad7170c))




## [0.5.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.5.0) (2021-05-04)

### Features
-  add support for EWP Factsheets Forward API ([61084934173ec5a](https://github.com/ULisboa/forward-ewp-api-client/commit/61084934173ec5a2ef71da4ebb9d248b730f81e3))




## [0.4.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.4.0) (2021-04-16)

### Features
-  add support for EWP IIAs Approvals V1 Forward API ([48b2f2113cc0171](https://github.com/ULisboa/forward-ewp-api-client/commit/48b2f2113cc0171c1cce5e23162f0a287976fd31))
-  update supported major versions retrieval to latest EWP Node ([29938602818a4a6](https://github.com/ULisboa/forward-ewp-api-client/commit/29938602818a4a63ec035d5b29dfa85429869a07))




## [0.3.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.3.0) (2020-12-14)

### Features
-  add support for versioned Outgoing Mobilities Forward EWP APIs ([d5b8f82846af0b7](https://github.com/ULisboa/forward-ewp-api-client/commit/d5b8f82846af0b7becaca6195ff7190c6252f396))
-  add support for versioned Incoming Mobilities Forward EWP APIs ([9db295b45fb326c](https://github.com/ULisboa/forward-ewp-api-client/commit/9db295b45fb326c12bc951d6851f37be44d6c6fd))
-  add support for versioned IIAS Forward EWP APIs ([fd9245de25e64a3](https://github.com/ULisboa/forward-ewp-api-client/commit/fd9245de25e64a3108ab17a75a5b37a17f312c8e))

### Improvements
-  adapt existing API contracts to consider versions ([4cfa00ea175c42d](https://github.com/ULisboa/forward-ewp-api-client/commit/4cfa00ea175c42d3b96417a904b88807b323805d))


### Refactor
-  minor refactoring ([e8e3506213602ea](https://github.com/ULisboa/forward-ewp-api-client/commit/e8e3506213602eadc88f1a1eadea5eb84eff55f8))

## [0.2.3](https://github.com/ULisboa/forward-ewp-api-client/tree/0.2.3) (2020-11-18)



### Bug Fixes
-  fix a parameter name typo in CoursesApi ([40a0d8c7d8faca1](https://github.com/ULisboa/forward-ewp-api-client/commit/40a0d8c7d8faca18535ce71a4121e7f65c846408))


## [0.2.2](https://github.com/ULisboa/forward-ewp-api-client/tree/0.2.2) (2020-06-03)

### Features
-  add contract method to obtain list of all HEI IDs registered on EWP Registry; refactor: update all URIs that use path prefix /rest to /api ([933847a92734a60](https://github.com/ULisboa/forward-ewp-api-client/commit/933847a92734a600371238f250e63244294db8f8))




## [0.2.1](https://github.com/ULisboa/forward-ewp-api-client/tree/0.2.1) (2020-05-13)

### Features
-  add contract methods to obtain maximum number of LOS IDs/codes and Organizational Units IDs/codes accepted in a request to the Courses API and Organizational Units API, respectively ([1620deae230232b](https://github.com/ULisboa/forward-ewp-api-client/commit/1620deae230232b654a79874488488b33fcd97cc))




## [0.2.0](https://github.com/ULisboa/forward-ewp-api-client/tree/0.2.0) (2020-04-28)

### Features
-  add client for Courses Forward EWP API ([0d109071a56ff0e](https://github.com/ULisboa/forward-ewp-api-client/commit/0d109071a56ff0e5a8ef49aa12077c9f2abe5a04))


### Bug Fixes
-  avoid exception when resolving an error response without body ([2bcf05ab1441a1e](https://github.com/ULisboa/forward-ewp-api-client/commit/2bcf05ab1441a1e04895cb19658bb2e75e82283c))


## [v0.1.0](https://github.com/ULisboa/forward-ewp-api-client/tree/v0.1.0) (2020-04-27)

### Features
-  add client for Simple Course Replication Forward EWP API ([c2b5125944480b3](https://github.com/ULisboa/forward-ewp-api-client/commit/c2b5125944480b352321a97d805517ec9d38f1d6))
-  add client for Organizational Units Forward EWP API ([36104349ca4e63f](https://github.com/ULisboa/forward-ewp-api-client/commit/36104349ca4e63f2b88326e9387b76ddc76581f1))


### Bug Fixes
-  **readme**  better header markdown ([421003b945071b2](https://github.com/ULisboa/forward-ewp-api-client/commit/421003b945071b2282ae1f2e88f4fcb088ae844e))


