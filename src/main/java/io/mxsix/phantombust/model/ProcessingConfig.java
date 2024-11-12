package io.mxsix.phantombust.model;

import io.mxsix.phantombust.service.csv.CsvProcessor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Record class.
 */
public record ProcessingConfig<D, E>(JpaRepository<E, Long> repository, Converter<D, E> converter,
                                     CsvProcessor<D> csvProcessor) {
}
