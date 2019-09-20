package com.echo.crm.utils;

import lombok.Data;

/**
 * @author yucheng
 * @description Contoller层返回结果包装类
 * @create 2019-07-24 14:05
 */

@Data
public class ResultInfo<T> {

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

    public ResultInfo(T data) {
        this.success = Result.RESULT_SUCCESS.getValue();
        this.data = data;
    }

    public ResultInfo(Result result) {
        this.success = result.getValue();
    }

    public ResultInfo(Result result, String errorMsg) {
        this.success = result.getValue();
        this.errorMsg = errorMsg;
    }

    public ResultInfo(String errorMsg) {
        this.success = Result.RESULT_FAILURE.getValue();
        this.errorMsg = errorMsg;
    }

    public ResultInfo(Exception e) {
        this.success = Result.RESULT_FAILURE.getValue();
        this.errorMsg = e.getMessage();
    }

    /**
     * 生成Result对象
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> createResult(T obj) {
        return new ResultInfo<>(obj);
    }

    /**
     * 生成没有结果的ResultDTO对象
     * @return
     */
    public static ResultInfo<Object> createEmptyResult() {
        return new ResultInfo<Object>(Result.RESULT_SUCCESS);
    }
}
