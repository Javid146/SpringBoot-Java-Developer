package com.cydeo.controller;

import com.cydeo.model.Organization;
import com.cydeo.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

/*
KEYCLOAK IS AN SSO CONCEPT (SINGLE SIGN-ON)
todo Keycloak needs to be set up and run on 8080. It is all documented in OneNote. module 18 and 19 is like organization wants to get license via keycloak
 In CMD you need to run:
 CMD: cd C:\Users\Dell\Downloads\Developer Files\Spring Rest\keycloak-22.0.1\keycloak-22.0.1\bin
 CMD: "C:\Users\Dell\Downloads\DEVELO~2\SPRING~1\keycloak-22.0.1\keycloak-22.0.1\bin\kc.bat" start-dev    Make sure port 8080 is not in use
 Go to Keycloak UI: localhost:8080

TODO FOLLOW ONENOTE KEYCLOAK SECTION TO SET UP ACCESS TOKEN FROM POSTMAN.

 1. add @RolesAllowed({"ADMIN","USER"}) on top of method to be accessible to certain roles
 2. In Keycloak go to: Realm settings>OpenID Endpoint Configuration. Click link and copy URL from browser.
 Add in Postman as GET call. Response body will give token_endpoint. Copy that URL (http://localhost:8080/realms/cydeo-dev/protocol/openid-connect/token).
 Make POST call with it and you will get todo bearer type access_token and refresh token.
  Before that add client credentials:
 a. Authorization: Basic Auth
 Username: training (add clientId fom Keycloak)
 Password: 1nxv4Y2AbtI9QstPyYff476O8rfVB0rX (Secret key)
 b. Body>x-www-form-urlencoded (add client user credentials):
 grant_type: password
 username: javid
 password: Turkic14
 With given access_token we connect Spring project to Keycloak and get authorization/authentication through it.

 In order Keycloak to work on our project we add its dependency.
 todo add SecurityConfig class in Intellij dir 19 OrganizationApps module: config. This class is for access control for users.
  Modules spring-19-Keycloak-LicenseApp and Spring-19-Keycloak-OrganizationsApp should be run together.
     1st is running on port 8081, 2nd on 8082. Organization get license from License place.

 todo We create organization with postman (endpoint from OrganizationController class):
 Postman and POST localhost:8082/v1/organization. Before posting:
 Add organization details under body on Postman from model>Organization class:
    {"name":"ABC Limited",
    "contactName":"tom",
    "contactEmail":"tom@gmail.com",
    "contactPhone":"31232132432"}
  In Authorization section we select OAuth2:
  Grant Type: Password Credentials
  Access URL: URL from keycloak (localhost:8080) > Realm Settings > "OpenID Endpoint Configuration" link > copy "token_endpoint" from text
  Client ID: training (name of client in keycloak)
  Client secret: 1nxv4Y2AbtI9QstPyYff476O8rfVB0rX (keycloak: Clients>training>Credentials>Client Secret)
  Username: javid
  Password: Turkic14
  Get New Access Token btn > Use Token. Done. You will be able to get access with post call now

 todo Now we create license with POST localhost:8081/v1/organization/{organizationId}/license (endpoint from LicenseController class)
organizationId comes from response of POST call above (localhost:8082/v1/organization)
And on body add details from model>License class:
{"description":"Office License",
"organizationId":"{organizationId from response of localhost:8082/v1/organization}",
"productName":"Office 365",
"licenseType":"software"}
this call will create license.

You can verify that license created for required organization:
GET localhost:8081/v1/organization/license/{licenseId} . licenseId comes from response of localhost:8081/v1/organization/{organizationId}/license
*/

@RestController
@RequestMapping(value = "/v1/organization") //localhost:8082/v1/organization
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

//    @RolesAllowed({"ADMIN","USER"}) //only these two roles have access to this method. roles need to match with ones on keycloak at localhost:8080
    @GetMapping("/{organizationId}")
    public ResponseEntity<Organization> getOrganization(@PathVariable("organizationId") Long organizationId) throws Exception {
        return ResponseEntity.ok(organizationService.findById(organizationId));
    }

    @RolesAllowed({"ADMIN","USER"})
    @PostMapping
    public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.create(organization));
    }

    @RolesAllowed({"ADMIN"}) //only ADMIN role can delete. roles need to match with ones on keycloak at localhost:8080
    //if you add cucu instead on javid in Postman authorization to DELETE license, you will get 401 responseCode
    @DeleteMapping("/{organizationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLicense(@PathVariable("organizationId") Long organizationId) {
        organizationService.delete(organizationId);
    }

}
