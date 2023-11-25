package heewon.bloi.controller;

import heewon.bloi.payload.PostDto;
import heewon.bloi.payload.PostResponse;
import heewon.bloi.service.PostService;
import heewon.bloi.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // @Controller와 @ResponseBody가 내부적으로 있음
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(@Qualifier("postServiceImpl") PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        PostDto res = postService.createPost(postDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
    ){
        return postService.getAllPosts(pageSize, pageNo, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id){
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully!", HttpStatus.OK);
    }

}