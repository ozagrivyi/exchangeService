package co.ozdev.validation;

import co.ozdev.model.Currency;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyValidator implements ConstraintValidator<CurrencyConstraint, Currency> {

    @Override
    public void initialize(CurrencyConstraint currencyConstraint) {
    }

    @Override
    public boolean isValid(Currency value, ConstraintValidatorContext context) {
        return value != null && !value.name().isEmpty() && !value.name().toLowerCase().equals("null");
    }
}
