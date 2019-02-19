package com.example.hello.service;

import com.example.hello.dto.UserDto;
import com.example.hello.model.Athrztn;
import com.example.hello.model.Orgn;
import com.example.hello.model.User;
import com.example.hello.repository.AthrztnRepository;
import com.example.hello.repository.OrgnRepository;
import com.example.hello.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrgnRepository orgnRepository;

    @Autowired
    private AthrztnRepository athrztnRepository;

    @Autowired
    private ModelMapper modelMapper;


    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        User u = userRepository.findById(id).orElse(null);
        if ( u != null ) {
            return u;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity Not Found");
        }
    }

    // TODO: getUsersPaging

    @Transactional
    public String addUser(UserDto dto) {
        log.debug(dto.toString());
        boolean isExists = userRepository.existsById(dto.getUserId());
        if ( isExists ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Entity Duplicate");
        }

        Orgn orgn = orgnRepository.findById(dto.getOrgn().getOrgnNo()).orElse(null);
        if (orgn == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Orgn invalid");
        }

        Athrztn auth = athrztnRepository.findById(dto.getAthrztn().getAthrztnNo()).orElse(null);
        if (auth == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Athrztn invalid");
        }

        User u = modelMapper.map(dto, User.class);
        u.setOrgn(orgn);
        u.setAthrztn(auth);

        Date now = new Date();
        u.setFirstRgstrId(u.getUserId());
        u.setFirstRgstTm(now);
        u.setLastUpdtrId(u.getUserId());
        u.setLastUpdtTm(now);

        log.debug(u.toString());
        userRepository.save(u);
        return "OK";
    }

    @Transactional
    public String updateUser(String userId, UserDto dto) {
        log.debug(dto.toString());
        User u = userRepository.findById(userId).orElse(null);
        if ( u == null ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity Not Found");
        }

        modelMapper.map(dto, u);
        log.debug(u.toString());

        //u.setLastUpdtrId(); // 최종 수정자를 현재 세션의 사용자로 변경
        u.setLastUpdtrId(u.getUserId());
        u.setLastUpdtTm(new Date());

        userRepository.save(u);
        return "OK";
    }

    @Transactional
    public String deleteUser(String id) {
        if ( userRepository.existsById(id) ) {
            userRepository.deleteById(id);
            return "OK";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity Not Found");
        }
    }

    @Transactional
    public String addUserBulk(UserDto dto, int count) {
        log.debug(dto.toString());
        Orgn orgn = orgnRepository.findById(dto.getOrgn().getOrgnNo()).orElse(null);
        if (orgn == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Orgn invalid");
        }

        Athrztn auth = athrztnRepository.findById(dto.getAthrztn().getAthrztnNo()).orElse(null);
        if (auth == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Athrztn invalid");
        }

        User u = modelMapper.map(dto, User.class);
        u.setOrgn(orgn);
        u.setAthrztn(auth);

        Date now = new Date();
        u.setFirstRgstrId(u.getUserId());
        u.setFirstRgstTm(now);
        u.setLastUpdtrId(u.getUserId());
        u.setLastUpdtTm(now);

        for (int i=1; i <= count; i++) {
            String userId = String.format("U-%04d", i);
            String userNm = String.format("User(%d)", i);
            u.setUserId(userId);
            u.setUserNm(userNm);
            log.debug(u.toString());
            userRepository.save(u);
        }

        return "OK";
    }

    @Transactional
    public String deleteUserBulk(String target) {
        String[] idList = target.split(",");
        for (String id : idList) {
            userRepository.deleteById(id);
        }

        return "OK";
    }

    public Iterable<User> searchUser(String key, String value) {
        if ( key.equals("email") )
            return userRepository.findByEmail(value);
        else if ( key.equals("tel"))
            return userRepository.findByTelLike("%"+value+"%");
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
