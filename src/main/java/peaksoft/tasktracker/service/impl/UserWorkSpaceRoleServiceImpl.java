package peaksoft.tasktracker.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import peaksoft.tasktracker.repository.UserWorkSpaceRoleRepository;
import peaksoft.tasktracker.service.UserWorkSpaceRoleService;
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserWorkSpaceRoleServiceImpl implements UserWorkSpaceRoleService {
    private final UserWorkSpaceRoleRepository userWorkSpaceRoleRepository;
}
