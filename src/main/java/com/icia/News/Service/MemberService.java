package com.icia.News.Service;

import com.icia.News.DTO.MemberDTO;
import com.icia.News.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        memberRepository.save(memberDTO);
    }

    public MemberDTO findByMemberEmail(String memberEmail) {
        return memberRepository.findByMemberEmail(memberEmail);
    }

    public boolean login(MemberDTO memberDTO) {
        MemberDTO dto = memberRepository.login(memberDTO);
        if(dto!=null){
            return true;
        }else{
            return false;
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.update(memberDTO);
    }

    public void delete(Long id) {
        memberRepository.delete(id);
    }

    public List<MemberDTO> findAll() {
        List<MemberDTO> memberDTOList =memberRepository.findAll();
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        MemberDTO memberDTO = memberRepository.findById(id);
        return memberDTO;
    }
}
