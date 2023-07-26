package peaksoft.tasktracker.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.tasktracker.dto.request.LabelRequest;
import peaksoft.tasktracker.dto.response.LabelResponse;
import peaksoft.tasktracker.dto.response.SimpleResponse;
import peaksoft.tasktracker.service.LabelService;

import java.util.List;

@RestController
@RequestMapping("/labels")
@RequiredArgsConstructor
@Tag(name = "Labels API", description = "All labels endpoints")
public class LabelApi {

    private final LabelService labelService;

    @Operation(summary = "Get all labels", description = "Get all labels ")
    @GetMapping
    List<LabelResponse> getAllLabels() {
        return labelService.getAllLabels();
    }

    @Operation(summary = "Save label", description = "save label to card by cardId ")
    @PostMapping
    SimpleResponse saveLabels(@RequestBody LabelRequest labelRequest) {
        return labelService.saveLabels(labelRequest);
    }

    @Operation(summary = "Add label to card", description = "Find label and card and add label to card ")
    @GetMapping("/{cardId}/{labelId}")
    public SimpleResponse addLabelsToCard(@PathVariable Long cardId,@PathVariable Long labelId) {
        return labelService.addLabelsToCard(cardId,labelId);
    }

        @Operation(summary = "Get by id", description = "Get label by id ")
    @GetMapping("/{labelId}")
    LabelResponse getLabelById(@PathVariable Long labelId) {
        return labelService.getLabelById(labelId);
    }

    @Operation(summary = "Get all labels", description = "Get all labels ")
    @PutMapping("/{labelId}")
    SimpleResponse updateLabelDeleteById(@PathVariable Long labelId, @RequestBody LabelRequest labelRequest) {
        return labelService.updateLabelDeleteById(labelId, labelRequest);
    }

    @Operation(summary = "Delete label by id", description = "Delete label by id!    ")
    @DeleteMapping("/{labelId}")
    SimpleResponse deleteLabelById(@PathVariable Long labelId) {
        return labelService.deleteLabelById(labelId);

    }

    @Operation(summary = "Get all label by card id", description = " Get all labels by card id")
    @GetMapping("/byCardId/{cardId}")
    List<LabelResponse> getAllLabelsByCardId(@PathVariable Long cardId) {
        return labelService.getAllLabelsByCardId(cardId);
    }


}
