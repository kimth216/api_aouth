package com.springboot.api.member;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * MemberService
 * Created by TaeHyeong Kim on 2020-07-21
**/

@Service

 // @RequiredArgsConstructor
public interface MemberService {

  List<Member> findAll();

  Optional<Member> findById(Long mbrNo);

  void deleteById(Long mbrNo);

  Member save(Member member);

  void updateById(Long mbrNo, Member member);

}
