package com.harshajayamanna.blog;

import com.harshajayamanna.blog.entity.BlogPost;
import com.harshajayamanna.blog.entity.Meta;
import com.harshajayamanna.blog.service.BlogPostService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Named
@RequestScoped
public class MetaController {

    @Inject
    private BlogPostService blogPostService;

    private Long postId;

    private String postTitle;

    private String postContent;

    @NotEmpty
    @NotNull
    private String content;

    private List<Meta> metas;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public List<Meta> getMetas() {
        return metas;
    }

    public void setMetas(List<Meta> metas) {
        this.metas = metas;
    }

    public void getPostDetails(){
        if(postId!=null){
            this.postId = postId;
            BlogPost blogPost = blogPostService.getBlogPost(postId);
            this.postTitle = blogPost.getTitle();
            this.postContent = blogPost.getContent();
            this.metas = blogPost.getMetas();
        }
    }

    public String addMetaContent(){
        BlogPost blogPost = blogPostService.getBlogPost(this.postId);
        blogPostService.addMeta(blogPost, this.content);
        return "new-meta.xhtml?postId="+this.postId+"&faces-redirect=true";

    }
}
