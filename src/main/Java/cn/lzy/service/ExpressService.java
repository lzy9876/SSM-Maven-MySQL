package cn.lzy.service;

import cn.lzy.util.Result;

import java.util.Map;

public interface ExpressService {

    /**
     * @Author liziyang
     * @Description 快件新增
     * @Date 21:13 2019/12/2
     * @Param [map]
     * @return cn.lzy.util.Result
     **/
    Result saveExpress(Map map);

    /**
     * @Author liziyang
     * @Description 根据手机号OR取件码查询快件
     * @Date 12:09 2019/12/3
     * @Param [map]
     * @return cn.lzy.util.Result
     **/
    Result queryExpressByCodeORuPhone(Map map);

    /**
     * @Author liziyang
     * @Description 根据eStatus查询快件信息
     * @Date 16:23 2019/12/3
     * @Param [eStatus, page, limit]
     * @return cn.lzy.util.Result
     **/
    Result queryExpressByeStatus(Integer eStatus, int page, int limit);

    /**
     * @Author liziyang
     * @Description 根据id查询快件信息
     * @Date 10:44 2019/12/4
     * @Param [id]
     * @return cn.lzy.util.Result
     **/
    Result queryExpressById(Integer id);

    /**
     * @Author liziyang
     * @Description 快件信息修改
     * @Date 12:41 2019/12/4
     * @Param [map]
     * @return cn.lzy.util.Result
     **/
    Result updateExpressById(Map map);

    /**
     * @Author liziyang
     * @Description 删除快件信息
     * @Date 13:21 2019/12/4
     * @Param [id]
     * @return cn.lzy.util.Result
     **/
    Result deleteExpressById(Integer id);

    /**
     * @Author liziyang
     * @Description 根据用户手机号 快件状态码  查询快件状态
     * @Date 21:52 2019/12/5
     * @Param [map]
     * @return cn.lzy.util.Result
     **/
    Result queryExpressByuPhone(Map map);

    /**
     * @Author liziyang
     * @Description 根据ID 修改快件状态
     * @Date 18:02 2019/12/7
     * @Param [id]
     * @return cn.lzy.util.Result
     **/
    Result updateExpressCodeById(Map map);

    /**
     * @Author liziyang
     * @Description 查询所有快件信息
     * @Date 21:53 2019/12/9
     * @Param [ int page,
     *          int limit,
     *
     * @return cn.lzy.util.Result
     **/
    Result queryExpress(int page, int limit);

    /**
     * @Author liziyang
     * @Description 查询快件数量
     * @Date 16:38 2019/12/17
     * @Param [map] 参数列表
     * (eStatus) 快件状态       1-未取件 2-已取件
     * (inTime)  快件到达时间    toDate -- doDate
     * @return cn.lzy.util.Result
     **/
    Result queryExpressCount(Map map);

    /**
     * @Author liziyang
     * @Description 根据用户手机号查询所有快件信息
     * @Date 16:09 2020/1/5
     * @Param [ int page,
     *          int limit,
     *          Integer uPhone]
     * @return cn.lzy.util.Result
     **/
    Result queryExpressByuPhoneLoss(int page, int limit, String uPhone);



}
