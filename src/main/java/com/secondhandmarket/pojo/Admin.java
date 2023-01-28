package com.secondhandmarket.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("admin")
public class Admin {

    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;

    @TableField("username")
    private String username;

    private String phone;
    private String password;

    @TableField("userRole")
    private String userRole;
}
