package heewon.bloi.service;

import heewon.bloi.entity.Post;
import heewon.bloi.payload.PostDto;
import heewon.bloi.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageSize, int pageNo, String sortBy, String sortDir);
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto, long id);
    void deletePostById(long id);
}
