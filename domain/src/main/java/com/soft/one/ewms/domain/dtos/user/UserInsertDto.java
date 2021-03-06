package com.soft.one.ewms.domain.dtos.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "管理员添加用户")
public class UserInsertDto implements Serializable {

    private static final long serialVersionUID = -9132260918825420504L;

    /**
     * 用户ID,至少6个字符
     */
    @ApiModelProperty(value = "用户ID,至少6个字符",required = true)
    private String userId;

    /**
     * 用户密码,至少為八個字符. 需由大寫英文, 小寫英文, 數字及符號4項中的其中3項組成
     */
    @ApiModelProperty(value = "用户密码",required = true)
    private String userPsw;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户名字")
    private String userName;
}
