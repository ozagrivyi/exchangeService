package co.ozdev.services;

import co.ozdev.dto.CommissionDto;
import co.ozdev.dto.mappers.CommissionMapper;
import co.ozdev.exception.ExchangePairNotFoundException;
import co.ozdev.persistance.entities.CommissionEntity;
import co.ozdev.persistance.entities.ExchangePairEntity;
import co.ozdev.persistance.entities.ExchangeRateEntity;
import co.ozdev.persistance.repositories.CommissionRepository;
import co.ozdev.persistance.repositories.ExchangePairRepository;
import co.ozdev.persistance.repositories.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommissionService {
    
    @Autowired
    private CommissionRepository commissionRepository;
    
    @Autowired 
    private ExchangePairRepository exchangePairRepository;
    
    @Autowired
    private CommissionMapper commissionMapper;

    public CommissionDto setCommission(CommissionDto commissionDto) {
        ExchangePairEntity exchangePairEntity = exchangePairRepository
                .findOneByCurrencyFromAndCurrencyTo(commissionDto.getFrom(), commissionDto.getTo());
        if (exchangePairEntity == null) {
            exchangePairEntity = new ExchangePairEntity();
            exchangePairEntity.setCurrencyFrom(commissionDto.getFrom());
            exchangePairEntity.setCurrencyTo(commissionDto.getTo());

        }
        CommissionEntity commissionEntity = exchangePairEntity.getCommission();
        if (commissionEntity == null) {
            commissionEntity = commissionMapper.convertToEntity(commissionDto, exchangePairEntity);
        } else {
            commissionEntity.setCommissionPt(commissionDto.getCommissionPt());
        }
        exchangePairEntity.setCommission(commissionEntity);

        commissionRepository.save(commissionEntity);
        exchangePairRepository.save(exchangePairEntity);
        return commissionDto;
    }

    public List<CommissionDto> getCommissions() {
        List<CommissionEntity> commissionEntities = commissionRepository.findAll();
        List<CommissionDto> result = commissionEntities.stream().map(entity -> commissionMapper.convertToDto(entity)).collect(Collectors.toList());
        return result;
    }
}
