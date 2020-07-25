package com.acggov.telegram.utils;


import com.acggov.telegram.constant.AppConstant;
import com.acggov.telegram.dto.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created: Nekoer
 * Desc:
 * Date: 2020/5/12 18:12
 */
@Component
public class HttpUtils {


    public HttpUtils() {
    }

    public Result setBuild(HttpServletResponse res, Result result) {

        if (AppConstant.HTTP_CODE_SUCCESS.contains(result.getCode())) {
            return buildSuccess(res, result.getCode(), result.getData(),String.valueOf(result.getMsg()));
        } else {
            return buildFailure(res, result.getCode(), String.valueOf(result.getError()),String.valueOf(result.getMsg()));
        }
    }

    public Result buildSuccess(HttpServletResponse res, Integer code, Object data, String msg) {

        Result result = new Result();
        try{
            res.setStatus(code);
        }catch (Exception e){
            e.printStackTrace();
        }

        result.setMsg(msg);
        result.setCode(code);
        result.setData(data);
        return result;
    }

    public Result buildFailure(HttpServletResponse res, Integer code, String errMsg , String msg) {
        Result result = new Result();
        try{
            res.setStatus(code);
        }catch (Exception e){
            e.printStackTrace();
        }
        result.setCode(code);
        result.setMsg(msg);
        result.setError(errMsg);
        return result;
    }
}
