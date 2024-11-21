package com.dev.electricity.mapper;

import com.dev.electricity.dto.request.User.UserCreationRequest;
import com.dev.electricity.dto.request.User.UserUpdateRequest;
import com.dev.electricity.dto.response.UsageHistoryResponse;
import com.dev.electricity.dto.response.UserResponse;
import com.dev.electricity.entity.UsageHistory;
import com.dev.electricity.entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( request.getUsername() );
        user.setPassword( request.getPassword() );
        user.setFullName( request.getFullName() );

        return user;
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.usageHistories( usageHistorySetToUsageHistoryResponseSet( user.getUsageHistories() ) );
        userResponse.username( user.getUsername() );
        userResponse.fullName( user.getFullName() );

        return userResponse.build();
    }

    @Override
    public void updateUser(User user, UserUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        user.setPassword( request.getPassword() );
        user.setFullName( request.getFullName() );
    }

    protected UsageHistoryResponse usageHistoryToUsageHistoryResponse(UsageHistory usageHistory) {
        if ( usageHistory == null ) {
            return null;
        }

        UsageHistoryResponse.UsageHistoryResponseBuilder usageHistoryResponse = UsageHistoryResponse.builder();

        usageHistoryResponse.idUsage( usageHistory.getIdUsage() );
        usageHistoryResponse.monthUsage( usageHistory.getMonthUsage() );
        usageHistoryResponse.unitsUsed( usageHistory.getUnitsUsed() );
        usageHistoryResponse.amount( usageHistory.getAmount() );

        return usageHistoryResponse.build();
    }

    protected Set<UsageHistoryResponse> usageHistorySetToUsageHistoryResponseSet(Set<UsageHistory> set) {
        if ( set == null ) {
            return null;
        }

        Set<UsageHistoryResponse> set1 = new LinkedHashSet<UsageHistoryResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UsageHistory usageHistory : set ) {
            set1.add( usageHistoryToUsageHistoryResponse( usageHistory ) );
        }

        return set1;
    }
}
