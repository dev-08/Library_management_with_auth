package com.library.library_management_system.controller;


import com.library.library_management_system.entities.Book;
import com.library.library_management_system.entities.Members;
import com.library.library_management_system.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MembersController {



    @Autowired
    MemberService memberService;

    @PostMapping("/add")
    public ResponseEntity<?> addMember(@RequestBody Members members){
        Members members1 = memberService.addMember(members);
        return ResponseEntity.ok(members1);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getMemberService() {

      List<Members> membersList = memberService.getMembers();

      if (membersList ==  null){
          return ResponseEntity.ok("No Mwmber Found");
      }

      return ResponseEntity.ok(membersList);
    }

    


}
