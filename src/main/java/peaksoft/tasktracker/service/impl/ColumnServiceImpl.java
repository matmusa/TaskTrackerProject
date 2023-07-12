package peaksoft.tasktracker.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import peaksoft.tasktracker.repository.ColumnRepository;
import peaksoft.tasktracker.service.ColumnService;
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ColumnServiceImpl implements ColumnService {
    private final ColumnRepository columnRepository;
}
