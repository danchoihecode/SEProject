'use client'
import React, { useState } from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Typography from '@mui/material/Typography';
import CheckIcon from '@mui/icons-material/Check';
import CloseIcon from '@mui/icons-material/Close';
import AccountAvatar from "@/components/letter-avatar";
import NotificationsIcon from '@mui/icons-material/Notifications';
import {IconButton} from "@mui/material";
import Box from "@mui/material/Box";
import {acceptFriendRequest} from "@/server/friend-request";
import {useRouter} from "next/navigation";

export interface FriendRequest {
    name: string;
    id: string;
}

interface NotificationButtonProps {
    friendRequests: FriendRequest[]
}

const NotificationButton: React.FC<NotificationButtonProps> = (props :NotificationButtonProps) => {

    const router = useRouter()

    const [open, setOpen] = useState(false);
    const [requests, setRequests] = useState<FriendRequest[]>(props.friendRequests);

    const handleClick = () => {
        setOpen(true);
    };

    const handleAccept = (id: string) => {
        acceptFriendRequest(id).then(
            r =>{
                if(r){
                    setRequests(requests.filter((request) => request.id !== id));
                    setOpen(false);
                    router.refresh()
                }
            }
        )
    };

    const handleReject = (id: string) => {
        setRequests(requests.filter((request) => request.id !== id));
        setOpen(false);
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <div>
            <IconButton color='inherit' onClick={handleClick}>
                <NotificationsIcon/> {requests.length}
            </IconButton>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Friend Requests</DialogTitle>
                <DialogContent>
                    {requests.map((request) => (
                        <div
                            key={request.id}
                            style={{
                                display: 'flex',
                                alignItems: 'center',
                                marginBottom: '16px',
                            }}
                        >
                            <Box sx={{
                                display:'flex',
                                justifyContent:'center',
                                alignItems:'center',
                                padding:'10px'
                            }}>
                                <AccountAvatar name={request.name}  />
                            </Box>
                            <div>
                                <Typography variant="h6" fontWeight="bold">
                                    {request.name}
                                </Typography>
                                <div>
                                    <Button
                                        onClick={() => handleAccept(request.id)}
                                        variant="contained"
                                        color="primary"
                                        sx={{ marginRight: '8px' }}
                                    >
                                        <CheckIcon />
                                    </Button>
                                    <Button
                                        onClick={() => handleReject(request.id)}
                                        variant="contained"
                                        color="secondary"
                                    >
                                        <CloseIcon />
                                    </Button>
                                </div>
                            </div>
                        </div>
                    ))}
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} color="primary">
                        Close
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
};

export default NotificationButton;