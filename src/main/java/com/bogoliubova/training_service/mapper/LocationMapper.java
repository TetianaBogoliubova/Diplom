package com.bogoliubova.training_service.mapper;

import com.bogoliubova.training_service.dto.LocationDto;
import com.bogoliubova.training_service.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface LocationMapper {

   // @Mapping(source = "teacher.firstName", target = "firstName")
   // @Mapping(source = "teacher.lastName", target = "lastName")
   // @Mapping(source = "teacher.teacherEmail", target = "teacherEmail")
    LocationDto toDto(Location location);
}
