package com.blog.util;

import com.blog.entity.Blog;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;



//提交数据库时，一定要将所有的数据都再提交一次，否侧未修改数据将会为0
/*package com.blog.service.ServiceImpl.updateblog*/
public class MyBeanUtils {
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper beanWrapper=new BeanWrapperImpl(source);
        PropertyDescriptor[] pds=beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames=new ArrayList<>();
        for (PropertyDescriptor pd:pds){
            String propertyName=pd.getName();
            if(beanWrapper.getPropertyValue(propertyName)==null){
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
    }
}

