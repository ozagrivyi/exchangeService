package co.ozdev.integration;

import co.ozdev.dto.ExchangeDto;
import co.ozdev.dto.mappers.ExchangeRateMapper;
import co.ozdev.exception.ExchangePairNotFoundException;
import co.ozdev.model.Currency;
import co.ozdev.model.OperationType;
import co.ozdev.persistance.entities.CommissionEntity;
import co.ozdev.persistance.entities.ExchangePairEntity;
import co.ozdev.persistance.entities.ExchangeRateEntity;
import co.ozdev.persistance.repositories.CommissionRepository;
import co.ozdev.persistance.repositories.ExchangePairRepository;
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

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ExchangeService.class, CommissionRepository.class, ExchangeRateRepository.class, ExchangeRateMapper.class})
public class ExchageTests {

    @MockBean
    ExchangePairRepository exchangePairRepository;

    @MockBean
    ExchangeRateRepository exchangeRateRepository;

    @MockBean
    CommissionRepository commissionRepository;

    @Autowired
    ExchangeService exchangeService;

    @Before
    public void initializeMocks() {
        ExchangePairEntity exchangePairEntity = new ExchangePairEntity();
        exchangePairEntity.setCurrencyFrom(Currency.USD);
        exchangePairEntity.setCurrencyTo(Currency.UAH);
        ExchangeRateEntity exchangeRateEntity = new ExchangeRateEntity();
        exchangeRateEntity.setRate(BigDecimal.valueOf(27));
        exchangeRateEntity.setExchangePair(exchangePairEntity);
        exchangePairEntity.setExchangeRate(exchangeRateEntity);

        CommissionEntity commissionEntity = new CommissionEntity();
        commissionEntity.setCommissionPt(BigDecimal.valueOf(0.2));
        commissionEntity.setExchangePair(exchangePairEntity);
        exchangePairEntity.setCommission(commissionEntity);

        commissionEntity.setExchangePair(exchangePairEntity);
        exchangePairEntity.setCommission(commissionEntity);

        Mockito.when(exchangePairRepository.findOneByCurrencyFromAndCurrencyTo(Currency.USD, Currency.UAH)).thenReturn(exchangePairEntity);
    }

    @Test
    public void exchangeGiveTest() {
        BigDecimal amountTo = BigDecimal.valueOf(100L);
        ExchangeDto exchangeDto = new ExchangeDto();
        exchangeDto.setFrom(Currency.USD);
        exchangeDto.setTo(Currency.UAH);
        exchangeDto.setAmountFrom(BigDecimal.valueOf(1000L));
        exchangeDto.setAmountTo(amountTo);
        exchangeDto.setOperationType(OperationType.GIVE);

        ExchangeDto result = exchangeService.exchange(exchangeDto);

        Assert.assertEquals(exchangeDto.getOperationType(), result.getOperationType());
        Assert.assertNotEquals(amountTo, result.getAmountTo());
        Assert.assertEquals(exchangeDto.getAmountFrom(), result.getAmountFrom());
        Assert.assertEquals(BigDecimal.valueOf(26946.0d), result.getAmountTo());
    }

    @Test
    public void exchangeGetTest() {
        BigDecimal amountFrom = BigDecimal.valueOf(100L);
        ExchangeDto exchangeDto = new ExchangeDto();
        exchangeDto.setFrom(Currency.USD);
        exchangeDto.setTo(Currency.UAH);
        exchangeDto.setAmountFrom(amountFrom);
        exchangeDto.setAmountTo(BigDecimal.valueOf(1100L));
        exchangeDto.setOperationType(OperationType.GET);

        ExchangeDto result = exchangeService.exchange(exchangeDto);

        Assert.assertEquals(exchangeDto.getOperationType(), result.getOperationType());
        Assert.assertNotEquals(amountFrom, result.getAmountFrom());
        Assert.assertEquals(exchangeDto.getAmountTo(), result.getAmountTo());
        Assert.assertEquals(BigDecimal.valueOf(40.82239d), result.getAmountFrom());
    }

    @Test(expected = ExchangePairNotFoundException.class)
    public void exceptionWhenExchangePairIsMissed() {
        ExchangeDto exchangeDto = new ExchangeDto();
        exchangeDto.setFrom(Currency.EUR);
        exchangeDto.setTo(Currency.USD);
        exchangeDto.setAmountFrom(BigDecimal.valueOf(1000L));
        exchangeDto.setAmountTo(BigDecimal.valueOf(110L));
        exchangeDto.setOperationType(OperationType.GIVE);

        ExchangeDto result = exchangeService.exchange(exchangeDto);
    }
}
