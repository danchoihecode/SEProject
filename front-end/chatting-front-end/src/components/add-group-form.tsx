'use client'
import React, { Key, useState } from 'react';
import { Box, Button, TextField, Typography } from '@mui/material';

export type Friend = {
    id: Key;
    name: String;
};

interface AddGroupFormProps {
    listFriends: Friend[];
    onCreateGroup: (groupName: string, members: Friend[]) => void;
    handleCloseForm: () => void;
}

const AddGroupForm: React.FC<AddGroupFormProps> = ({
                                                       listFriends,
                                                       onCreateGroup,
                                                       handleCloseForm,
                                                   }) => {
    const [groupName, setGroupName] = useState('');
    const [selectedFriends, setSelectedFriends] = useState<Friend[]>([]);

    const handleAddFriend = (friend: Friend) => {
        setSelectedFriends((prevSelectedFriends) => [...prevSelectedFriends, friend]);
    };

    const handleRemoveFriend = (friend: Friend) => {
        setSelectedFriends((prevSelectedFriends) =>
            prevSelectedFriends.filter((f) => f.id !== friend.id)
        );
    };

    const handleCreateGroup = () => {
        onCreateGroup(groupName, selectedFriends);
        setGroupName('');
        setSelectedFriends([]);
        handleCloseForm();
    };

    return (
        <Box>
            <Typography variant="h5" gutterBottom>
                Create New Group
            </Typography>
            <TextField
                label="Group Name"
                value={groupName}
                onChange={(e) => setGroupName(e.target.value)}
                fullWidth
                margin="normal"
            />
            <Box>
                <Typography variant="h6" gutterBottom>
                    Add Friends
                </Typography>
                {listFriends.map((friend) => (
                    <Box
                        key={friend.id}
                        display="flex"
                        alignItems="center"
                        justifyContent="space-between"
                        marginY={1}
                    >
                        <Typography>{friend.name}</Typography>
                        {selectedFriends.some((f) => f.id === friend.id) ? (
                            <Button variant="contained" color="error" onClick={() => handleRemoveFriend(friend)}>
                                Remove
                            </Button>
                        ) : (
                            <Button variant="contained" onClick={() => handleAddFriend(friend)}>
                                Add
                            </Button>
                        )}
                    </Box>
                ))}
            </Box>
            <Box display="flex" justifyContent="flex-end" marginTop={2}>
                <Button variant="contained" onClick={handleCreateGroup}>
                    Create Group
                </Button>
            </Box>
        </Box>
    );
};

export default AddGroupForm;