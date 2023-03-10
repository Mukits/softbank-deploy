package edu.dss.softbank.domain.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import edu.dss.softbank.domain.vo.Password;

@Converter
public class PasswordAttributeConverter implements AttributeConverter<Password, String> { // <2>
    @Override
    public String convertToDatabaseColumn(Password attribute) {
        return attribute == null ? null : attribute.toString(); // <3>
    }

    @Override
    public Password convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new Password(dbData); // <4>
    }

}

