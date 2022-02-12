package com.pavelchuk.bonus.mapper;


import com.pavelchuk.bonus.dto.CreateBonusCardDto;
import com.pavelchuk.bonus.entity.BonusCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BonusCardMapper  {

   @Mapping(target ="client.id", source = "clientId")
    BonusCard mapCreateBonusCardDtoToBonusCard(CreateBonusCardDto dto);


}
