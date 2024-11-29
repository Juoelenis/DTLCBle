package org.dinky.data.dto;

import org.dinky.data.model.rbac.Role;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RoleDTO
 *
 * @since 2023/10/23 15:34
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "RoleDTO", description = "API Role Data Transfer Object")
public class RoleDTO {

    @ApiModelProperty(value = "ID", dataType = "Integer", example = "1", notes = "Unique identifier for the role")
    private Integer id;

    @ApiModelProperty(
            value = "Tenant ID",
            dataType = "Integer",
            example = "1001",
            notes = "ID of the tenant associated with the role")
    private Integer tenantId;

    @ApiModelProperty(
            value = "Role Code",
            dataType = "String",
            example = "ROLE_ADMIN",
            notes = "Code representing the role")
    private String roleCode;

    @ApiModelProperty(value = "Role Name", dataType = "String", example = "Administrator", notes = "Name of the role")
    private String roleName;

    @ApiModelProperty(
            value = "Is Delete",
            dataType = "Boolean",
            notes = "Flag indicating if the role is marked as deleted")
    private Boolean isDelete;

    @ApiModelProperty(value = "Note", dataType = "String", notes = "Additional notes or details about the role")
    private String note;

    public Role toBean() {
        Role role = new Role();
        BeanUtil.copyProperties(this, role);
        return role;
    }
}
