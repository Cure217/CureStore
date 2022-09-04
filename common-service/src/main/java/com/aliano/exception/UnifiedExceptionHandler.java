package com.aliano.exception;

import com.aliano.util.ResultVOUtil;
import com.aliano.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.ResultSet;

/**
 * @Author Cure
 * @Time 2022/5/19 20:15
 */
@RestControllerAdvice
@Slf4j
public class UnifiedExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultVO handlerException(Exception e){
        log.info("服务器内部异常，{}", e.getMessage());
        //ResultVO resultVO = new ResultVO();
        //resultVO.setCode(-1);
        //resultVO.setData(e.getMessage());
        //resultVO.setMsg("失败");
        return ResultVOUtil.fail(e.getMessage());
    }
}
