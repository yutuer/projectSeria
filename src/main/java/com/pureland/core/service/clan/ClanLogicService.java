package com.pureland.core.service.clan;

import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.clan.ClanBean;

import java.util.List;

/**
 * Created by Administrator on 2015/3/12.
 */
public interface ClanLogicService {

    List<Clan> clanLogic(ClanBean clanBean) throws CoreException;
}
