package com.xxx.entity.po;

import com.alibaba.fastjson.JSON;
import com.mickey.model.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;

/**
 * @author jack
 * @Description: sc_qr_code
 * @date 2020年02月27日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ExamplePo extends BasePo {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer qrCodeId;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}