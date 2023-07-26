package peaksoft.tasktracker.service;

import peaksoft.tasktracker.dto.request.LabelRequest;
import peaksoft.tasktracker.dto.response.LabelResponse;
import peaksoft.tasktracker.dto.response.SimpleResponse;

import java.util.List;

public interface LabelService {

    List<LabelResponse> getAllLabels();
    SimpleResponse saveLabels(LabelRequest labelRequest);
    SimpleResponse addLabelsToCard(Long cardId,Long labelId);

    LabelResponse getLabelById(Long labelId);

    SimpleResponse updateLabelDeleteById(Long labelId, LabelRequest labelRequest);

    SimpleResponse deleteLabelById(Long labelId);

    List<LabelResponse> getAllLabelsByCardId(Long cardId);

}
