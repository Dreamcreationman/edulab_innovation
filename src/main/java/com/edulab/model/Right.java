package com.edulab.model;

import com.edulab.model_base.BaseRight;

import java.util.List;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Right extends BaseRight<Right> {
	public static final Right dao = new Right();

    /**
     * 根据传入的权限ID查询角色名
     * @param rightId
     * @return null表示ID找不到对应的权限，需要检查ID是否正确
     */
	public String getRightNameById(int rightId){
        String sqlRight = "select rightName from edu_right where rightId = ?";
        List<Right> right = find(sqlRight,rightId);
        return right.size()==0?null:right.get(0).getRightName();
    }
}
