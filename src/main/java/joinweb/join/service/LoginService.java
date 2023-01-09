package joinweb.join.service;

import joinweb.join.domain.Member;
import joinweb.join.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                 .filter(m -> m.getPassword().equals(password))
                 .orElse(null);
    }

    /*
    public Member login(String loginId, String password) {
        Member findMember = memberRepository.findByLoginId(loginId);

        if(findMember == null) {
            return null;
        }

        if(!findMember.getPassword().equals(password)) {
            return null;
        }

        return findMember;
    }

 */
}
