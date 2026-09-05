package Product;

import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, java.sql.Date> {
    @Override
    public java.sql.Date convertToDatabaseColumn(LocalDate locDate) {
        if (locDate == null) {
            return null;
        }
        return java.sql.Date.valueOf(locDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(java.sql.Date sqlDate) {
        if (sqlDate == null) {
            return null;
        }
        return sqlDate.toLocalDate();
    }
}
