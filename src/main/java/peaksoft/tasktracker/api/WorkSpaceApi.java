package peaksoft.tasktracker.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.tasktracker.dto.request.WorkSpaceRequest;
import peaksoft.tasktracker.dto.response.SimpleResponse;
import peaksoft.tasktracker.dto.response.WorkSpaceInnerPageResponse;
import peaksoft.tasktracker.dto.response.WorkSpaceResponse;
import peaksoft.tasktracker.service.WorkSpaceService;


import java.util.List;


@RestController
@RequestMapping("api/work_spaces")
@RequiredArgsConstructor
@Tag(name = "Workspace API", description = "All workspace endpoints")
public class WorkSpaceApi {

    private final WorkSpaceService workSpaceService;

    @Operation(summary = "Get all workspaces", description = "Get all workspaces by user auth id")
    @GetMapping
    public List<WorkSpaceResponse> getAllWorkSpaces() {
        return workSpaceService.getAllWorkSpaces();
    }

    @Operation(summary = "Create workSpace", description = "create workspace by user auth id")
    @PostMapping
    public SimpleResponse saveWorkSpaces(@RequestBody WorkSpaceRequest workSpaceRequest) throws MessagingException {
        return workSpaceService.saveWorkSpace(workSpaceRequest);
    }

    @Operation(summary = "Get workspace by id ", description = "get workspace by user auth id and own id")
    @GetMapping("/innerPage/{workSpaceId}")
    public WorkSpaceInnerPageResponse getWorkSpaceInnerPageById(@PathVariable Long workSpaceId) {
        return workSpaceService.getWorkSpaceInnerPage(workSpaceId);
    }
    @Operation(summary = "Get workspace by id ", description = "get workspace by user auth id and own id")
    @GetMapping("/{workSpaceId}")
    public WorkSpaceResponse getWorkSpaceById(@PathVariable Long workSpaceId) {
        return workSpaceService.getWorkSpaceById(workSpaceId);
    }
    @Operation(summary = "Update workspace by id", description = "get workspace by user auth id and own id")
    @PutMapping("/{workSpaceId}")
    public SimpleResponse updateWorkSpaceById(@PathVariable Long workSpaceId, @RequestBody WorkSpaceRequest workSpaceRequest) {
        return workSpaceService.updateWorkSpaceById(workSpaceId, workSpaceRequest);
    }

    @Operation(summary = "delete workspace by id", description = "get workspace by user auth id and own id")
    @DeleteMapping("/{workSpaceId}")
    public SimpleResponse deleteWorkSpaceById(@PathVariable Long workSpaceId) {
        return workSpaceService.deleteWorkSpaceById(workSpaceId);
    }


}
