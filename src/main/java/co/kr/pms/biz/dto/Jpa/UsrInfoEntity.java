package co.kr.pms.biz.dto.Jpa;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "TB_USR_INFO", schema = "my_rds_db", catalog = "")
public class UsrInfoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USR_ID_NO")
    private long usrIdNo;
    @Basic
    @Column(name = "USR_ID")
    private String usrId;
    @Basic
    @Column(name = "USR_MAIL")
    private String usrMail;
    @Basic
    @Column(name = "USR_NM")
    private String usrNm;
    @Basic
    @Column(name = "USR_PW")
    private String usrPw;
    @Basic
    @Column(name = "MOBILE")
    private String mobile;
    @Basic
    @Column(name = "USR_TYPE")
    private String usrType;
    @Basic
    @Column(name = "LAST_LOGIN_DT")
    private Date lastLoginDt;
    @Basic
    @Column(name = "FIRST_CREATE_DT")
    private Date firstCreateDt;
    @Basic
    @Column(name = "PW_CNFM_NO")
    private String pwCnfmNo;
    @Basic
    @Column(name = "MAIL_CNFM_NO")
    private String mailCnfmNo;
    @Basic
    @Column(name = "AUTH_KEY")
    private String authKey;
    @Basic
    @Column(name = "LOGIN_IP")
    private String loginIp;
    @Basic
    @Column(name = "LOGIN_TYPE")
    private String loginType;
    @Basic
    @Column(name = "USE_YN")
    private String useYn;
    @Basic
    @Column(name = "USER_STATE")
    private String userState;
    @Basic
    @Column(name = "REG_DT")
    private Date regDt;
    @Basic
    @Column(name = "REG_ID")
    private String regId;
    @Basic
    @Column(name = "UPD_DT")
    private Date updDt;
    @Basic
    @Column(name = "UPD_ID")
    private String updId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsrInfoEntity that = (UsrInfoEntity) o;
        return usrIdNo == that.usrIdNo && Objects.equals(usrId, that.usrId) && Objects.equals(usrMail, that.usrMail) && Objects.equals(usrNm, that.usrNm) && Objects.equals(usrPw, that.usrPw) && Objects.equals(mobile, that.mobile) && Objects.equals(usrType, that.usrType) && Objects.equals(lastLoginDt, that.lastLoginDt) && Objects.equals(firstCreateDt, that.firstCreateDt) && Objects.equals(pwCnfmNo, that.pwCnfmNo) && Objects.equals(mailCnfmNo, that.mailCnfmNo) && Objects.equals(authKey, that.authKey) && Objects.equals(loginIp, that.loginIp) && Objects.equals(loginType, that.loginType) && Objects.equals(useYn, that.useYn) && Objects.equals(userState, that.userState) && Objects.equals(regDt, that.regDt) && Objects.equals(regId, that.regId) && Objects.equals(updDt, that.updDt) && Objects.equals(updId, that.updId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usrIdNo, usrId, usrMail, usrNm, usrPw, mobile, usrType, lastLoginDt, firstCreateDt, pwCnfmNo, mailCnfmNo, authKey, loginIp, loginType, useYn, userState, regDt, regId, updDt, updId);
    }
}
