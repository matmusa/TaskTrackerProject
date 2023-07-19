package peaksoft.tasktracker.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.tasktracker.dto.request.WorkSpaceRequest;
import peaksoft.tasktracker.dto.response.WorkSpaceResponse;
import peaksoft.tasktracker.service.WorkSpaceService;


import java.util.List;



@RestController
@RequestMapping("/work_spaces")
@RequiredArgsConstructor
public class WorkSpaceApi {

    private final WorkSpaceService workSpaceService;


//    @GetMapping
//    public List<WorkSpaceResponse> getAllWorkSpaces() {
//
//        return workSpaceService.getAllWorkSpaces();
//    }
//
//    @PostMapping
//    public SimpleResponse saveWorkSpaces(@RequestBody WorkSpaceRequest workSpaceRequest) {
//        return workSpaceService.saveWorkSpace(workSpaceRequest);
//    }
//
//    @GetMapping("/{id}")
//    public WorkSpaceResponse getWorkSpaceById(@PathVariable Long id) {
//        return workSpaceService.getWorkSpaceById(id);
//    }
//
//    @PutMapping("/{id}")
//    public SimpleResponse updateWorkSpaceById(@PathVariable Long id, @RequestBody WorkSpaceRequest workSpaceRequest) {
//        return workSpaceService.updateWorkSpaceById(id, workSpaceRequest);
//    }
//
//    @DeleteMapping("/{id}")
//    public SimpleResponse deleteWorkSpaceById(@PathVariable Long id) {
//        return workSpaceService.deleteWorkSpace(id);
//    }
//
//    @GetMapping("/page")
//    public WorkSpacePaginationResponse getWorkSpaceByPage(@RequestParam int page,@RequestParam int size){
//        return workSpaceService.getWorkSpacesPage(page,size);
//    }
}
