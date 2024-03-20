package com.algoExpert.demo.Service;


import com.algoExpert.demo.Entity.Member;
import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemberRepository memberRepository;

//    Invite member to project
    public Project inviteMember (int project_id , int user_id){

        Project userproject = projectRepository.findById(project_id).get();

        List<Member> members =  userproject.getMembersList();
        Member newMember = new Member(0,user_id, project_id,null);
        members.add(newMember);
        userproject.setMembersList(members);

        return projectRepository.save(userproject);
    }

//    get all members
    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    //    get member id
    public Integer findLoginMember(int user_id) {
        List<Member> memberList = memberRepository.findAll();

        Optional<Member> optionalMember = memberList.stream()
                .filter(member -> member.getUser_id() == user_id)
                .findFirst();

        if (optionalMember.isPresent()) {
            return optionalMember.get().getMember_id();
        } else {
            // Handle case when member with given user_id is not found
            return null; // Or throw an exception, depending on your use case
        }
    }


}
