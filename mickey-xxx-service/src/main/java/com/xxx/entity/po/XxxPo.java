package com.xxx.entity.po;

import com.alibaba.fastjson.JSON;
import com.mickey.model.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
*
* @Description: xxx
* @author jack
* @date 2020年03月30日
*
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
@Table(name = "xxx")
public class XxxPo extends BasePo {

   private static final long serialVersionUID = 1L;

   public static final String TABLE_ALIAS = "Xxx";

   /**
   * 自增主键
   */
   @Id
   @Column(name = "xxx_id")
   private Integer xxxId;
   /**
   * 登记码（R开头）
   */
   @Column(name = "name")
   private String name;
   /**
   * 0-男 1-女
   */
   @Column(name = "sex")
   private Integer sex;
   /**
   * 删除标志：0：未删除，1：已删除
   */
   @Column(name = "del_flag")
   private Boolean delFlag;
   /**
   * create_time
   */
   @Column(name = "create_time")
   private Date createTime;
   /**
   * update_time
   */
   @Column(name = "update_time")
   private Date updateTime;

   @Override
   public String toString()
   {
       return JSON.toJSONString(this);
   }
}