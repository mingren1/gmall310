package com.athome.gmall.orderservice.constant;

public enum OrderStatus {

    UNPAID("未支付"),
    PAID("已支付");
    private String comment;

    OrderStatus(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
