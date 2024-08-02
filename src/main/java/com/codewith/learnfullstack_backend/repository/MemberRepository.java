package com.codewith.learnfullstack_backend.repository;

import com.codewith.learnfullstack_backend.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
