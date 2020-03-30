package com.xxx.web.advice;

import com.mickey.core.advice.AbstractExceptionAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Jack
 * @Description: 全局异常类
 * @date 2018/1/30 19:16
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice extends AbstractExceptionAdvice {
}
