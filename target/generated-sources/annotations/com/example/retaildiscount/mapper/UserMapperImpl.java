package com.example.retaildiscount.mapper;

import com.example.retaildiscount.dto.request.UserRequest;
import com.example.retaildiscount.dto.response.UserResponse;
import com.example.retaildiscount.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-01T19:40:16+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User map(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User user = new User();

        user.setName( userRequest.name() );
        user.setUserType( userRequest.userType() );
        user.setRegisteredDate( userRequest.registeredDate() );

        return user;
    }

    @Override
    public UserResponse map(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.name( user.getName() );
        userResponse.userType( user.getUserType() );
        userResponse.registeredDate( user.getRegisteredDate() );

        return userResponse.build();
    }

    @Override
    public List<UserResponse> map(List<User> user) {
        if ( user == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( user.size() );
        for ( User user1 : user ) {
            list.add( map( user1 ) );
        }

        return list;
    }
}
