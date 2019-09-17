package com.echo.crm.dto;

import lombok.Data;

/**
 * @author yucheng
 * @description Contoller层返回结果包装类
 * @create 2019-07-24 14:05
 */

@Data
public class ResultDTO<T> {

    public static enum Result {
        RESULT_SUCCESS(Integer.valueOf(1)),
        RESULT_FAILURE(Integer.valueOf(0));

        private Integer value;

        Result(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    /**
     * 结果状态
     */
    private Integer success;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 异常消息
     */
    private String errorMsg;

    public ResultDTO(T data) {
        this.success = Result.RESULT_SUCCESS.getValue();
        this.data = data;
    }

    public ResultDTO(Result result) {
        this.success = result.getValue();
    }

    public ResultDTO(Result result, String errorMsg) {
        this.success = result.getValue();
        this.errorMsg = errorMsg;
    }

    public ResultDTO(String errorMsg) {
        this.success = Result.RESULT_FAILURE.getValue();
        this.errorMsg = errorMsg;
    }

    public ResultDTO(Exception e) {
        this.success = Result.RESULT_FAILURE.getValue();
        this.errorMsg = e.getMessage();
    }
}
