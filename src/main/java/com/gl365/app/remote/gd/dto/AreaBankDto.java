package com.gl365.app.remote.gd.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by qiuchaojie on 2017/6/8.
 */
public class AreaBankDto{

    static class BankDto {

        @ApiModelProperty("银行行号")
        private String bankNo;

        @ApiModelProperty("上级银行行号量")
        private String parentBankNo;

        @ApiModelProperty("银行名称")
        private String name;

        @ApiModelProperty("外部区域id")
        private String deptId;

        @ApiModelProperty("给乐区域id")
        private int glAreaId;

        public String getBankNo() {
            return bankNo;
        }

        public void setBankNo(String bankNo) {
            this.bankNo = bankNo;
        }

        public String getParentBankNo() {
            return parentBankNo;
        }

        public void setParentBankNo(String parentBankNo) {
            this.parentBankNo = parentBankNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public int getGlAreaId() {
            return glAreaId;
        }

        public void setGlAreaId(int glAreaId) {
            this.glAreaId = glAreaId;
        }
    }

    @ApiModelProperty("总数量")
    private int totalCount;

    @ApiModelProperty("当前页")
    private int curPage;

    @ApiModelProperty("页面容量")
    private int pageSize;

    private List<BankDto> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<BankDto> getList() {
        return list;
    }

    public void setList(List<BankDto> list) {
        this.list = list;
    }
}
