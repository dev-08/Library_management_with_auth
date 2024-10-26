package com.library.library_management_system.service;

import com.library.library_management_system.LibraryManagementSystemApplication;
import com.library.library_management_system.entities.Members;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface MemberService {


    Members addMember(Members members);

    List<Members> getMembers();
}
