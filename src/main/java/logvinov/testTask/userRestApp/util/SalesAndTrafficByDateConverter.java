package logvinov.testTask.userRestApp.util;

import logvinov.testTask.userRestApp.dto.entitiesDTO.SalesAndTrafficByDateDTO;
import logvinov.testTask.userRestApp.model.entities.SalesAndTrafficByDate;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class SalesAndTrafficByDateConverter {
    private ModelMapper modelMapper;
    @Autowired
    public SalesAndTrafficByDateConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SalesAndTrafficByDateDTO convertToSalesAndTrafficByDateDTO(SalesAndTrafficByDate salesAndTrafficByDate) {
        return modelMapper.map(salesAndTrafficByDate, SalesAndTrafficByDateDTO.class);
    }
}