package com.base.net.xa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import cn.afterturn.easypoi.excel.annotation.Excel;


/**
 * <p>
 * 
 * </p>
 *
 * @author zhaikaixuan
 * @since 2019-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XaAccount对象", description="")
public class XaAccount extends Model<XaAccount> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户地址")
    @TableId(value = "account_id", type = IdType.UUID)
    @Excel(name = "用户地址")
    private String accountId;

    @ApiModelProperty(value = "账户昵称")
    @TableField("acount_nick")
    @Excel(name = "账户昵称")
    private String acountNick;

    @ApiModelProperty(value = "账户名称")
    @TableField("account_name")
    @Excel(name = "账户名称")
    private String accountName;

    @ApiModelProperty(value = "账户邮箱")
    @TableField("account_email")
    @Excel(name = "账户邮箱")
    private String accountEmail;

    @ApiModelProperty(value = "账户联系方式")
    @TableField("account_phone")
    @Excel(name = "账户联系方式")
    private String accountPhone;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @Excel(name = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @Excel(name = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "修改时间")
    private Date updateDate;

    @ApiModelProperty(value = "账户的状态(0 正常 1 冻结 2 锁定)")
    @TableField("account_status")
    @Excel(name = "账户的状态(0 正常 1 冻结 2 锁定)")
    private String accountStatus;


    @Override
    protected Serializable pkVal() {
        return this.accountId;
    }

}
