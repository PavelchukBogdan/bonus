package com.pavelchuk.bonus.mapper;


import com.pavelchuk.bonus.dto.BonusDto;
import com.pavelchuk.bonus.dto.CreateBonusDto;
import com.pavelchuk.bonus.entity.Bonus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BonusMapper  {


    @Mapping(target ="bonusCard.id", source = "dto.bonusCardId")
    Bonus mapCreateBonusDtoToBonus(CreateBonusDto dto);

    BonusDto mapBonusDtoToBonus(Bonus bonus);

}
