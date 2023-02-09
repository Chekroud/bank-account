package org.bank.bankaccount.domain.model;

import org.bank.bankaccount.application.request.AccountRequest;
import org.bank.bankaccount.infrastracture.security.RoleNameEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @JsonIgnore
    private Long clientId;
    private Integer clientNumber;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private RoleNameEnum roleName;

    public Client(AccountRequest accountRequest) {

        username = accountRequest.getUsername();
        firstName = accountRequest.getFirstName();
        lastName = accountRequest.getLastName();
        email = accountRequest.getEmail();
        roleName = RoleNameEnum.ROLE_CLIENT;
    }

}
