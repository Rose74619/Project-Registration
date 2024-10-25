package com.apiexamples.service;

import com.apiexamples.entity.Registration;
import com.apiexamples.payload.RegistrationDto;
import com.apiexamples.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    private RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        Registration registration=mapToEntity(registrationDto);
        Registration savedEntity=registrationRepository.save(registration);
        RegistrationDto dto=mapToDto(savedEntity);
        dto.setMessage("registration Created successfully");
        return dto;
    }
    Registration mapToEntity(RegistrationDto dto){
        Registration entity=new Registration();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        return entity;

    }
    RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto=new RegistrationDto();
        dto.setId(registration.getId());
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setPhone(registration.getPhone());
        return dto;

    }
}
