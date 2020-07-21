package com.springboot.api.member;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by kth on 2020-07-21
 */

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

 private final MemberRepository memberRepository;

  private final ModelMapper modelMapper;

  @Override
  public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
    memberRepository.findAll().forEach(e -> members.add(e));
    return members;
  }

  @Override
  public Optional<Member> findById(Long mbrNo) {
    return Optional.empty();
  }

  @Override
  public void deleteById(Long mbrNo) {
    memberRepository.deleteById(mbrNo);
  }

  @Override
  public Member save(final Member member) {
    Member mem = modelMapper.map(member, Member.class);
    memberRepository.save(mem);
    return member;
  }

  @Override
  public void updateById(Long mbrNo, Member member) {
    Optional<Member> e = memberRepository.findById(mbrNo);
    if (e.isPresent()) {
      e.get().setMbrNo(member.getMbrNo());
      e.get().setId(member.getId());
      e.get().setName(member.getName());
      memberRepository.save(member);
    }
  }
}
