package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.database.entities.UserEntity;

public interface UserTaskService {

    public void checkTasksCompletion(UserEntity user);

    public void updateTaskCompletion(UserEntity user);

    int getCompletedTasksCount(Long userId);
}
