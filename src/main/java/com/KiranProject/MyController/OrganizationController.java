package com.KiranProject.MyController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KiranProject.EntityArea.Employee;
import com.KiranProject.EntityArea.Organization;
import com.KiranProject.EntityArea.Staff;
import com.KiranProject.Repo.OrgRepo;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/org")
public class OrganizationController {
	
	@Autowired
	OrgRepo orgRepo;
	
	@GetMapping("/show")
	public List<Organization> showme()
	{
		List<Organization> findAll = orgRepo.findAll();
		
		
		return findAll;
		
	}
	
	@GetMapping("/show/{id}")
	public Optional<Organization> showme(@PathVariable long id)	{
	
		Optional<Organization> findById = orgRepo.findById(id);		
		return findById;
		
	}
	
	// done in one shot
	@Transactional
	@PostMapping("/insert")
	public Organization insert(@RequestBody Organization organization) {
		
		Optional<Organization> findById = orgRepo.findById(organization.getId());
		if(findById.isPresent()) {
			
			for(Staff staff :organization.getStaffList()) {				
				staff.setOrganization(organization);
				
			}	
			
			orgRepo.save(organization);					
		}
		
		else {
			for(Staff staff :organization.getStaffList()) {				
				staff.setOrganization(organization);
				for(Employee employee:staff.getEmployees()) {
					
					employee.setStaff(staff);
				}
				
				
			}	
			orgRepo.save(organization);	
		}
		
		return organization;
		
	}
	
	

}
