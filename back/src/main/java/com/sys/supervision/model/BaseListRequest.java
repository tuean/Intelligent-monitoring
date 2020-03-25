package com.sys.supervision.model;


import com.sys.supervision.utils.PageUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseListRequest {

    private String name;

    private Integer year;

    private Integer month;

    private String group;

    private String buildingSite;

    private Integer pageNo;

    private Integer pageSize;

    private Integer status;


    private Integer salaryStatus;
    private Integer livingCostStatus;

    private Integer startRow;

    private Integer endRow;

    private String creater;

    private Integer type;

    private Integer paidStatus;



    public void paramCheck() {
        PageTair tair = PageUtils.transferTo(this.getPageNo(), this.getPageSize());
        Integer startRow = tair.getStartRow() == -1 ? null : tair.getStartRow();
        Integer endRow = tair.getEndRow() == -1 ? null : tair.getEndRow();
//
        this.setStartRow(startRow);
        this.setEndRow(endRow);
    }

}
