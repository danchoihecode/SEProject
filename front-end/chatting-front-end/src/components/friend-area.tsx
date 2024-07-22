'use client'

import React from 'react';
import AccountAvatar from './letter-avatar';
import { Typography } from '@mui/material';
import router from 'next/router';

export type Friend = {
    id: string;
    name: string;
};

interface FriendAreaProps {
    friends: Friend[];
}

const FriendArea: React.FC<FriendAreaProps> = ({ friends }) => {
    return (
        <div className="friend-list">
            <div style={{display: 'flex', justifyContent: 'center', marginTop: '15px', marginBottom: '16px'}}>
                <Typography variant="h4" color="black">
                    User friends
                </Typography>
            </div>
            {friends.map((friend) => (
                <div className="friend" key={friend.id}
                     style={{border: '1px solid #ccc', padding: '10px', borderRadius: '4px', marginBottom: '10px', width:'200px', marginLeft: '120px'}}>
                    <AccountAvatar name={friend.name}  />
                    <Typography variant="h5" ml={2} color="black">
                        {friend.name}
                    </Typography>
                </div>
            ))}
        </div>
    );
};

export default FriendArea;