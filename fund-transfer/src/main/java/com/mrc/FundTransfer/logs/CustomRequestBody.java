package com.mrc.FundTransfer.logs;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

@ControllerAdvice
public class CustomRequestBody extends RequestBody {

    @Autowired
    HttpServletRequest httpSevletRequest;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        HttpLoggingHelper httpLoggingHelper = new HttpLoggingHelper();
        try {
            httpLoggingHelper.logRequest(httpSevletRequest, body);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

}