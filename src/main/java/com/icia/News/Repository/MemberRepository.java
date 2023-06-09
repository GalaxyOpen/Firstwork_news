package com.icia.News.Repository;

import com.icia.News.DTO.MemberDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    @Autowired
    public SqlSessionTemplate sql;
    public void save(MemberDTO memberDTO) {
        sql.insert("Member.save", memberDTO);
    }

    public MemberDTO findByMemberEmail(String memberEmail) {
       return sql.selectOne("Member.findByMemberEmail", memberEmail);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        return sql.selectOne("Member.login",memberDTO);
    }

    public void update(MemberDTO memberDTO) {
        sql.update("Member.update",memberDTO);
    }

    public void delete(Long id) {
        sql.delete("Member.delete",id);
    }

    public List<MemberDTO> findAll() {
       return sql.selectList("Member.findAll");
    }

    public MemberDTO findById(Long id) {
        return sql.selectOne("Member.findById",id);
    }
}
