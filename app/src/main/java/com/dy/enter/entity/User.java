package com.dy.enter.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 类名 User
 * 作者 dy
 * 功能 用户类
 * 创建日期 2016/7/12 16:57
 * 修改日期 2016/7/12 16:57
 */


public class User implements Parcelable {
    //用户所属的公司名称
    private String companyName;

    //公司的组织id
    private String orgId;

    //token
    private String token;

    //用户id
    private String userId;

    //用户名
    private String userName;

    //密码
    private String password;

    //部门名称
    private String deptName;

    //是否自动登录
    private boolean isAutoLogin;

    private boolean isChecked = true;

    public User() {
    }


    protected User(Parcel in) {
        companyName = in.readString();
        orgId = in.readString();
        token = in.readString();
        userId = in.readString();
        userName = in.readString();
        password = in.readString();
        deptName = in.readString();
        isAutoLogin = in.readByte() != 0;
        isChecked = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAutoLogin() {
        return isAutoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        isAutoLogin = autoLogin;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(companyName);
        dest.writeString(orgId);
        dest.writeString(token);
        dest.writeString(userId);
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(deptName);
        dest.writeByte((byte) (isAutoLogin ? 1 : 0));
        dest.writeByte((byte) (isChecked ? 1 : 0));
    }
}
