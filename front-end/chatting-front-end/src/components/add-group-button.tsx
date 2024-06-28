'use client'
import React from 'react';
import { SvgIcon, SvgIconProps } from '@mui/material';
import GroupAddIcon from '@mui/icons-material/GroupAdd';

interface AddGroupButtonProps {
    handleAddGroupClick: () => void;
}

const AddGroupButton: React.FC<AddGroupButtonProps> = ({ handleAddGroupClick }) => {
    return (
        <SvgIcon
            component={GroupAddIcon}
            onClick={handleAddGroupClick}
            sx={{
                marginLeft: '5px',
                width: '10px',
                height: '10px',
                cursor: 'pointer',
            }}
        />
    );
};

export default AddGroupButton;