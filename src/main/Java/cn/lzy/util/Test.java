package cn.lzy.util;

import cn.lzy.dao.CourierDao;
import cn.lzy.entity.Express;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import cn.lzy.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
/**
 * @ClassName Test
 * @Author:Liziy
 * @Date 2019/11/23 23:52
 * @Description:
 **/
@Service
public class Test {

    @Autowired
    CourierDao courierDao;


    public  void courier(){
        PageHelper.startPage(1,10);
        System.out.println(courierDao);
        List<User> userList = courierDao.queryUserByRole(1);
        for(User users:userList){
            System.out.println(users);
        }
    }

    public static void main(String[] args) {
      //Test.demo(0);
        List<Express> expressList = null;
        System.out.println(expressList.size());





    }

    public static int  demo(int start){

        for (int i = 0; i<10; i++){

            if(i>=9){
                return i;
            }

        }



        return 0;
    }

}
