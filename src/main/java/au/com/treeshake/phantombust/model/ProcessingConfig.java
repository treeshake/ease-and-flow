package au.com.treeshake.phantombust.model;

import au.com.treeshake.phantombust.service.csv.CsvProcessor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.JpaRepository;

@AllArgsConstructor
@Getter
public class ProcessingConfig<D,E> {
    private final JpaRepository<E, Long> repository;
    private final Converter<D,E> converter;
    private final CsvProcessor<D> csvProcessor;
}
