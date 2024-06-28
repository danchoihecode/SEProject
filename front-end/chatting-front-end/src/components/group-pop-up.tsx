'use client'
import React from 'react';
import { Modal, Box, Typography } from '@mui/material';
import AddGroupForm from './add-group-form';

interface GroupPopUpProps {
    showAddGroupForm: boolean;
    handleCloseForm: () => void;
    listFriends: any[];
    handleCreateGroup: (groupName: string, friends: any[]) => void;
}

const GroupPopUp: React.FC<GroupPopUpProps> = ({
                                                   showAddGroupForm,
                                                   handleCloseForm,
                                                   listFriends,
                                                   handleCreateGroup,
                                               }) => {

    return (
        <Modal
            open={showAddGroupForm}
            onClose={handleCloseForm}
            aria-labelledby="modal-title"
            aria-describedby="modal-description"
        >
            <Box
                sx={{
                    position: 'absolute',
                    top: '50%',
                    left: '50%',
                    transform: 'translate(-50%, -50%)',
                    bgcolor: 'background.paper',
                    boxShadow: 24,
                    p: 4,
                }}
            >
                <Typography id="modal-title" variant="h6" component="h2">
                </Typography>
                <AddGroupForm
                    listFriends={listFriends}
                    onCreateGroup={handleCreateGroup}
                    handleCloseForm={handleCloseForm}
                />
            </Box>
        </Modal>
    );
};

export default GroupPopUp;