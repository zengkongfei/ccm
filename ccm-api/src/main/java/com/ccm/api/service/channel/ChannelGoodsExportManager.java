package com.ccm.api.service.channel;

import java.util.List;

import com.ccm.api.model.channel.vo.ChannelGoodsVO;

public interface ChannelGoodsExportManager {
	
	public List<String[]> getChannelGoodsData(ChannelGoodsVO cgvo);
	
}
