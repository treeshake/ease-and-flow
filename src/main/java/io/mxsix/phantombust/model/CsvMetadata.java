package io.mxsix.phantombust.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Model class.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CsvMetadata {
    private String query;
    private String error;
}
