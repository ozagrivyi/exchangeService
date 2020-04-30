package co.ozdev.services;

import co.ozdev.dto.CommissionDto;
import co.ozdev.dto.mappers.CommissionMapper;
import co.ozdev.exception.ExchangePairNotFoundException;
import co.ozdev.persistance.entities.CommissionEntity;
import co.ozdev.persistance.entities.ExchangeRateEntity;
import co.ozdev.persistance.repositories.CommissionRepository;
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
    private ExchangeRateRepository exchangeRateRepository;
    
    @Autowired
    private CommissionMapper commissionMapper;

    public CommissionDto setCommission(CommissionDto commissionDto) {
        ExchangeRateEntity exchangePair = exchangeRateRepository.findOneByCurrencyFromAndCurrencyTo(commissionDto.getFrom(), commissionDto.getTo());
        if (exchangePair != null) {
            CommissionEntity commissionEntity = commissionRepository.findOneByExchangeRate(exchangePair);
            if (commissionEntity != null) {
                commissionEntity.setCommissionPt(commissionDto.getCommissionPt());
            } else {
                commissionEntity = commissionMapper.convertToEntity(commissionDto, exchangePair);
            }
            commissionRepository.save(commissionEntity);
            return commissionDto;
        } 
        throw new ExchangePairNotFoundException(commissionDto.getFrom(), commissionDto.getTo());
    }

    public List<CommissionDto> getCommissions() {
        List<CommissionEntity> commissionEntities = commissionRepository.findAll();
        List<CommissionDto> result = commissionEntities.stream().map(entity -> commissionMapper.convertToDto(entity)).collect(Collectors.toList());
        return result;
    }
}
