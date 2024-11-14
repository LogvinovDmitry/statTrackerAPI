package logvinov.testTask.userRestApp.util;

import logvinov.testTask.userRestApp.dto.entitiesDTO.SalesAndTrafficByAsinDTO;
import logvinov.testTask.userRestApp.model.entities.SalesAndTrafficByAsin;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class SalesAndTrafficByAsinConverter {
    private ModelMapper modelMapper;
    @Autowired
    public SalesAndTrafficByAsinConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SalesAndTrafficByAsinDTO convertToSalesAndTrafficByAsinDTO(SalesAndTrafficByAsin salesAndTrafficByAsin) {
        return modelMapper.map(salesAndTrafficByAsin, SalesAndTrafficByAsinDTO.class);
    }
}