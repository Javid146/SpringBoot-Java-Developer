package com.cydeo.controller;

import com.cydeo.model.License;
import com.cydeo.service.LicenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/*
todo  Module spring-19-Keycloak-LicenseApp and Spring-19-Keycloak-OrganizationsApp should be run together. 1st is running on port 8081, 2nd on 8082.
 And Also Keycloak needs to run on 8080. It is all documented in OneNote. module 18 and 19 is like organization wants to get license via keycloak
 In CMD you need to run:
 CMD: cd C:\Users\Dell\Downloads\Developer Files\Spring Rest\keycloak-22.0.1\keycloak-22.0.1\bin
 CMD: "C:\Users\Dell\Downloads\DEVELO~2\SPRING~1\keycloak-22.0.1\keycloak-22.0.1\bin\kc.bat" start-dev    Make sure port 8080 is not in use
 Go to Keycloak UI: localhost:8080
 We need to go to postman and POST localhost:8082/v1/organization to create organization.
 And on body add details from model>Organization class:
    {"name":"ABC Limited",
    "contactName":"tom",
    "contactEmail":"tom@gmail.com",
    "contactPhone":"31232132432"}

Response will have:
{
    "id": 1,  take this id to use below
    "name": "ABC Limited",
    "contactName": "tom",
    "contactEmail": "tom@gmail.com",
    "contactPhone": "31232132432"
}
    after that we POST localhost:8081/v1/organization/1/license
     And on body add details from model>License class:
    {"description":"Office License",
    "organizationId":"1",
    "productName":"Office 365",
    "licenseType":"software"}
    this call will create license for required organization.

   todo  we also add config>SecurityConfig class
*/

@RestController
@RequestMapping(value = "/v1/organization/{organizationId}/license")
public class LicenseController {

    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable("organizationId") Long organizationId, @PathVariable("licenseId") Long licenseId) throws Exception {
        License license = licenseService.getLicense(licenseId, organizationId);
        return ResponseEntity.ok(license);
    }

    @RolesAllowed({"ADMIN","USER"}) //only these two roles have access to this method. roles need to match with ones on keycloak at localhost:8080
    @PutMapping
    public ResponseEntity<License> updateLicense(@RequestBody License request) {
        return ResponseEntity.ok(licenseService.updateLicense(request));
    }

    @PostMapping
    public ResponseEntity<License> createLicense(@RequestBody License request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(licenseService.createLicense(request));
    }

    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable("licenseId") Long licenseId, @PathVariable String organizationId) throws Exception {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId));
    }

    @GetMapping
    public List<License> getLicenses(@PathVariable("organizationId") Long organizationId) {
        return licenseService.getLicensesByOrganization(organizationId);
    }

}
