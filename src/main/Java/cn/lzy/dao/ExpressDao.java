package cn.lzy.dao;

import cn.lzy.entity.Express;

import java.util.List;
import java.util.Map;

public interface ExpressDao {

    /**
     * @Author liziyang
     * @Description 快件新增
     * @Date 21:10 2019/12/2
     * @Param [map]
     * @return int
     **/
    int saveExpress(Map map);

    /**
     * @Author liziyang
     * @Description 根据取件码或者手机号查询快递信息
     * @Date 11:56 2019/12/3
     * @Param [map]
     * @return java.util.List<cn.lzy.entity.Express>
     **/
    List<Express> queryExpressByCodeORuPhone(Map map);

    /**
     * @Author liziyang
     * @Description 根据eStatus查询快递信息
     * @Date 15:53 2019/12/3
     * @Param [eStatus]
     * @return java.util.List<cn.lzy.entity.Express>
     **/
    List<Express> queryExpressByeStatus(Integer eStatus);

    /**
     * @Author liziyang
     * @Description 根据id查询快件信息
     * @Date 10:43 2019/12/4
     * @Param [id]
     * @return cn.lzy.entity.Express
     **/
    Express queryExpressById(Integer id);

    /**
     * @Author liziyang
     * @Description 快件信息修改
     * @Date 12:40 2019/12/4
     * @Param [map]
     * @return int
     **/
    int updateExpressById(Map map);

    /**
     * @Author liziyang
     * @Description 删除快件信息
     * @Date 13:20 2019/12/4
     * @Param [id]
     * @return int
     **/
    int deleteExpressById(Integer id);

    /**
     * @Author liziyang
     * @Description 根据用户手机号 快件状态码查询快件
     * @Date 21:51 2019/12/5
     * @Param [map]
     * @return java.util.List<cn.lzy.entity.Express>
     **/
    List<Express> queryExpressByuPhone(Map map);

   /**
    * @Author liziyang
    * @Description 根据id 修改快件code 增加时间信息
    * @Date 18:09 2019/12/7
    * @Param [map]
    * @return int
    **/
    int updateExpressCodeById(Map map);

    /**
     * @Author liziyang
     * @Description 查询所有快件信息
     * @Date 21:52 2019/12/9
     * @Param []
     * @return java.util.List<cn.lzy.entity.Express>
     **/
    List<Express> queryExpress();

    /**
     * @Author liziyang
     * @Description //查询快件数量
     * @Date 16:33 2019/12/17
     * @Param [map] 参数列表
     * (eStatus) 快件状态       1-未取件 2-已取件
     * (inTime)  快件到达时间    toDate -- doDate
     * @return int
     **/
    int queryExpressCount(Map map);


}
