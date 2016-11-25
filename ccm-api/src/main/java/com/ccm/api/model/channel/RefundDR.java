package com.ccm.api.model.channel;
/**
 * 
 * @author Devin
 *
 */
public class RefundDR {
    /**
     * 入住前d天内退订
     */
    private int d;
    /**
     * 收取订单总额r % 的违约金
     */
    private int r;
    
    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
