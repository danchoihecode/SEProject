'use client'
import React, {useEffect, useState } from 'react';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import { pink } from '@mui/material/colors';
import PostAddIcon from '@mui/icons-material/PostAdd';
import ReportIcon from '@mui/icons-material/Report';
import { Grid, Box } from '@mui/material';
import { getPostList } from '@/server/view-profile';

export type Post = {
    id: string;
    content: string;
    date: string;
    likes: number;
};

interface PostAreaProps {
    name: string;
}

const PostArea: React.FC<PostAreaProps> = ({ name }) => {
    const [posts, setPosts] = useState<Post[]>([]);
    useEffect(() => {
        getPostList(name).then((data) => {
            if (data) setPosts(data)
        })
    }, []);

    const onLike = (postId: string) => {
        setPosts((prevPosts) =>
            prevPosts.map((post) =>
                post.id === postId
                    ? { ...post, likes: post.likes + 1 }
                    : post
            )
        );
    };

    const onPostSubmit = (content: string) => {
        const newPost: Post = {
            id: `${posts.length + 1}`,
            content,
            date: new Date().toISOString().slice(0, 10),
            likes: 0
        };
        setPosts([newPost, ...posts]);
    };

    const [newPostContent, setNewPostContent] = useState('');

    const handleLike = (postId: string) => {
        onLike(postId);
    };

    const handlePostSubmit = () => {
        if (newPostContent.trim() !== '') {
            onPostSubmit(newPostContent);
            setNewPostContent('');
        }
    };

    return (
        <Grid container spacing={4}>
            <Grid item md={5}>
                <Box className="new-post-area" display="flex" flexDirection="column">
                    <textarea
                        placeholder="Write a new post..."
                        value={newPostContent}
                        onChange={(e) => setNewPostContent(e.target.value)}
                        style={{
                            border: '1px solid #ccc',
                            boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
                            padding: '10px',
                            width: '100%',
                            boxSizing: 'border-box',
                            marginBottom: '10px'
                        }}
                    />
                    <PostAddIcon onClick={handlePostSubmit} style={{cursor: 'pointer', color: 'blue' ,width:'2rem', height:'2rem' }} />
                </Box>
            </Grid>
            <Grid item md={7}>
                <Box className="post-history">
                    {posts.map((post) => (
                        <Box className="post" key={post.id} mb={2} style={{
                            border: '1px solid #ccc',
                            boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
                            padding: '10px'
                        }}>
                            <p>{post.content}</p>
                            <Box className="post-footer" display="flex" justifyContent="space-between">
                                <span>{post.date}</span>
                                <span><ReportIcon style = {{color :'red '}}/>   </span>
                                <span>
                                    <FavoriteBorderIcon onClick={() => handleLike(post.id)} sx={{color: pink[500]}}/>
                                    {post.likes} Like{post.likes !== 1 ? 's' : ''}
                                </span>
                            </Box>
                        </Box>
                    ))}
                </Box>
            </Grid>
        </Grid>
    );
};

export default PostArea;