package com.xiaoma.studyretrofit;

public class NetResponse {

    /**
     * code : 0
     * msg : 成功
     * data : {"userId":"zdny001_ltb2","name":"集散中心","sex":"1","token":"7349CDDB28C6D29D09196DB20217A75E","empCode":"zdny001_ltb2","empName":"集散中心","empNameEn":"","officeCode":"jdsc0111","officeName":"智道农业","companyCode":"cs100","companyName":"智道农业","avatar":"/userfiles/avatar/0/employee/zdny001_ltb2.jpg","defaultBaseId":"1078573934696366080","defaultBaseName":"测试基地信息","userRoleInfo":{"tunnelPermi":0}}
     */

    private int code;
    private String msg;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NetResponse{");
        sb.append("code=").append(code);
        sb.append(", msg='").append(msg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
