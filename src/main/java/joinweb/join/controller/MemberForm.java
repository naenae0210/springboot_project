package joinweb.join.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회워이름을 입력해주세요")
    private String name;
    private String birth;
    private String loginId;
    private String password;

}
