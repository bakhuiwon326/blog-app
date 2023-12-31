package heewon.bloi.service.impl;

import heewon.bloi.entity.Category;
import heewon.bloi.entity.Post;
import heewon.bloi.exception.ResourceNotFoundException;
import heewon.bloi.payload.PostDto;
import heewon.bloi.payload.PostResponse;
import heewon.bloi.repository.CategoryRepository;
import heewon.bloi.repository.PostRepository;
import heewon.bloi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper mapper;
    private CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository,
                           ModelMapper modelMapper,
                           CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.mapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));
        Post post = mapToPost(postDto);
        post.setCategory(category);
        Post newPost = postRepository.save(post);
        // convert entity to dto
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
    }
    
    @Override
    public PostResponse getAllPosts(int pageSize, int pageNo, String sortBy, String sortDir) {
        // jpa가 PagingAndSortingRepository를 구현하고 있음.
        // 거기에 findAll의 파라미터로 Pageable객체가 들어가고, Page를 반환함.
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> page = postRepository.findAll(pageable);
        // Page 객체에서 내용 꺼내기
        List<Post> listOfPosts = page.getContent();
        // 커스텀한 PostResponse객체를 넘기기 위해!
        List<PostDto>  content = listOfPosts.stream().map((post) -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalElements(page.getTotalElements());
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setLast(page.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));
        post.setTitle(postDto.getTitle());
        post.setDescription(post.getDescription());
        post.setContent(post.getContent());
        post.setCategory(category);
        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getPostsByCategoryId(long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        List<PostDto> posts = postRepository.findByCategoryId(categoryId).stream().map((post) -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
        return posts;
    }

    private PostDto mapToDto(Post post){
        PostDto postDto = mapper.map(post, PostDto.class);
        return postDto;
    }

    private Post mapToPost(PostDto postDto){
       Post post = mapper.map(postDto, Post.class);
        return post;
    }
}
