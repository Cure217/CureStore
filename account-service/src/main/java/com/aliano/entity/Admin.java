package com.aliano.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author aliano
 * @since 2022-05-22
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Admin implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "admin_id", type = IdType.AUTO)
      private Integer adminId;

      /**
     * 账号
     */
      private String username;

      /**
     * 密码
     */
      private String password;

      /**
     * 用户头像
     */
      private String imgUrl;

      /**
     * 用户名称
     */
      private String name;


}
