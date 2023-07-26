package peaksoft.tasktracker.repository.jdbcTemplateService;

import peaksoft.tasktracker.dto.response.LabelResponse;

import java.util.List;

public interface LabelJdbcTemplateService {
    List<LabelResponse> getAllLabels();
    List<LabelResponse> getAllLabelsByCardId(Long cardId);
    LabelResponse getLabelById(Long labelId);


}
