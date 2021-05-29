package com.luna.post.dto;

/**
 * @author luna
 * 2021/5/29
 */
public class PostDeatilDTO {

    private PostDTO     postDTO;

    private ShowUserDTO showUserDTO;

    public PostDeatilDTO() {}

    public PostDeatilDTO(PostDTO postDTO, ShowUserDTO showUserDTO) {
        this.postDTO = postDTO;
        this.showUserDTO = showUserDTO;
    }

    public PostDTO getPostDTO() {
        return postDTO;
    }

    public void setPostDTO(PostDTO postDTO) {
        this.postDTO = postDTO;
    }

    public ShowUserDTO getShowUserDTO() {
        return showUserDTO;
    }

    public void setShowUserDTO(ShowUserDTO showUserDTO) {
        this.showUserDTO = showUserDTO;
    }
}
