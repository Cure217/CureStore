package com.aliano.util;

import com.aliano.vo.ResultVO;

/**
 * @Author Cure
 * @Time 2022/5/21 20:16
 */
public class ResultVOUtil {
    public static ResultVO success(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO fail(String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(-1);
        resultVO.setMsg(msg);
        return resultVO;
    }


    public static ResultVO failMsg(String error){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(-1);
        resultVO.setMsg("失败");
        resultVO.setData(error);
        return resultVO;
    }
}
