package com.yang.shirospringboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:∂
 * @Author: Guo.Yang
 * @Date: 2022/03/10/10:12
 */
@Data
@TableName("m_user")
public class User {
    @TableId
    private String username;
    private String password;
}
