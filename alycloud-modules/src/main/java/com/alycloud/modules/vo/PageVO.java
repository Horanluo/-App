package com.alycloud.modules.vo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVO implements Serializable{

	/**
     * 分页
     */
    private static final long serialVersionUID = -8266369736035588788L;
    /** 当前页 */
    protected Integer pageNum=1;
    /** 查询时页面数据条数 */
    protected Integer pageSize=20;
    /** 重置页面数据条数 */
    protected Integer numPerPage;
    /** 上次查询时页面数据条数 */
    protected Integer numPerNum;
    
    /** 是否分页 */
    protected Boolean flag = true;
}
