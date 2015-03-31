package com.pureland.core.service.impl;

import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.UserExt;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.impl.ResourceCommonServiceImpl;
import com.pureland.common.service.impl.UserExtCommonServiceImpl;
import com.pureland.common.util.GameUtil;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.common.service.ResourceCommonService;
import com.pureland.core.init.RankModelHelper;
import com.pureland.core.service.UserExtService;

/**
 * Created by qinpeirong on 15-1-20.
 */
public class UserExtServiceImpl extends UserExtCommonServiceImpl implements UserExtService {

    private ResourceCommonService resourceCommonService = (ResourceCommonService) SpringContextUtil.getBean(ResourceCommonServiceImpl.class.getSimpleName());

    @Override
    public void updateCrown(Long userRaceId, Integer crown) throws CoreException {
        UserExt userExt = getUserExt(userRaceId);
        if (userExt == null) {
            throw new CoreException("userExt is null where userRaceId is " + userRaceId);
        }
        Integer dbCrown = userExt.getCrown();
        if (Math.abs(crown) > GameUtil.MAXCROWN) {
            throw new CoreException("you are cheater ,max 35 , your crown:d%", crown);
        }
        userExt.setCrown(dbCrown + crown);
        updateUserExt(userExt);
    }

    /**
     * 给玩家加经验
     *
     * @param userRaceId
     * @param addExp
     * @throws CoreException
     */
    @Override
    public void addExp(Long userRaceId, int addExp) throws CoreException {
        UserExt userExt = super.getUserExt(userRaceId);
        int totalExp = userExt.getExperience() + addExp;
        userExt.setExperience(totalExp);
        //根据经验设置玩家等级
        userExt.setLevel(RankModelHelper.getLevelByExp(totalExp));
        super.updateUserExt(userExt);
    }

    @Override
    public void chargeDiamond(Long userRaceId, int count) throws CoreException {
        UserExt userExt = super.getUserExt(userRaceId);
        Resource diamondResource = userExt.getResourcesForMap().get(ResourceServerTypeEnum.Diamond);
        diamondResource.setCount(diamondResource.getCount() + count);
        resourceCommonService.updateResource(diamondResource);
    }
}
