package com.example.retaildiscount.mapper;

import com.example.retaildiscount.dto.request.UserRequest;
import com.example.retaildiscount.dto.response.UserResponse;
import com.example.retaildiscount.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "SPRING")
public interface UserMapper {

    User map(UserRequest userRequest);

    UserResponse map(User user);

    List<UserResponse> map(List<User> user);
}
