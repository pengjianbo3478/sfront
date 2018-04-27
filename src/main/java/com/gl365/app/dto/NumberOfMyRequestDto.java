package com.gl365.app.dto;

/**
 * Created by qiuchaojie on 2017/6/16.
 */
public class NumberOfMyRequestDto {

    private int doingNumber;
    private int todoNumber;
    private int completedNumber;

    public int getDoingNumber() {
        return doingNumber;
    }

    public void setDoingNumber(int doingNumber) {
        this.doingNumber = doingNumber;
    }

    public int getTodoNumber() {
        return todoNumber;
    }

    public void setTodoNumber(int todoNumber) {
        this.todoNumber = todoNumber;
    }

    public int getCompletedNumber() {
        return completedNumber;
    }

    public void setCompletedNumber(int completedNumber) {
        this.completedNumber = completedNumber;
    }
}
