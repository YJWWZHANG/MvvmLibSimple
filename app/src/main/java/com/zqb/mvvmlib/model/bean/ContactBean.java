package com.zqb.mvvmlib.model.bean;

import java.util.List;

public class ContactBean {


    /**
     * msg : success
     * code : 0
     * data : [{"name":"zsq调度","imei":"02345678901234","sip":"800580000","tsc":"860311000010201992999"},{"name":"消防测试001","imei":"12345678901234","sip":"3001","tsc":"860311000010220210001"}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : zsq调度
         * imei : 02345678901234
         * sip : 800580000
         * tsc : 860311000010201992999
         */

        private String name;
        private String imei;
        private String sip;
        private String tsc;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public String getSip() {
            return sip;
        }

        public void setSip(String sip) {
            this.sip = sip;
        }

        public String getTsc() {
            return tsc;
        }

        public void setTsc(String tsc) {
            this.tsc = tsc;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "name='" + name + '\'' +
                    ", imei='" + imei + '\'' +
                    ", sip='" + sip + '\'' +
                    ", tsc='" + tsc + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ContactBean{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
