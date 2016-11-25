/**
 * 
 */
package com.ccm.api.service.taobaoAPI2;

import com.ccm.api.model.channel.vo.GoodsVO;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.common.vo.InvokeResponse;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.taobao.api.ApiException;
import com.taobao.api.domain.Rate;
import com.taobao.api.domain.RatePlan;
import com.taobao.api.domain.XHotel;
import com.taobao.api.domain.XRoom;
import com.taobao.api.domain.XRoomType;
import com.taobao.api.request.XhotelAddRequest;
import com.taobao.api.request.XhotelOrderAlipayfaceUpdateRequest;
import com.taobao.api.request.XhotelRoomtypeAddRequest;
import com.taobao.api.request.XhotelUpdateRequest;

/**
 * @author Devin
 * 对应淘宝2.0接口
 */
public interface TaobaoApiManager {

    /***
     * taobao.xhotel.add
     * 添加酒店
     * @param req
     * @return
     */
    public InvokeResponse hotelAdd(XhotelAddRequest req)throws Exception;
    
    /***
     * taobao.xhotel.get
     * 通过hid获取酒店
     * @param req
     * @return
     */
    public XHotel hotelGet(String hid)throws Exception;
    /***
     * taobao.xhotel.confirm
     * 确认taobao.hotel.add的匹配结果
     * @param req
     * @return
     */
    public InvokeResponse hotelConfirm(String hid,Long confirm)throws Exception;
    /***
     * taobao.xhotel.update
     * 更新酒店
     * @param req
     * @param sessionKey 
     * @return
     * @throws Exception 
     */
    public InvokeResponse hotelUpdate(XhotelUpdateRequest req, String sessionKey) throws Exception;
    
    /***
     * taobao.xhotel.roomType.add
     * 添加房型
     * @param req
     * @return
     */
    public InvokeResponse roomTypeAdd(RoomTypeVO roomTypeVO)throws Exception;
    /***
     * taobao.xhotel.roomType.update
     * 更新房型
     * @param req
     * @return
     */
    public InvokeResponse roomTypeUpdate(RoomTypeVO roomTypeVO)throws Exception;
    /***
     * taobao.xhotel.roomType.get
     * 获取房型
     * @param req
     * @return
     */
    public XRoomType roomTypeGet(Long rid)throws Exception;
    
    /***
     * rootaobao.xhotel.roomType.confirm
     * 确认taobao.xhotel.roomType.add的匹配结果
     */
    public InvokeResponse roomTypeConfirm(String rid,Long confirm)throws Exception;
    
    /***
     * taobao.xhotel.room.add(发宝贝)
     * 此接口用于发布一个集市酒店商品，商品的发布者是当前会话的用户。
     * 如果该酒店、该房型、该用户所对应的商品在淘宝集市酒店系统中已经存在，则会返回错误提示。
     */
    public InvokeResponse roomAdd(RoomTypeVO roomTypeVO)throws Exception;
    
    /***
     * taobao.xhotel.room.update(发宝贝)
     * 规则：卖家只上线需要更新的字段。如需删除可以更新为空值
     */
    public InvokeResponse roomUpdate(RoomTypeVO roomTypeVO)throws Exception;
    
    /***
     * taobao.xhotel.room.get
     * 此接口用于查询一个商品，根据传入的gid查询商品信息。卖家只能查询自己的商品。
     */
    public XRoom roomGet(Long gid)throws Exception;
    
    
    /***
     * 添加房价
     * taobao.xhotel.rateplan.add
     * 返回 rpid
     * @throws Exception
     */
    public InvokeResponse rateplanAdd(RatePlanVO ratePlanVO)throws Exception;
    
    /***
     * taobao.xhotel.rateplan.update
     * 酒店产品库rateplan更新
     * 返回修改的rpid
     */
    public InvokeResponse rateplanUpdate(RatePlanVO roomTypeVO)throws Exception;
    
    /***
     * taobao.xhotel.rateplan.get 
                酒店产品库rateplan查询
     */
    public RatePlan rateplanGet(String rpid )throws Exception;

    
    /***
     * 添加产品
     * @param gsVo
     * @return
     * @throws ApiException 
     */
    public InvokeResponse rateAdd(GoodsVO gsVo) throws Exception;
    
    /***
     * 更新产品
     * @param gsVo
     * @return
     */
    public InvokeResponse rateUpdate(GoodsVO gsVo)throws Exception;
    /***
     * 获取产品
     * @param gsVo
     * @return
     */
    public Rate rateGet(Long gid, Long rpId) throws Exception;
    
    
    /***
     * 批量更新产品
     * @param gsVo
     * @return resObj = List<String = GidAndRpids>
     */
    public InvokeResponse ratesUpdate(String rate_inventory_price_map)throws Exception;
    
    /***
     * 批量更新产品
     * @param gsVo
     * @return resObj = List<String = GidAndRpids>
     */
    public InvokeResponse ratesUpdate(String rate_inventory_price_map,String sessionKey)throws Exception;

    InvokeResponse hotelAdd(XhotelAddRequest req, String sessionKey)
            throws Exception;

    String getSessionKey();
    
    public InvokeResponse alipayfaceUpdate(XhotelOrderAlipayfaceUpdateRequest req,String sessionKey) throws Exception;
}
