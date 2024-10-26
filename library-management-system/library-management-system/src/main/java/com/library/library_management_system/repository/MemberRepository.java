package com.library.library_management_system.repository;

import com.library.library_management_system.entities.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, Long> {


}
