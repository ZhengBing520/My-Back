package com.zb.generator.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/2.
 */
//@JsonFilter("bcd")
@Getter
@Setter
@SuppressWarnings("unchecked")
public abstract class SuperBaseBean implements Serializable {
    @ApiModelProperty(value = "主键(唯一标识符,自动生成)(不需要赋值)")
    @TableId(value = "id", type = IdType.AUTO)
    public Long id;

    @Override
    public int hashCode() {
        return id==null?0:id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        //1、先比较对象引用,相同则对象相等
        if(this==obj){
            return true;
        }else{
            //2、如果传入的参数为null，则对象不相等
            if(obj==null){
                return false;
            }else{
                //3、如果两个对象类型不不相等,则对象不相等
                if(this.getClass()==obj.getClass()){
                    Long objId=((SuperBaseBean)obj).getId();
                    //4、如果两个对象主键相等
                    if(id==objId){
                        //4.1、如果主键相等且都为空,此时当作两个对象不想等
                        if(id==null){
                            return false;
                        }
                        //4.2、主键不空且相等时,则对象相等
                        return true;
                    }else{
                        //5、如果一个主键为空另一个不为空,则对象不相等
                        if(id==null||objId==null){
                            return false;
                        }else{
                            //6、判定主键equals是否相等
                            return id.equals(objId);
                        }
                    }
                }else{
                    return false;
                }
            }
        }
    }
}
