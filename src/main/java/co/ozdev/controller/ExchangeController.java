package co.ozdev.controller;

import co.ozdev.dto.ExchangeDto;
import co.ozdev.dto.ExchangeRateDto;
import co.ozdev.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @RequestMapping(value = "/exchange-rates", method = RequestMethod.GET)
    public List<ExchangeRateDto> getExchangeRates() {
        List<ExchangeRateDto> result = exchangeService.getExchangeRates();
        return result;
    }

    @RequestMapping(value = "/exchange-rates", method = RequestMethod.POST)
    public ExchangeRateDto setExchangeRate(@Valid @RequestBody ExchangeRateDto exchangeRateDto) {
        ExchangeRateDto result = exchangeService.setExchangeRate(exchangeRateDto);
        return result;
    }

    @RequestMapping(value = "/exchange", method = RequestMethod.POST)
    public ExchangeDto exchange(@Valid @RequestBody ExchangeDto exchangeDto) {
        ExchangeDto result = exchangeService.exchange(exchangeDto);
        return result;
    }



}
