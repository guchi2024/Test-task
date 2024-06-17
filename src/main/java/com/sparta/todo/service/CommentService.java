package com.sparta.todo.service;

import com.sparta.todo.dto.commentRequest.CommentCreateRequestDto;
import com.sparta.todo.dto.commentRequest.CommentDeleteRequestDto;
import com.sparta.todo.dto.commentRequest.CommentUpdateRequestDto;
import com.sparta.todo.dto.commentResponse.CommentResponseDto;
import com.sparta.todo.entity.Comment;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.exception.CommentNotFoundException;
import com.sparta.todo.exception.TodoNotFoundException;
import com.sparta.todo.exception.UnauthorizedUserException;
import com.sparta.todo.repository.CommentRepository;
import com.sparta.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public CommentResponseDto createComment(CommentCreateRequestDto createRequestDto) {
        Todo todo = todoRepository.findById(createRequestDto.getTodoId()).orElseThrow(
                () -> new TodoNotFoundException("선택한 id로는 일정은 찾을 수 없습니다: " + createRequestDto.getTodoId())
        );
        Comment comment = new Comment(createRequestDto, todo);
        Comment saveComment = commentRepository.save(comment);
        return new CommentResponseDto(saveComment);
    }

    @Transactional
    public CommentResponseDto updateComment(CommentUpdateRequestDto updateRequestDto) {
        Comment comment = findComment(updateRequestDto.getTodoId(), updateRequestDto.getId());
        if (!comment.isUserNameMatch(updateRequestDto.getUsername())){
            throw new UnauthorizedUserException("댓글의 작성자만 수정할 수 있습니다: "+ updateRequestDto.getUsername());
        }
        comment.update(updateRequestDto);
        return new CommentResponseDto(comment);
    }

    public void deleteComment(CommentDeleteRequestDto deleteRequestDto) {
        Comment comment = findComment(deleteRequestDto.getTodoId(), deleteRequestDto.getId());
        if (!comment.isUserNameMatch(deleteRequestDto.getUsername())){
            throw new UnauthorizedUserException("댓글의 작성자만 삭제할 수 있습니다: "+ deleteRequestDto.getUsername());
        }
        commentRepository.delete(comment);
    }

    private Comment findComment(Long todoId, Long commentId){
        return commentRepository.findByTodoIdAndId(todoId,commentId).orElseThrow(
                () -> new CommentNotFoundException("선택한 id로는 댓글을 찾을 수 없습니다: " + commentId)
        );
    }
}
