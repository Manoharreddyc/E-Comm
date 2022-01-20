package com.mrc.FundTransfer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errorDetails;
    private String errorMoreInfo;
}
