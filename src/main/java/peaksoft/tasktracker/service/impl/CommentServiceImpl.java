package peaksoft.tasktracker.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.tasktracker.config.JwtService;
import peaksoft.tasktracker.dto.request.CommentRequest;
import peaksoft.tasktracker.dto.response.CommentResponse;
import peaksoft.tasktracker.dto.response.SimpleResponse;
import peaksoft.tasktracker.entity.Card;
import peaksoft.tasktracker.entity.Comment;
import peaksoft.tasktracker.entity.User;
import peaksoft.tasktracker.exceptions.NotFoundException;
import peaksoft.tasktracker.repository.CardRepository;
import peaksoft.tasktracker.repository.CommentRepository;
import peaksoft.tasktracker.repository.jdbcTemplateService.CommentJdbcTemplateService;
import peaksoft.tasktracker.service.CommentService;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentJdbcTemplateService commentJdbcTemplateService;
    private final JwtService jwtService;
    private final CardRepository cardRepository;

    @Override
    public List<CommentResponse> getAllComments() {
        User user = jwtService.getAuthentication();
        return commentJdbcTemplateService.getAllComments(user.getId());
    }
    @Override
    public SimpleResponse saveComment(Long cardId, CommentRequest commentRequest) {
        User user = jwtService.getAuthentication();
        Card card = cardRepository.findById(cardId).orElseThrow(() -> {
            log.error(String.format("Card with id: %s doesn't exist", cardId) );
            return new NotFoundException(String.format("Card with id: %s doesn't exist", cardId));
        });
        Comment comment = new Comment(commentRequest.comment(), ZonedDateTime.now(), card, user);
        commentRepository.save(comment);
        log.info(String.format("Comment with id: %s successfully saved !", comment.getId()));
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Comment with id: %s successfully saved !", comment.getId()))
                .build();
    }
    @Override
    public CommentResponse getCommentById(Long commentId) {
        User user = jwtService.getAuthentication();
        return commentJdbcTemplateService.getCommentById(user.getId(), commentId);
    }
    @Override
    public SimpleResponse updateCommentById(Long commentId, CommentRequest commentRequest) {
        User user = jwtService.getAuthentication();
        Comment comment = commentRepository.getCommentByUserIdAndId(user.getId(), commentId).orElseThrow(() -> {
            log.error(String.format("Card with id: %s or user with id %s doesn't exist", commentId,user.getId()));
            return new NotFoundException(String.format("Card with id: %s or user with id %s doesn't exist", commentId,user.getId()));
        });
        comment.setComment(commentRequest.comment());
        comment.setCreatedDate(ZonedDateTime.now());
        commentRepository.save(comment);
        log.info(String.format("Comment with id: %s successfully updated !", comment.getId()));
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Comment with id: %s successfully updated !", comment.getId()))
                .build();
    }
    @Override
    public SimpleResponse deleteCommentById(Long commentId) {
        User user = jwtService.getAuthentication();
        Comment comment = commentRepository.getCommentByUserIdAndId(user.getId(), commentId).orElseThrow(() -> {
            log.error(String.format("Card with id: %s or user with id %s doesn't exist", commentId,user.getId()));
            return new NotFoundException(String.format("Card with id: %s or user with id %s doesn't exist", commentId,user.getId()));
        });
      commentRepository.delete(comment);
      log.info(String.format("Comment with id: %s successfully deleted!", comment.getId()));
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Comment with id: %s successfully deleted!", comment.getId()))
                .build();
    }
}
