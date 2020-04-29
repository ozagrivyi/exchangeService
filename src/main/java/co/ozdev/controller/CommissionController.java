package co.ozdev.controller;

import co.ozdev.dto.CommissionDto;
import co.ozdev.services.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommissionController {

    @Autowired
    private CommissionService commissionService;

    @RequestMapping(value = "/commissions", method = RequestMethod.POST)
    public CommissionDto setCommission(@Valid @RequestBody CommissionDto commissionDto) {
        CommissionDto result = commissionService.setCommission(commissionDto);
        return result;
    }

    @RequestMapping(value = "/commissions", method = RequestMethod.GET)
    public List<CommissionDto> getCommissions() {
        List<CommissionDto> result = commissionService.getCommissions();
        return result;
    }
}
