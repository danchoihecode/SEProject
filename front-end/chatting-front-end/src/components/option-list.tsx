'use client'
import React, { useContext, useState, useEffect } from 'react';
import Box from "@mui/material/Box";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemText from "@mui/material/ListItemText";
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import { SelectedRoomContext } from "@/context/selected-room-context";
import { updateGroupName } from '@/server/update-group-name';
import { deleteGroup } from '@/server/delete-group';
import { useRouter } from "next/navigation";
import { toast, ToastContainer } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import { searchUser, addMember } from '@/server/add-member';
import { MemberDTO } from '@/dto/MemberDTO';
import { getOwnerId } from '@/server/get-owner';

const options = [
    "View list of members",
    "Add member",
    "Update group name",
    "Delete group",
    "Back to conversations"
];

const OptionList: React.FC<{ userId: string }> = ({ userId }) => {
    const { selectedIndex, setSelectedIndex } = useContext(SelectedRoomContext);
    const [open, setOpen] = useState(false);
    const [newGroupName, setNewGroupName] = useState('');
    const [addMemberOpen, setAddMemberOpen] = useState(false);
    const [searchTerm, setSearchTerm] = useState('');
    const [searchResult, setSearchResult] = useState<MemberDTO | null>(null);
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);
    const router = useRouter();
    const [ownerId, setOwnerId] = useState('');

    useEffect(() => {
        const selectedConversationID = localStorage.getItem('selectedConversationID');
        if (selectedConversationID) {
            setSelectedIndex(selectedConversationID);
        }
    }, [setSelectedIndex]);

    useEffect(() => {
        if (selectedIndex && selectedIndex !== '0') {
            getOwnerId(selectedIndex).then((data) => {
                if (data) setOwnerId(data);
            });
        }
    }, [selectedIndex]);

    const handleClose = () => {
        setOpen(false);
        setAddMemberOpen(false);
        setDeleteDialogOpen(false);
    };

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleUpdateGroupName = async () => {
        if (!newGroupName.trim()) {
            toast.error('Group name is invalid!');
            return;
        }

        try {
            await updateGroupName(selectedIndex, newGroupName);
            setOpen(false);
            window.location.reload();
        } catch (error) {
            toast.error('Group name is the same as before!');
        }
    };

    const handleOptionClick = async (index: number) => {
        if (index === 0) {
            router.push('/home/member');
        } else if (index === 4) {
            router.push('/home/chat');
        } else if (index === 1 || index === 3 || index === 2) {
            // alert(userId + ' and ' + ownerId);
            if (userId !== ownerId) {
                toast.error('You do not have permission to perform this action.');
                return;
            }
            if (index === 1) {
                setAddMemberOpen(true);
            } else if (index === 3) {
                setDeleteDialogOpen(true);
            }
            else if (index === 2) {
                handleClickOpen();
            }
        }
    };

    const handleSearchUser = async () => {
        if (!searchTerm.trim()) {
            toast.error('User name is invalid!');
            return;
        }
        try {
            const result = await searchUser(searchTerm);
            setSearchResult(result);
        } catch (error) {
            toast.error('This user does not exist!');
        }
    };

    const handleAddMember = async () => {
        if (!searchResult) {
            toast.error('No user selected to add.');
            return;
        }

        try {
            const memberId = searchResult.id;
            await addMember(memberId, selectedIndex);
            toast.success('Member added successfully!');
            setAddMemberOpen(false);
        } catch (error) {
            toast.error('This user is already a member of the group!');
            setAddMemberOpen(false);
        }
    };

    const handleDeleteGroup = async () => {
        try {
            await deleteGroup(selectedIndex);
            localStorage.removeItem('selectedConversationID');
            setSelectedIndex('0');
            router.push('/home/chat');
            window.location.reload();
        } catch (error) {
            toast.error('Failed to delete the group!');
        }
    };

    return (
        <Box sx={{ flex: 1, p: 2 }}>
            <List>
                {options.map((option, index) => (
                    <ListItem button key={index} onClick={() => handleOptionClick(index)}>
                        <ListItemText primary={option} />
                    </ListItem>
                ))}
            </List>

            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Update Group Name</DialogTitle>
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="newGroupName"
                        label="New Group Name"
                        type="text"
                        fullWidth
                        value={newGroupName}
                        onChange={(e) => setNewGroupName(e.target.value)}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={handleUpdateGroupName}>Submit</Button>
                </DialogActions>
            </Dialog>

            <Dialog open={addMemberOpen} onClose={handleClose}>
                <DialogTitle>Add Member</DialogTitle>
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="searchTerm"
                        label="Search User"
                        type="text"
                        fullWidth
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                    />
                    {searchResult && (
                        <div>
                            <p>Full Name: {searchResult.fullName}</p>
                            <p>Nick Name: {searchResult.nickName}</p>
                            <p>Email: {searchResult.email}</p>
                        </div>
                    )}
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={handleSearchUser}>Search</Button>
                    {searchResult && (
                        <Button onClick={handleAddMember}>Add Member</Button>
                    )}
                </DialogActions>
            </Dialog>

            <Dialog open={deleteDialogOpen} onClose={handleClose}>
                <DialogTitle className="text-xl font-semibold text-gray-900">Confirm Delete Group</DialogTitle>
                <DialogContent className="mt-2">
                    <p className="text-sm text-gray-500">Are you sure you want to delete this group? This action cannot be undone.</p>
                </DialogContent>
                <DialogActions className="flex justify-end p-4">
                    <Button onClick={handleClose} className="bg-gray-300 hover:bg-gray-400 text-black font-medium py-2 px-4 rounded">Cancel</Button>
                    <Button onClick={handleDeleteGroup} className="bg-red-500 hover:bg-red-600 text-white font-medium py-2 px-4 rounded ml-2">Delete</Button>
                </DialogActions>
            </Dialog>

            <ToastContainer />
        </Box>
    );
}

export default OptionList;
