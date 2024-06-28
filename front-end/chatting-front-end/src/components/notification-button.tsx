'use client'
import React, { useState } from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import { blue} from '@mui/material/colors';
import PersonIcon from '@mui/icons-material/Person';
import CheckIcon from '@mui/icons-material/Check';
import CloseIcon from '@mui/icons-material/Close';

interface FriendRequest {
    name: string;
    id: string;
}

interface NotificationButtonProps {
    friendRequests: FriendRequest[];
    onAccept: (id: string) => void;
    onReject: (id: string) => void;
}

const NotificationButton: React.FC<NotificationButtonProps> = ({
                                                                   friendRequests,
                                                                   onAccept,
                                                                   onReject,
                                                               }) => {
    const [open, setOpen] = useState(false);
    const [requests, setRequests] = useState<FriendRequest[]>(friendRequests);

    const handleClick = () => {
        setOpen(true);
    };

    const handleAccept = (id: string) => {
        onAccept(id);
        setRequests(requests.filter((request) => request.id !== id));
        setOpen(false);
    };

    const handleReject = (id: string) => {
        onReject(id);
        setRequests(requests.filter((request) => request.id !== id));
        setOpen(false);
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <div>
            <Button variant="contained" onClick={handleClick}>
                Notifications ({requests.length})
            </Button>
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
                            <Avatar
                                sx={{
                                    backgroundColor: blue[500],
                                    color: 'white',
                                    marginRight: '16px',
                                }}
                            >
                                <PersonIcon />
                            </Avatar>
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