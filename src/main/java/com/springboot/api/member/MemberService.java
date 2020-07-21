package com.springboot.api.member;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * MemberService 
 * Created by TaeHyoung Kim on 2020-07-21
**/
@Service

@RequiredArgsConstructor
public class MemberService {
 @Autowired

  private MemberRepository memberRepository;

  private final ModelMapper modelMapper;

  public List<MemberVo> findAll() {
    List<MemberVo> members = new ArrayList<>();
    memberRepository.findAll().forEach(e -> members.add(e));
    return members;
  }

  public Optional<MemberVo> findById(Long mbrNo) {
    Optional<MemberVo> member = memberRepository.findById(mbrNo);
    return member;
  }

  public void deleteById(Long mbrNo) {
    memberRepository.deleteById(mbrNo);
  }

  public MemberVo save(MemberVo member) {
    MemberVo memberVo = modelMapper.map(member, MemberVo.class);
    memberRepository.save(memberVo);
    return member;
  }

  public void updateById(Long mbrNo, MemberVo member) {
    Optional<MemberVo> e = memberRepository.findById(mbrNo);
    if (e.isPresent()) {
      e.get().setMbrNo(member.getMbrNo());
      e.get().setId(member.getId());
      e.get().setName(member.getName());
      memberRepository.save(member);
    }
  }
}
