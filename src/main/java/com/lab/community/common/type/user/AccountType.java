package com.lab.community.common.type.user;

import com.lab.community.common.code.ResultCode;
import com.lab.community.common.exception.LabException;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.List;

@Getter
public enum AccountType implements GrantedAuthority {

    REALTOR("공인중개사", "Realtor"),
    LESSOR("임대인", "Lessor"),
    LESSEE("임차인", "Lessee"),
    ANONYMOUS("외부 사용자");

    private String name;
    private String headerKey;

    AccountType(String name) {
        this.name = name;
    }
    AccountType(String name, String headerKey) {
        this.name = name;
        this.headerKey = headerKey;
    }

    public static AccountType from(String accountType) {
        switch (accountType){
            case "Realtor" : return REALTOR;
            case "Lessor" : return LESSOR;
            case "Lessee" : return  LESSEE;
            default: throw new LabException(ResultCode.RESULT_4001);
        }
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + this;
    }

    public static List<AccountType> getKnownUsers(){
        return Arrays.asList(REALTOR, LESSOR, LESSEE);
    }

    public static boolean isValidAccountType(String accountType){
        return getKnownUsers().stream().anyMatch(knownType -> knownType.getHeaderKey().equals(accountType));
    }

}
