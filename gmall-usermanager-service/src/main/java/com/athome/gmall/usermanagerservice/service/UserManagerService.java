package com.athome.gmall.usermanagerservice.service;

import java.util.List;

public interface UserManagerService {

    List<beans.UserAddress> getUserAddressList(String userId);
}
