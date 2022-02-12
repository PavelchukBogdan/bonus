package com.pavelchuk.bonus.mapper;

import com.pavelchuk.bonus.dto.CreateClientDto;
import com.pavelchuk.bonus.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper  {


    Client mapCreateClientDtoToClient(CreateClientDto dto);


}
