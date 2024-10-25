package com.apiexamples.service;

import com.apiexamples.entity.Registration;
import com.apiexamples.exception.ResourceNotFound;
import com.apiexamples.payload.RegistrationDto;
import com.apiexamples.repository.RegistrationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    @Override
    public RegistrationDto updateRegistration(RegistrationDto registrationDto, long id) {
        Optional<Registration> byId = registrationRepository.findById(id);
        Registration registration=byId.get();
        registration.setName(registrationDto.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setPhone(registrationDto.getPhone());
        Registration savedEntity=registrationRepository.save(registration);
        RegistrationDto dto=mapToDto(registration);
        dto.setMessage("Registration Updated Successfully");
        return dto;
    }

    @Override
    public List<RegistrationDto> getRegistration(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable=PageRequest.of(pageNo, pageSize, sort);
        Page<Registration> all = registrationRepository.findAll(pageable);
        List<Registration> registrations = all.getContent();
        List<RegistrationDto> registrationDtos=registrations.stream().map(registration->mapToDto(registration)).collect(Collectors.toList());
        return registrationDtos;
    }

    @Override
    public RegistrationDto getRegistrationById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(()->new ResourceNotFound("Registration not found for id " +id));
        RegistrationDto dto=mapToDto(registration);
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
