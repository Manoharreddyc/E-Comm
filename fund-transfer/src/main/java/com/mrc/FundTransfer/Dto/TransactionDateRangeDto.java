package com.mrc.FundTransfer.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDateRangeDto {

    private long accountNumber;
    @Schema(name = "fromDate", type = "LocalDateTime", format = "LocalDateTime", description = "The from-Date value", required = true, example = "2022-01-03 11:38:56.347221")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fromDate;

    @Schema(name = "toDate", type = "LocalDateTime", format = "LocalDateTime", description = "The to-Date value", required = true, example = "2022-01-03 11:38:56.347221")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime toDate;
}
