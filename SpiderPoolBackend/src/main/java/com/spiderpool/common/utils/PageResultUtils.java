package com.spiderpool.common.utils;


import com.spiderpool.entity.pojo.User;
import lombok.Data;

import java.util.List;

@Data
public class PageResultUtils {

    /**
     * 当前页码
     */
    private Long pageNum;

    /**
     * 页面大小
     */
    private Long pageSize;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 返回的数据
     */
    private List<User>  data;
}
