'use client'
import React from 'react';
import {IconButton} from '@mui/material';
import GroupAddIcon from '@mui/icons-material/GroupAdd';

interface AddGroupButtonProps {
    handleAddGroupClick: () => void;
}

const AddGroupButton: React.FC<AddGroupButtonProps> = ({ handleAddGroupClick }) => {
    return (
       <IconButton onClick={handleAddGroupClick}>
           <GroupAddIcon/>
       </IconButton>
    );
};

export default AddGroupButton;