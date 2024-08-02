package com.codewith.learnfullstack_backend.controller;

import com.codewith.learnfullstack_backend.exception.MemberNotFoundException;
import com.codewith.learnfullstack_backend.model.Member;
import com.codewith.learnfullstack_backend.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/member")
    Member newMember(@RequestBody Member newMember) {
        return memberRepository.save(newMember);
    }

    @GetMapping("/members")
    List<Member> getMember() {
        return memberRepository.findAll(Sort.by("id"));
    }

    @GetMapping("/member/{id}")
    Member getMemeberById(@PathVariable Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
    }

    @PutMapping("/member/{id}")
    Member updateUser(@RequestBody Member newMember, @PathVariable Long id) {
        return memberRepository.findById(id).map(member -> {
            member.setUsername(newMember.getUsername());
            member.setName(newMember.getName());
            member.setEmail(newMember.getEmail());

            return memberRepository.save(member);
        }).orElseThrow(() -> new MemberNotFoundException(id));

    }

    @DeleteMapping("/member/{id}")
    String deleteMember(@PathVariable Long id) {
        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException(id);
        }
        memberRepository.deleteById(id);
        return "User with id " + id + " has been deleted success";
    }
}
