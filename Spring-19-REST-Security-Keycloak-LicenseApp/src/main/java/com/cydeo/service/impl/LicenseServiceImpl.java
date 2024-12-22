package com.cydeo.service.impl;

import com.cydeo.model.License;
import com.cydeo.model.Organization;
import com.cydeo.repository.LicenseRepository;
import com.cydeo.client.OrganizationFeignClient;
import com.cydeo.service.LicenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenseServiceImpl implements LicenseService {

    private final LicenseRepository licenseRepository;
    private final OrganizationFeignClient feignClient;

    public LicenseServiceImpl(LicenseRepository licenseRepository, OrganizationFeignClient feignClient) {
        this.licenseRepository = licenseRepository;
        this.feignClient = feignClient;
    }

    //this method fetches license from license repo and gives it to organization from OrganizationsApp module 19.
    public License getLicense(Long licenseId, Long organizationId) throws Exception {

        License license = licenseRepository.findById(licenseId).orElseThrow(() -> new Exception("License not found"));

        //description below
        Organization organization = retrieveOrganizationInfo(organizationId);

        if (organization != null) {
            license.setOrganizationName(organization.getName());
            license.setContactName(organization.getContactName());
            license.setContactEmail(organization.getContactEmail());
            license.setContactPhone(organization.getContactPhone());
        }
        return license;
    }

    //calls module 19 OrganizationsApp and gets data of organization that needs license
    //because localhost:8082/v1/organization endpoint belongs to module 19 OrganizationsApp
    //so 2 module 19s are connected to each other via this method.
    private Organization retrieveOrganizationInfo(Long organizationId) {
        return feignClient.getOrganization(organizationId);
    }

    public License createLicense(License license) {
        licenseRepository.save(license);
        return license;
    }

    public License updateLicense(License license) {
        licenseRepository.save(license);
        return license;
    }

    public String deleteLicense(Long licenseId) throws Exception {
        License license = licenseRepository.findById(licenseId).orElseThrow(() -> new Exception("License not found"));
        licenseRepository.delete(license);
        return "License deleted";
    }

    public List<License> getLicensesByOrganization(Long organizationId) {
        return licenseRepository.findByOrganizationId(organizationId);
    }

}
