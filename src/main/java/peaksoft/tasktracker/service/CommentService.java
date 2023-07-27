package peaksoft.tasktracker.service;

import peaksoft.tasktracker.dto.request.CommentRequest;
import peaksoft.tasktracker.dto.response.CommentResponse;
import peaksoft.tasktracker.dto.response.SimpleResponse;

import java.util.Comparator;
import java.util.List;

public interface CommentService {

    List<CommentResponse> getAllComments();

    SimpleResponse saveComment(Long cardId,CommentRequest commentRequest);

    CommentResponse getCommentById(Long commentId);

    SimpleResponse updateCommentById(Long commentId, CommentRequest commentRequest);

    SimpleResponse deleteCommentById(Long commentId);


}
