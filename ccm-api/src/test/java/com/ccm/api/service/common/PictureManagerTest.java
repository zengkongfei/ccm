/**
 * 
 */
package com.ccm.api.service.common;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.model.common.Picture;
import com.ccm.api.service.base.BaseManagerTestCase;

/**
 * @author Jenny Liao
 *
 */
public class PictureManagerTest  extends BaseManagerTestCase {

	@Autowired
	private PictureManager pictureMgr;

	@Test
	@Rollback(false)
	public void testGetAll() throws Exception {

		List<Picture> picList = pictureMgr.getHotelPictureOfHotelId("291");
		picList.size();
	}
	
}