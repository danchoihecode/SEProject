'use client'
import {Box, ListItemAvatar} from '@mui/material';
import Avatar from "@mui/material/Avatar";
import AccountAvatar from "@/components/letter-avatar";
import React from "react";

function ChatMessage({key,message, username}) {
    if (message.messageType === 'CONNECT' || message.messageType === 'DISCONNECT') {
        return (
            <Box key={key} sx={{display: 'flex', justifyContent: 'center', alignItems: 'center', width: '100%', margin: '10px 0'}}>
                <p style={{color: message.messageType === 'CONNECT' ? 'lime' : 'orangered'}}>{message.senderName + " " + message.messageType.toLowerCase() + "ed"}</p>
            </Box>
        );
    }
    return (
        <Box key={key} sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: message.senderUserId === username ? 'flex-end' : 'flex-start'}}>
            <Box sx={{ marginRight: message.senderUserId === username ? '8px' : 'auto', display: 'flex', flexDirection: message.sender === username ? 'row-reverse' : 'row', alignItems: 'center', gap: 1 }}>
                <AccountAvatar name={message.senderName}/>
                <h4>{message.senderName}</h4>
            </Box>
            <Box sx={{
                marginRight: message.senderUserId === username ? '8px' : 'auto',
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                maxWidth: '60%',
                height: '16px',
                padding: '10px',
                borderRadius: '16px',
                color:'white',
                backgroundColor:'secondary.main',
                wordWrap: 'break-word',
            }}>
                <p>{message.messageContent}</p>
            </Box>
        </Box>
    );
}

export default ChatMessage;