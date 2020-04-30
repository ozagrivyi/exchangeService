package co.ozdev.integration;

import co.ozdev.dto.ExchangeDto;
import co.ozdev.dto.mappers.ExchangeRateMapper;
import co.ozdev.exception.ExchangePairNotFoundException;
import co.ozdev.model.Currency;
import co.ozdev.model.OperationType;
import co.ozdev.persistance.entities.CommissionEntity;
import co.ozdev.persistance.entities.ExchangeRateEntity;
import co.ozdev.persistance.repositories.CommissionRepository;
import co.ozdev.persistance.repositories.ExchangeRateRepository;
import co.ozdev.services.ExchangeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ExchangeService.class, CommissionRepository.class, ExchangeRateRepository.class, ExchangeRateMapper.class})
public class ExchageTests {
    @MockBean
    ExchangeRateRepository exchangeRateRepository;

    @MockBean
    CommissionRepository commissionRepository;

    @Autowired
    ExchangeService exchangeService;

    @Before
    public void initializeMocks() {
        ExchangeRateEntity exchangeRateEntity = new ExchangeRateEntity();
        exchangeRateEntity.setCurrencyFrom(Currency.USD);
        exchangeRateEntity.setCurrencyTo(Currency.UAH);
        exchangeRateEntity.setRate(BigDecimal.valueOf(26.01d));

        CommissionEntity commissionEntity = new CommissionEntity();
        commissionEntity.setCommissionPt(BigDecimal.valueOf(0.2d));
        commissionEntity.setExchangeRate(exchangeRateEntity);
        exchangeRateEntity.setCommission(commissionEntity);

        commissionEntity.setExchangeRate(exchangeRateEntity);
        exchangeRateEntity.setCommission(commissionEntity);

        Mockito.when(commissionRepository.findOneByExchangeRate(exchangeRateEntity)).thenReturn(commissionEntity);
        Mockito.when(exchangeRateRepository.findOneByCurrencyFromAndCurrencyTo(Currency.USD, Currency.UAH)).thenReturn(exchangeRateEntity);

        exchangeRateEntity = new ExchangeRateEntity();
        exchangeRateEntity.setCurrencyFrom(Currency.UAH);
        exchangeRateEntity.setCurrencyTo(Currency.USD);
        exchangeRateEntity.setRate(BigDecimal.valueOf(0.04d));

        commissionEntity = new CommissionEntity();
        commissionEntity.setCommissionPt(BigDecimal.valueOf(0.3d));
        commissionEntity.setExchangeRate(exchangeRateEntity);
        exchangeRateEntity.setCommission(commissionEntity);

        commissionEntity.setExchangeRate(exchangeRateEntity);
        exchangeRateEntity.setCommission(commissionEntity);

        Mockito.when(commissionRepository.findOneByExchangeRate(exchangeRateEntity)).thenReturn(commissionEntity);
        Mockito.when(exchangeRateRepository.findOneByCurrencyFromAndCurrencyTo(Currency.UAH, Currency.USD)).thenReturn(exchangeRateEntity);

        exchangeRateEntity = new ExchangeRateEntity();
        exchangeRateEntity.setCurrencyFrom(Currency.EUR);
        exchangeRateEntity.setCurrencyTo(Currency.UAH);
        exchangeRateEntity.setRate(BigDecimal.valueOf(31.2));

        commissionEntity = new CommissionEntity();
        commissionEntity.setCommissionPt(BigDecimal.valueOf(0.3d));
        commissionEntity.setExchangeRate(exchangeRateEntity);
        exchangeRateEntity.setCommission(commissionEntity);

        commissionEntity.setExchangeRate(exchangeRateEntity);
        exchangeRateEntity.setCommission(commissionEntity);

        Mockito.when(commissionRepository.findOneByExchangeRate(exchangeRateEntity)).thenReturn(commissionEntity);
        Mockito.when(exchangeRateRepository.findOneByCurrencyFromAndCurrencyTo(Currency.UAH, Currency.USD)).thenReturn(exchangeRateEntity);


    }

    @Test
    public void exchageGetTest() {
        ExchangeDto exchangeDto = new ExchangeDto();
        exchangeDto.setFrom(Currency.USD);
        exchangeDto.setTo(Currency.UAH);
        exchangeDto.setAmountFrom(BigDecimal.valueOf(1000L));
        exchangeDto.setAmountTo(BigDecimal.valueOf(100L));
        exchangeDto.setOperationType(OperationType.GET);

        ExchangeDto result = exchangeService.exchange(exchangeDto);

        Assert.assertEquals(exchangeDto.getOperationType(), result.getOperationType());
        Assert.assertEquals(exchangeDto.getAmountTo(), result.getAmountTo());
        Assert.assertEquals(exchangeDto.getAmountFrom(), result.getAmountFrom());
        Assert.assertEquals(BigDecimal.valueOf(38.37d), result.getAmountTo());
    }

    @Test
    public void exchageGiveTest() {
        ExchangeDto exchangeDto = new ExchangeDto();
        exchangeDto.setFrom(Currency.UAH);
        exchangeDto.setTo(Currency.USD);
        exchangeDto.setAmountFrom(BigDecimal.valueOf(1000L));
        exchangeDto.setAmountTo(BigDecimal.valueOf(110L));
        exchangeDto.setOperationType(OperationType.GIVE);

        ExchangeDto result = exchangeService.exchange(exchangeDto);

        Assert.assertEquals(exchangeDto.getOperationType(), result.getOperationType());
        Assert.assertEquals(exchangeDto.getAmountTo(), result.getAmountTo());
        Assert.assertEquals(exchangeDto.getAmountFrom(), result.getAmountFrom());
        Assert.assertEquals(BigDecimal.valueOf(4.22d), result.getAmountFrom());
    }

    @Test(expected = ExchangePairNotFoundException.class)
    public void exceptionWhenGiveExchangePairIsMissed() {
        ExchangeDto exchangeDto = new ExchangeDto();
        exchangeDto.setFrom(Currency.EUR);
        exchangeDto.setTo(Currency.UAH);
        exchangeDto.setAmountFrom(BigDecimal.valueOf(1000L));
        exchangeDto.setAmountTo(BigDecimal.valueOf(110L));
        exchangeDto.setOperationType(OperationType.GIVE);

        ExchangeDto result = exchangeService.exchange(exchangeDto);
    }
}
