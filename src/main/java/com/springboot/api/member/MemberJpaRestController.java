package com.springboot.api.member;

  import io.swagger.annotations.ApiOperation;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.HttpStatus;
  import org.springframework.http.MediaType;
  import org.springframework.http.ResponseEntity;
  import org.springframework.validation.BindException;
  import org.springframework.validation.BindingResult;
  import org.springframework.web.bind.annotation.*;
  import javax.servlet.http.HttpServletRequest;
  import javax.validation.Valid;
  import java.util.List;
  import java.util.Optional;

  /**
   * MemberJpaRestController 
   * Created by TaeHyoung Kim on 2020-07-21
  **/
  
  @RestController
  @RequestMapping("member")
  public class MemberJpaRestController {
    // 기본형
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemberService memberService;

    // 모든 회원 조회
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "모든 회원 조회")
    public ResponseEntity<List<Member>> getAllmembers() {
      List<Member> member = memberService.findAll();
      return new ResponseEntity<List<Member>>(member, HttpStatus.OK);
    }

    // 회원번호로 한명의 회원 조회
    @GetMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Member> getMember(@PathVariable("mbrNo") Long mbrNo) {
      Optional<Member> member = memberService.findById(mbrNo);
      return new ResponseEntity<Member>(member.get(), HttpStatus.OK);
    }

    // 회원번호로 회원 삭제
    @DeleteMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> deleteMember(@PathVariable("mbrNo") Long mbrNo) {
      memberService.deleteById(mbrNo);
      return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // 회원번호로 회원 수정(mbrNo로 회원을 찾아 Member 객체의 id, name로 수정함)
    @PutMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Member> updateMember(@PathVariable("mbrNo") Long mbrNo, Member member) {
      memberService.updateById(mbrNo, member);
      return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    // 회원 입력
    @PostMapping
    public ResponseEntity<Member> save(Member member) {
      return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }

    // 회원 입력
    @RequestMapping(value="/saveMember", method = RequestMethod.GET)
    public ResponseEntity<Member> save(HttpServletRequest req, Member member){
      return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Member> createAccount(@RequestBody @Valid final Member member,
                                                final BindingResult bindingResult) throws BindException {

      if (bindingResult.hasErrors()) {
        throw new BindException(bindingResult);
      }
      return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }


}
