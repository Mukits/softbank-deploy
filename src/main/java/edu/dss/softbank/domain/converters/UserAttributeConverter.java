package edu.dss.softbank.domain.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import edu.dss.softbank.domain.User;

@Converter
public class UserAttributeConverter implements AttributeConverter<User, String> { // <2>

    @Override
    //@Convert("null -> null")
    public String convertToDatabaseColumn(User attribute) {
        return attribute == null ? null : attribute.toString(); // <3>
    }

    @Override
    //@Convert("null -> null")
    public User convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new User(dbData); // <4>
    }

}
