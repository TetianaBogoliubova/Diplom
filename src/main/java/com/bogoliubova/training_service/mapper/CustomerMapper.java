package com.bogoliubova.training_service.mapper;

import com.bogoliubova.training_service.dto.CustomerDto;
import com.bogoliubova.training_service.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CustomerMapper {

    @Mapping(source = "customer.location", target = "location")
    @Mapping(source = "customer.directions", target = "directions")
    CustomerDto toDto(Customer customer);
}
