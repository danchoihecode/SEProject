'use client'
import React, {useEffect, useState } from 'react';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import { pink } from '@mui/material/colors';
import PostAddIcon from '@mui/icons-material/PostAdd';
import ReportIcon from '@mui/icons-material/Report';

// interface PostAreaProps {
//     posts: Post[];
//     onLike: (postId: string) => void;
//     onPostSubmit: (content: string) => void;
// }

export type Post = {
    id: string;
    content: string;
    date: string;
    likes: number;
};

const PostArea: React.FC = ({  }) => {
    const [posts, setPosts] = useState<Post[]>([]);
    useEffect(() => {
        async function fetchData() {
            setPosts([
                {
                    id: '1',
                    content: 'Excited to share my latest blog post! Check it out.',
                    date: '2023-06-15',
                    likes: 10
                },
                {
                    id: '2',
                    content: 'Just had a great day exploring the city. Loving the summer weather!',
                    date: '2023-07-01',
                    likes: 15
                },
                {
                    id: '3',
                    content: 'Can\'t wait for the weekend. Time to catch up on some reading.',
                    date: '2023-07-10',
                    likes: 8
                },
                {
                    id: '4',
                    content: 'Trying out a new recipe for dinner tonight. Hope it turns out well!',
                    date: '2023-07-12',
                    likes: 12
                },
                {
                    id: '5',
                    content: 'Feeling productive today. Crossed a few items off my to-do list.',
                    date: '2023-07-16',
                    likes: 20
                }
            ]);
        }

        fetchData();
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
        <div className="post-area">
            <div className="new-post-area" style={{display: 'flex', flexDirection: 'column'}}>
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
            </div>
            <div className="post-history">
                {posts.map((post) => (
                    <div className="post" key={post.id} style={{
                        border: '1px solid #ccc',
                        boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
                        padding: '10px',
                        marginTop: '10px'
                    }}>
                        <p>{post.content}</p>
                        <div className="post-footer" style={{display: 'flex',gap: '20px'}}>
                            <span>{post.date}</span>
                            <span><ReportIcon style = {{color :'red '}}/>   </span>
                            <span>
            <FavoriteBorderIcon onClick={() => handleLike(post.id)} sx={{color: pink[500]}}/>
                                {post.likes} Like{post.likes !== 1 ? 's' : ''}
          </span>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default PostArea;