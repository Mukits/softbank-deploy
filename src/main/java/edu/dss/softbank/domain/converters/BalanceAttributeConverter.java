package edu.dss.softbank.domain.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import edu.dss.softbank.domain.vo.Balance;

@Converter
public class BalanceAttributeConverter implements AttributeConverter<Balance, String> { // <2>

    @Override
    //@Convert("null -> null")
    public String convertToDatabaseColumn(Balance attribute) {
        return attribute == null ? null : attribute.toString(); // <3>
    }

    @Override
    //@Convert("null -> null")
    public Balance convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new Balance(dbData); // <4>
    }

}
